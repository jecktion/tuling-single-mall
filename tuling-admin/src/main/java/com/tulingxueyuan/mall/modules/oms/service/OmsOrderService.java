package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.core.metadata.PageList;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.oms.dto.*;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2023-02-05
 */

public interface OmsOrderService extends IService<OmsOrder> {


    Page<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum);

    int close(List<Long> ids, String note);

    int delete(List<Long> ids);

    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);

    OmsOrderDetail detail(Long id);

    int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);

    int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam);

    int updateNote(Long id, String note, Integer status);
}
