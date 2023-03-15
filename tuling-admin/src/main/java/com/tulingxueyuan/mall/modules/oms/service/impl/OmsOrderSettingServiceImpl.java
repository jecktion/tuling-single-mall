package com.tulingxueyuan.mall.modules.oms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderSettingMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderSetting;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单设置表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-12
 */
@Service
public class OmsOrderSettingServiceImpl extends ServiceImpl<OmsOrderSettingMapper, OmsOrderSetting> implements OmsOrderSettingService{

    @Autowired
    OmsOrderSettingMapper orderSettingMapper;

    /**
     * 获取指定订单设置
     * @param id
     * @return
     */
    @Override
    public OmsOrderSetting getItem(Long id) {
        return orderSettingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, OmsOrderSetting orderSetting) {
        orderSetting.setId(id);
        return orderSettingMapper.updateByPrimaryKey(orderSetting);
    }
}
