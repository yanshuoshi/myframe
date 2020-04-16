package com.yss.cn.results;

import com.yss.cn.constants.Constants;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yss on 2019/12/12 11:12:23.
 */
@Data//只提供提供get set equalsAndEqualhashCode toStrings RequiredArgsConstructor一个6个方法
public class ApiResult<T> implements Serializable {

    private String code;
    private String msg;
    private T data;

    public ApiResult() {
        this.code = Constants.kCode_Fail;
        this.msg = "";
    }


    public ApiResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    //  默认方法
    public static ApiResult success() {
        return successMsg("请求成功");
    }

    public static ApiResult successMsg(String msg) {
        ApiResult apiBean = new ApiResult(Constants.kCode_Success, msg, null);
        return apiBean;
    }

    public static ApiResult success(Object data, String msg) {
        ApiResult apiBean = new ApiResult(Constants.kCode_Success, msg, data);
        return apiBean;
    }

    public static ApiResult success(Object data) {
        ApiResult apiBean = new ApiResult(Constants.kCode_Success, "", data);
        return apiBean;
    }

    public static ApiResult fail() {
        return fail("请求失败");
    }

    public static ApiResult fail(String msg) {
        ApiResult apiBean = new ApiResult(Constants.kCode_Fail, msg);
        return apiBean;
    }

    public static ApiResult fail(String code, String msg) {
        ApiResult apiBean = new ApiResult(code, msg);
        return apiBean;
    }


    public static ApiResult sessionError() {
        ApiResult apiBean = new ApiResult(Constants.kCode_SessionError, "登陆超时");
        return apiBean;
    }

}
