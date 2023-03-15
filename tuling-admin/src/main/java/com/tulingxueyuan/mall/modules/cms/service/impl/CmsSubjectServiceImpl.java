package com.tulingxueyuan.mall.modules.cms.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.cms.mapper.CmsSubjectMapper;
import com.tulingxueyuan.mall.modules.cms.model.CmsSubject;
import com.tulingxueyuan.mall.modules.cms.service.CmsSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 专题表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
@Service
public class CmsSubjectServiceImpl extends ServiceImpl<CmsSubjectMapper, CmsSubject> implements CmsSubjectService {

    @Autowired
    CmsSubjectMapper subjectMapper;
    /**
     * 根据专题名称分页获取专题
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize) {
        Page<CmsSubject> page = new Page<>(pageNum, pageSize);
        QueryWrapper<CmsSubject> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)){
            queryWrapper.lambda().like(CmsSubject::getTitle,keyword);
        }
        return this.page(page,queryWrapper);
    }

    /**
     * 获取全部商品专题
     * @return
     */
    @Override
    public List<CmsSubject> listAll() {
        QueryWrapper<CmsSubject> queryWrapper = new QueryWrapper<>();
        return subjectMapper.selectList(queryWrapper);
    }


}
