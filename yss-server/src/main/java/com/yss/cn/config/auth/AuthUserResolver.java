package com.yss.cn.config.auth;

import com.yss.auth.AuthToken;
import com.yss.common.auth.TokenResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author:Shuoshi.Yan
 * @description:
 * @date: 2020/4/10 10:57
 */
@Configuration
public class AuthUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是TokenResult并且有AuthToken注解则支持
        if (parameter.getParameterType().isAssignableFrom(TokenResult.class) && parameter.hasParameterAnnotation(AuthToken.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return nativeWebRequest.getAttribute("tokenResult", RequestAttributes.SCOPE_REQUEST);
    }
}
