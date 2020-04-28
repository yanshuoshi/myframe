package com.yss.cn.api.io.yssAccount;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@ApiModel(value = "AuthLoginIO", description = "用户登录表单")
@Data
public class AuthLoginIO implements Serializable {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", example = "admin")
    private String userName;
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", example = "123456")
    private String password;
}
