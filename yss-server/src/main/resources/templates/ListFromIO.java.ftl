package com.yss.cn.api.io.${table.entityPath};

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
/**
* @author ${author}
* @since ${date}
*/
@Data
@ApiModel(value = "${entity}ListFromIO", description = "${entity}ListFromIO描述")
public class ${entity}ListFromIO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String[] updateTimeRange;

}
