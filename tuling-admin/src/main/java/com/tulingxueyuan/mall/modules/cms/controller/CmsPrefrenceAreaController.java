package com.tulingxueyuan.mall.modules.cms.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.cms.model.CmsPrefrenceArea;
import com.tulingxueyuan.mall.modules.cms.service.CmsPrefrenceAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 优选专区 前端控制器
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
@RestController
@Api(tags = "CmsPrefrenceAreaController", description = "商品优选管理")
@RequestMapping("/prefrenceArea")
public class CmsPrefrenceAreaController {

    @Autowired
    private CmsPrefrenceAreaService prefrenceAreaService;

    /**
     * 获取所有商品优选
     * url:'/prefrenceArea/listAll',
     *     method:'get',
     */
    @ApiOperation("获取所有商品优选")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsPrefrenceArea>> listAll() {
        List<CmsPrefrenceArea> prefrenceAreaList = prefrenceAreaService.listAll();
        return CommonResult.success(prefrenceAreaList);
    }

}

