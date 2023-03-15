package com.tulingxueyuan.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendProduct;

/**
 * <p>
 * 人气推荐商品表 Mapper 接口
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
public interface SmsHomeRecommendProductMapper extends BaseMapper<SmsHomeRecommendProduct> {

    void insertOne(SmsHomeRecommendProduct recommendProduct);
}
