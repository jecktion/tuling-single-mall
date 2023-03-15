package com.tulingxueyuan.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeBrand;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 Mapper 接口
 * </p>
 *
 * @author WGS
 * @since 2023-02-14
 */
public interface SmsHomeBrandMapper extends BaseMapper<SmsHomeBrand> {

    void insertOne(SmsHomeBrand smsHomeBrand);
}
