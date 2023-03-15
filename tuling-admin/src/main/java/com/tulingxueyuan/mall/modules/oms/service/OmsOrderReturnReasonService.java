package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
 * <p>
 * 退货原因表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
public interface OmsOrderReturnReasonService extends IService<OmsOrderReturnReason> {

    Page list(Integer pageSize, Integer pageNum);

    OmsOrderReturnReason getItem(Long id);

    int updateStatus(List<Long> ids, Integer status);

    int create(OmsOrderReturnReason returnReason);

    int update(Long id, OmsOrderReturnReason returnReason);

    int delete(List<Long> ids);
}
