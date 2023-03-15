package com.tulingxueyuan.mall.modules.oms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.dto.*;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2023-02-05
 */
@Controller
@RestController
@Api(tags = "OmsOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsOrderController {

    /**
     *
     *  url:'/order/list',
     *     method:'get',
     *     params:params
     */
    @Autowired
    OmsOrderService orderService;

    @ApiOperation("查询订单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult list(OmsOrderQueryParam queryParam,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<OmsOrder> orderList = orderService.list(queryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(orderList));

    }

    /**
     *  批量关闭订单
     *  url:'/order/update/close',
     *     method:'post',
     *     params:params
     */
    @ApiOperation("批量关闭订单")
    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        int count = orderService.close(ids, note);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 批量删除订单
     * url:'/order/delete',
     *     method:'post',
     *     params:params
     */
    @ApiOperation("批量删除订单")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = orderService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 批量发货
     * url:'/order/update/delivery',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("批量发货")
    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delivery(@RequestBody List<OmsOrderDeliveryParam> deliveryParamList) {
        int count = orderService.delivery(deliveryParamList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 获取订单详情:订单信息、商品信息、操作记录
     * url:'/order/'+id,
     *     method:'get'url:'/order/'+id,
     *     method:'get'
     */
    @ApiOperation("获取订单详情:订单信息、商品信息、操作记录")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OmsOrderDetail> detail(@PathVariable Long id) {
        OmsOrderDetail orderDetailResult = orderService.detail(id);
        return CommonResult.success(orderDetailResult);
    }

    /**
     * 修改收货人信息
     * url:'/order/update/receiverInfo',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("修改收货人信息")
    @RequestMapping(value = "/update/receiverInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateReceiverInfo(@RequestBody OmsReceiverInfoParam receiverInfoParam) {
        int count = orderService.updateReceiverInfo(receiverInfoParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 修改订单费用信息
     * url:'/order/update/moneyInfo',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("修改订单费用信息")
    @RequestMapping(value = "/update/moneyInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateReceiverInfo(@RequestBody OmsMoneyInfoParam moneyInfoParam) {
        int count = orderService.updateMoneyInfo(moneyInfoParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 备注订单
     * url:'/order/update/note',
     *     method:'post',
     *     params:params
     */
    @ApiOperation("备注订单")
    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateNote(@RequestParam("id") Long id,
                                   @RequestParam("note") String note,
                                   @RequestParam("status") Integer status) {
        int count = orderService.updateNote(id, note, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }




}

