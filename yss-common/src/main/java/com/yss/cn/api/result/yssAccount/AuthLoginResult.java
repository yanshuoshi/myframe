package com.yss.cn.api.result.yssAccount;

import com.yss.cn.results.ApiResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@ApiModel(value = "AuthLoginResult", description = "AuthLoginResult描述")
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthLoginResult extends ApiResult implements Serializable {

    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "accountDetail")
    private YssAccountResult accountResult;
}
