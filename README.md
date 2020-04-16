# myframe

## 特别注意


## 工程模块描述

```
yss-common | 基础类(自定义异常、入参、回参、service接口、自定义注解、公共类[三层、入参、回参、ws]、枚举、util)
yss-dao | 数据库Entity、数据库Mapper 
yss-server | 服务（默认前台接口和后台接口共用工程，使用api和modules区分） 
```

## 主要类说明

`wtemplete-common`工程包含了业务相关的DO、DTO。<br/>
1. `exception`为共同使用的异常类，业务中的运行异常通过`AppRuntimeException`向外抛出。<br/>
2. `io`为网页请求的表单接收对象，需要使用swagger中的相关注解（如@ApiModel等）进行接口文档的说明，使用hibernate.validator中的相关注解（如@NotBlank等，详情见下文）进行入参校验。<br/>
3. `result`为返回到前台的数据对象，需要使用swagger中的相关注解（如@ApiModel等）进行接口文档的说明。<br/>
4. `service`为消费者、提供者共用的接口类。<br/>

## 入参校验

当入参类型为@RequestBody时，使用@Valid标记AuthFormIO类为参数验证状态。
```java
@PostMapping("/login")
public ApiResult login(@ApiParam(required = true) @Valid @RequestBody AuthFormIO body) {
    AccountLoginResult result = authService.login("test", "test", "127");
    return ApiResult.success(result);
}
```
使用入参校验相关注解方法进行校验
```java
public class AuthFormIO implements Serializable {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @ApiModelProperty(value = "IP地址", example = "127.0.0.1")
    private String ip;
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

在控制器中，`@WtAuthorization`注解表示此方法需要在header中传递`x-access-token`才可以访问<br/>
在控制器中，`@AuthToken ApiTokenResult authToken`参数表示当用户传递了`x-access-token`后，自动获取到的用户数据
如下所示：
```java
@ApiOperation(value = "用户列表", notes = "用户列表", response = AccountResult.class)
@PostMapping("/accountList")
@WtAuthorization
public ApiResult accountList(@AuthToken ApiTokenResult authToken, @ApiParam(required = true) @Valid @RequestBody PageListIO<AuthFormIO> body) {
    FormListResult<AccountResult> result = accountService.queryAccountPageList(body);
    return ApiResult.success(result);
}
```



## 功能自检表

### 硬性要求

* [wtemplete-*]工程中，方法名、参数名均使用驼峰命名法
* [wtemplete-common]工程中，`io`需要实现Serializable接口
* [wtemplete-common]工程中，`io`需要使用`@ApiModel`、`@ApiModelProperty`进行描述，并且提供合理的example
* [wtemplete-common]工程中，`io`入参如果需要校验则需要使用hibernate.validator进行注解，如（@NotBlank）
* [wtemplete-common]工程中，`result`需要实现Serializable接口
* [wtemplete-common]工程中，`result`需要使用`@ApiModel`、`@ApiModelProperty`进行描述，并且提供合理的example
* [wtemplete-server]工程中，`controller`需要使用`@ApiOperation`、`@ApiParam`进行描述，提供正确合理的response

### 软性要求

* [wtemplete-server]工程中，入参使用IO对象，不建议使用其他类型（特殊情况可以使用）
* [wtemplete-server]工程中，方法接口返回值为Result对象，不建议使用其他类型（特殊情况可以使用）

## 批量创建permission
```sql
INSERT INTO `stla_db`.`wt_permission` ( `module_id`, `permission_code`, `permission_name`, `order_num`, `is_publish`, `is_delete`, `update_time`, `create_time` ) 
SELECT wm.id, CONCAT(wm.module_code, ':VIEW'), '查看', 0, 1, 0, '2019-01-01','2019-01-01'  FROM wt_module AS wm;
INSERT INTO `stla_db`.`wt_permission` ( `module_id`, `permission_code`, `permission_name`, `order_num`, `is_publish`, `is_delete`, `update_time`, `create_time` ) 
SELECT wm.id, CONCAT(wm.module_code, ':EDIT'), '编辑', 0, 1, 0, '2019-01-01','2019-01-01'  FROM wt_module AS wm;
INSERT INTO `stla_db`.`wt_permission` ( `module_id`, `permission_code`, `permission_name`, `order_num`, `is_publish`, `is_delete`, `update_time`, `create_time` ) 
SELECT wm.id, CONCAT(wm.module_code, ':ADD'), '新建', 0, 1, 0, '2019-01-01','2019-01-01'  FROM wt_module AS wm;
INSERT INTO `stla_db`.`wt_permission` ( `module_id`, `permission_code`, `permission_name`, `order_num`, `is_publish`, `is_delete`, `update_time`, `create_time` ) 
SELECT wm.id, CONCAT(wm.module_code, ':DELETE'), '删除', 0, 1, 0, '2019-01-01','2019-01-01'  FROM wt_module AS wm;

INSERT INTO `stla_db`.`wt_role_permission` (`role_id`,`permission_id`,`is_publish`, `is_delete`) 
SELECT 1, wp.id, 1, 0 FROM wt_permission wp ;
```
