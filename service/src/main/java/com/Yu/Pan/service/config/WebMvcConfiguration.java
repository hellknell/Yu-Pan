package com.Yu.Pan.service.config;

import com.Yu.Pan.service.intercept.UserIntercept;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    final UserIntercept userIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userIntercept)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/register")
                .excludePathPatterns("/swagger**/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/v3/**")
                .excludePathPatterns("/user/username/check")
                .excludePathPatterns("/user/answer/check")
                .excludePathPatterns("/user/password/reset")
                .excludePathPatterns("/doc.html");
    }
}

