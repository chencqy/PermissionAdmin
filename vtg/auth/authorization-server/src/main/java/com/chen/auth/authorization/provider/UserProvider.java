package com.chen.auth.authorization.provider;

import com.chen.auth.authorization.entity.Result;
import com.chen.auth.authorization.entity.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author : Chen
 * @Date : Create in 2019/9/3 20:51
 * @Description :
 * @Modify by :
 */
@FeignClient(name = "sys-admin-user", fallback = UserProviderFallback.class)
public interface UserProvider {

    @GetMapping(value = "/user/info")
    Result<UserVo> getUserInfo();

}
