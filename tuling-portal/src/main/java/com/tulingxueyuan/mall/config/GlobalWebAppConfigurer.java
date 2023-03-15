package com.tulingxueyuan.mall.config;

import com.tulingxueyuan.mall.common.util.JwtTokenUtil;
import com.tulingxueyuan.mall.component.TradePayProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限验证拦截器
 */
@Configuration
public class GlobalWebAppConfigurer implements WebMvcConfigurer {

    @Autowired
    TradePayProp tradePayProp;



    // 将物理文件夹中的支付二维码映射为静态资源路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(tradePayProp.getHttpBasePath()+"/**")
                .addResourceLocations("file:"+tradePayProp.getStorePath()+"/");
    }




}
