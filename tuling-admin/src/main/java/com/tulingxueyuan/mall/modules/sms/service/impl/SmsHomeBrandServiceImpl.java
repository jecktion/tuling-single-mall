package com.tulingxueyuan.mall.modules.sms.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeBrandMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeBrand;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-14
 */
@Service
public class SmsHomeBrandServiceImpl extends ServiceImpl<SmsHomeBrandMapper, SmsHomeBrand> implements SmsHomeBrandService {

    @Autowired
    SmsHomeBrandMapper homeBrandMapper;
    /**
     * 分页查询推荐品牌
     * @param brandName
     * @param recommendStatus
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Page<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeBrand> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsHomeBrand> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsHomeBrand> lambda = queryWrapper.lambda();
        if (!StringUtils.isEmpty(brandName)){
            lambda.like(SmsHomeBrand::getBrandName,brandName);
        }
        if (recommendStatus!=null){
            lambda.eq(SmsHomeBrand::getRecommendStatus,recommendStatus);
        }
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
        SmsHomeBrand smsHomeBrand = new SmsHomeBrand();
        smsHomeBrand.setRecommendStatus(recommendStatus);
        QueryWrapper<SmsHomeBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SmsHomeBrand::getId,ids);
        return homeBrandMapper.update(smsHomeBrand,queryWrapper);
    }

    /**
     * 添加首页推荐品牌
     * @param homeBrandList
     * @return
     */
    @Override
    public int create(List<SmsHomeBrand> homeBrandList) {
        for (SmsHomeBrand smsHomeBrand : homeBrandList) {
            smsHomeBrand.setRecommendStatus(1);
            smsHomeBrand.setSort(0);
            homeBrandMapper.insertOne(smsHomeBrand);
        }
        return homeBrandList.size();
    }

    /**
     * 修改品牌排序
     * @param id
     * @param sort
     * @return
     */
    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setSort(sort);
        QueryWrapper<SmsHomeBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SmsHomeBrand::getId,id);
        return homeBrandMapper.update(homeBrand,queryWrapper);
    }

    /**
     * 批量删除推荐品牌
     * @param ids
     * @return
     */
    @Override
    public int delete(List<Long> ids) {
        return homeBrandMapper.deleteBatchIds(ids);
    }

}
