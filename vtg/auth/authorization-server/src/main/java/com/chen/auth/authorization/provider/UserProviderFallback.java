package com.chen.auth.authorization.provider;

import com.chen.auth.authorization.entity.Result;
import com.chen.auth.authorization.entity.vo.UserVo;

/**
 * @Author : Chen
 * @Date : Create in 2019/9/4 20:05
 * @Description :
 * @Modify by :
 */
public class UserProviderFallback implements UserProvider {
    @Override
    public Result<UserVo> getUserInfo() {
        return Result.ok(new UserVo());
    }
}
