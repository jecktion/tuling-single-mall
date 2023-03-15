package com.tulingxueyuan.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionSession;

/**
 * <p>
 * 限时购场次表 Mapper 接口
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
public interface SmsFlashPromotionSessionMapper extends BaseMapper<SmsFlashPromotionSession> {

    int deleteByPrimaryKey(Long id);

    int insertOne(SmsFlashPromotionSession promotionSession);

    int updateByPrimaryKey(SmsFlashPromotionSession promotionSession);
}
