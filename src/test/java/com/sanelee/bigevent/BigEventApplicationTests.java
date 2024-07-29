package com.sanelee.bigevent;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BigEventApplicationTests {

    @Test
    public void testJWT(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","sanelee");
        String token = JWT.create()
                .withClaim("user",claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//添加过期时间
                .sign(Algorithm.HMAC256("edcfvwsx"));//指定密钥
        System.out.println(token);
    }

    @Test
    public void testParse(){
        //定义字符串，模拟用户传递过来的token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"+
                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6InNhbmVsZWUifSwiZXhwIjoxNzIxOTE0NzkxfQ"+
                ".UhD8dXbVlEfRTaFXxD4A5tywy1crACwLdziAEUu2jvQ";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("edcfvwsx")).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        System.out.println(claims.get("user"));
    }


}

