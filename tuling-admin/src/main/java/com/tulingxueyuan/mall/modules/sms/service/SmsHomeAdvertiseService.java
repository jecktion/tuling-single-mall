package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
public interface SmsHomeAdvertiseService extends IService<SmsHomeAdvertise> {

    Page<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum);

    SmsHomeAdvertise getItem(Long id);

    int create(SmsHomeAdvertise advertise);

    int update(Long id, SmsHomeAdvertise advertise);

    int updateStatus(Long id, Integer status);

    int delete(List<Long> ids);
}
