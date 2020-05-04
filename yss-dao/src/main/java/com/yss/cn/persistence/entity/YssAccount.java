package com.yss.cn.persistence.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * 系统用户信息
 * @author ShuoShi Yan
 * @since 2020-04-17
 */
@TableName(value = "yss_account")
@Data
public class YssAccount implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "id",type = AUTO)
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
    private String password;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 备注
     */
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
    private String state;

}
