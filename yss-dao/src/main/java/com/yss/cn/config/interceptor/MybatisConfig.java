package com.yss.cn.config.interceptor;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

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
//@Configuration
public class MybatisConfig {
    /**
     * @author:Shuoshi.Yan
     * @description: mybatis 自定义拦截器（使用了@Component注解所以不需要配置）
     * @date: 2020/4/20 10:41
     */
    @Bean
    public Interceptor getInterceptor(){
        return new MybatisInterceptor();
    }
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
//        p.setProperty("reasonable", "false");
//        //通过设置pageSize=0或者RowBounds.limit = 0就会查询出全部的结果。
//        p.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
