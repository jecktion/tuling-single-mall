package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponHistory;

/**
 * <p>
 * 优惠券使用、领取历史表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-14
 */
public interface SmsCouponHistoryService extends IService<SmsCouponHistory> {

    Page<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum);
}
