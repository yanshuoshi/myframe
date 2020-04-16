package com.yss.common.utils;

import java.util.UUID;

/**
 * @author:Shuoshi.Yan
 * @description: UUID
 * @date: 2020/1/14 15:14
 * @param: 
 * @return: 
 */

public class UUIDUtil {
    static public String randomUUID(){
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-","");
    }
}
