package com.yss.persistence.dao.pc;

import com.yss.api.result.pc.tBaseAuth.TBaseAuthResult;
import com.yss.persistence.entity.pc.TBaseAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* 授权管理-授权表 Mapper 接口
* @author ShuoShi Yan
* @since 2019-12-23
*/
public interface TBaseAuthMapper extends BaseMapper<TBaseAuth> {
    //列表查询
    List<TBaseAuthResult> queryTBaseAuthList(Map map);

    //删除 根据列表id进行循环逻辑删除
    void deleteTBaseAuthListByListId(List<Integer> idList);

}
