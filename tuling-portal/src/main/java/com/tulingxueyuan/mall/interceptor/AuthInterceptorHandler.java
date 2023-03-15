package com.tulingxueyuan.mall.interceptor;



import org.springframework.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.common.exception.BusinessException;
import com.tulingxueyuan.mall.config.properties.JwtProperties;
import com.tulingxueyuan.mall.util.JwtKit;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * @author ：wgs
 * @date ：Created in 2020/2/14
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
@Slf4j
public class AuthInterceptorHandler implements HandlerInterceptor {

    @Autowired
    private JwtKit jwtKit;

    @Autowired
    private JwtProperties jwtProperties;

    public final static String GLOBAL_JWT_USER_INFO="jwttoken:usermember:info";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入前置拦截器");
        String message = null;
        String authorization = request.getHeader(jwtProperties.getTokenHeader());
        log.info("authorization:"+authorization);
        //校验token
        if(!StringUtils.isEmpty(authorization)
                && authorization.startsWith(jwtProperties.getTokenHead())){
            String authToken = authorization.substring(jwtProperties.getTokenHead().length());

            Claims claims = null;
            try {
                //解析jwt-token
                claims = jwtKit.parseJwtToken(authToken);
                if(claims != null){
                    request.setAttribute(GLOBAL_JWT_USER_INFO,claims);
                    return true;
                }
            } catch (BusinessException e) {
                log.error(message = (e.getMessage()+":"+authToken));
            }
        }

        /*if(!ObjectUtils.isEmpty(request.getSession().getAttribute("member"))){
            return true;
        }*/
        print(response,"您没有权限访问！请先登录.");
        return false;
    }

    protected void print(HttpServletResponse response,String message) throws Exception{
        /**
         * 设置响应头
         */
        response.setHeader("Content-Type","application/json");
        response.setCharacterEncoding("UTF-8");
        String result = new ObjectMapper().writeValueAsString(CommonResult.forbidden(message));
        response.getWriter().print(result);

    }
//    private static final List<String> WHITE_LIST = Arrays.asList("/login", "/register", "/user/**");
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Method method = handlerMethod.getMethod();
//            String uri = request.getRequestURI();
//            if(WHITE_LIST.contains(uri)) {
//                return true;
//            }
//            // 校验用户是否登录
//            String token = request.getHeader("Authorization");
//            if(token == null || "".equals(token)) {
//                throw new ApiException(ResultCode.UNAUTHORIZED);
//            }
//            Long userId = jwtKit.verifyAndGetUserId(token);
//            if(userId == null) {
//                throw new ApiException(ResultCode.UNAUTHORIZED);
//            }
//            return true;
//        }
//        return true;
//    }

}



