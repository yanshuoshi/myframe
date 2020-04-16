package com.yss.cn.api.app.token;

import com.yss.cn.api.service.pc.token.TokenService;
import com.yss.cn.common.auth.TokenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenHelper {

    @Autowired
    private TokenService tokenService;

    public TokenResult checkToken(String tokenPrefix, String token) {
        return tokenService.checkToken(tokenPrefix, token);
    }
}
