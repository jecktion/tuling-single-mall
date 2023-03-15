package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.sms.dto.SmsCouponParam;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsCouponMapper;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsCouponProductCategoryRelationMapper;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsCouponProductRelationMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponProductCategoryRelation;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponProductRelation;
import com.tulingxueyuan.mall.modules.sms.service.SmsCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠卷表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-14
 */
@Service
public class SmsCouponServiceImpl extends ServiceImpl<SmsCouponMapper, SmsCoupon> implements SmsCouponService {

    @Autowired
    SmsCouponMapper couponMapper;
    @Autowired
    SmsCouponProductRelationMapper productRelationMapper;
    @Autowired
    SmsCouponProductCategoryRelationMapper productCategoryRelationMapper;

    /**
     * 根据优惠券名称和类型分页获取优惠券列表
     * @param name
     * @param type
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Page<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        Page<SmsCoupon> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsCoupon> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.lambda().like(SmsCoupon::getName,name);
        }
        if (type != null){
            queryWrapper.lambda().eq(SmsCoupon::getType,type);
        }
        return this.page(page,queryWrapper);
    }

    /**
     * 添加优惠券
     * @param couponParam
     * @return
     */
    @Override
    public int create(SmsCouponParam couponParam) {
        couponParam.setCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);
        //插入优惠券表
        int count = couponMapper.insert(couponParam);
        //插入优惠券和商品关系表
        if(couponParam.getUseType().equals(2)){
            if (couponParam.getProductRelationList().size()>0) {
                for (SmsCouponProductRelation productRelation : couponParam.getProductRelationList()) {
                    productRelation.setCouponId(couponParam.getId());
                }
                List<SmsCouponProductRelation> list = couponParam.getProductRelationList();
                productRelationMapper.insertList(list);
            }
        }
        //插入优惠券和商品分类关系表
        if(couponParam.getUseType().equals(1)){
            if (couponParam.getProductCategoryRelationList().size()>0) {
                for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                    couponProductCategoryRelation.setCouponId(couponParam.getId());
                }
                List<SmsCouponProductCategoryRelation> list = couponParam.getProductCategoryRelationList();
                productCategoryRelationMapper.insertList(list);
            }
        }
        return count;
    }

    /**
     * 获取单个优惠券的详细信息
     * @param id
     * @return
     */
    @Override
    public SmsCouponParam getItem(Long id) {
        return couponMapper.getItem(id);
    }

    private void deleteProductCategoryRelation(Long id) {
        QueryWrapper<SmsCouponProductCategoryRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SmsCouponProductCategoryRelation::getCouponId,id);
        productCategoryRelationMapper.delete(queryWrapper);
    }

    private void deleteProductRelation(Long id) {
        QueryWrapper<SmsCouponProductRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SmsCouponProductRelation::getCouponId,id);
        productRelationMapper.delete(queryWrapper);
    }

    /**
     * 修改优惠券
     * @param id
     * @param couponParam
     * @return
     */
    @Override
    public int update(Long id, SmsCouponParam couponParam) {
        couponParam.setId(id);
        int count =couponMapper.updateByPrimaryKey(couponParam);
        //删除后插入优惠券和商品关系表
        if(couponParam.getUseType().equals(2)){
            if (couponParam.getProductRelationList().size()>0) {
                for (SmsCouponProductRelation productRelation : couponParam.getProductRelationList()) {
                    productRelation.setCouponId(couponParam.getId());
                }
                deleteProductRelation(id);
                productRelationMapper.insertList(couponParam.getProductRelationList());
            }
        }
        //删除后插入优惠券和商品分类关系表
        if(couponParam.getUseType().equals(1)){
            if (couponParam.getProductCategoryRelationList().size()>0) {
                for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                    couponProductCategoryRelation.setCouponId(couponParam.getId());
                }
                deleteProductCategoryRelation(id);
                productCategoryRelationMapper.insertList(couponParam.getProductCategoryRelationList());
            }
        }
        return count;
    }

    /**
     * 删除优惠券
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        //删除优惠券
        int count = couponMapper.deleteByPrimaryKey(id);
        //删除商品关联
        deleteProductRelation(id);
        //删除商品分类关联
        deleteProductCategoryRelation(id);
        return count;
    }
}
