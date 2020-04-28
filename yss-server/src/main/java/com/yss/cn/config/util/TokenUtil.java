package com.yss.cn.config.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yss.cn.api.exception.AppRuntimeException;
import com.yss.cn.common.auth.TokenResult;
import com.yss.cn.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author:Shuoshi.Yan
 * @description: token
 * @date: 2020/4/21 14:31
 */
@Component
public class TokenUtil {

    private final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    private JWTVerifier verifier;

    @Autowired
//    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    private static String kTokenKey = "yss";

    public TokenResult createToken(Integer userId, String userUuid, String userName) {
        return createToken(Constants.kPrefix_PC, userId, userUuid, userName);
    }

    public TokenResult getToken(String token) {
        return new TokenResult(0, token, token);
    }

    public TokenResult checkToken(String token) {
        return checkToken(Constants.kPrefix_PC, token);
    }

    public void deleteToken(String userUuid) {
        redisTemplate.delete(userUuid);
    }

    public TokenResult createToken(String prefix, Integer userId, String userUuid, String userName) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(Constants.kToken_Secret);
            String token = JWT.create().withExpiresAt(DateUtils.addDays(new Date(), (int) Constants.TOKEN_EXPIRES_DAY)).withClaim("uid", userUuid).withClaim("createTime", new Date()).sign(algorithm);
            TokenResult result = new TokenResult(userId, userUuid, token);
            result.setUserName(userName);
            result.setUserId(userId);
            result.setUserUuid(userUuid);
            redisTemplate.boundValueOps(kTokenKey + ":" + prefix + ":" + userUuid).set(result, Constants.TOKEN_EXPIRES_DAY, TimeUnit.DAYS);
            return result;
        } catch (Exception exception) {
            throw new AppRuntimeException("token生成失败");
        }
    }

    public TokenResult checkToken(String prefix, String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        DecodedJWT jwt = null;
        try {
            if (verifier == null) {
                Algorithm algorithm = Algorithm.HMAC256(Constants.kToken_Secret);
                verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            }
            jwt = verifier.verify(token);
            String uid = jwt.getClaims().get("uid").asString();
            TokenResult result = (TokenResult) redisTemplate.boundValueOps(kTokenKey + ":" + prefix + ":" + uid).get();
            return result;
        } catch (Exception e) {
            logger.error("checkToken error : {}", e.getLocalizedMessage());
            return null;
        }
    }
}
