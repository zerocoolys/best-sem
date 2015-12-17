package com.perfect.usercenter.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2015-12-14.
 *
 * @author dolphineor
 */
@RestController
@Scope("prototype")
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("/loginOrReg/login");
    }

    @RequestMapping(value = "/platformPage")
    public ModelAndView platform() {
        return new ModelAndView("/bestPage/bestIndex");
    }

    @RequestMapping(value = "/userCenter/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public void logout(HttpServletResponse response) {
        Cookie cookies = new Cookie("semToken", null);
        cookies.setMaxAge(0);
        response.addCookie(cookies);
    }

    @RequestMapping(value = "/userCenter/reg")
    public ModelAndView reg() {
        return new ModelAndView("/loginOrReg/register");
    }

    @RequestMapping(value = "/userCenter/account", method = RequestMethod.GET)
    public ModelAndView account() {
        return new ModelAndView("/account/account");
    }

    @RequestMapping(value = "/userCenter/password", method = RequestMethod.GET)
    public ModelAndView password() {    return new ModelAndView("/password/password"); }

    @RequestMapping(value = "/userCenter/forget", method = RequestMethod.GET)
    public ModelAndView forget() {    return new ModelAndView("/password/forget");  }

    @RequestMapping(value = "/userCenter/safetyTool", method = RequestMethod.GET)
    public ModelAndView safetyTool() {
        return new ModelAndView("/safe/safetool");
    }
}

