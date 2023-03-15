package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeAdvertiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 前端控制器
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
@RestController
@Api(tags = "SmsHomeAdvertiseController", description = "首页轮播广告管理")
@RequestMapping("/home/advertise")
public class SmsHomeAdvertiseController {

    @Autowired
    SmsHomeAdvertiseService advertiseService;
    /**
     * 分页查询广告
     * url:'/home/advertise/list',
     *     method:'get',
     *     params:params
     */
    @ApiOperation("分页查询广告")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult list(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "type", required = false) Integer type,
                             @RequestParam(value = "endTime", required = false) String endTime,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        Page<SmsHomeAdvertise> advertiseList = advertiseService.list(name, type, endTime, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(advertiseList));
    }

    /**
     * 获取广告详情
     *  url:'/home/advertise/'+id,
     *     method:'get',
     */
    @ApiOperation("获取广告详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<SmsHomeAdvertise> getItem(@PathVariable Long id) {
        SmsHomeAdvertise advertise = advertiseService.getItem(id);
        return CommonResult.success(advertise);
    }

    /**
     * 添加广告
     * url:'/home/advertise/create',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("添加广告")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody SmsHomeAdvertise advertise) {
        int count = advertiseService.create(advertise);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();

    }

    /**
     * 修改广告
     * url:'/home/advertise/update/'+id,
     *     method:'post',
     *     data:data
     */
    @ApiOperation("修改广告")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody SmsHomeAdvertise advertise) {
        int count = advertiseService.update(id, advertise);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    /**
     * 修改上下线状态
     * url:'/home/advertise/update/status/'+id,
     *     method:'post',
     *     params:params
     */
    @ApiOperation("修改上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id, Integer status) {
        int count = advertiseService.updateStatus(id, status);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    /**
     * 删除广告
     * url:'/home/advertise/delete',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("删除广告")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = advertiseService.delete(ids);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}

