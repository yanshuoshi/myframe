<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yss.cn.persistence.dao.${entity}Mapper">

    <#if enableCache>
        <!-- 开启二级缓存 -->
        <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

    </#if>
    <#if baseResultMap>
        <!-- 通用查询映射结果 -->
        <resultMap id="BaseResultMap" type="com.yss.cn.persistence.entity.${entity}">
            <#list table.fields as field>
                <#if field.keyFlag><#--生成主键排在第一位-->
                    <id column="${field.name}" property="${field.propertyName}" />
                </#if>
            </#list>
            <#list table.commonFields as field><#--生成公共字段 -->
                <result column="${field.name}" property="${field.propertyName}" />
            </#list>
            <#list table.fields as field>
                <#if !field.keyFlag><#--生成普通字段 -->
                    <result column="${field.name}" property="${field.propertyName}" />
                </#if>
            </#list>
        </resultMap>

    </#if>
    <#if baseColumnList>
        <!-- 通用查询结果列 -->
        <sql id="BaseColumnList">
            <#list table.commonFields as field>
                ${field.name},
            </#list>
            ${table.fieldNames}
        </sql>
    </#if>

    <select id="query${table.entityName}List" resultType="com.yss.cn.api.result.${table.entityPath}.${table.entityName}Result">
        SELECT
            <include refid="BaseColumnList"/>
        FROM ${table.name} AS <#list "${table.name}"?split("_") as num><#if !num_has_next><#assign a = num?substring(0,1)>${a}</#if></#list>
        WHERE 1=1 AND ${a}.is_delete = 0
        <if test="updateTimeRangeStart != null and updateTimeRangeStart != ''">
            AND ${a}.update_time >= ${"#\{updateTimeRangeStart}"}
        </if>
        <if test="updateTimeRangeEnd != null and updateTimeRangeEnd != ''">
            AND ${"#\{updateTimeRangeEnd}"} >= ${a}.update_time
        </if>
    </select>
    <update id="delete${table.entityName}ListByListId">
        UPDATE ${table.name} AS ${a}
        SET ${a}.id in
        <foreach item="item" index="index" collection="list" open="(" separator="," close=")">
            ${"#\{item}"}
        </foreach>
    </update>
</mapper>
