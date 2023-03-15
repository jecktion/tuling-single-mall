package com.tulingxueyuan.mall.controller;


import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.EsProductDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@RestController
@Api(tags = "CarController",description = "购物车服务接口")
@RequestMapping("/esProduct")
public class EsProductController {

    /**
     * this.axios.post('/esProduct/search/simple',
     * Qs.stringify({keyword : this.keywords},{indices: false}),
     */
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @ApiOperation(value = "简单搜索")
//    @RequestMapping(value = "/search/simple", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult<CommonPage<EsProductDTO>> search(@RequestParam(required = false) String keyword,
//                                                         @RequestParam(required = false, defaultValue = "0") String pageNum,
//                                                         @RequestParam(required = false, defaultValue = "10") String pageSize) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
//        map.add("keyword", keyword);
//        map.add("pageNum", pageNum);
//        map.add("pageSize", pageSize);
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//        try {
//            ResponseEntity<CommonResult> result = restTemplate.postForEntity("http://localhost:8888/esProduct/search/simple",request,CommonResult.class);
//            CommonResult body = result.getBody();
//            return body;
//        } catch (RestClientException e) {
//            System.out.println("==============================");
//            e.printStackTrace();
//        }
//        return CommonResult.failed();
//    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10000);
        requestFactory.setReadTimeout(10000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
//        int timeout = 10000;
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectionRequestTimeout(timeout);
//        factory.setReadTimeout(timeout);
//        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;

    }

}
