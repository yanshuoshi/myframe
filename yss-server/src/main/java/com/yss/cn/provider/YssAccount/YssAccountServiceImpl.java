package com.yss.cn.provider.yssAccount;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yss.cn.api.exception.AppRuntimeException;
import com.yss.cn.api.result.yssAccount.AuthLoginResult;
import com.yss.cn.common.auth.TokenResult;
import com.yss.cn.config.util.TokenUtil;
import com.yss.cn.persistence.entity.TBaseAuth;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.yss.cn.results.*;
import com.yss.cn.api.service.yssAccount.YssAccountService;
import com.yss.cn.persistence.dao.YssAccountMapper;
import com.yss.cn.common.utils.*;
import com.yss.cn.io.*;
import com.yss.cn.api.result.yssAccount.YssAccountResult;
import com.yss.cn.api.io.yssAccount.YssAccountIO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public Page queryYssAccountPageList(PageListIO io){
        QueryWrapper<YssAccount> queryWrapper = new QueryWrapper<>();
        //此处参数处理可以提取一个公共方法
        Map map = io.buildSQLMap();
        if(!org.springframework.util.StringUtils.isEmpty(map.get("updateTimeRangeEnd"))){
            queryWrapper.le("create_time",io.buildSQLMap().get("updateTimeRangeEnd"));
        }
        if(!org.springframework.util.StringUtils.isEmpty(map.get("updateTimeRangeStart"))){
            queryWrapper.ge("create_time",io.buildSQLMap().get("updateTimeRangeStart"));
        }
        Page page = yssAccountMapper.selectPage(io.setPage(),queryWrapper);
        return page;
    }

    @Override
    public YssAccountResult findYssAccountResultById(Integer id){
        return BeanUtil.changeBean(yssAccountMapper.selectById(id), YssAccountResult.class);
    }

    @Override
    public void saveYssAccount(YssAccountIO io){
        if (!StringUtils.equals(io.getPassword(), io.getConfirmPassword())) {
            throw new AppRuntimeException("新密码或确认密码必须相同");
        }
        QueryWrapper<YssAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",io.getUserName());
        YssAccount yssAccount = yssAccountMapper.selectOne(queryWrapper);
        if(yssAccount != null){
            throw new AppRuntimeException("用户名已存在");
        }
        YssAccount entity = BeanUtil.changeBean(io, YssAccount.class);
        entity.setPassword(PasswordUtil.createHash(io.getPassword()));
        //  TODO 补充set
        yssAccountMapper.insert(entity);
    }

    @Override
    public void updateYssAccount(YssAccountIO io) {
        YssAccount entity = BeanUtil.changeBean(io, YssAccount.class);
        //  TODO 补充set
        yssAccountMapper.updateById(entity);
    }

    @Override
    public void deleteYssAccountListByListId(List<Integer> idList){
        //yssAccountMapper.deleteYssAccountListByListId(idList);
    }

    @Override
    public AuthLoginResult userSecretLogin(String username, String password, String ip) {
        AuthLoginResult result = new AuthLoginResult();
        QueryWrapper<YssAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        YssAccount yssAccount = yssAccountMapper.selectOne(queryWrapper);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<YssAccount> yssAccountPage =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
        yssAccountPage.setCurrent(0);
            yssAccountPage.setSize(10);
            com.baomidou.mybatisplus.extension.plugins.pagination.Page e =  yssAccountMapper.selectPage(yssAccountPage, null);
            if(yssAccount == null){
            throw new AppRuntimeException("用户名不存在");
        }
        if("0".equals(yssAccount.getState())){
            throw new AppRuntimeException("用户已禁用");
        }
        if(!PasswordUtil.validatePassword(password,yssAccount.getPassword())){
            throw new AppRuntimeException("密码错误");
        }

        TokenResult tokenResult = tokenUtil.createToken(yssAccount.getId(),yssAccount.getUserName(),username);
        YssAccountResult yssAccountResult =  BeanUtil.changeBean(yssAccount,YssAccountResult.class);
        result.setAccountResult(yssAccountResult);
        result.setToken(tokenResult.getToken());
        //可将登陆状态及信息保存导表中
        return result;
    }


}
