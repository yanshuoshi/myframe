package com.yss.cn.config.aspect;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author:Shuoshi.Yan
 * @description:
 * @date: 2020/4/10 11:43
 */
//@Aspect
//@Order(5)
//@Component
public class LogAspect {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.yss.cn.controller..*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object monitorElapsedTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        double elapsedTime = stopWatch.getTime() / 1000.0;
        Signature signature = proceedingJoinPoint.getSignature();
        String infoString = signature.toShortString() + "  -> " + elapsedTime + " s";
        if (elapsedTime > 1) {
            logger.warn("[耗时过长!!!]" + infoString);
        } else {
//            logger.info(infoString);
        }
        return result;
    }


//    @Pointcut("execution(public * com.wangtiansoft.stla.modules..*tBaseAuthController.*(..))")
//    public void modulesWebLog() {
//    }
//
//    @Before("modulesWebLog()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringType().getSimpleName() + " : " + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "modulesWebLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
////         处理完请求，返回内容
//        logger.info("RESPONSE : " + ret);
//    }


}
