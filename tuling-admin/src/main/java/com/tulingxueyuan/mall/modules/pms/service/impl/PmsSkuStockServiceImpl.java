package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsSkuStockMapper;
import com.tulingxueyuan.mall.modules.pms.service.PmsSkuStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2021-02-26
 */
@Service
public class PmsSkuStockServiceImpl extends ServiceImpl<PmsSkuStockMapper, PmsSkuStock> implements PmsSkuStockService {
    @Autowired
    PmsSkuStockMapper skuStockMapper;
    /**
     * 根据商品编号及编号模糊搜索sku库存
     * @param pid
     * @param keyword
     * @return
     */
    @Override
    public List<PmsSkuStock> getList(Long pid, String keyword) {
        QueryWrapper<PmsSkuStock> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsSkuStock::getProductId,pid);
        if(!StringUtils.isEmpty(keyword)){
            queryWrapper.lambda().like(PmsSkuStock::getSkuCode,keyword);
        }

        return this.list(queryWrapper);
    }

    /**
     * 批量更新库存信息
     * @param pid
     * @param skuStockList
     * @return
     */
    @Override
    public int update(Long pid, List<PmsSkuStock> skuStockList) {
        return skuStockMapper.insertList(skuStockList);
    }
}
