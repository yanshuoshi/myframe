package com.yss.cn.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 授权管理-授权表
 * @author ShuoShi Yan
 * @since 2019-12-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_base_auth")
public class TBaseAuth implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;


}
