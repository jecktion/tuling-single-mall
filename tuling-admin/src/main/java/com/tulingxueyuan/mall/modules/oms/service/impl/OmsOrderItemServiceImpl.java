package com.tulingxueyuan.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderItemMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderItemService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-11
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements OmsOrderItemService {

}
