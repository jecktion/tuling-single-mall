package com.tulingxueyuan.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendSubject;

/**
 * <p>
 * 首页推荐专题表 Mapper 接口
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
public interface SmsHomeRecommendSubjectMapper extends BaseMapper<SmsHomeRecommendSubject> {

    void insertOne(SmsHomeRecommendSubject recommendSubject);
}
