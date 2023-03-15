package com.tulingxueyuan.mall.modules.oms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.dto.ProductConditionDTO;
import com.tulingxueyuan.mall.modules.oms.dto.*;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderMapper;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderOperateHistoryMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderOperateHistory;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2023-02-05
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements OmsOrderService{

    @Autowired
    OmsOrderMapper orderMapper;
    @Autowired
    OmsOrderOperateHistoryMapper orderOperateHistoryMapper;


    @Override
    public Page<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        Page<OmsOrder> page = new Page<>(pageNum,pageSize);
        QueryWrapper<OmsOrder> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<OmsOrder> lambdaWrapper = queryWrapper.lambda();
        if (!StrUtil.isBlank(queryParam.getOrderSn()) && queryParam.getOrderSn()!=""){
            lambdaWrapper.eq(OmsOrder::getOrderSn,queryParam.getOrderSn());
        }
        if (queryParam.getStatus()!=null){
            lambdaWrapper.eq(OmsOrder::getStatus,queryParam.getStatus());
        }
        if (queryParam.getSourceType()!=null){
            lambdaWrapper.eq(OmsOrder::getSourceType,queryParam.getSourceType());
        }
        if (queryParam.getOrderType()!=null){
            lambdaWrapper.eq(OmsOrder::getOrderType,queryParam.getOrderType());
        }
        if (queryParam.getCreateTime()!=null && queryParam.getCreateTime()!=""){
            lambdaWrapper.like(OmsOrder::getCreateTime,queryParam.getCreateTime());
        }
        if (queryParam.getReceiverKeyword()!=null && queryParam.getReceiverKeyword()!=""){
            lambdaWrapper
                    .like(OmsOrder::getReceiverName,queryParam.getReceiverKeyword())
                    .or()
                    .like(OmsOrder::getReceiverPhone,queryParam.getReceiverKeyword());
        }
        return this.page(page,lambdaWrapper);
//        PageHelper.startPage(pageNum, pageSize);
//        return orderMapper.getList(queryParam);
    }

    @Override
    public int close(List<Long> ids, String note) {
        OmsOrder record = new OmsOrder();
        record.setStatus(4);
        UpdateWrapper<OmsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(OmsOrder::getDeleteStatus,0)
                .in(OmsOrder::getId,ids);
        int count = orderMapper.update(record,updateWrapper);
        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(new Date());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭:"+note);
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryMapper.insertList(historyList);
        return count;
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrder record = new OmsOrder();
        record.setDeleteStatus(1);
        QueryWrapper<OmsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .in(OmsOrder::getId,ids)
                .eq(OmsOrder::getDeleteStatus,0);
        int count = orderMapper.update(record, queryWrapper);
        return count;
    }

    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        //批量发货
        int count = orderMapper.delivery(deliveryParamList);
        //添加操作记录
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime(new Date());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    return history;
                }).collect(Collectors.toList());
        orderOperateHistoryMapper.insertList(operateHistoryList);
        return count;
    }

    /**
     * 获取订单详情:订单信息、商品信息、操作记录
     * @param id
     * @return
     */
    @Override
    public OmsOrderDetail detail(Long id) {

        return orderMapper.getDetail(id);
    }

    /**
     * 修改收货人信息
     * @param receiverInfoParam
     * @return
     */
    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        //修改更新操作
        OmsOrder order = new OmsOrder();
        order.setId(receiverInfoParam.getOrderId());
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
        order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
        order.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
        order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
        order.setReceiverCity(receiverInfoParam.getReceiverCity());
        order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        orderOperateHistoryMapper.insertOne(history);
        return count;

    }

    /**
     * 修改订单费用信息
     * @param moneyInfoParam
     * @return
     */
    @Override
    public int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) {
        //修改更新操作
        OmsOrder order = new OmsOrder();
        order.setId(moneyInfoParam.getOrderId());
        order.setFreightAmount(moneyInfoParam.getFreightAmount());
        order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(moneyInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(moneyInfoParam.getStatus());
        history.setNote("修改费用信息");
        orderOperateHistoryMapper.insertOne(history);
        return count;

    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        //修改更新操作
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息："+note);
        orderOperateHistoryMapper.insertOne(history);
        return count;
    }

}
