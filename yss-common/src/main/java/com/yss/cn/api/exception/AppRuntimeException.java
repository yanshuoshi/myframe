package com.yss.cn.api.exception;

import com.yss.cn.constants.Constants;
import lombok.Data;

/**
 * @author Shuoshi.Yan
 * @package:com.yss.api.exception
 * @className: 运行异常
 * @description:
 * @date 2020-03-25 11:02
 * @version:V1.0
 * @NOTICE：本内容仅限于xxx有限公司内部传阅,禁止外泄以及用于其他的商业项目
 * @ Copyright  xxx. All rights reserved.
 **/

@Data
public class AppRuntimeException extends RuntimeException {

    private String code;
    private String msg;

    public AppRuntimeException() {
        this.code = Constants.kCode_Fail;
        this.msg = "";
    }

    public AppRuntimeException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AppRuntimeException(String message) {
        this.code = Constants.kCode_Fail;
        this.msg = message;
    }

    @Override
    public String getLocalizedMessage() {
        return msg;
    }

}
