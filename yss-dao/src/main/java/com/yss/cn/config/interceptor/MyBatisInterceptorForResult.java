package com.yss.cn.config.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

/**
 * @author:Shuoshi.Yan
 * @description: 打印结果拦截器
 * @date: 2020/4/28 18:15
 */
@Component
@Intercepts({@Signature(type = Executor.class, method = "query", args = {
        MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})})
public class MyBatisInterceptorForResult implements Interceptor
{

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object intercept(Invocation invocation)
            throws Throwable
    {
        Object result = invocation.proceed(); // 执行请求方法，并将所得结果保存到result中
        String str = JSON.toJSONString(result);
        System.out.println(str);
        return result;
    }

    public Object plugin(Object target)
    {
        return Plugin.wrap(target, this);
    }

}
