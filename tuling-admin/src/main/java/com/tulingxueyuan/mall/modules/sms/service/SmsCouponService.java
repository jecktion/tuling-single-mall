package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.dto.SmsCouponParam;
import com.tulingxueyuan.mall.modules.sms.model.SmsCoupon;

/**
 * <p>
 * 优惠卷表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-14
 */
public interface SmsCouponService extends IService<SmsCoupon> {

    Page<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum);

    int create(SmsCouponParam couponParam);

    SmsCouponParam getItem(Long id);

    int update(Long id, SmsCouponParam couponParam);

    int delete(Long id);
}
