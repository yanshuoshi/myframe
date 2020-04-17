package com.yss.cn.persistence.dao;

import com.yss.cn.persistence.entity.${entity};
import ${superMapperClassPackage};
import java.util.*;
import com.yss.cn.api.result.${table.entityPath}.${table.entityName}Result;
import org.apache.ibatis.annotations.Mapper;

/**
* ${table.comment!} Mapper 接口
* @author ${author}
* @since ${date}
*/
@Mapper
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    //列表查询
    List<${entity}Result> query${table.entityName}List(Map map);

    //删除 根据列表id进行循环逻辑删除
    void delete${table.entityName}ListByListId(List<Integer> idList);

}
