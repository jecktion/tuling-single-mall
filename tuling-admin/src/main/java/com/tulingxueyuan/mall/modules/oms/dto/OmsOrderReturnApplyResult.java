package com.tulingxueyuan.mall.modules.oms.dto;

import com.tulingxueyuan.mall.modules.oms.model.OmsCompanyAddress;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnApply;
import lombok.Getter;
import lombok.Setter;

/**
 * 申请信息封装
 * Created on 2018/10/18.
 */
public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {
    @Getter
    @Setter
    private OmsCompanyAddress companyAddress;
}
