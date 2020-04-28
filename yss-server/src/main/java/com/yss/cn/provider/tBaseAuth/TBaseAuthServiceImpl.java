package com.yss.cn.provider.tBaseAuth;

import com.yss.cn.api.io.tBaseAuth.*;
import com.yss.cn.api.result.tBaseAuth.TBaseAuthResult;
import com.yss.cn.api.service.tBaseAuth.TBaseAuthService;
import com.yss.cn.common.utils.BeanUtil;
import com.yss.cn.io.PageListIO;
import com.yss.cn.results.FormListResult;
import com.yss.cn.persistence.dao.TBaseAuthMapper;
import com.yss.cn.persistence.entity.TBaseAuth;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;
import java.util.List;

/**
 *  服务实现类
 * @author ShuoShi Yan
 * @since 2019-12-23
 */
@Service
@Transactional
public class TBaseAuthServiceImpl implements TBaseAuthService {

    @Resource
    private TBaseAuthMapper tBaseAuthMapper;

    @Override
    public FormListResult queryTBaseAuthPageList(PageListIO io){
        PageHelper.startPage(io.currentPage(), io.pageSize());
        Page page = (Page<TBaseAuthResult>) tBaseAuthMapper.queryTBaseAuthList(io.buildSQLMap());
        return new FormListResult<TBaseAuthResult>(page);
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
