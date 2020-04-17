package com.yss.cn.common.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.yss.cn.api.io.tBaseAuth.TBaseAuthIO;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuoshi.Yan
 * @package:com.yss.common.utils
 * @className:对象转换
 * @description:
 * @date 2019-12-12 13:07
 * @version:V1.0
 * @NOTICE：本内容仅限于xxx有限公司内部传阅,禁止外泄以及用于其他的商业项目
 * @ Copyright  xxx. All rights reserved.
 **/

public class BeanUtil {

    //对象转换
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    /**
     * @author:Shuoshi.Yan
     * @description: 对象转换
     * @date: 2019/12/12 16:33
     * @param: 
     * @return: 
     */
    public static <T> T mapToBean(Object source, Class<T> targetClass) {
        T result = mapper.map(source, targetClass);
        return result;
    }
    /**
     * @author:Shuoshi.Yan
     * @description: bean转map
     * @date: 2019/12/12 13:07
     * @param:
     * @return:
     */
    public static Map beanToMap(Object bean) {
        Map map = new HashMap<>();
        //获取JavaBean的描述器
        BeanInfo b = null;
        try {
            b = Introspector.getBeanInfo(bean.getClass(),Object.class);
            //获取属性描述器
            PropertyDescriptor[] pds = b.getPropertyDescriptors();
            //对属性迭代
            for (PropertyDescriptor pd : pds) {
                //属性名称
                String propertyName = pd.getName();
                //属性值,用getter方法获取
                Method m = pd.getReadMethod();
                Object properValue = m.invoke(bean);//用对象执行getter方法获得属性值
                //把属性名-属性值 存到Map中
                map.put(propertyName, properValue);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        TBaseAuthIO tBaseAuthIO = new TBaseAuthIO();
        tBaseAuthIO.setAuthId("1");
        tBaseAuthIO.setAuthMode("1");
        System.out.println("beanToMap" + BeanUtil.beanToMap(tBaseAuthIO));
        Map map = new HashMap();
        map.put("authId","1");
        System.out.println("MapToBean" + BeanUtil.mapToBean(map,TBaseAuthIO.class));
    }
}
