package com.tulingxueyuan.mall.modules.pms.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.tulingxueyuan.mall.modules.pms.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * sku的库存 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2021-02-26
 */
@RestController
@Api(tags = "PmsSkuStockController", description = "sku商品库存管理")
@RequestMapping("/sku")
public class PmsSkuStockController {
    @Autowired
    private PmsSkuStockService skuStockService;
    /**
     * 根据商品编号及编号模糊搜索sku库存
     * url:'/sku/'+pid,
     * method:'get',
     * params:params
     */
    @ApiOperation("根据商品编号及编号模糊搜索sku库存")
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsSkuStock>> getList(@PathVariable Long pid, @RequestParam(value = "keyword",required = false) String keyword) {
        List<PmsSkuStock> skuStockList = skuStockService.getList(pid, keyword);
        if(!skuStockList.isEmpty()){
            return CommonResult.success(skuStockList);
        }else {
            return CommonResult.failed();
        }

    }

    /**
     * 批量更新库存信息
     * url:'/sku/update/'+pid,
     *     method:'post',
     *     data:data
     */
    @ApiOperation("批量更新库存信息")
    @RequestMapping(value ="/update/{pid}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long pid,@RequestBody List<PmsSkuStock> skuStockList){
        int count = skuStockService.update(pid,skuStockList);
        if(count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
}

