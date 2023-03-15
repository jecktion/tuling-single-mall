package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.tulingxueyuan.mall.modules.pms.service.PmsBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2021-02-26
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    PmsBrandService brandService;

    /**
     *  品牌数据列表
     *      在商品中进行共用
     *  url:'/brand/list',
     *     method:'get',
     *     params:params
     */
    @ApiOperation(value = "品牌数据列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult list(
            @RequestParam(value="keyword",defaultValue = "") String keyword,
            @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value="pageSize",defaultValue = "5") Integer pageSize)
    {
        Page page = brandService.list(keyword, pageNum, pageSize);

        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     *添加品牌数据
     * url:'/brand/create',
     *     method:'post',
     *     data:data
     */
    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult create(@RequestBody PmsBrand pmsBrand) {

        boolean result= brandService.createBrand(pmsBrand);
        if(result){
            return CommonResult.success(result);
        }
        else {
            return CommonResult.failed();
        }

    }

    /**
     *  更新显示状态
     *  url:'/brand/update/showStatus',
     *     method:'post',
     *     data: ids,
     *           showStatus,
     */
    @ApiOperation(value = "更新显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,
                                         @RequestParam("showStatus") Integer showStatus) {
        boolean result = brandService.updateShowStatus(ids, showStatus);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    /**
     * 更新厂家制造商状态
     * url:'/brand/update/factoryStatus',
     *     method:'post',
     *     data: ids,factoryStatus
     */
    @ApiOperation(value = "更新厂家制造商状态")
    @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.POST)
    public CommonResult updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("factoryStatus") Integer factoryStatus) {
        boolean result = brandService.updateFactoryStatus(ids, factoryStatus);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    /**
     * 删除品牌
     * url:'/brand/delete/'+id,
     *     method:'get',
     */
    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable("id") Long id) {
        boolean result = brandService.deleteBrand(id);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    /**
     * 根据编号查询品牌信息
     * url:'/brand/'+id,
     *     method:'get',
     */
    @ApiOperation(value = "根据编号查询品牌信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<PmsBrand> getItem(@PathVariable("id") Long id) {
        PmsBrand result = brandService.getBrand(id);
        return CommonResult.success(result);
    }

    /**
     * 更新品牌
     * url:'/brand/update/'+id,
     *     method:'post',
     *     data:data
     */
    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable("id") Long id,
                               @RequestBody PmsBrand pmsBrand) {
        Boolean result = brandService.updateBrand(id, pmsBrand);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }

    }

}

