package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.dto.SmsFlashPromotionSessionDetail;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionSession;

import java.util.List;

/**
 * <p>
 * 限时购场次表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
public interface SmsFlashPromotionSessionService extends IService<SmsFlashPromotionSession> {
    /**
     * 根据启用状态获取场次列表
     */
    List<SmsFlashPromotionSession> list();

    List<SmsFlashPromotionSessionDetail> selectList(Long flashPromotionId);

    int updateStatus(Long id, Integer status);

    int delete(Long id);

    int create(SmsFlashPromotionSession promotionSession);

    int update(Long id, SmsFlashPromotionSession promotionSession);
}
