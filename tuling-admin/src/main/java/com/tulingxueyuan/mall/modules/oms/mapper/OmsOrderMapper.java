package com.tulingxueyuan.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.oms.dto.OmsOrderDeliveryParam;
import com.tulingxueyuan.mall.modules.oms.dto.OmsOrderDetail;
import com.tulingxueyuan.mall.modules.oms.dto.OmsOrderQueryParam;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2023-02-05
 */
@Mapper
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {

    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    OmsOrderDetail getDetail(@Param("id")Long id);

    int updateByPrimaryKeySelective(OmsOrder order);


}
