package com.tulingxueyuan.mall.modules.pms.mapper;

import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 品牌表 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2021-02-26
 */
public interface PmsBrandMapper extends BaseMapper<PmsBrand> {


    PmsBrand selectByPrimaryKey(Long id);
}
