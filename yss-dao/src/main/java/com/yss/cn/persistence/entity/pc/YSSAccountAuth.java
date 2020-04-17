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
@TableName("yss_account")
public class YSSAccountAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 密码
     */
    private String password;

    /**
     * 联系方式
     */
    private String mobile;
    /**
     * 备注
     */
    private String remark;
    /**
     * 启用/禁用 0 禁用；1 启用
     */
    private String state;

    /**
     * 是否删除标志 0：否  1：是
     */
    @TableField("is_delete")
    private Integer isDelete;


    /**
     * 创建时间（授权时间）
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

}
