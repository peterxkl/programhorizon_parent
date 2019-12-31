package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/29/2019 5:14 PM
 */
@ConfigurationProperties("jwt.config")
@Component
@Data
public class JwtUtil {
    private String key;
    private long ttl;

    /*
    生成JWT
     */
    public String createJWT(String id, String subject, String roles) {
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)        //设置用户名
                .setIssuedAt(new Date())         //设置签发时间
                .signWith(SignatureAlgorithm.HS256,key).claim("roles", roles); //添加角色
        if (ttl > 0) {
            builder.setExpiration(new Date(System.currentTimeMillis() + ttl));  //设置token过期时间
        }
        return builder.compact();  //
    }

    /*
    解析JWT
     */
    public Claims parseJWT(String jwtStr) {
        return Jwts.parser()
                .setSigningKey(key)      //key：是之前规定好的“盐”
                .parseClaimsJws(jwtStr)   //jwtStr: 即先前登录时获取的token
                .getBody();
    }
}