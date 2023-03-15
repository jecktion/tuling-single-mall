package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionProductRelation;

/**
 * <p>
 * 商品限时购与商品关系表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
public interface SmsFlashPromotionProductRelationService extends IService<SmsFlashPromotionProductRelation> {

    long getCount(Long flashPromotionId, Long promotionSessionId);
}
