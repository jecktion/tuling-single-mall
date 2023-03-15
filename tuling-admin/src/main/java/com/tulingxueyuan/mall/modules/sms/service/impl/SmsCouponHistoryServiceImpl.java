package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsCouponHistoryMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsCouponHistory;
import com.tulingxueyuan.mall.modules.sms.service.SmsCouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券使用、领取历史表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-14
 */
@Service
public class SmsCouponHistoryServiceImpl extends ServiceImpl<SmsCouponHistoryMapper, SmsCouponHistory> implements SmsCouponHistoryService {

    @Autowired
    SmsCouponHistoryMapper couponHistoryMapper;

    /**
     * 根据优惠券id，使用状态，订单编号分页获取领取记录
     * @param couponId
     * @param useStatus
     * @param orderSn
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Page<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        Page<SmsCouponHistory> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsCouponHistory> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsCouponHistory> lambda = queryWrapper.lambda();
        if (couponId!=null){
            lambda.eq(SmsCouponHistory::getCouponId,couponId);
        }
        if(useStatus!=null){
            lambda.eq(SmsCouponHistory::getUseStatus,useStatus);
        }
        //对括号中的内容判断是否为null 和 " " 空字符串
        if (!StringUtils.isEmpty(orderSn)){
            lambda.eq(SmsCouponHistory::getOrderSn,orderSn);
        }
        return this.page(page,queryWrapper);
    }
}
