package com.Yu.Pan.service.context;

import com.Yu.Pan.core.exception.BizException;
import com.Yu.Pan.core.response.ResponseCode;
import com.Yu.Pan.service.resp.UserResp;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/7/1 10:26
 */
public class LoginMemberContext {
    private static ThreadLocal<UserResp> threadLocal = new ThreadLocal<>();

    public static UserResp getLoginUser() {
        return threadLocal.get();
    }

    public static void setLoginUser(UserResp userResp) {
        threadLocal.set(userResp);

    }

    public static Long getUserId() {
        try {
            Long id = getLoginUser().getId();
            return id;
        } catch (Exception e) {
            throw new BizException(ResponseCode.NO_LOGIN);
        }
    }

}
