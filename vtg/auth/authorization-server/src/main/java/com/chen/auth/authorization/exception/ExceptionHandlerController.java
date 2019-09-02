package com.chen.auth.authorization.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author : Chen
 * @Date : Create in 2018/8/27 20:01
 * @Description :
 * @Modify by :
 */
@ControllerAdvice
@RequestMapping("exception")
public class ExceptionHandlerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(UserNameNotMatchPasswordException.class)
    @ResponseBody
    public Map<String, Object> handlerUserNameNotMatchPasswordException(UserNameNotMatchPasswordException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("error message:", e.getMessage());
        result.put("error type:", "UserNameNotMatchPasswordException");
        return result;
    }

    @ExceptionHandler(LoginFailureException.class)
    @ResponseBody
    public Map<String, Object> handlerLoginFailureException(LoginFailureException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("error message:", e.getMessage());
        result.put("error type:", "LoginFailureException");
        return result;
    }
}
