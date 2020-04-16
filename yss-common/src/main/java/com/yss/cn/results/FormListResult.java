package com.yss.cn.results;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yss on 2019/12/12 1331.
 */
@Data
public class FormListResult<T> implements Serializable {

    @ApiModelProperty(value = "当前页码", dataType = "String")
    private int currentPage;
    @ApiModelProperty(value = "每页记录数", dataType = "String")
    private int pageSize;
    @ApiModelProperty(value = "总记录数", dataType = "String")
    private long total;
    @ApiModelProperty(value = "数据列表")
    private List<T> list;

    private FormListResult() {
    }

    public FormListResult(Page<T> page) {
        this.currentPage = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = page.getTotal();
        this.list = page.getResult();
    }

}
