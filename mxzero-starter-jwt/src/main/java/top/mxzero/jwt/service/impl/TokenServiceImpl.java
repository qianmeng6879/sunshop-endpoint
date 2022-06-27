package top.mxzero.jwt.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import top.mxzero.jwt.config.JWTConfigProperties;
import top.mxzero.jwt.service.ITokenService;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class TokenServiceImpl implements ITokenService {
    @Autowired
    private JWTConfigProperties jwtConfigProperties;

    @Value("${spring.application.name:microboot-jwt}")
    private String applicationName;

    public SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    @Override
    public SecretKey generaKey() {
        byte[] encodeKey = Base64.decodeBase64(Base64.encodeBase64(this.jwtConfigProperties.getSecret().getBytes()));
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
    }
    @Override
    public String createToken(String id, Map<String, Object> subject) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + this.jwtConfigProperties.getExpire() * 1000);
        // 附加信息
//        Map<String, Object> claims = new HashMap<>();
        // 头部信息
        Map<String, Object> headers = new HashMap<>();
        headers.put("site", jwtConfigProperties.getSite());
        headers.put("module", this.applicationName);
        JwtBuilder jwtBuilder;
        jwtBuilder = Jwts.builder()
//                .setClaims(claims)
                .setHeader(headers)
                .setId(id)
                .setIssuedAt(currentDate)
                .setIssuer(this.jwtConfigProperties.getIssuer())
                .setSubject(JSONObject.toJSONString(subject))
                .signWith(this.signatureAlgorithm, generaKey())
                .setExpiration(expireDate);
        return jwtBuilder.compact();
    }

    @Override
    public Jws<Claims> parseToken(String token) throws JwtException {
        if(this.verifyToken(token)){
            return Jwts.parser().setSigningKey(this.generaKey()).parseClaimsJws(token);
        }
        return null;
    }

    @Override
    public boolean verifyToken(String token) {
        try{
            Jwts.parser().setSigningKey(this.generaKey()).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String refreshToken(String token) {
        if(this.verifyToken(token)){
            Jws<Claims> claimsJws = this.parseToken(token);
            return this.createToken(claimsJws.getBody().getId(), JSONObject.parseObject(claimsJws.getBody().getSubject()));
        }
        return null;
    }

    @Override
    public Map<String, Object> getSubject(String token) {
        if(this.verifyToken(token)){
            return JSONObject.parseObject(this.parseToken(token).getBody().getSubject());
        }
        return null;
    }
}
