package com.alkac.mobile.client.views.tutoni;

/**
 * Created by IntelliJ IDEA.
 * User: pair5
 * Date: 30.04.2012
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 */
public class LoginInfo {
    private static UserInfo info;
    public static void setLogin(UserInfo userInfo) {
        info = userInfo;
    }

    public static UserInfo getUserInfo() {
        return info;
    }
}
