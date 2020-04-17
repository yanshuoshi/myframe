package com.yss.cn.api.io.${table.entityPath};


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;
import java.io.Serializable;
import lombok.Data;
/**
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Data
</#if>
@ApiModel(value = "${entity}IO", description = "${entity}IO描述")
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity} extends Model<${entity}> {
<#else>
public class ${entity}IO implements Serializable {
</#if>

    private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
<#if field.keyFlag>
<#assign keyPropertyName="${field.propertyName}"/>
</#if>
<#if field.comment!?length gt 0>
    @ApiModelProperty(value = "${field.comment}")
</#if>
    <#if field.propertyType == "LocalDateTime">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ${field.propertyName};
    <#else>
    private ${field.propertyType} ${field.propertyName};</#if>
</#list>
<#if !entityLombokModel>
<#list table.fields as field>
<#if field.propertyType == "boolean">
    <#assign getprefix="is"/>
<#else>
    <#assign getprefix="get"/>
</#if>
    public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
    }
<#if entityBuilderModel>
    public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
<#else>
    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
</#if>
        this.${field.propertyName} = ${field.propertyName};
<#if entityBuilderModel>
        return this;
</#if>
    }
</#list>
</#if>
<#if entityColumnConstant>
</#if>
<#if activeRecord>
    @Override
    protected Serializable pkVal() {
<#if keyPropertyName??>
        return this.${keyPropertyName};
<#else>
        return null;
</#if>
    }

</#if>
<#if !entityLombokModel>
    @Override
    public String toString() {
        return "${entity}{" +
<#list table.fields as field>
<#if field_index==0>
        "${field.propertyName}=" + ${field.propertyName} +
<#else>
        ", ${field.propertyName}=" + ${field.propertyName} +
</#if>
</#list>
        "}";
    }
</#if>
}
