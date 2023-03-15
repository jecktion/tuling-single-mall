package com.tulingxueyuan.mall.modules.sms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendProduct;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeRecommendProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 前端控制器
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
@RestController
@Api(tags = "SmsHomeRecommendProductController", description = "首页人气推荐管理")
@RequestMapping("/home/recommendProduct")
public class SmsHomeRecommendProductController {

    @Autowired
    SmsHomeRecommendProductService homeRecommendProductService;
    /**
     * 分页查询推荐
     * url:'/home/recommendProduct/list',
     *     method:'get',
     *     params:params
     */
    @ApiOperation("分页查询推荐")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult list(@RequestParam(value = "productName", required = false) String productName,
                             @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<SmsHomeRecommendProduct> homeBrandList = homeRecommendProductService.list(productName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(homeBrandList));
    }

    /**
     * 批量修改推荐状态
     * url:'/home/recommendProduct/update/recommendStatus',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = homeRecommendProductService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 修改推荐排序
     * url:'/home/recommendProduct/update/sort/'+params.id,
     *     method:'post',
     *     params:params
     */
    @ApiOperation("修改推荐排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateSort(@PathVariable Long id, Integer sort) {
        int count = homeRecommendProductService.updateSort(id, sort);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     *
     * url:'/home/recommendProduct/create',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("添加首页推荐")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody List<SmsHomeRecommendProduct> homeBrandList) {
        int count = homeRecommendProductService.create(homeBrandList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 批量删除推荐
     * url:'/home/recommendProduct/delete',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("批量删除推荐")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = homeRecommendProductService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}

