package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.oms.dto.OmsOrderReturnApplyResult;
import com.tulingxueyuan.mall.modules.oms.dto.OmsReturnApplyQueryParam;
import com.tulingxueyuan.mall.modules.oms.dto.OmsUpdateStatusParam;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnApply;

import java.util.List;

/**
 * <p>
 * 订单退货申请 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-12
 */
public interface OmsOrderReturnApplyService extends IService<OmsOrderReturnApply> {

    List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    int delete(List<Long> ids);

    int updateStatus(Long id, OmsUpdateStatusParam statusParam);

    OmsOrderReturnApplyResult getItem(Long id);
}
