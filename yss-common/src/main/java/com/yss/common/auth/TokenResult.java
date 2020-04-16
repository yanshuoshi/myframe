package com.yss.common.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yss on 2020/03/25
 */
@Data
public class TokenResult implements Serializable {

    private Integer userId;
    private String userUuid;
    private String userName;
    private String token;

    public TokenResult() {
    }

    public TokenResult(Integer userId, String userUuid) {
        this.userId = userId;
        this.userUuid = userUuid;
    }

    public TokenResult(Integer userId, String userUuid, String token) {
        this.userId = userId;
        this.userUuid = userUuid;
        this.token = token;
    }
}
