package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeNewProduct;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
public interface SmsHomeNewProductService extends IService<SmsHomeNewProduct> {

    Page<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    int updateSort(Long id, Integer sort);

    int create(List<SmsHomeNewProduct> homeBrandList);

    int delete(List<Long> ids);
}
