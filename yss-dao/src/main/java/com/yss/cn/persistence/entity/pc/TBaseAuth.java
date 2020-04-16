package com.yss.cn.persistence.entity.pc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 授权管理-授权表
 * @author ShuoShi Yan
 * @since 2019-12-23
 */
@Data

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_base_auth")
public class TBaseAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 授权方式  字典项 keyword:authMode 分为正常授权和特殊授权
     */
    @TableField("auth_mode")
    private String authMode;

    /**
     * 权限类别 字典项 keyword:authType 卡片类型
     */
    @TableField("auth_type_code")
    private String authTypeCode;

    /**
     * 卡id
     */
    @TableField("auth_id")
    private String authId;

    /**
     * 卡片名称
     */
    @TableField("card_name")
    private String cardName;

    /**
     * 押金
     */
    @TableField("card_deposit")
    private String cardDeposit;

    /**
     * 照片地址或拍照地址
     */
    @TableField("pic")
    private String pic;

    /**
     * 是否删除标志 0：否  1：是
     */
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;

    /**
     * 创建时间（授权时间）
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField("update_user")
    private String updateUser;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
