package com.tulingxueyuan.mall.modules.oms.dto;


import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderItem;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderOperateHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单详情信息
 * Created on 2018/10/11.
 */
public class OmsOrderDetail extends OmsOrder {
    @Getter
    @Setter
    private List<OmsOrderItem> orderItemList;
    @Getter
    @Setter
    private List<OmsOrderOperateHistory> historyList;
}
