package com.yss.cn.config.advices;

import com.yss.cn.api.exception.UnAuthorizedException;
import com.yss.cn.auth.Authorization;
import com.yss.cn.api.app.token.TokenHelper;
import com.yss.cn.common.auth.TokenResult;
import com.yss.cn.config.util.ApplicationContextUtil;
import com.yss.cn.constants.Constants;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Shuoshi.Yan
 * @package:com.yss.cn.config.advices
 * @className:过滤器方法
 * @description:
 * @date 2020-04-09 16:17
 * @version:V1.0
 * @NOTICE：本内容仅限于xxx有限公司内部传阅,禁止外泄以及用于其他的商业项目
 * @ Copyright  xxx. All rights reserved.
 **/

public class BaseAuthorizationInterceptor {
    private void handlePreHandle(String tokenPrefix, HttpServletRequest request, HttpServletResponse response, Object handler) {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String xAccessToken = request.getHeader("x-access-token");
        Authorization wtAuthorization = method.getAnnotation(Authorization.class);
        if (wtAuthorization != null) {
            TokenHelper tokenHelper = ApplicationContextUtil.getBean(TokenHelper.class);
            TokenResult apiTokenResult = tokenHelper.checkToken(tokenPrefix, xAccessToken);
            if (apiTokenResult == null){
                throw new UnAuthorizedException();
            }
            request.setAttribute("tokenResult", apiTokenResult);
        }
    }

    protected void handlePCPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        this.handlePreHandle(Constants.kPrefix_PC, request, response, handler);
    }

}
