package com.tulingxueyuan.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.sms.dto.SmsCouponParam;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;

/**
 * <p>
 * 优惠卷表 Mapper 接口
 * </p>
 *
 * @author WGS
 * @since 2023-02-14
 */
public interface SmsCouponMapper extends BaseMapper<SmsCoupon> {

    SmsCouponParam getItem(Long id);


    int updateByPrimaryKey(SmsCouponParam couponParam);

    int deleteByPrimaryKey(Long id);
}
