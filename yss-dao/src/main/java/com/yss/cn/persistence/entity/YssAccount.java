package com.yss.cn.persistence.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;

import javax.persistence.*;

/**
 * 系统用户信息
 * @author ShuoShi Yan
 * @since 2020-04-17
 */
@Table(name = "yss_account")
@Data
public class YssAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 姓名
     */
    @Column(name = "real_name")
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
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * 状态 0/禁用,1/启用
     */
    @Column(name = "state")
    private String state;


}
