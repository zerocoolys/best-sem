package com.perfect.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yousheng on 2014/7/24.
 *
 * @author yousheng
 */
public class AppContext {

    public static final String CTX_USER = "CTX_USER";
    public static final String CTX_ACCOUNT = "CTX_ACC";

    private static Map<String, SessionObject> sessionMap = new HashMap<>();

    private static ContextLocal contextMap = new ContextLocal();

    private static ThreadLocal<SystemUserInfo> systemUserInfoThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<String> remoteIpThreadLocal = new ThreadLocal<>();

    private static Object accountId;

    public static void setSystemUserInfo(SystemUserInfo systemUserInfo) {
        systemUserInfoThreadLocal.set(systemUserInfo);
    }

    public static SystemUserInfo getSystemUserInfo() {
        return systemUserInfoThreadLocal.get();
    }

    public static boolean isAdminSystemLogin() {
        return systemUserInfoThreadLocal.get() != null;
    }

    public static SessionObject get() {
        return contextMap.get();
    }

    public static boolean contains(String sessionId) {
        return sessionMap.containsKey(sessionId);
    }


//    public static void setSessionObject(String sessionId, SessionObject so) {
//        if (sessionId == null || so == null) {
//            return;
//        }
//        sessionMap.put(sessionId, so);
//
//        contextMap.set(so);
//    }

    public static void remove(String id) {
        sessionMap.remove(id);
        contextMap.set(null);
    }

    public static SessionObject getObject(String id) {
        return sessionMap.get(id);
    }

//    public static void setLocal(String sessionId) {
//        SessionObject so = sessionMap.get(sessionId);
//        contextMap.set(so);
//    }

    public static void setUser(String userName) {
        SessionObject so = new SessionObject();
        so.setUserName(userName);
        contextMap.set(so);
    }


    public static void setUser(String userName, Long accountId) {
        SessionObject so = new SessionObject();
        so.setUserName(userName);
        so.setAccountId(accountId);
        contextMap.set(so);
    }

    public static String getUser() {
        if (contextMap.get() == null)
            return null;
        return contextMap.get().getUserName();
    }

    public static Long getAccountId() {
        SessionObject sessionObject = contextMap.get();

        if (sessionObject != null) {
            return sessionObject.getAccountId();
        } else {
            return -1l;
        }
    }

    public static String getRemote() {
        return remoteIpThreadLocal.get();
    }

    public static void setRemote(String remote) {
        remoteIpThreadLocal.set(remote);
    }

}
