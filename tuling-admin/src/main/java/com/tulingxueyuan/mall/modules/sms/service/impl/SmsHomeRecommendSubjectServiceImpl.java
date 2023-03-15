package com.tulingxueyuan.mall.modules.sms.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeRecommendSubjectMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendSubject;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeRecommendSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 首页推荐专题表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
@Service
public class SmsHomeRecommendSubjectServiceImpl extends ServiceImpl<SmsHomeRecommendSubjectMapper, SmsHomeRecommendSubject> implements SmsHomeRecommendSubjectService {

    @Autowired
    SmsHomeRecommendSubjectMapper recommendSubjectMapper;
    /**
     * 分页查询推荐
     * @param subjectName
     * @param recommendStatus
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Page<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeRecommendSubject> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsHomeRecommendSubject> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsHomeRecommendSubject> lambda = queryWrapper.lambda();
        if (!StringUtils.isEmpty(subjectName)){
            lambda.like(SmsHomeRecommendSubject::getSubjectName,subjectName);
        }
        if (recommendStatus!=null){
            lambda.eq(SmsHomeRecommendSubject::getRecommendStatus,recommendStatus);
        }
        lambda.orderByDesc(SmsHomeRecommendSubject::getSort);

        return this.page(page,queryWrapper);
    }

    /**
     * 批量修改推荐状态
     * @param ids
     * @param recommendStatus
     * @return
     */
    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeRecommendSubject recommendSubject = new SmsHomeRecommendSubject();
        recommendSubject.setRecommendStatus(recommendStatus);
        QueryWrapper<SmsHomeRecommendSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SmsHomeRecommendSubject::getId,ids);

        return recommendSubjectMapper.update(recommendSubject,queryWrapper);
    }

    /**
     * 修改推荐排序
     * @param id
     * @param sort
     * @return
     */
    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendSubject recommendSubject = new SmsHomeRecommendSubject();
        recommendSubject.setSort(sort);
        QueryWrapper<SmsHomeRecommendSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SmsHomeRecommendSubject::getId,id);

        return recommendSubjectMapper.update(recommendSubject,queryWrapper);
    }

    /**
     * 添加首页推荐专题
     * @param homeBrandList
     * @return
     */
    @Override
    public int create(List<SmsHomeRecommendSubject> homeBrandList) {
        for (SmsHomeRecommendSubject recommendSubject : homeBrandList) {
            recommendSubject.setRecommendStatus(1);
            recommendSubject.setSort(0);
            recommendSubjectMapper.insertOne(recommendSubject);
        }
        return homeBrandList.size();
    }

    /**
     * 批量删除推荐
     * @param ids
     * @return
     */
    @Override
    public int delete(List<Long> ids) {
        return recommendSubjectMapper.deleteBatchIds(ids);
    }


}
