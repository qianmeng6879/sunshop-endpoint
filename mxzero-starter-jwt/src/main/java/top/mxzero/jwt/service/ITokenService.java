package top.mxzero.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;


import javax.crypto.SecretKey;
import java.util.Map;

public interface ITokenService {

    public SecretKey generaKey();

    public String createToken(String id, Map<String, Object> subject);

    public Jws<Claims> parseToken(String token) throws JwtException;

    public boolean verifyToken(String token);

    public String refreshToken(String token);

    public Map<String, Object> getSubject(String token);

}
