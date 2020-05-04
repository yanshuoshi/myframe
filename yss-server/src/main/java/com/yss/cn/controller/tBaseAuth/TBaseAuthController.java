package com.yss.cn.controller.tBaseAuth;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yss.cn.api.io.tBaseAuth.*;
import com.yss.cn.api.result.tBaseAuth.TBaseAuthListResult;
import com.yss.cn.api.result.tBaseAuth.TBaseAuthResult;
import com.yss.cn.api.service.tBaseAuth.TBaseAuthService;
import com.yss.cn.auth.AuthToken;
import com.yss.cn.auth.Authorization;
import com.yss.cn.bean.BaseController;
import com.yss.cn.common.auth.TokenResult;
import com.yss.cn.io.PageListIO;
import com.yss.cn.io.SimpleIO;
import com.yss.cn.io.SimpleListIO;
import com.yss.cn.results.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 授权管理-授权表 前端控制器
 * @author ShuoShi Yan
 * @since 2019-12-23
 */
@Api(value = "[后台]TBaseAuthController",description = "[后台]TBaseAuthController")
@RestController
@Slf4j
@RequestMapping("/pc/tBaseAuth")
public class TBaseAuthController  extends BaseController {

    @Autowired
    private TBaseAuthService tBaseAuthService;

    @ApiOperation(value = "tBaseAuth列表",notes="tBaseAuth列表",response = TBaseAuthListResult.class)
    @PostMapping("/tBaseAuthList")
    @Authorization
    public ApiResult tBaseAuthList(@AuthToken TokenResult authToken, @Valid @ApiParam(required = true) @RequestBody PageListIO<TBaseAuthListFromIO> body) {
        Page result = tBaseAuthService.queryTBaseAuthPageList(body);
        return ApiResult.success(result);
    }

    @ApiOperation(value = "tBaseAuth详情",notes="tBaseAuth详情",response = TBaseAuthResult.class)
    @PostMapping("/tBaseAuthDetail")
    public ApiResult tBaseAuthDetail(@ApiParam(required = true) @Valid @RequestBody SimpleIO body) {
        TBaseAuthResult result = tBaseAuthService.findTBaseAuthResultById(NumberUtils.toInt(body.getId()));
        return ApiResult.success(result);
    }

    @ApiOperation(value = "新增tBaseAuth",notes="新增tBaseAuth",response = ApiResult.class)
    @PostMapping("/tBaseAuthAdd")
    public ApiResult tBaseAuthAdd(@ApiParam(required = true) @Valid @RequestBody TBaseAuthIO body) {
        tBaseAuthService.saveTBaseAuth(body);
        return ApiResult.success();
    }

    @ApiOperation(value = "编辑tBaseAuth",notes="编辑tBaseAuth")
    @PutMapping("/tBaseAuthUpdate")
    public ApiResult tBaseAuthUpdate(@ApiParam(required = true) @Valid @RequestBody TBaseAuthIO body) {
        tBaseAuthService.updateTBaseAuth(body);
        return ApiResult.success();
    }

    @ApiOperation(value = "删除tBaseAuth",notes="根据tBaseAuthID删除tBaseAuth信息")
    @PutMapping("/tBaseAuthDelete")
    public ApiResult tBaseAuthDelete(@ApiParam(required = true) @Valid @RequestBody SimpleListIO body) {
        if (CollectionUtils.isNotEmpty(body.getList())) {
            tBaseAuthService.deleteTBaseAuthListByListId(body.getList());
        }
        return ApiResult.success();
    }
}


