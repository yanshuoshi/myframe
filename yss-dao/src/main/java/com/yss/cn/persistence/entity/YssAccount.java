package com.yss.cn.persistence.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
/**
 * 系统用户信息
 * @author ShuoShi Yan
 * @since 2020-04-17
 */
@TableName("yss_account")
@Data
public class YssAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;

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
    @TableField("password")
    private String password;

    /**
     * 联系电话
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 是否删除 0/未删除,1/已删除
     */
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    /**
     * 状态 0/禁用,1/启用
     */
    @TableField("state")
    private String state;


}
