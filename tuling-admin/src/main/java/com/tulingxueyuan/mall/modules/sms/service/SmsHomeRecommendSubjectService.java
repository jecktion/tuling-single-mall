package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeRecommendSubject;

import java.util.List;

/**
 * <p>
 * 首页推荐专题表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
public interface SmsHomeRecommendSubjectService extends IService<SmsHomeRecommendSubject> {

    Page<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    int updateSort(Long id, Integer sort);

    int create(List<SmsHomeRecommendSubject> homeBrandList);

    int delete(List<Long> ids);
}
