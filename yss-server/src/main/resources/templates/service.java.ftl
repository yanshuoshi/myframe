package com.yss.cn.api.service.${table.entityPath};

import com.yss.cn.api.io.${table.entityPath}.${table.entityName}IO;
import com.yss.cn.api.result.${table.entityPath}.${table.entityName}Result;
import com.yss.cn.results.*;
import com.yss.cn.io.*;
import java.util.List;

/**
* ${table.comment!}
* @author ${author}
* @since ${date}
*/
public interface ${table.entityName}Service {
    //分页查询
    FormListResult query${table.entityName}PageList(PageListIO body);

    //查询
    ${table.entityName}Result find${table.entityName}ResultById(Integer id);

    //新增
    void save${table.entityName}(${table.entityName}IO body);

    //编辑
    void update${table.entityName}(${table.entityName}IO body);

    //删除 根据列表id进行循环逻辑删除
    void delete${table.entityName}ListByListId(List<Integer> idList);

}

