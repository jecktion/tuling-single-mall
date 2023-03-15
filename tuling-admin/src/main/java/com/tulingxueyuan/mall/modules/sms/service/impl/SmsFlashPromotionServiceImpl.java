package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsFlashPromotionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsFlashPromotionMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsFlashPromotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 限时购表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
@Service
public class SmsFlashPromotionServiceImpl extends ServiceImpl<SmsFlashPromotionMapper, SmsFlashPromotion> implements SmsFlashPromotionService {

    @Autowired
    SmsFlashPromotionMapper flashPromotionMapper;

    /**
     *添加活动
     * @param flashPromotion
     * @return
     */
    @Override
    public int create(SmsFlashPromotion flashPromotion) {
        flashPromotion.setCreateTime(new Date());
        return flashPromotionMapper.insertOne(flashPromotion);
    }

    /**
     * 根据活动名称分页查询
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Page<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<SmsFlashPromotion> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsFlashPromotion> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)){
            queryWrapper.lambda().like(SmsFlashPromotion::getTitle,keyword);
        }
        return flashPromotionMapper.selectPage(page,queryWrapper);
    }

    /**
     * 获取活动详情
     * @param id
     * @return
     */
    @Override
    public SmsFlashPromotion getItem(Long id) {
        QueryWrapper<SmsFlashPromotion> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SmsFlashPromotion::getId,id);
        return this.getOne(queryWrapper);
    }

    /**
     * 修改上下线状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();
        flashPromotion.setStatus(status);
        QueryWrapper<SmsFlashPromotion> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SmsFlashPromotion::getId,id);
        return flashPromotionMapper.update(flashPromotion,queryWrapper);
    }

    /**
     * 编辑活动信息
     * @param id
     * @param flashPromotion
     * @return
     */
    @Override
    public int update(Long id, SmsFlashPromotion flashPromotion) {
        flashPromotion.setId(id);
        return flashPromotionMapper.updateByPrimaryKey(flashPromotion);
    }

    @Override
    public int delete(Long id) {
        return flashPromotionMapper.deleteByPrimaryKey(id);
    }

}
