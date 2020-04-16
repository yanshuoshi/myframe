package com.yss.cn.config.interceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;

/**
 * @author Shuoshi.Yan
 * @package:com.yss.cn.config.interceptor
 * @className:mybatis 打印完整slq配置类
 * @description:
 * @date 2020-04-16 17:17
 * @version:V1.0
 * @NOTICE：本内容仅限于xxx有限公司内部传阅,禁止外泄以及用于其他的商业项目
 * @ Copyright  xxx. All rights reserved.
 **/

public class MybatisConfig {
    /**
     * mybatis 自定义拦截器
     */
    @Bean
    public Interceptor getInterceptor(){
        return new MybatisInterceptor();
    }

}
