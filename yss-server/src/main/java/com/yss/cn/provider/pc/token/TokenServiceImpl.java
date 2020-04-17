package com.yss.cn.provider.pc.token;

import com.yss.cn.api.service.pc.token.TokenService;
import com.yss.cn.config.util.TokenUtil;
import com.yss.cn.common.auth.TokenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:Shuoshi.Yan
 * @description:token
 * @date: 2020/4/10 10:25
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public TokenResult checkToken(String tokenPrefix, String token) {
        return tokenUtil.checkToken(tokenPrefix, token);
    }

}
