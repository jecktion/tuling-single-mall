package com.tulingxueyuan.mall.modules.cms.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.cms.mapper.CmsPrefrenceAreaMapper;
import com.tulingxueyuan.mall.modules.cms.model.CmsPrefrenceArea;
import com.tulingxueyuan.mall.modules.cms.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优选专区 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
@Service
public class CmsPrefrenceAreaServiceImpl extends ServiceImpl<CmsPrefrenceAreaMapper, CmsPrefrenceArea> implements CmsPrefrenceAreaService {

    @Autowired
    CmsPrefrenceAreaMapper prefrenceAreaMapper;
    /**
     * 获取所有商品优选
     * @return
     */
    @Override
    public List<CmsPrefrenceArea> listAll() {
        QueryWrapper<CmsPrefrenceArea> queryWrapper = new QueryWrapper<>();
        return prefrenceAreaMapper.selectList(queryWrapper);
    }
}
