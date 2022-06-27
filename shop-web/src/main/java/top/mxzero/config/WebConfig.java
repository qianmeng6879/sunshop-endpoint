package top.mxzero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.mxzero.interceptor.JWTAuthenticationInterceptor;

import java.awt.image.BufferedImage;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthenticationInterceptor()).addPathPatterns("/**").order(-10);
    }

    @Bean
    public JWTAuthenticationInterceptor jwtAuthenticationInterceptor(){
        return new JWTAuthenticationInterceptor();
    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new BufferedImageHttpMessageConverter());
    }
}
