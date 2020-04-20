package com.yss.cn.controller.yssAccount;

import com.yss.cn.api.io.yssAccount.YssAccountIO;
import com.yss.cn.io.*;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.RestController;
import com.yss.cn.io.PageListIO;
import com.yss.cn.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import javax.validation.Valid;
import org.apache.commons.collections.CollectionUtils;
import com.yss.cn.api.service.yssAccount.YssAccountService;
import com.yss.cn.api.result.yssAccount.YssAccountResult;
import com.yss.cn.api.io.yssAccount.YssAccountListFromIO;

/**
* 系统用户信息 控制器
* @author ShuoShi Yan
* @since 2020-04-20
*/
@RestController
@Api(value = "[后台]YssAccountController",description = "YssAccountController")
@RequestMapping("/pc/yssAccount")
public class YssAccountController {

    @Autowired
    private YssAccountService yssAccountService;

    @ApiOperation(value = "yssAccount列表",notes="yssAccount列表",response = YssAccountResult.class)
    @PostMapping("/yssAccountList")
    public ApiResult yssAccountList(@Valid @ApiParam(required = true) @RequestBody PageListIO<YssAccountListFromIO> body) {
        FormListResult<YssAccountResult> result = yssAccountService.queryYssAccountPageList(body);
        return ApiResult.success(result);
    }

    @ApiOperation(value = "yssAccount详情",notes="yssAccount详情",response = YssAccountResult.class)
    @PostMapping("/yssAccountDetail")
    public ApiResult yssAccountDetail(@ApiParam(required = true) @Valid @RequestBody SimpleIO body) {
        YssAccountResult result = yssAccountService.findYssAccountResultById(NumberUtils.toInt(body.getId()));
        return ApiResult.success(result);
    }

    @ApiOperation(value = "新增yssAccount",notes="新增yssAccount",response = ApiResult.class)
    @PostMapping("/yssAccountAdd")
    public ApiResult yssAccountAdd(@ApiParam(required = true) @Valid @RequestBody YssAccountIO body) {
        yssAccountService.saveYssAccount(body);
        return ApiResult.success();
    }

    @ApiOperation(value = "编辑yssAccount",notes="编辑yssAccount")
    @PutMapping("/yssAccountUpdate")
    public ApiResult yssAccountUpdate(@ApiParam(required = true) @Valid @RequestBody YssAccountIO body) {
        yssAccountService.updateYssAccount(body);
        return ApiResult.success();
    }

    @ApiOperation(value = "删除yssAccount",notes="根据yssAccountID删除yssAccount信息")
    @PutMapping("/yssAccountDelete")
    public ApiResult yssAccountDelete(@ApiParam(required = true) @Valid @RequestBody SimpleListIO body) {
        if (CollectionUtils.isNotEmpty(body.getList())) {
            yssAccountService.deleteYssAccountListByListId(body.getList());
        }
        return ApiResult.success();
    }
}
