package com.tulingxueyuan.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;

/**
 * <p>
 * 首页轮播广告表 Mapper 接口
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
public interface SmsHomeAdvertiseMapper extends BaseMapper<SmsHomeAdvertise> {

    SmsHomeAdvertise selectByPrimaryKey(Long id);

    int insert(SmsHomeAdvertise advertise);

    int updateByPrimaryKeySelective(SmsHomeAdvertise advertise);
}
