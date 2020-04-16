package com.yss.cn.api.io.pc.tBaseAuth;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ShuoShi Yan
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TBaseAuthIO", description = "TBaseAuthIO描述")
public class TBaseAuthIO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "序号")
    private Long id;
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "授权方式  字典项 keyword:authMode 分为正常授权和特殊授权")
    private String authMode;
    @ApiModelProperty(value = "权限类别 字典项 keyword:authType 卡片类型")
    private String authTypeCode;
    @ApiModelProperty(value = "卡id")
    private String authId;
    @ApiModelProperty(value = "卡片名称")
    private String cardName;
    @ApiModelProperty(value = "押金")
    private String cardDeposit;
    @ApiModelProperty(value = "照片地址或拍照地址")
    private String pic;
    @ApiModelProperty(value = "是否删除标志 0：否  1：是")
    private Integer isDelete;
    @ApiModelProperty(value = "创建人")
    private String createUser;
    @ApiModelProperty(value = "创建时间（授权时间）")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private String updateUser;
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;
}
