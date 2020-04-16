package com.yss.io;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "SimpleListIO", description = "简单列表")
public class SimpleListIO implements Serializable{

    @ApiModelProperty(value = "列表")
    private List list;

}
