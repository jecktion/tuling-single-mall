package com.tulingxueyuan.mall.modules.sms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 新鲜好物表
 * </p>
 *
 * @author WGS
 * @since 2023-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_home_new_product")
@ApiModel(value="SmsHomeNewProduct对象", description="新鲜好物表")
public class SmsHomeNewProduct implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private String productName;

    private Integer recommendStatus;

    private Integer sort;


}
