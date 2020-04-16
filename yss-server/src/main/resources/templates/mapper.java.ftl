package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import java.util.*;
import org.apache.ibatis.annotations.*;

/**
* ${table.comment!} Mapper 接口
* @author ${author}
* @since ${date}
*/
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    //列表查询
    List<com.yss.module.result.${table.entityPath}.${entity}Result> query${table.entityName}List(Map map);

    //删除 根据列表id进行循环逻辑删除
    void delete${table.entityName}ListByListId(List<Integer> idList);

}
