package com.yss.cn.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Shuoshi.Yan
 * @package:com.yss.auth
 * @className:
 * @description:
 * @date 2020-03-25 13:04
 * @version:V1.0
 * @NOTICE：本内容仅限于xxx有限公司内部传阅,禁止外泄以及用于其他的商业项目
 * @ Copyright  xxx. All rights reserved.
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

    String hasAnyPermission() default "";

    String hasAnyRole() default "";
}
