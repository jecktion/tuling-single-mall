package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeBrand;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-14
 */
public interface SmsHomeBrandService extends IService<SmsHomeBrand> {

    Page<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    int create(List<SmsHomeBrand> homeBrandList);

    int updateSort(Long id, Integer sort);

    int delete(List<Long> ids);
}
