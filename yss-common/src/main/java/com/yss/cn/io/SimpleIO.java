package com.yss.cn.io;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@ApiModel(value = "SimpleIO", description = "简单对象")
@Data
public class SimpleIO implements Serializable{

    @ApiModelProperty(value = "ID")
    @NotBlank(message = "主键id不能为空")
    private String id;
    @ApiModelProperty(value = "类型")
    private String type;
    public SimpleIO() {
    }
}
