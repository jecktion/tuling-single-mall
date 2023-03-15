package com.tulingxueyuan.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponProductCategoryRelation;

import java.util.List;

/**
 * <p>
 * 优惠券和产品分类关系表 Mapper 接口
 * </p>
 *
 * @author WGS
 * @since 2023-02-14
 */
public interface SmsCouponProductCategoryRelationMapper extends BaseMapper<SmsCouponProductCategoryRelation> {

    void insertList(List<SmsCouponProductCategoryRelation> productCategoryRelationList);
}
