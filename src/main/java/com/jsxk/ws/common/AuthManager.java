package com.jsxk.ws.common;

import com.jsxk.ws.model.UserInfor;
import com.jsxk.ws.service.UserServcie;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import java.security.Key;
import java.util.Date;

@Component
public class AuthManager {


    @Autowired
    UserServcie userServcie;

    private long ttlMillis = 1000 * 60 * 60 * 24 * 7 + 60000;

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


    public String getToken(UserInfor userInfo) {


        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //当前的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("miyao");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(userInfo.getUserId())
                .setIssuedAt(now)
                .setSubject("ws")
                .setIssuer("")
                .signWith(signatureAlgorithm, signingKey);


        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);

        builder.setExpiration(exp);

        return builder.compact();


    }


    public void refreshUserToken(String token) {

    }

    public void loginOff(String token) {

    }


    public UserInfor getUserInfoByToken(HttpServletRequest request) {

        /*
        String token = request.getHeader("token");

        String userId = parseJWT(token);

        **/
        return userServcie.getUserInforByuserId("1");


    }

    private String parseJWT(String jwt) {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("miyao"))
                .parseClaimsJws(jwt).getBody();

        return claims.getId();

    }
}
