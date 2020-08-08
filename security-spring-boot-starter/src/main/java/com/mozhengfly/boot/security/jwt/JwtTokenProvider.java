package com.mozhengfly.boot.security.jwt;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.mozhengfly.boot.security.constants.JwtEnums;
import com.mozhengfly.boot.security.pojo.JwtUserInfo;
import com.mozhengfly.boot.security.pojo.RefreshToken;
import com.mozhengfly.boot.security.properties.JwtProperties;
import io.jsonwebtoken.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

/**
 * JwtTokenProvider
 * @Description JwtToken处理类
 * @Author wangchonglin
 * @Date 2020-01-02 15:36:35
 * @Version 1.0.0
 */
@Slf4j
@Setter
public class JwtTokenProvider {

    private JwtProperties jwtProperties;

    public String generateAccessToken(JwtUserInfo user) {
        Instant expiryDate = Instant.now().plusMillis(jwtProperties.getExpiration());
        return Jwts.builder().setSubject(user.getId()).setIssuedAt(Date.from(Instant.now()))
                .claim("deviceId", user.getDeviceId())
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret()).compact();
    }

    public String generateRefreshToken(JwtUserInfo user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setExpiryTime(Instant.now().plusMillis(jwtProperties.getRefreshDuration()));
        refreshToken.setUserId(user.getId());
        refreshToken.setDeviceId(user.getDeviceId());
        return encrypt(refreshToken);
    }

    public String updateRefreshToken(RefreshToken refreshToken) {
        refreshToken.setExpiryTime(Instant.now().plusMillis(jwtProperties.getRefreshDuration()));
        return encrypt(refreshToken);
    }

    private String encrypt(RefreshToken refreshToken) {
        String text = JSON.toJSONString(refreshToken);
        return SecureUtil.aes(jwtProperties.getRefreshSecret().getBytes()).encryptBase64(text);
    }

    public RefreshToken parseForRefreshToken(String refreshToken) {
        try {
            String text = SecureUtil.aes(jwtProperties.getRefreshSecret().getBytes()).decryptStr(refreshToken);
            return JSON.parseObject(text, RefreshToken.class);
        } catch (Exception e) {
            throw new JwtException(JwtEnums.INVALID, e);
        }
    }

    public String getUserIdFromToken(String accessToken, String deviceId) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(accessToken);
            String tokenDeviceId = (String)claims.getBody().get("deviceId");
            if(!StringUtils.equals(tokenDeviceId, deviceId)) {
                log.error("token中的deviceId [{}] 和 params中的deviceId [{}] 不一致", tokenDeviceId, deviceId);
                throw new JwtException(JwtEnums.DEVICE_ID_NOT_MATCH);
            }
            return claims.getBody().getSubject();
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            log.error("token [{}] 签名无效", accessToken);
            throw new JwtException(JwtEnums.SIGNATURE_INVALID);
        } catch (ExpiredJwtException ex) {
            log.error("token [{}] 过期", accessToken);
            throw new JwtException(JwtEnums.EXPIRED);
        }
    }
}
