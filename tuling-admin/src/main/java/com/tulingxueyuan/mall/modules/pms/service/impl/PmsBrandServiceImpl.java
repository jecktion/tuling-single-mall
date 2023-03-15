package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsBrand;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsBrandMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.service.PmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2021-02-26
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;
    /**
     *
     *  品牌数据列表
     * @param keyword 商品名称
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return
     */
    @Override
    public Page list(String keyword, Integer pageNum, Integer pageSize) {

        Page page=new Page(pageNum,pageSize);

        QueryWrapper<PmsBrand> pmsBrandQueryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)){
            pmsBrandQueryWrapper.lambda().like(PmsBrand::getName,keyword);
        }
        pmsBrandQueryWrapper.lambda().orderByAsc(PmsBrand::getSort);

        return this.page(page,pmsBrandQueryWrapper);
    }

    /**
     * 添加品牌数据
     * @param pmsBrand
     * @return
     */
    @Override
    public boolean createBrand(PmsBrand pmsBrand) {
        boolean result = this.save(pmsBrand);
        if(result) {
            return result;
        }
        return false;
    }

    /**
     * 更新显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    @Override
    public boolean updateShowStatus(List<Long> ids, Integer showStatus) {
        UpdateWrapper<PmsBrand> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(PmsBrand::getShowStatus,showStatus)
                .in(PmsBrand::getId,ids);   // where in (ids)
        return this.update(updateWrapper);
    }

    /**
     * 更新品牌制造商状态
     * @param ids
     * @param factoryStatus
     * @return
     */
    @Override
    public boolean updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        UpdateWrapper<PmsBrand> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(PmsBrand::getFactoryStatus,factoryStatus)
                .in(PmsBrand::getId,ids);   // where in (ids)
        return this.update(updateWrapper);
    }

    /**
     * 删除品牌
     * @param id
     * @return
     */
    @Override
    public boolean deleteBrand(Long id) {
        QueryWrapper<PmsBrand> Wrapper = new QueryWrapper<>();
        Wrapper.lambda().eq(PmsBrand::getId,id);   // where in (ids)
        return this.remove(Wrapper);
    }

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新品牌
     * @param id
     * @param pmsBrandParam
     * @return
     */
    @Override
    public Boolean updateBrand(Long id, PmsBrand pmsBrandParam) {
        UpdateWrapper<PmsBrand> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(PmsBrand::getFactoryStatus,pmsBrandParam.getFactoryStatus())
                .set(PmsBrand::getShowStatus,pmsBrandParam.getShowStatus())
                .set(PmsBrand::getName,pmsBrandParam.getName())
                .set(PmsBrand::getBigPic,pmsBrandParam.getBigPic())
                .set(PmsBrand::getBrandStory,pmsBrandParam.getBrandStory())
                .set(PmsBrand::getFirstLetter,pmsBrandParam.getFirstLetter())
                .set(PmsBrand::getLogo,pmsBrandParam.getLogo())
                .set(PmsBrand::getProductCommentCount,pmsBrandParam.getProductCommentCount())
                .set(PmsBrand::getProductCount,pmsBrandParam.getProductCount())
                .set(PmsBrand::getSort,pmsBrandParam.getSort())
                .eq(PmsBrand::getId,id);

        return this.update(pmsBrandParam,updateWrapper);
    }


}
