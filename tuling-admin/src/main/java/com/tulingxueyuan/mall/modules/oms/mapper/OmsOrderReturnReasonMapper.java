package com.tulingxueyuan.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;

/**
 * <p>
 * 退货原因表 Mapper 接口
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
public interface OmsOrderReturnReasonMapper extends BaseMapper<OmsOrderReturnReason> {

    int insert(OmsOrderReturnReason record);

    int updateByPrimaryKey(OmsOrderReturnReason returnReason);
}
