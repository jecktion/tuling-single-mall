package com.tulingxueyuan.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.oms.dto.OmsOrderReturnApplyResult;
import com.tulingxueyuan.mall.modules.oms.dto.OmsReturnApplyQueryParam;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单退货申请 Mapper 接口
 * </p>
 *
 * @author WGS
 * @since 2023-02-12
 */
public interface OmsOrderReturnApplyMapper extends BaseMapper<OmsOrderReturnApply> {

    List<OmsOrderReturnApply> getList(@Param("queryParam")OmsReturnApplyQueryParam queryParam);

    int updateByPrimaryKeySelective(OmsOrderReturnApply returnApply);

    OmsOrderReturnApplyResult getDetail(Long id);

//    Page<OmsOrderReturnApply> getList(@Param("page")Page<OmsOrderReturnApply> page, @Param("queryParam")OmsReturnApplyQueryParam queryParam);

//    List<OmsOrderReturnApply> getList(@Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize, @Param("queryParam")OmsReturnApplyQueryParam queryParam);
}
