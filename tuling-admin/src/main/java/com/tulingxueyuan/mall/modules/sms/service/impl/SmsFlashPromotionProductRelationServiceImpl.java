package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsFlashPromotionProductRelationMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionProductRelation;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionProductRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品限时购与商品关系表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl extends ServiceImpl<SmsFlashPromotionProductRelationMapper, SmsFlashPromotionProductRelation> implements SmsFlashPromotionProductRelationService {

    @Autowired
    private SmsFlashPromotionProductRelationMapper relationMapper;

    /**
     *
     * @param flashPromotionId
     * @param promotionSessionId
     * @return
     */
    @Override
    public long getCount(Long flashPromotionId, Long promotionSessionId) {
//        SmsFlashPromotionProductRelation productRelation = new SmsFlashPromotionProductRelation();
//        productRelation.setFlashPromotionId(flashPromotionId);
//        productRelation.setFlashPromotionSessionId(promotionSessionId);
        QueryWrapper<SmsFlashPromotionProductRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(SmsFlashPromotionProductRelation::getFlashPromotionId,flashPromotionId)
                .eq(SmsFlashPromotionProductRelation::getFlashPromotionSessionId,promotionSessionId);
        return relationMapper.selectCount(queryWrapper);
    }
}
