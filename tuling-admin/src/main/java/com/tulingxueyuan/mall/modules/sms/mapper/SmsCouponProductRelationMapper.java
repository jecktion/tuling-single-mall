package com.tulingxueyuan.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponProductRelation;

import java.util.List;

/**
 * <p>
 * 优惠券和产品的关系表 Mapper 接口
 * </p>
 *
 * @author WGS
 * @since 2023-02-14
 */
public interface SmsCouponProductRelationMapper extends BaseMapper<SmsCouponProductRelation> {

    void insertList(List<SmsCouponProductRelation> productRelationList);
}
