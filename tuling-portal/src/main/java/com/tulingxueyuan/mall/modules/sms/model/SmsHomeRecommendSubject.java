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
 * 首页推荐专题表
 * </p>
 *
 * @author WGS
 * @since 2023-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_home_recommend_subject")
@ApiModel(value="SmsHomeRecommendSubject对象", description="首页推荐专题表")
public class SmsHomeRecommendSubject implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long subjectId;

    private String subjectName;

    private Integer recommendStatus;

    private Integer sort;


}
