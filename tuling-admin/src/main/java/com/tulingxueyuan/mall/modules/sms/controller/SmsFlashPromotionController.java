package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotion;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 限时购表 前端控制器
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
@RestController
@Api(tags = "SmsFlashPromotionController", description = "限时购活动管理")
@RequestMapping("/flash")
public class SmsFlashPromotionController {

    @Autowired
    private SmsFlashPromotionService flashPromotionService;

    /**
     * 根据活动名称分页查询
     * url:'/flash/list',
     *     method:'get',
     *     params:params
     */
    @ApiOperation("根据活动名称分页查询")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<SmsFlashPromotion> flashPromotionPage = flashPromotionService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(flashPromotionPage));
    }

    /**
     * 获取活动详情
     *url:'/flash/id',
     *     method:'get',
     *     params:params
     */
    @ApiOperation("获取活动详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@PathVariable Long id) {
        SmsFlashPromotion flashPromotion = flashPromotionService.getItem(id);
        return CommonResult.success(flashPromotion);
    }

    /**
     * 修改上下线状态
     * url:'/flash/update/status/'+id,
     *     method:'post',
     *     params:params
     */
    @ApiOperation("修改上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, Integer status) {
        int count = flashPromotionService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 编辑活动信息
     * url:'/flash/update/'+id,
     *     method:'post',
     *     data:data
     */
    @ApiOperation("编辑活动信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, @RequestBody SmsFlashPromotion flashPromotion) {
        int count = flashPromotionService.update(id, flashPromotion);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 添加活动
     * url:'/flash/create',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("添加活动")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody SmsFlashPromotion flashPromotion) {
        int count = flashPromotionService.create(flashPromotion);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 删除活动信息
     * url:'/flash/delete/'+id,
     *     method:'post'
     */
    @ApiOperation("删除活动信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@PathVariable Long id) {
        int count = flashPromotionService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}

