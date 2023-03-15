package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotion;

/**
 * <p>
 * 限时购表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
public interface SmsFlashPromotionService extends IService<SmsFlashPromotion> {

    int create(SmsFlashPromotion flashPromotion);

    Page<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum);

    int updateStatus(Long id, Integer status);

    SmsFlashPromotion getItem(Long id);

    int update(Long id, SmsFlashPromotion flashPromotion);

    int delete(Long id);
}
