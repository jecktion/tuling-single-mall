package com.tulingxueyuan.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderSetting;

/**
 * <p>
 * 订单设置表 Mapper 接口
 * </p>
 *
 * @author WGS
 * @since 2023-02-12
 */
public interface OmsOrderSettingMapper extends BaseMapper<OmsOrderSetting> {

    OmsOrderSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKey(OmsOrderSetting orderSetting);
}
