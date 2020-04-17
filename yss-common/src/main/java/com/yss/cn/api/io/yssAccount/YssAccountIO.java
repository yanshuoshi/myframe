package com.yss.cn.api.io.yssAccount;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;
import java.io.Serializable;
/**
 * @author ShuoShi Yan
 * @since 2020-04-17
 */
@Data
@ApiModel(value = "YssAccountIO", description = "YssAccountIO描述")
public class YssAccountIO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键ID")
    private Integer id;
    @ApiModelProperty(value = "角色ID")
    private Integer roleId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "姓名")
    private String realName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "联系电话")
    private String mobile;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "是否删除 0/未删除,1/已删除")
    private Integer isDelete;
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "状态 0/禁用,1/启用")
    private String state;
}
