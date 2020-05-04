package com.yss.cn.config.advices;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
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
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {//信息转换器
        //调用父类
        WebMvcConfigurer.super.configureMessageConverters(converters);
        //fastJaon消息转换器（返回json）
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //配置类
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //返回内容过滤的配置
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteNullStringAsEmpty);
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //添加到转换器中
//        converters.add(fastJsonHttpMessageConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//添加拦截器
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new PCAuthorizationInterceptor()).addPathPatterns("/pc/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {//参数解析器
        WebMvcConfigurer.super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new AuthUserResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {//静态资源配置
        //  测试用，使用当前web资源目录作为临时的文件存储目录，正式环境不应该这样使用
        // 映射文件路径，使能够访问静态资源
        registry.addResourceHandler("/files/**").addResourceLocations("file:files/");//对外暴露的访问位置，内部文件存储位置
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {//跨域处理
        registry.addMapping("/**");
    }

}
