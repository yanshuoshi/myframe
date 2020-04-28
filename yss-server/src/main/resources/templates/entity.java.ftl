package com.yss.cn.persistence.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
/**
 * ${table.comment}
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
</#if>
<#if table.convert>
@Table("${table.name}")
</#if>
<#if superEntityClass??>
@Data
public class ${entity} extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
@Data
public class ${entity} extends Model<${entity}> {
<#else>
@Data
public class ${entity} implements Serializable {
</#if>

    private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
<#if field.keyFlag>
<#assign keyPropertyName="${field.propertyName}"/>
</#if>

<#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
</#if>
<#if field.keyFlag>
<#-- 主键 -->
<#if field.keyIdentityFlag>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<#elseif idType??>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<#elseif field.convert>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
</#if>
<#-- 普通字段 -->
<#elseif field.fill??>
<#-- -----   存在字段填充设置   ----->
<#if field.convert>
    @Column(value = "${field.name}", fill = FieldFill.${field.fill})
<#else>
    @Column(fill = FieldFill.${field.fill})
</#if>
<#elseif field.convert>
    @Column(name = "${field.name}")
</#if>
<#-- 乐观锁注解 -->
<#if (versionFieldName!"") == field.name>
    @Version
</#if>
<#-- 逻辑删除注解 -->
<#if (logicDeleteFieldName!"") == field.name>
    @TableLogic
</#if>
    <#if field.propertyType == "LocalDateTime">
    private Date ${field.propertyName};
    <#else>
    private ${field.propertyType} ${field.propertyName};</#if>
</#list>
<#------------  END 字段循环遍历  ---------->

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
<#--<#list table.fields as field>-->
    <#--public static final String ${field.name?upper_case} = "${field.name}";-->

<#--</#list>-->
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
