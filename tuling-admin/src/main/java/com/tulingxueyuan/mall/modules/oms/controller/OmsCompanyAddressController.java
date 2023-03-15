package com.tulingxueyuan.mall.modules.oms.controller;


import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.oms.model.OmsCompanyAddress;
import com.tulingxueyuan.mall.modules.oms.service.OmsCompanyAddressService;
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
 * 公司收发货地址表 前端控制器
 * </p>
 *
 * @author WGS
 * @since 2023-02-12
 */
@RestController
@Api(tags = "OmsCompanyAddressController", description = "收货地址管理")
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {

    /**
     * 获取所有收货地址
     * url:'/companyAddress/list',
     *     method:'get'
     */
    @Autowired
    private OmsCompanyAddressService companyAddressService;

    @ApiOperation("获取所有收货地址")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<OmsCompanyAddress>> list() {
        List<OmsCompanyAddress> companyAddressList = companyAddressService.list();
        return CommonResult.success(companyAddressList);
    }


}

