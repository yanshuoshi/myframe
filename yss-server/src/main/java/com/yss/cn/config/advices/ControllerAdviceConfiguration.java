package com.yss.cn.config.advices;

import com.yss.api.exception.AppRuntimeException;
import com.yss.api.exception.UnAuthorizedException;
import com.yss.results.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author:Shuoshi.Yan
 * @description: 控制器扩展处理
 * @date: 2020/4/10 10:41
 */
@Slf4j
@RestControllerAdvice("com.yss.cn")
public class ControllerAdviceConfiguration {

    @ExceptionHandler(value = Exception.class)
    public ApiResult handleException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        if (exception instanceof AppRuntimeException) {
            //  业务异常
            return ApiResult.fail(exception.getLocalizedMessage());
        } else if (exception instanceof MethodArgumentNotValidException) {
            //  Hibernate Validator 异常(入参校验异常)
            List<ObjectError> objectErrorList = ((MethodArgumentNotValidException) exception).getBindingResult().getAllErrors();
            if (CollectionUtils.isNotEmpty(objectErrorList)) {
                return ApiResult.fail(objectErrorList.get(0).getDefaultMessage());
            }
        } else if (exception instanceof RuntimeException) {
            //  系统异常
//            return ApiResult.fail("发生错误，请联系管理员或稍候再试");
        }
        exception.printStackTrace();
        return ApiResult.fail("发生错误，请联系管理员或稍候再试");
    }

    @ExceptionHandler(value = UnAuthorizedException.class)
    public ApiResult handleUnAuthorizedException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-access-token");
        response.addHeader("Access-Control-Max-Age", "3600");
        return ApiResult.fail("2000", "用户登录过期");//2000 没有权限
    }


}
