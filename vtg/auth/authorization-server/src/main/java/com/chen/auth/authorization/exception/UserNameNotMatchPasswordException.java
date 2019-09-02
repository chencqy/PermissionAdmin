package com.chen.auth.authorization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author : Chen
 * @Date : Create in 2018/8/28 20:01
 * @Description : 自定义异常类
 * @Modify by :
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "用户名与密码不匹配")
public class UserNameNotMatchPasswordException extends RuntimeException {

    private static final long serialVersionUID = -1444540000024754393L;

    /**
      * @Author : Chen
      * @Param : [message]
      * @Return :
      * @Description : 用户名密码不匹配
      * @Create: 2018/8/28 20:06
      */
    public UserNameNotMatchPasswordException(String message) {
        super(message);
    }

    /**
      * @Author : Chen 
      * @Param : [message, cause]
      * @Return :  
      * @Description :
      * @Create: 2018/8/28 20:11 
      */
    public UserNameNotMatchPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

}
