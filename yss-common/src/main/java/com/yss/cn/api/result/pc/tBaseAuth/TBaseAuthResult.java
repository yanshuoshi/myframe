package com.yss.cn.api.result.pc.tBaseAuth;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Shuoshi.Yan
 * @package:com.yss.modules.result
 * @className:
 * @description:
 * @date 2019-12-12 10:56
 * @version:V1.0
 * @NOTICE：本内容仅限于xxx有限公司内部传阅,禁止外泄以及用于其他的商业项目
 * @ Copyright  xxx. All rights reserved.
 **/
@Data
public class TBaseAuthResult implements Serializable {
    /**
     * 序号
     */
    private Long id;

    /**
     * 编码
     */
    private String code;

    /**
     * 授权方式  字典项 keyword:authMode 分为正常授权和特殊授权
     */
    private String authMode;

    /**
     * 权限类别 字典项 keyword:authType 卡片类型
     */
    private String authTypeCode;

    /**
     * 卡id
     */
    private String authId;

    /**
     * 卡片名称
     */
    private String cardName;

    /**
     * 押金
     */
    private String cardDeposit;

    /**
     * 照片地址或拍照地址
     */
    private String pic;

    /**
     * 是否删除标志 0：否  1：是
     */
    private Integer isDelete;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间（授权时间）
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;


}
