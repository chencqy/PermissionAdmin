package com.chen.vtg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author : Chen
 * @Date : Create in 2019/7/18 20:20
 * @Description :
 * @Modify by :
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "图片上传失败")
public class ImgException extends RuntimeException {

    private static final long serialVersionUID = 7552724455368881320L;

    /**
     * @Author : Chen
     * @Param : [message]
     * @Return :
     * @Description : 图片上传失败
     * @Create: 2019/7/18 20:26
     */
    public ImgException(String message) {
        super(message);
    }
}
