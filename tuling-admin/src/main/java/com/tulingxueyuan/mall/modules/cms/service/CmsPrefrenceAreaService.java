package com.tulingxueyuan.mall.modules.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.cms.model.CmsPrefrenceArea;

import java.util.List;

/**
 * <p>
 * 优选专区 服务类
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
public interface CmsPrefrenceAreaService extends IService<CmsPrefrenceArea> {

    List<CmsPrefrenceArea> listAll();
}
