package com.exampleView.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    private final String jwtSecretKey = "your-secret-key";
    private final Integer timeLiveMinute = 10;

    @Bean
    public String jwtSecret() {
        return jwtSecretKey;
    }

    @Bean
    public Integer timeLive() {
        return timeLiveMinute;
    }
}
