package com.yss.cn.config.swagger;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Shuoshi.Yan
 * @package:com.plus.yss
 * @className:Swagger2Config
 * @description:
 * @date 2019-12-11 15:19
 * @version:V1.0
 * @NOTICE：本内容仅限于xxx有限公司内部传阅,禁止外泄以及用于其他的商业项目
 * @ Copyright  xxx. All rights reserved.
 **/

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi(){
        String packageString = StringUtils.removeEnd(ClassUtils.getPackageName(Swagger2Config.class), ".config.swagger") + "";
        ParameterBuilder tokenParameterBuilder = new ParameterBuilder();
        List<Parameter> parameterList = new ArrayList<Parameter>();
        tokenParameterBuilder.name("x-access-token").description("请求令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameterList.add(tokenParameterBuilder.build());

        Set<String> consumesSet = new HashSet<>();
        consumesSet.add("application/json");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(packageString))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("接口服务文档") //接口管理文档首页显示
                .description("接口服务文档")//API的描述
//                .contact("binzh303@163.com")
                .version("1.0")
                .build();
    }
}

