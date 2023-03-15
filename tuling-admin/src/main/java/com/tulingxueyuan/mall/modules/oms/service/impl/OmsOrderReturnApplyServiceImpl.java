package com.tulingxueyuan.mall.modules.oms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.alibaba.druid.util.StringUtils;
import com.tulingxueyuan.mall.modules.oms.dto.OmsOrderReturnApplyResult;
import com.tulingxueyuan.mall.modules.oms.dto.OmsReturnApplyQueryParam;
import com.tulingxueyuan.mall.modules.oms.dto.OmsUpdateStatusParam;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderReturnApplyMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnApply;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderReturnApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单退货申请 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-12
 */
@Service
public class OmsOrderReturnApplyServiceImpl extends ServiceImpl<OmsOrderReturnApplyMapper, OmsOrderReturnApply> implements OmsOrderReturnApplyService {

    @Autowired
    OmsOrderReturnApplyMapper returnApplyMapper;

    @Override
    public List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
//        Page<OmsOrderReturnApply> page = new Page<>(pageNum, pageSize);
//        QueryWrapper<OmsOrderReturnApply> queryWrapper = new QueryWrapper<>();
//        LambdaQueryWrapper<OmsOrderReturnApply> lambda = queryWrapper.lambda();
//        if (queryParam.getId()!=null){
//            lambda.eq(OmsOrderReturnApply::getId,queryParam.getId());
//        }
//        if (!StringUtils.isEmpty(queryParam.getReceiverKeyword())){
//            lambda
//                    .like(OmsOrderReturnApply::getReturnName,queryParam.getReceiverKeyword())
//                    .or()
//                    .like(OmsOrderReturnApply::getReturnPhone,queryParam.getReceiverKeyword());
//        }
//        if (queryParam.getStatus()!=null){
//            lambda.eq(OmsOrderReturnApply::getStatus,queryParam.getStatus());
//        }
//        if (queryParam.getCreateTime()!=null){
//            lambda.like(OmsOrderReturnApply::getCreateTime,queryParam.getCreateTime());
//        }
//        if (!StringUtils.isEmpty(queryParam.getHandleMan())){
//            lambda.eq(OmsOrderReturnApply::getHandleMan,queryParam.getHandleMan());
//        }
//        if (queryParam.getHandleTime()!=null){
//            lambda.like(OmsOrderReturnApply::getHandleTime,queryParam.getHandleTime());
//        }
        PageHelper.startPage(pageNum,pageSize);
        return returnApplyMapper.getList(queryParam);
    }

    /**
     * 批量删除申请
     * @param ids
     * @return
     */
    @Override
    public int delete(List<Long> ids) {
        QueryWrapper<OmsOrderReturnApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(OmsOrderReturnApply::getStatus,3)
                .in(OmsOrderReturnApply::getId,ids);
        return returnApplyMapper.delete(queryWrapper);
    }

    /**
     * 修改申请状态
     * @param id
     * @param statusParam
     * @return
     */
    @Override
    public int updateStatus(Long id, OmsUpdateStatusParam statusParam) {
        Integer status = statusParam.getStatus();
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        if(status.equals(1)){
            //确认退货
            returnApply.setId(id);
            returnApply.setStatus(1);
            returnApply.setReturnAmount(statusParam.getReturnAmount());
            returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else if(status.equals(2)){
            //完成退货
            returnApply.setId(id);
            returnApply.setStatus(2);
            returnApply.setReceiveTime(new Date());
            returnApply.setReceiveMan(statusParam.getReceiveMan());
            returnApply.setReceiveNote(statusParam.getReceiveNote());
        }else if(status.equals(3)){
            //拒绝退货
            returnApply.setId(id);
            returnApply.setStatus(3);
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else{
            return 0;
        }
        return returnApplyMapper.updateByPrimaryKeySelective(returnApply);

    }

    @Override
    public OmsOrderReturnApplyResult getItem(Long id) {
        return returnApplyMapper.getDetail(id);
    }
}
