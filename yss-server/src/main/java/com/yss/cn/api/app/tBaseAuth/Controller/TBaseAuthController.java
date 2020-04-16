package com.yss.cn.api.app.tBaseAuth.Controller;


import com.yss.api.io.pc.tBaseAuth.TBaseAuthIO;
import com.yss.api.io.pc.tBaseAuth.TBaseAuthListFromIO;
import com.yss.api.result.pc.tBaseAuth.TBaseAuthListResult;
import com.yss.api.result.pc.tBaseAuth.TBaseAuthResult;
import com.yss.api.service.pc.tBaseAuth.TBaseAuthService;
import com.yss.io.PageListIO;
import com.yss.io.SimpleIO;
import com.yss.io.SimpleListIO;
import com.yss.results.ApiResult;
import com.yss.results.FormListResult;
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
@RestController
@Slf4j
@Api(value = "[后台]TBaseAuthController",description = "TBaseAuthController")
@RequestMapping("/tBaseAuth")
public class TBaseAuthController {

    @Autowired
    private TBaseAuthService tBaseAuthService;

    @ApiOperation(value = "tBaseAuth列表",notes="tBaseAuth列表",response = TBaseAuthListResult.class)
    @PostMapping("/tBaseAuthList")
    public ApiResult tBaseAuthList(@Valid @ApiParam(required = true) @RequestBody PageListIO<TBaseAuthListFromIO> body) {
        FormListResult<TBaseAuthResult> result = tBaseAuthService.queryTBaseAuthPageList(body);
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
