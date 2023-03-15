package com.tulingxueyuan.mall.modules.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeBrand;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 前端控制器
 * </p>
 *
 * @author WGS
 * @since 2023-02-14
 */
@RestController
@Api(tags = "SmsHomeBrandController", description = "首页品牌管理")
@RequestMapping("/home/brand")
public class SmsHomeBrandController {

    @Autowired
    private SmsHomeBrandService homeBrandService;

    /**
     * 分页查询推荐品牌
     * url:'/home/brand/list',
     *     method:'get',
     *     params:params
     */
    @ApiOperation("分页查询推荐品牌")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult list(@RequestParam(value = "brandName", required = false) String brandName,
                             @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<SmsHomeBrand> homeBrandList = homeBrandService.list(brandName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(homeBrandList));
    }

    /**
     * 批量修改推荐状态
     * url:'/home/brand/update/recommendStatus',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = homeBrandService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 添加首页推荐品牌
     * url:'/home/brand/create',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("添加首页推荐品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody List<SmsHomeBrand> homeBrandList) {
        int count = homeBrandService.create(homeBrandList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     *  修改品牌排序
     * url:'/home/brand/update/sort/'+params.id,
     *     method:'post',
     *     params:params
     */
    @ApiOperation("修改品牌排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateSort(@PathVariable Long id, Integer sort) {
        int count = homeBrandService.updateSort(id, sort);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 批量删除推荐品牌
     * url:'/home/brand/delete',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("批量删除推荐品牌")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = homeBrandService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}

