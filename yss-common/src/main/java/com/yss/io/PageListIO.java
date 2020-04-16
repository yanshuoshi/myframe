package com.yss.io;

import com.yss.common.utils.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@ApiModel(value = "PageListIO", description = "列表及分页数据")
public class PageListIO<T> implements Serializable {

    @ApiModelProperty(value = "分页对象")
    private PageDataIO pageData;
    @Valid
    @ApiModelProperty(value = "表单对象")
    private T formData;

    public Map buildSQLMap() {
        Map<String, Object> paramMap = new HashMap();
        if (formData != null) {
            paramMap = BeanUtil.beanToMap(this.formData);
        }
        Set<String> keySet = new HashSet<>(paramMap.keySet());
                for (String key : keySet) {
                    if (key.endsWith("DateRange") || key.endsWith("TimeRange") && paramMap.get(key) != null && (paramMap.get(key) instanceof Object[])) {
                        Object[] range = (Object[]) paramMap.get(key);
                        paramMap.put(key + "Start", range.length > 0 ? range[0] + " 00:00:00" : null);
                        paramMap.put(key + "End", range.length > 1 ? range[1] + " 23:59:59" : null);
                    }
                    if (paramMap.get(key) != null && paramMap.get(key) instanceof String) {
                        paramMap.put(key, StringUtils.trimToEmpty(String.valueOf(paramMap.get(key))));
            }
        }
        return paramMap;
    }

    /**
     * PageHelper 工具类分页从1开始，前台接口为了统一去文，第一页是从0开始。
     * @return
     */
    public int currentPage() {
        if (pageData == null)
            return 1;
        return Math.max(pageData.getCurrentPage() + 1, 1);
    }

    public int pageSize() {
        if (pageData == null)
            return 20;
        return pageData.getPageSize();
    }

}
