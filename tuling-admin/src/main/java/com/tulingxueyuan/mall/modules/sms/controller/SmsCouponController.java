package com.tulingxueyuan.mall.modules.sms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.dto.SmsCouponParam;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;
import com.tulingxueyuan.mall.modules.sms.service.SmsCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠卷表 前端控制器
 * </p>
 *
 * @author WGS
 * @since 2023-02-14
 */
@RestController
@Api(tags = "SmsCouponController", description = "优惠券管理")
@RequestMapping("/coupon")
public class SmsCouponController {

    @Autowired
    private SmsCouponService couponService;

    /**
     * 根据优惠券名称和类型分页获取优惠券列表
     * url:'/coupon/list',
     *     method:'get',
     *     params:params
     */
    @ApiOperation("根据优惠券名称和类型分页获取优惠券列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult list(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "type",required = false) Integer type,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<SmsCoupon> couponList = couponService.list(name,type,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(couponList));
    }

    /**
     * 获取单个优惠券的详细信息
     * url:'/coupon/'+id,
     *     method:'get',
     */
    @ApiOperation("获取单个优惠券的详细信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<SmsCouponParam> getItem(@PathVariable Long id) {
        SmsCouponParam couponParam = couponService.getItem(id);
        return CommonResult.success(couponParam);
    }

    /**
     * 添加优惠券
     * url:'/coupon/create',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("添加优惠券")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody SmsCouponParam couponParam) {
        int count = couponService.create(couponParam);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 修改优惠券
     *  url:'/coupon/update/'+id,
     *     method:'post',
     *     data:data
     */
    @ApiOperation("修改优惠券")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,@RequestBody SmsCouponParam couponParam) {
        int count = couponService.update(id,couponParam);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 删除优惠券
     * url:'/coupon/delete/'+id,
     *     method:'post',
     */
    @ApiOperation("删除优惠券")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = couponService.delete(id);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


}

