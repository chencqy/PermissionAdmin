package com.chen.vtg.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @Author : Chen
 * @Date : Create in 2019/6/20 20:02
 * @Description :
 * @Modify by :
 */
public class SecurityUtil {

    /**
    public static int getId() {
        Authentication authObj = SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User)authObj.getPrincipal();
        return Integer.valueOf(authUser.getUsername());
    }
     */

    public static int getIdWithoutException() {
        try {
            Authentication authObj = SecurityContextHolder.getContext().getAuthentication();
            User authUser = (User)authObj.getPrincipal();
            return Integer.valueOf(authUser.getUsername());
        }catch (Exception ex){
            return 0;
        }

    }

    public static String getLoginName() {
        Authentication authObj = SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User)authObj.getPrincipal();
        return authUser.getUsername();
    }

    public static User getAuthenticationUser() {
        Authentication authObj = SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User)authObj.getPrincipal();
        return authUser;
    }
}
