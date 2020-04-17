package com.yss.cn.api.service.tBaseAuth;

import com.yss.cn.api.io.tBaseAuth.*;
import com.yss.cn.io.PageListIO;
import com.yss.cn.api.result.tBaseAuth.TBaseAuthResult;
import com.yss.cn.results.FormListResult;

import java.util.List;

/**
* 授权管理-授权表
* @author ShuoShi Yan
* @since 2019-12-23
*/
public interface TBaseAuthService {
    //分页查询 tBaseAuth
    FormListResult queryTBaseAuthPageList(PageListIO body);

    //查询
    TBaseAuthResult findTBaseAuthResultById(Integer id);

    //新增
    void saveTBaseAuth(TBaseAuthIO body);

    //编辑
    void updateTBaseAuth(TBaseAuthIO body);

    //删除 根据列表id进行循环逻辑删除
    void deleteTBaseAuthListByListId(List<Integer> idList);

}

