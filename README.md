# myframe

## 总体概况
使用SpringBoot、MyBatisPlus、Swagger、自定义注解、JWT、MySQL以及提供MyBatisPlus + ftl模板进行代码生成等

## 特别注意
1. mybatisplus 查询时必须使用resultMap指定返回对象

## 工程模块描述

```
yss-common | 基础工程(自定义异常、入参回参、service接口、自定义注解、公共类[公共的入参回参等]、枚举、util)
yss-dao | 数据库的Entity、Mapper、xml 
yss-server | 业务处理工程 
```

## 主要类说明

`yss-common`工程包含了业务相关的DO、DTO。<br/>
1. `exception`为共同使用的异常类，业务中的运行异常通过`AppRuntimeException`向外抛出。<br/>
2. `io`为网页请求的表单接收对象，需要使用swagger中的相关注解（如@ApiModel等）进行接口文档的说明，使用hibernate.validator中的相关注解（如@NotBlank等 ）进行入参校验。<br/>
3. `result`为返回到前台的数据对象，需要使用swagger中的相关注解（如@ApiModel等）进行接口文档的说明。<br/>
4. `service`为消费者、提供者共用的接口类。<br/>
5. `common`提供各种util
6. `constants`设置常量及枚举

## 入参校验

当入参类型为@RequestBody时，使用@Valid标记AuthFormIO类为参数验证状态。
```java
@PostMapping("/login")
public ApiResult login(@ApiParam(required = true) @Valid @RequestBody AuthLoginIO body) {
    AuthLoginResult result = yssAccountService.userSecretLogin(body.getUserName(), body.getPassword(), NetworkUtil.getIpAddress(request));
        return ApiResult.success(result);
}
```
使用入参校验相关注解方法进行校验
```java

@ApiModel(value = "AuthLoginIO", description = "用户登录表单")
public class AuthLoginIO implements Serializable {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", example = "admin")
    private String userName;
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", example = "123456")
    private String password;
}
```


使用hibernate.validator进行入参校验，常用方法如下：
```
Hibernate Validator 附加的 constraint     
@NotBlank(message =)   验证字符串非null，且长度必须大于0     
@Email  被注释的元素必须是电子邮箱地址     
@Length(min=,max=)  被注释的字符串的大小必须在指定的范围内     
@NotEmpty   被注释的字符串的必须非空     
@Range(min=,max=,message=)  被注释的元素必须在合适的范围内
@Null   被注释的元素必须为 null     
@NotNull    被注释的元素必须不为 null     
@AssertTrue     被注释的元素必须为 true     
@AssertFalse    被注释的元素必须为 false     
@Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值     
@Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值     
@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值     
@DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值     
@Size(max=, min=)   被注释的元素的大小必须在指定的范围内     
@Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内     
@Past   被注释的元素必须是一个过去的日期     
@Future     被注释的元素必须是一个将来的日期     
@Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式   
```

## 权限控制

在控制器中，`@Authorization`注解表示此方法需要在header中传递`x-access-token`才可以访问<br/>
在控制器中，`@AuthToken TokenResult authToken`参数表示当用户传递了`x-access-token`后，自动获取到的用户数据
如下所示：
```java
    @ApiOperation(value = "tBaseAuth列表",notes="tBaseAuth列表",response = TBaseAuthListResult.class)
    @PostMapping("/tBaseAuthList")
    @Authorization
    public ApiResult tBaseAuthList(@AuthToken TokenResult authToken, @Valid @ApiParam(required = true) @RequestBody PageListIO<TBaseAuthListFromIO> body) {
        Page result = tBaseAuthService.queryTBaseAuthPageList(body);
        return ApiResult.success(result);
    }
```



## 功能自检表

### 硬性要求

* [yss-*]工程中，方法名、参数名均使用驼峰命名法
* [yss-common]工程中，`io`需要实现Serializable接口
* [yss-common]工程中，`io`需要使用`@ApiModel`、`@ApiModelProperty`进行描述，并且提供合理的example
* [yss-common]工程中，`io`入参如果需要校验则需要使用hibernate.validator进行注解，如（@NotBlank）
* [yss-common]工程中，`result`需要实现Serializable接口
* [yss-common]工程中，`result`需要使用`@ApiModel`、`@ApiModelProperty`进行描述，并且提供合理的example
* [yss-server]工程中，`controller`需要使用`@ApiOperation`、`@ApiParam`进行描述，提供正确合理的response

### 软性要求

* [yss-server]工程中，入参使用IO对象，不建议使用其他类型（特殊情况可以使用）
* [yss-server]工程中，方法接口返回值为Result对象，不建议使用其他类型（特殊情况可以使用）


