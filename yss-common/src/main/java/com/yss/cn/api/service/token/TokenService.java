package com.yss.cn.api.service.token;

import com.yss.cn.common.auth.TokenResult;

/**
 * @author:Shuoshi.Yan
 * @description:token
 * @date: 2020/4/10 10:17
 */
public interface TokenService {

    /**
     * 检查token是否有效
     *
     * @param token
     * @return
     */
    TokenResult checkToken(String tokenPrefix, String token);
}
