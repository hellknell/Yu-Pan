package com.Yu.Pan.service.intercept;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.Yu.Pan.cache.core.constants.CacheConstants;
import com.Yu.Pan.core.exception.BizException;
import com.Yu.Pan.core.response.ResponseCode;
import com.Yu.Pan.core.utils.JwtUtil;
import com.Yu.Pan.service.constants.UserConstants;
import com.Yu.Pan.service.context.LoginMemberContext;
import com.Yu.Pan.service.resp.UserResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/7/1 10:24
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class UserIntercept implements HandlerInterceptor {
    final CacheManager cacheManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("requestURI:{}", requestURI);
        String token = request.getHeader("Authorization");
        log.info("当前用户登录token:{}", token);
        if (token == null) {
            token = request.getParameter("authorization");
        }
        if (StrUtil.isBlank(token)) {
            throw new BizException(ResponseCode.NO_LOGIN);
        }
        if (StrUtil.isNotBlank(token)) {
            if (!JwtUtil.validateToken(token)) {
                throw new BizException(ResponseCode.INVAILD_TOKEN);
            }
            log.info("当前用户登录token:{}", token);
            Cache cache = cacheManager.getCache(CacheConstants.YU_PAN_CACHE);
            JSONObject member = JwtUtil.getMember(token);
            UserResp loginUser = BeanUtil.toBean(member, UserResp.class);
            Long id = loginUser.getId();
            log.info("userId:{}", id);
            Cache.ValueWrapper valueWrapper = cache.get(UserConstants.USER_LOGIN_PREFIX + id);
            log.info("token1:{}", valueWrapper.get());
            log.info("当前用户登信息:{}", loginUser);
            LoginMemberContext.setLoginUser(loginUser);
        }
        return true;
    }
}