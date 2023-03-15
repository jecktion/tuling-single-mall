package com.tulingxueyuan.mall.modules.cms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 优选专区
 * </p>
 *
 * @author WGS
 * @since 2023-02-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_prefrence_area")
@ApiModel(value="CmsPrefrenceArea对象", description="优选专区")
public class CmsPrefrenceArea implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String subTitle;

    @ApiModelProperty(value = "展示图片")
    private byte[] pic;

    private Integer sort;

    private Integer showStatus;


}
