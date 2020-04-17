package com.yss.cn.api.service.yssAccount;

import com.yss.cn.api.io.yssAccount.YssAccountIO;
import com.yss.cn.api.result.yssAccount.YssAccountResult;
import com.yss.cn.results.*;
import com.yss.cn.io.*;
import java.util.List;

/**
* 系统用户信息
* @author ShuoShi Yan
* @since 2020-04-17
*/
public interface YssAccountService {
    //分页查询
    FormListResult queryYssAccountPageList(PageListIO body);

    //查询
    YssAccountResult findYssAccountResultById(Integer id);

    //新增
    void saveYssAccount(YssAccountIO body);

    //编辑
    void updateYssAccount(YssAccountIO body);

    //删除 根据列表id进行循环逻辑删除
    void deleteYssAccountListByListId(List<Integer> idList);

}

