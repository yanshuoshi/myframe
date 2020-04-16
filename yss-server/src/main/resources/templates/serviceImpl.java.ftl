package ${package.ServiceImpl};

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.yss.results.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yss.cn.modules.service.${table.serviceName};
import com.yss.cn.modules.mapper.${table.mapperName};
import com.yss.common.utils.*;
import com.yss.io.*;
import com.yss.module.result.${table.entityPath}.${table.entityName}Result;
import com.yss.module.io.${table.entityPath}.${table.entityName}IO;
import java.util.List;
import com.yss.cn.modules.entity.${table.entityName};

/**
 * $!{table.comment} 服务实现类
 * @author ${author}
 * @since ${date}
 */
@Service
@Transactional
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Autowired
    private ${table.mapperName} ${table.entityPath}Mapper;

    @Override
    public FormListResult query${table.entityName}PageList(PageListIO io){
        PageHelper.startPage(io.currentPage(), io.pageSize());
        Page page = (Page<${table.entityName}Result>) ${table.entityPath}Mapper.query${table.entityName}List(io.buildSQLMap());
        return new FormListResult<${table.entityName}Result>(page);
    }

    @Override
    public ${table.entityName}Result find${table.entityName}ResultById(Integer id){
        return BeanUtil.cast(${table.entityPath}Mapper.selectById(id), ${table.entityName}Result.class);
    }

    @Override
    public void save${table.entityName}(${table.entityName}IO io){
        ${table.entityName} entity = BeanUtil.cast(io, ${table.entityName}.class);
        //  TODO 补充set
        ${table.entityPath}Mapper.insert(entity);
    }

    @Override
    public void update${table.entityName}(${table.entityName}IO io) {
        ${table.entityName} entity = BeanUtil.cast(io, ${table.entityName}.class);
        //  TODO 补充set
        ${table.entityPath}Mapper.updateById(entity);
    }

    @Override
    public void delete${table.entityName}ListByListId(List<Integer> idList){
        //${table.entityPath}Mapper.delete${table.entityName}ListByListId(idList);
    }


}
