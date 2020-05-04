package com.yss.cn.io;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "PageDataIO", description = "分页数据")
public class PageDataIO {

    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "每页显示条数")
    private long size = 10;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private long current = 0;

}
