package com.snake.springbootlilproject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootLilprojectApplicationTests {
    /*
    * 生成JWT
    * */
    @Test
   public void testGenJwt(){
        Map<String,Object> claims =new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");
        String jwt =Jwts.builder().
                signWith(SignatureAlgorithm.HS256,"itheima")//设置签名算法
                .setClaims(claims)//自定义部分（载荷）
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置有效期为1h
                .compact();
        System.out.println(jwt);
    }
    /*
    * 解析令牌
    * */
    @Test
    public void  testParseJWT(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcxMjU4NjIyNH0.E8cXZDTHUbPLWJxYCT0zbBmqQZMJP6YMHl_ckTyIYSg")
                .getBody();
        System.out.println(claims);
    }

}
