package com.yss.cn.provider.tBaseAuth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.cn.api.io.tBaseAuth.*;
import com.yss.cn.api.result.tBaseAuth.TBaseAuthResult;
import com.yss.cn.api.service.tBaseAuth.TBaseAuthService;
import com.yss.cn.common.utils.BeanUtil;
import com.yss.cn.io.PageListIO;
import com.yss.cn.persistence.dao.TBaseAuthMapper;
import com.yss.cn.persistence.entity.TBaseAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *  服务实现类
 * @author ShuoShi Yan
 * @since 2019-12-23
 */
@Service
@Transactional
public class TBaseAuthServiceImpl implements TBaseAuthService{

    @Resource
    private TBaseAuthMapper tBaseAuthMapper;
    @Override
    public Page queryTBaseAuthPageList(PageListIO io){
        QueryWrapper<TBaseAuth> queryWrapper = new QueryWrapper<>();
        //此处参数处理可以提取一个公共方法
        Map map = io.buildSQLMap();
        if(!StringUtils.isEmpty(map.get("updateTimeRangeEnd"))){
            queryWrapper.le("createTime",io.buildSQLMap().get("updateTimeRangeEnd"));
        }
        if(!StringUtils.isEmpty(map.get("updateTimeRangeStart"))){
            queryWrapper.ge("createTime",io.buildSQLMap().get("updateTimeRangeStart"));
        }
        Page page = tBaseAuthMapper.selectPage(io.setPage(),queryWrapper);
        return page;
    }

    @Override
    public TBaseAuthResult findTBaseAuthResultById(Integer id){
        return BeanUtil.changeBean(tBaseAuthMapper.selectById(id), TBaseAuthResult.class);
    }

    @Override
    public void saveTBaseAuth(TBaseAuthIO io){
        TBaseAuth entity = BeanUtil.changeBean(io, TBaseAuth.class);
        //  TODO 补充set
        tBaseAuthMapper.insert(entity);
    }

    @Override
    public void updateTBaseAuth(TBaseAuthIO io) {
        TBaseAuth entity = BeanUtil.changeBean(io, TBaseAuth.class);
        //  TODO 补充set
        tBaseAuthMapper.updateById(entity);
    }

    @Override
    public void deleteTBaseAuthListByListId(List<Integer> idList){
        //tBaseAuthMapper.deleteTBaseAuthListByListId(idList);
    }


}
