package com.chen.vtg.utils;

import com.chen.vtg.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author : Chen
 * @Date : Create in 2019/6/20 20:02
 * @Description :
 * @Modify by :
 */
public class SecurityUtil {

    public static int getId() {
        Authentication authObj = SecurityContextHolder.getContext().getAuthentication();
        UserEntity authUser = (UserEntity)authObj.getPrincipal();
        return Integer.valueOf(authUser.getAccountName());
    }

    public static int getIdWithoutException() {
        try {
            Authentication authObj = SecurityContextHolder.getContext().getAuthentication();
            UserEntity authUser = (UserEntity)authObj.getPrincipal();
            return Integer.valueOf(authUser.getAccountName());
        }catch (Exception ex){
            return 0;
        }

    }

    public static String getLoginName() {
        Authentication authObj = SecurityContextHolder.getContext().getAuthentication();
        UserEntity authUser = (UserEntity)authObj.getPrincipal();
        return authUser.getAccountName();
    }

    public static UserEntity getAuthenticationUser() {
        Authentication authObj = SecurityContextHolder.getContext().getAuthentication();
        UserEntity authUser = (UserEntity)authObj.getPrincipal();
        return authUser;
    }
}
