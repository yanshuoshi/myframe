package com.yss.cn.provider.yssAccount;

import com.yss.cn.api.exception.AppRuntimeException;
import com.yss.cn.api.result.yssAccount.AuthLoginResult;
import com.yss.cn.common.auth.TokenResult;
import com.yss.cn.config.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public FormListResult queryYssAccountPageList(PageListIO io){
        PageHelper.startPage(io.currentPage(), io.pageSize());
        Page page = (Page<YssAccountResult>) yssAccountMapper.queryYssAccountList(io.buildSQLMap());
        return new FormListResult<YssAccountResult>(page);
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
        YssAccount yssAccount = yssAccountMapper.queryYssAccountByUserName(io.getUserName());
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
        YssAccount yssAccount = yssAccountMapper.queryYssAccountByUserName(username);
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
