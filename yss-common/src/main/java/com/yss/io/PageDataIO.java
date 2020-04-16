package com.yss.io;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "PageIO", description = "分页数据")
public class PageDataIO implements Serializable {

    @ApiModelProperty(value = "当前页码", example = "0", allowEmptyValue = true)
    private int currentPage;
    @ApiModelProperty(value = "单页记录数量", example = "20", allowEmptyValue = true)
    private int pageSize;
    @ApiModelProperty(value = "排序字段", example = " ", allowEmptyValue = true)
    private String sortName;
    @ApiModelProperty(value = "排序方向", example = " ", allowEmptyValue = true)
    private String sortOrder;

    public PageDataIO() {
        this.currentPage = 0;
        this.pageSize = 20;
        this.sortName = "";
        this.sortOrder = "";
    }

}
