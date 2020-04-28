package com.yss.cn.persistence.dao;

import com.yss.cn.persistence.entity.YssAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.*;
import com.yss.cn.api.result.yssAccount.YssAccountResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* 系统用户信息 Mapper 接口
* @author ShuoShi Yan
* @since 2020-04-17
*/
@Mapper
public interface YssAccountMapper extends BaseMapper<YssAccount> {
    //列表查询
    List<YssAccountResult> queryYssAccountList(Map map);

    //删除 根据列表id进行循环逻辑删除
    void deleteYssAccountListByListId(List<Integer> idList);

    @Select("SELECT * FROM yss_account where user_name = #{userName} and is_delete = 0 limit 1")
    YssAccount queryYssAccountByUserName(@Param("userName")String userName);

}
