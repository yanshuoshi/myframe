package com.yss.cn.controller.${table.entityPath};

import com.yss.cn.io.*;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.math.NumberUtils;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
import com.yss.cn.io.PageListIO;
import com.yss.cn.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import javax.validation.Valid;
import com.yss.cn.bean.BaseController;
import org.apache.commons.collections.CollectionUtils;
import com.yss.cn.api.service.${table.entityPath}.${table.entityName}Service;
import com.yss.cn.api.result.${table.entityPath}.${table.entityName}Result;
import com.yss.cn.api.io.${table.entityPath}.${table.entityName}ListFromIO;

/**
* ${table.comment!} 控制器
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@Api(value = "[后台]${table.controllerName}",description = "${table.controllerName}")
@RequestMapping("/pc<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName}  extends BaseController{
    </#if>

    @Autowired
    private ${table.entityName}Service ${table.entityPath}Service;

    @ApiOperation(value = "${table.entityPath}列表",notes="${table.entityPath}列表",response = ${table.entityName}Result.class)
    @PostMapping("/${table.entityPath}List")
    public ApiResult ${table.entityPath}List(@Valid @ApiParam(required = true) @RequestBody PageListIO<${table.entityName}ListFromIO> body) {
        FormListResult<${table.entityName}Result> result = ${table.entityPath}Service.query${table.entityName}PageList(body);
        return ApiResult.success(result);
    }

    @ApiOperation(value = "${table.entityPath}详情",notes="${table.entityPath}详情",response = ${table.entityName}Result.class)
    @PostMapping("/${table.entityPath}Detail")
    public ApiResult ${table.entityPath}Detail(@ApiParam(required = true) @Valid @RequestBody SimpleIO body) {
        ${table.entityName}Result result = ${table.entityPath}Service.find${table.entityName}ResultById(NumberUtils.toInt(body.getId()));
        return ApiResult.success(result);
    }

    @ApiOperation(value = "新增${table.entityPath}",notes="新增${table.entityPath}",response = ApiResult.class)
    @PostMapping("/${table.entityPath}Add")
    public ApiResult ${table.entityPath}Add(@ApiParam(required = true) @Valid @RequestBody ${table.entityName}IO body) {
        ${table.entityPath}Service.save${table.entityName}(body);
        return ApiResult.success();
    }

    @ApiOperation(value = "编辑${table.entityPath}",notes="编辑${table.entityPath}")
    @PutMapping("/${table.entityPath}Update")
    public ApiResult ${table.entityPath}Update(@ApiParam(required = true) @Valid @RequestBody ${table.entityName}IO body) {
        ${table.entityPath}Service.update${table.entityName}(body);
        return ApiResult.success();
    }

    @ApiOperation(value = "删除${table.entityPath}",notes="根据${table.entityPath}ID删除${table.entityPath}信息")
    @PutMapping("/${table.entityPath}Delete")
    public ApiResult ${table.entityPath}Delete(@ApiParam(required = true) @Valid @RequestBody SimpleListIO body) {
        if (CollectionUtils.isNotEmpty(body.getList())) {
            ${table.entityPath}Service.delete${table.entityName}ListByListId(body.getList());
        }
        return ApiResult.success();
    }
}
</#if>
