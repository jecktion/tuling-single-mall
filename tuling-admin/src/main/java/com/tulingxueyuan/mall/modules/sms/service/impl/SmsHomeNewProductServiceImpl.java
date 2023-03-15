package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeNewProductMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeNewProduct;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeNewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

/**
 * <p>
 * 新鲜好物表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
@Service
public class SmsHomeNewProductServiceImpl extends ServiceImpl<SmsHomeNewProductMapper, SmsHomeNewProduct> implements SmsHomeNewProductService {

    @Autowired
    SmsHomeNewProductMapper homeNewProductMapper;

    /**
     * 分页查询推荐
     * @param productName
     * @param recommendStatus
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Page<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeNewProduct> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsHomeNewProduct> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsHomeNewProduct> lambda = queryWrapper.lambda();
        if (!StringUtils.isEmpty(productName)){
            lambda.like(SmsHomeNewProduct::getProductName,productName);
        }
        if (recommendStatus!=null){
            lambda.eq(SmsHomeNewProduct::getRecommendStatus,recommendStatus);
        }
        lambda.orderByDesc(SmsHomeNewProduct::getSort);
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
        SmsHomeNewProduct newProduct = new SmsHomeNewProduct();
        newProduct.setRecommendStatus(recommendStatus);
        QueryWrapper<SmsHomeNewProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SmsHomeNewProduct::getId,ids);
        return homeNewProductMapper.update(newProduct,queryWrapper);
    }

    /**
     * 修改推荐排序
     * @param id
     * @param sort
     * @return
     */
    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeNewProduct newProduct = new SmsHomeNewProduct();
        newProduct.setSort(sort);
        QueryWrapper<SmsHomeNewProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SmsHomeNewProduct::getId,id);

        return homeNewProductMapper.update(newProduct,queryWrapper);
    }

    /**
     * 添加首页推荐品牌
     * @param homeBrandList
     * @return
     */
    @Override
    public int create(List<SmsHomeNewProduct> homeBrandList) {
        for (SmsHomeNewProduct SmsHomeNewProduct : homeBrandList) {
            SmsHomeNewProduct.setRecommendStatus(1);
            SmsHomeNewProduct.setSort(0);
            homeNewProductMapper.insertOne(SmsHomeNewProduct);
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
        return homeNewProductMapper.deleteBatchIds(ids);
    }

}
