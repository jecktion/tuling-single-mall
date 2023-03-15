package com.tulingxueyuan.mall.modules.sms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeNewProduct;
import com.tulingxueyuan.mall.modules.sms.service.SmsCouponProductRelationService;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeNewProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 前端控制器
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
@RestController
@Api(tags = "SmsHomeNewProductController", description = "首页新品管理")
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {

    @Autowired
    SmsHomeNewProductService homeNewProductService;

    /**
     * 分页查询推荐
     * url:'/home/newProduct/list',
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
        Page<SmsHomeNewProduct> homeBrandList = homeNewProductService.list(productName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(homeBrandList));
    }

    /**
     * 批量修改推荐状态
     * url:'/home/newProduct/update/recommendStatus',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = homeNewProductService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 修改推荐排序
     *  url:'/home/newProduct/update/sort/'+params.id,
     *     method:'post',
     *     params:params
     */
    @ApiOperation("修改推荐排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateSort(@PathVariable Long id, Integer sort) {
        int count = homeNewProductService.updateSort(id, sort);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 添加首页推荐品牌
     * url:'/home/newProduct/create',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("添加首页推荐品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody List<SmsHomeNewProduct> homeBrandList) {
        int count = homeNewProductService.create(homeBrandList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 批量删除推荐
     * url:'/home/newProduct/delete',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("批量删除推荐")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = homeNewProductService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}

