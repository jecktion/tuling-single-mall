package com.tulingxueyuan.mall.modules.sms.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeAdvertiseMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
@Service
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseMapper, SmsHomeAdvertise> implements SmsHomeAdvertiseService {

    @Autowired
    SmsHomeAdvertiseMapper advertiseMapper;
    /**
     * 分页查询广告
     * @param name
     * @param type
     * @param endTime
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Page<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
        Page<SmsHomeAdvertise> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsHomeAdvertise> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsHomeAdvertise> lambda = queryWrapper.lambda();
        if (!StringUtils.isEmpty(name)){
            lambda.like(SmsHomeAdvertise::getName,name);
        }
        if (type!=null){
            lambda.eq(SmsHomeAdvertise::getType,type);
        }
        if (!StringUtils.isEmpty(endTime)){
            String startStr = endTime + " 00:00:00";
            String endStr = endTime + " 23:59:59";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = null;
            try {
                start = sdf.parse(startStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date end = null;
            try {
                end = sdf.parse(endStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (start != null && end != null) {
                lambda.between(SmsHomeAdvertise::getEndTime,start,end);
            }
        }
        lambda.orderByDesc(SmsHomeAdvertise::getSort);

        return this.page(page,queryWrapper);
    }

    /**
     * 获取广告详情
     * @param id
     * @return
     */
    @Override
    public SmsHomeAdvertise getItem(Long id) {
        return advertiseMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加广告
     * @param advertise
     * @return
     */
    @Override
    public int create(SmsHomeAdvertise advertise) {
        advertise.setClickCount(0);
        advertise.setOrderCount(0);
        return advertiseMapper.insert(advertise);
    }

    /**
     * 修改广告
     * @param id
     * @param advertise
     * @return
     */
    @Override
    public int update(Long id, SmsHomeAdvertise advertise) {
//        QueryWrapper<SmsHomeAdvertise> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().in(SmsHomeAdvertise::getId,id);
//        return advertiseMapper.update(advertise,queryWrapper);
        advertise.setId(id);
        return advertiseMapper.updateByPrimaryKeySelective(advertise);
    }

    /**
     * 修改上下线状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public int updateStatus(Long id, Integer status) {
        SmsHomeAdvertise advertise = new SmsHomeAdvertise();
        advertise.setStatus(status);
        QueryWrapper<SmsHomeAdvertise> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SmsHomeAdvertise::getId,id);
        return advertiseMapper.update(advertise,queryWrapper);
    }

    /**
     * 删除广告
     * @param ids
     * @return
     */
    @Override
    public int delete(List<Long> ids) {
        return advertiseMapper.deleteBatchIds(ids);
    }
}
