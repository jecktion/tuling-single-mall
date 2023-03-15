package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tulingxueyuan.mall.modules.sms.dto.SmsFlashPromotionSessionDetail;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsFlashPromotionSessionMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotionSession;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionProductRelationService;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionSessionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 限时购场次表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
@Service
public class SmsFlashPromotionSessionServiceImpl extends ServiceImpl<SmsFlashPromotionSessionMapper, SmsFlashPromotionSession> implements SmsFlashPromotionSessionService {

    @Autowired
    SmsFlashPromotionSessionMapper flashPromotionSessionMapper;
    @Autowired
    private SmsFlashPromotionProductRelationService relationService;

    /**
     * 获取全部场次
     * @return
     */
    @Override
    public List<SmsFlashPromotionSession> list() {
        QueryWrapper<SmsFlashPromotionSession> queryWrapper = new QueryWrapper<>();
        return flashPromotionSessionMapper.selectList(queryWrapper);
    }

    /**
     * 获取全部可选场次及其数量
     * @param flashPromotionId
     * @return
     */
    @Override
    public List<SmsFlashPromotionSessionDetail> selectList(Long flashPromotionId) {
        QueryWrapper<SmsFlashPromotionSession> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SmsFlashPromotionSession::getStatus,1);
        List<SmsFlashPromotionSession> selectList = flashPromotionSessionMapper.selectList(queryWrapper);
        List<SmsFlashPromotionSessionDetail> result = selectList.stream().map(promotionSession -> {
            SmsFlashPromotionSessionDetail detail = new SmsFlashPromotionSessionDetail();
            BeanUtils.copyProperties(promotionSession, detail);
            long count = relationService.getCount(flashPromotionId, promotionSession.getId());
            detail.setProductCount(count);
            return detail;
        }).collect(Collectors.toList());
        return result;
    }

    /**
     * 修改启用状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotionSession promotionSession = new SmsFlashPromotionSession();
        promotionSession.setStatus(status);
        QueryWrapper<SmsFlashPromotionSession> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SmsFlashPromotionSession::getId,id);
        return flashPromotionSessionMapper.update(promotionSession,queryWrapper);

    }

    /**
     * 删除场次
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        return flashPromotionSessionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加场秒杀次
     * @param promotionSession
     * @return
     */
    @Override
    public int create(SmsFlashPromotionSession promotionSession) {
        promotionSession.setCreateTime(new Date());
        return flashPromotionSessionMapper.insertOne(promotionSession);

    }

    /**
     * 修改秒杀场次
     * @param id
     * @param promotionSession
     * @return
     */
    @Override
    public int update(Long id, SmsFlashPromotionSession promotionSession) {
        promotionSession.setId(id);
        return flashPromotionSessionMapper.updateByPrimaryKey(promotionSession);
    }

}
