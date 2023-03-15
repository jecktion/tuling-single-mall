package com.tulingxueyuan.mall.modules.oms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderReturnReasonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 退货原因表 前端控制器
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
@RestController
@Api(tags = "OmsOrderReturnReasonController", description = "退货原因管理")
@RequestMapping("/returnReason")
public class OmsOrderReturnReasonController {

    @Autowired
    private OmsOrderReturnReasonService orderReturnReasonService;

    /**
     * 分页查询全部退货原因
     * url:'/returnReason/list',
     *     method:'get',
     *     params:params
     */
    @ApiOperation("分页查询全部退货原因")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page page = orderReturnReasonService.list(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * 获取单个退货原因详情信息
     *  url:'/returnReason/'+id,
     *     method:'get'
     */
    @ApiOperation("获取单个退货原因详情信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OmsOrderReturnReason> getItem(@PathVariable Long id) {
        OmsOrderReturnReason reason = orderReturnReasonService.getItem(id);
        return CommonResult.success(reason);
    }

    /**
     * 修改退货原因启用状态
     * url:'/returnReason/update/status',
     *     method:'post',
     *     params:params
     */
    @ApiOperation("修改退货原因启用状态")
    @RequestMapping(value = "/update/status", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@RequestParam(value = "status") Integer status,
                                     @RequestParam("ids") List<Long> ids) {
        int count = orderReturnReasonService.updateStatus(ids, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 添加退货原因
     * url:'/returnReason/create',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("添加退货原因")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody OmsOrderReturnReason returnReason) {
        int count = orderReturnReasonService.create(returnReason);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     *  修改退货原因
     *  url:'/returnReason/update/'+id,
     *     method:'post',
     *     data:data
     */
    @ApiOperation("修改退货原因")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody OmsOrderReturnReason returnReason) {
        int count = orderReturnReasonService.update(id, returnReason);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     *
     * url:'/returnReason/delete',
     *     method:'post',
     *     params:params
     */
    @ApiOperation("批量删除退货原因")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = orderReturnReasonService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


}

