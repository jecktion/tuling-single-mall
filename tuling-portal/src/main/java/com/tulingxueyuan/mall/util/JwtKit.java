package com.tulingxueyuan.mall.util;

import com.tulingxueyuan.mall.common.exception.BusinessException;
import com.tulingxueyuan.mall.config.properties.JwtProperties;
import com.tulingxueyuan.mall.common.domain.UmsMember;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;



/**
 * @author ：wgs
 * @date ：Created in 2022/3/25
 * @version: V1.0
 * @slogan:
 * @description:
 **/
@Component
public class JwtKit {


    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 创建jwtToken
     * @param member
     * @return
     */

    public String generateJwtToken(UmsMember member){
        Map<String,Object> claims = new HashMap<>();

        claims.put("sub",member.getUsername());
        claims.put("createdate",new Date());
        claims.put("id",member.getId());
        claims.put("memberLevelId",member.getMemberLevelId());

        return Jwts.builder()
                //.addClaims(claims) 上课异常原因,改成下面的方式
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()*1000))
                .signWith(SignatureAlgorithm.HS256,jwtProperties.getSecret())
                .compact();
    }


    /**
     * 解析jwt
     * @param jwtToken
     * @return
     * @throws BusinessException
     */

    public Claims parseJwtToken(String jwtToken) throws BusinessException {
        Claims claims = null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    //.parseClaimsJwt(jwtToken) 上课时的异常原因：不用用jwt解析，用下面的jws解析
                    .parseClaimsJws(jwtToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new BusinessException("JWT验证失败:token已经过期");
        } catch (UnsupportedJwtException e) {
            throw new BusinessException("JWT验证失败:token格式不支持");
        } catch (MalformedJwtException e) {
            throw new BusinessException("JWT验证失败:无效的token");
        } catch (SignatureException e) {
            throw new BusinessException("JWT验证失败:无效的token");
        } catch (IllegalArgumentException e) {
            throw new BusinessException("JWT验证失败:无效的token");
        }
        return claims;
    }

//    public Long verifyAndGetUserId(String token) throws BusinessException {
//        Claims claims = null;
//        try {
//            claims = Jwts.parser()
//                    .setSigningKey(jwtProperties.getSecret())
//                    .parseClaimsJws(token)
//                    .getBody();
//
//            // 可以进行更多的验证，例如验证 Token 是否已过期
//        } catch (ExpiredJwtException e) {
//            throw new BusinessException("JWT验证失败:token已经过期");
//        } catch (UnsupportedJwtException e) {
//            throw new BusinessException("JWT验证失败:token格式不支持");
//        } catch (MalformedJwtException e) {
//            throw new BusinessException("JWT验证失败:无效的token");
//        } catch (SignatureException e) {
//            throw new BusinessException("JWT验证失败:无效的token");
//        } catch (IllegalArgumentException e) {
//            throw new BusinessException("JWT验证失败:无效的token");
//        }
//
//        Long userId = (Long) claims.get("userId");
//        return userId;
//
//
//    }
}
