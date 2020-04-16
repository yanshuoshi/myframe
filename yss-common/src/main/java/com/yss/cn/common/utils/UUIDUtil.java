package com.yss.cn.common.utils;

import java.util.UUID;

/**
 * @author:Shuoshi.Yan
 * @description: UUID
 * @date: 2020/1/14 15:14
 */

public class UUIDUtil {
    static public String randomUUID(){
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-","");
    }
}
