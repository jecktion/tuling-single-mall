package com.tulingxueyuan.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsOrderReturnReasonMapper;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrderReturnReason;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderReturnReasonService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 退货原因表 服务实现类
 * </p>
 *
 * @author WGS
 * @since 2023-02-13
 */
@Service
public class OmsOrderReturnReasonServiceImpl extends ServiceImpl<OmsOrderReturnReasonMapper, OmsOrderReturnReason> implements OmsOrderReturnReasonService {

    @Autowired
    OmsOrderReturnReasonMapper returnReasonMapper;

    /**
     * 分页查询全部退货原因
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Page list(Integer pageSize, Integer pageNum) {
        Page page=new Page(pageNum,pageSize);
        QueryWrapper<OmsOrderReturnReason> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");
        return this.page(page,queryWrapper);
    }

    /**
     * 获取单个退货原因详情信息
     * @param id
     * @return
     */
    @Override
    public OmsOrderReturnReason getItem(Long id) {
        QueryWrapper<OmsOrderReturnReason> reasonQueryWrapper = new QueryWrapper<>();
        reasonQueryWrapper.lambda().eq(OmsOrderReturnReason::getId,id);
        return this.getOne(reasonQueryWrapper);
    }

    /**
     * 修改退货原因启用状态
     * @param ids
     * @param status
     * @return
     */
    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        if(!status.equals(0)&&!status.equals(1)){
            return 0;
        }
        OmsOrderReturnReason omsOrderReturnReason = new OmsOrderReturnReason();
        omsOrderReturnReason.setStatus(status);
        QueryWrapper<OmsOrderReturnReason> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(OmsOrderReturnReason::getId,ids);
        return returnReasonMapper.update(omsOrderReturnReason,queryWrapper);
    }

    /**
     * 添加退货原因
     * @param returnReason
     * @return
     */
    @Override
    public int create(OmsOrderReturnReason returnReason) {
        returnReason.setCreateTime(new Date());
        return returnReasonMapper.insert(returnReason);
    }

    /**
     * 修改退货原因
     * @param id
     * @param returnReason
     * @return
     */
    @Override
    public int update(Long id, OmsOrderReturnReason returnReason) {
        returnReason.setId(id);
        return returnReasonMapper.updateByPrimaryKey(returnReason);
    }

    /**
     * 批量删除退货原因
     * @param ids
     * @return
     */
    @Override
    public int delete(List<Long> ids) {
        QueryWrapper<OmsOrderReturnReason> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(OmsOrderReturnReason::getId,ids);
        return returnReasonMapper.delete(queryWrapper);
    }
}
