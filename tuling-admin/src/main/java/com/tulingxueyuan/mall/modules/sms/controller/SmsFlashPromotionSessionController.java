package com.tulingxueyuan.mall.modules.sms.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.dto.SmsFlashPromotionSessionDetail;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotion;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionSession;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 限时购场次表 前端控制器
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
@RestController
@Api(tags = "SmsFlashPromotionSessionController", description = "秒杀限时购场次管理")
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {

    /**
     * 秒杀获取全部场次
     * url: '/flashSession/list',
     *     method: 'get',
     *     params: params
     */
    @Autowired
    private SmsFlashPromotionSessionService flashPromotionSessionService;

    @ApiOperation("获取全部秒杀场次")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsFlashPromotionSession>> list() {
        List<SmsFlashPromotionSession> promotionSessionList = flashPromotionSessionService.list();
        return CommonResult.success(promotionSessionList);
    }

    /**
     * 获取全部可选秒杀场次及其数量
     * url: '/flashSession/selectList',
     *     method: 'get',
     *     params: params
     */
    @ApiOperation("获取全部可选秒杀场次及其数量")
    @RequestMapping(value = "/selectList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsFlashPromotionSessionDetail>> selectList(Long flashPromotionId) {
        List<SmsFlashPromotionSessionDetail> promotionSessionList = flashPromotionSessionService.selectList(flashPromotionId);
        return CommonResult.success(promotionSessionList);
    }

    /**
     * 修改秒杀启用状态
     * url: '/flashSession/update/status/' + id,
     *     method: 'post',
     *     params: params
     */
    @ApiOperation("修改秒杀启用状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id, Integer status) {
        int count = flashPromotionSessionService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 添加秒杀场次
     * url: '/flashSession/create',
     *     method: 'post',
     *     data: data
     */
    @ApiOperation("添加秒杀场次")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody SmsFlashPromotionSession promotionSession) {
        int count = flashPromotionSessionService.create(promotionSession);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 删除秒杀场次
     * url: '/flashSession/delete/' + id,
     *     method: 'post'
     */
    @ApiOperation("删除场秒杀次")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = flashPromotionSessionService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 修改秒杀场次
     * url: '/flashSession/update/' + id,
     *     method: 'post',
     *     data: data
     */
    @ApiOperation("修改秒杀场次")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody SmsFlashPromotionSession promotionSession) {
        int count = flashPromotionSessionService.update(id, promotionSession);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}

