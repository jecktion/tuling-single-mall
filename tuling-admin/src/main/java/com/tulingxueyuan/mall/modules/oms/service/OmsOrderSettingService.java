package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderSetting;

/**
 * <p>
 * 订单设置表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-12
 */
public interface OmsOrderSettingService extends IService<OmsOrderSetting> {

    OmsOrderSetting getItem(Long id);

    int update(Long id, OmsOrderSetting orderSetting);
}
