package com.devsuperior.dscatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    //config de segurança
    // OAUTH 2 authorization server
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
    	JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
    	tokenConverter.setSigningKey("MY-JWT-SECRET");// SEGREDO DA APLICAÇAO
    	return tokenConverter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
    	return new JwtTokenStore(accessTokenConverter());
    }
}
