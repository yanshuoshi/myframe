package com.yss.cn.persistence.dao.pc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yss.cn.persistence.entity.pc.YSSAccountAuth;
import org.apache.ibatis.annotations.Mapper;

/**
* 授权管理-授权表 Mapper 接口
* @author ShuoShi Yan
* @since 2019-12-23
*/
@Mapper
public interface YSSAccountMapper extends BaseMapper<YSSAccountAuth> {

}
