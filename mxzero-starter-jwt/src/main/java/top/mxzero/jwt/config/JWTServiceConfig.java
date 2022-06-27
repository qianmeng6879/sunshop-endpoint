package top.mxzero.jwt.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.mxzero.jwt.service.ITokenService;
import top.mxzero.jwt.service.impl.TokenServiceImpl;

@Configuration
@EnableConfigurationProperties(JWTConfigProperties.class)
public class JWTServiceConfig {

    @Bean
    public ITokenService tokenService(){
        return new TokenServiceImpl();
    }

}
