package com.yss.cn.api.io.yssAccount;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
/**
* @author ShuoShi Yan
* @since 2020-04-20
*/
@Data
@ApiModel(value = "YssAccountListFromIO", description = "YssAccountListFromIO描述")
public class YssAccountListFromIO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String[] updateTimeRange;

}
