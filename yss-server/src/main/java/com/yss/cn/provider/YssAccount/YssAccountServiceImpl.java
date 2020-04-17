package com.yss.cn.provider.yssAccount;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.yss.cn.results.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yss.cn.api.service.yssAccount.YssAccountService;
import com.yss.cn.persistence.dao.YssAccountMapper;
import com.yss.cn.common.utils.*;
import com.yss.cn.io.*;
import com.yss.cn.api.result.yssAccount.YssAccountResult;
import com.yss.cn.api.io.yssAccount.YssAccountIO;
import java.util.List;
import com.yss.cn.persistence.entity.YssAccount;

/**
 * 系统用户信息服务实现类
 * @author ShuoShi Yan
 * @since 2020-04-17
 */
@Service
@Transactional
public class YssAccountServiceImpl implements YssAccountService {

    @Autowired
    private YssAccountMapper yssAccountMapper;

    @Override
    public FormListResult queryYssAccountPageList(PageListIO io){
        PageHelper.startPage(io.currentPage(), io.pageSize());
        Page page = (Page<YssAccountResult>) yssAccountMapper.queryYssAccountList(io.buildSQLMap());
        return new FormListResult<YssAccountResult>(page);
    }

    @Override
    public YssAccountResult findYssAccountResultById(Integer id){
        return BeanUtil.mapToBean(yssAccountMapper.selectById(id), YssAccountResult.class);
    }

    @Override
    public void saveYssAccount(YssAccountIO io){
        YssAccount entity = BeanUtil.mapToBean(io, YssAccount.class);
        //  TODO 补充set
        yssAccountMapper.insert(entity);
    }

    @Override
    public void updateYssAccount(YssAccountIO io) {
        YssAccount entity = BeanUtil.mapToBean(io, YssAccount.class);
        //  TODO 补充set
        yssAccountMapper.updateById(entity);
    }

    @Override
    public void deleteYssAccountListByListId(List<Integer> idList){
        //yssAccountMapper.deleteYssAccountListByListId(idList);
    }


}
