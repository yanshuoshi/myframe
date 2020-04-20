package com.yss.cn.api.io.tBaseAuth;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ShuoShi Yan
 * @since 2019-12-23
 */
@Data
@ApiModel(value = "TBaseAuthListFromIO", description = "TBaseAuthListFromIO描述")
public class TBaseAuthListFromIO implements Serializable {
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String[] updateTimeRange;
}
