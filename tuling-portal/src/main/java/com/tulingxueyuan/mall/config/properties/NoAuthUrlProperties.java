package com.tulingxueyuan.mall.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author ：杨过
 * @date ：Created in 2020/2/14
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 * secure:
 *   ignored:
 **/

@Data
@ConfigurationProperties(prefix = "secure.ignored")
public class NoAuthUrlProperties {
//    private LinkedHashSet<String> shouldSkipUrls;
    private List<String> urls = new ArrayList<>();
}
