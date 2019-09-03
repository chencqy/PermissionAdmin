package com.chen.admin.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author : Chen
 * @Date : Create in 2018/9/13 20:06
 * @Description :
 * @Modify by :
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "登录失败")
public class LoginFailureException extends RuntimeException {

    private static final long serialVersionUID = -1277563713852961657L;
    
    /**
      * @Author : Chen 
      * @Param : [message]
      * @Return :  
      * @Description :
      * @Create: 2018/9/13 20:37
      */
    public LoginFailureException(String message){
        super(message);
    }
}
