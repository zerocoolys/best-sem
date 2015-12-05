package com.perfect.web.support;

import com.perfect.api.baidu.BaiduApiService;
import com.perfect.api.baidu.BaiduServiceSupport;
import com.perfect.autosdk.sms.v3.AccountInfoType;
import com.perfect.core.AppContext;
import com.perfect.service.AccountManageService;
import com.perfect.vo.BaseBaiduAccountInfoVO;
import com.perfect.vo.UserInfoVO;
import com.perfect.web.filter.auth.AuthConstants;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by vbzer_000 on 2014/8/27.
 * 2014-12-2 refactor
 */
public class ContextInterceptor implements HandlerInterceptor, AuthConstants {

    private final boolean[] adminFlag = new boolean[1];

    private final AccountInfoType[] baiduAccountInfo = new AccountInfoType[1];

    @Resource
    private AccountManageService accountManageService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 判断Session里是否有用户信息
//        if (request.getSession().getAttribute(USER_INFORMATION) == null) {
//            // 用于判断AJAX请求出现的Session超时
//            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
//                response.addHeader("sessionStatus", "timeout");
//                return false;
//            }
//
//        }

        String username = WebUtils.getUserName(request);
        if (username == null) {
            return false;
        }

        Long accoundId = WebUtils.getAccountId(request);
        if (accoundId != null && accoundId > 0) {
            AppContext.setUser(username, accoundId);
            return true;
        } else {
            AppContext.setUser(username);
            handleRequest(request);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (isAdmin()) return;

        setAccountOverview(request, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void handleRequest(HttpServletRequest request) {
        if (Objects.nonNull(request.getSession().getAttribute(USER_INFORMATION))) {
            adminFlag[0] = false;
            UserInfoVO userInfo = (UserInfoVO) request.getSession().getAttribute(USER_INFORMATION);

            for (BaseBaiduAccountInfoVO baseBaiduAccountInfoVO : userInfo.getBaiduAccounts()) {
                if (baseBaiduAccountInfoVO.isDefault()) {
                    BaiduApiService apiService = new BaiduApiService(BaiduServiceSupport.getCommonService(
                            baseBaiduAccountInfoVO.getAccountName(),
                            baseBaiduAccountInfoVO.getPassword(),
                            baseBaiduAccountInfoVO.getToken())
                    );
                    baiduAccountInfo[0] = apiService.getAccountInfo();

                    WebUtils.setAccountId(request, baiduAccountInfo[0].getUserid());
                    AppContext.setUser(userInfo.getUsername(), baiduAccountInfo[0].getUserid());
                    break;
                }
            }
        } else {
            adminFlag[0] = true;
        }
    }

    private void setAccountOverview(HttpServletRequest request, ModelAndView modelAndView) {
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.put("currSystemUserName", WebUtils.getUserName(request));
        modelMap.put("accountBalance", baiduAccountInfo[0].getBalance());
        modelMap.put("accountBudget", baiduAccountInfo[0].getBudget());

        String vStr = Double.valueOf(baiduAccountInfo[0].getBalance() / baiduAccountInfo[0].getBudget()).toString();
        modelMap.put("remainderDays", vStr.substring(0, vStr.indexOf(".")));
    }

    private boolean isAdmin() {
        return adminFlag[0];
    }
}