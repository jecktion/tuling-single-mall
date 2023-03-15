package com.tulingxueyuan.mall.modules.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.cms.model.CmsSubject;
import com.tulingxueyuan.mall.modules.cms.service.CmsSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 专题表 前端控制器
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
@RestController
@Api(tags = "CmsSubjectController", description = "商品专题管理")
@RequestMapping("/subject")
public class CmsSubjectController {

    @Autowired
    private CmsSubjectService subjectService;
    /**
     * 根据专题名称分页获取专题
     * url:'/subject/list',
     *     method:'get',
     *     params:params
     */
    @ApiOperation(value = "根据专题名称分页获取专题")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getList(@RequestParam(value = "keyword", required = false) String keyword,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<CmsSubject> subjectList = subjectService.list(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(subjectList));
    }

    /**
     * 获取全部商品专题
     * url:'/subject/listAll',
     *     method:'get',
     */
    @ApiOperation("获取全部商品专题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsSubject>> listAll() {
        List<CmsSubject> subjectList = subjectService.listAll();
        return CommonResult.success(subjectList);
    }

}

