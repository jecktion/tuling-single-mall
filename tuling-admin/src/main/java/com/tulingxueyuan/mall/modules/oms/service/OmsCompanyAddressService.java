package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.oms.model.OmsCompanyAddress;

import java.util.List;

/**
 * <p>
 * 公司收发货地址表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-12
 */
public interface OmsCompanyAddressService extends IService<OmsCompanyAddress> {
    /**
     * 获取全部收货地址
     */
    List<OmsCompanyAddress> list();
}
