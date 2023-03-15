package com.tulingxueyuan.mall.modules.sms.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeRecommendProductMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendProduct;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeRecommendProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
@Service
public class SmsHomeRecommendProductServiceImpl extends ServiceImpl<SmsHomeRecommendProductMapper, SmsHomeRecommendProduct> implements SmsHomeRecommendProductService {

    @Autowired
    SmsHomeRecommendProductMapper recommendProductMapper;
    /**
     * 分页查询推荐
     * @param productName
     * @param recommendStatus
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Page<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeRecommendProduct> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsHomeRecommendProduct> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsHomeRecommendProduct> lambda = queryWrapper.lambda();
        if (!StringUtils.isEmpty(productName)){
            lambda.like(SmsHomeRecommendProduct::getProductName,productName);
        }
        if (recommendStatus!=null){
            lambda.eq(SmsHomeRecommendProduct::getRecommendStatus,recommendStatus);
        }
        lambda.orderByDesc(SmsHomeRecommendProduct::getSort);

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
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setRecommendStatus(recommendStatus);
        QueryWrapper<SmsHomeRecommendProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SmsHomeRecommendProduct::getId,ids);
        return recommendProductMapper.update(recommendProduct,queryWrapper);
    }

    /**
     * 修改推荐排序
     * @param id
     * @param sort
     * @return
     */
    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setSort(sort);
        QueryWrapper<SmsHomeRecommendProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SmsHomeRecommendProduct::getId,id);

        return recommendProductMapper.update(recommendProduct,queryWrapper);
    }

    @Override
    public int create(List<SmsHomeRecommendProduct> homeBrandList) {
        for (SmsHomeRecommendProduct recommendProduct : homeBrandList) {
            recommendProduct.setRecommendStatus(1);
            recommendProduct.setSort(0);
            recommendProductMapper.insertOne(recommendProduct);
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
        return recommendProductMapper.deleteBatchIds(ids);
    }
}
