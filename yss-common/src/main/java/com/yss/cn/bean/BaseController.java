package com.yss.cn.bean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:Shuoshi.Yan
 * @description: 
 * @date: 2020/4/28 15:47
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;


}
