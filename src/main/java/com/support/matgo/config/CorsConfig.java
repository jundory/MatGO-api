package com.support.matgo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {
  @Bean
  public CorsConfigurationSource corsConfigurationSource(){
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();

//    config.setAllowCredentials(true);   //쿠키 허용 여부
    config.setAllowedOrigins(Arrays.asList("*"));   //허용할 도메인
    config.setAllowedMethods(Arrays.asList("HEAD","POST","GET","DELETE"));  //허용할 HTTP 메서드
    config.setAllowedHeaders(Arrays.asList("*"));   //허용할 헤더

    source.registerCorsConfiguration("/**", config);    // 모든 경로에 대해 CORS 설정을 적용.
    return source;
  }
}
