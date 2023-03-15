package com.tulingxueyuan.mall.modules.pms.mapper;

import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * sku的库存 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2021-02-26
 */
public interface PmsSkuStockMapper extends BaseMapper<PmsSkuStock> {
    /**
     * 批量更新库存信息
     * @param skuStockList
     * @return
     */
    int insertList(List<PmsSkuStock> skuStockList);
}
