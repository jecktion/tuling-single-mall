package com.tulingxueyuan.mall.config;

import com.tulingxueyuan.mall.config.properties.NoAuthUrlProperties;
import com.tulingxueyuan.mall.interceptor.AuthInterceptorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author ：wgs
 * @date ：Created in 2020/2/15
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:  拦截器配置
 **/
@EnableConfigurationProperties({NoAuthUrlProperties.class})
@Configuration
public class IntercepterConfiguration implements WebMvcConfigurer {

    @Autowired
    private NoAuthUrlProperties noAuthUrlProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urls = noAuthUrlProperties.getUrls();
        //注册拦截器
        registry.addInterceptor(authInterceptorHandler())
                .addPathPatterns("/**")
//                .excludePathPatterns(new ArrayList<>(shouldSkipUrls));
                .excludePathPatterns(urls);
    }

    @Bean
    public AuthInterceptorHandler authInterceptorHandler(){
        return new AuthInterceptorHandler();
    }
}
