package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendProduct;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
public interface SmsHomeRecommendProductService extends IService<SmsHomeRecommendProduct> {

    Page<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    int updateSort(Long id, Integer sort);

    int create(List<SmsHomeRecommendProduct> homeBrandList);

    int delete(List<Long> ids);
}
