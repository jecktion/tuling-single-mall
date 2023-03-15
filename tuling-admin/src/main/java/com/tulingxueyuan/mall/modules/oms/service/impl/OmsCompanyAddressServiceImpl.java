package com.tulingxueyuan.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsCompanyAddressMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsCompanyAddress;
import com.tulingxueyuan.mall.modules.oms.service.OmsCompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公司收发货地址表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-12
 */
@Service
public class OmsCompanyAddressServiceImpl extends ServiceImpl<OmsCompanyAddressMapper, OmsCompanyAddress> implements OmsCompanyAddressService {

    @Autowired
    private OmsCompanyAddressMapper companyAddressMapper;

    /**
     * 获取所有收货地址
     * @return
     */
    @Override
    public List<OmsCompanyAddress> list() {
        QueryWrapper<OmsCompanyAddress> queryWrapper = new QueryWrapper<>();
        return companyAddressMapper.selectList(queryWrapper);
    }
}
