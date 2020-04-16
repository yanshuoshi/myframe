package com.yss.cn.config.advices;

import com.yss.cn.config.auth.AuthUserResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author:Shuoshi.Yan
 * @description:
 * @date: 2020/4/10 10:46
 */
@Configuration
public class WebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//拦截器
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new PCAuthorizationInterceptor()).addPathPatterns("/pc/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new AuthUserResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //  测试用，使用当前web资源目录作为临时的文件存储目录，正式环境不应该这样使用
        // 映射文件路径，使能够访问静态资源
        registry.addResourceHandler("/files/**").addResourceLocations("file:files/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

}
