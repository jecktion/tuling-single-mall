package com.tulingxueyuan.mall.modules.pms.service;

import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku的库存 服务类
 * </p>
 *
 * @author XuShu
 * @since 2021-02-26
 */
public interface PmsSkuStockService extends IService<PmsSkuStock> {

    List<PmsSkuStock> getList(Long pid, String keyword);

    int update(Long pid, List<PmsSkuStock> skuStockList);
}
