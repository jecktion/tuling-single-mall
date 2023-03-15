package com.tulingxueyuan.mall.modules.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.cms.model.CmsSubject;

import java.util.List;

/**
 * <p>
 * 专题表 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
public interface CmsSubjectService extends IService<CmsSubject> {

    Page<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize);

    List<CmsSubject> listAll();
}
