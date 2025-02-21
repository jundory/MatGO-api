package com.support.matgo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
  private final CorsConfig corsConfig;
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())   //csrf 비활성화
        .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource())) // CORS 설정
        .authorizeRequests(authorize -> authorize
                .requestMatchers("/**").permitAll() // 요청 허용할 경로 명시(현재는 모든 경로)
//                        .anyRequest().authenticated() // 밑의 permitAll()과 반대로 인증할 경로 명시(인증된 사용자만 접근 허용)
                //commit test
        );
    return http.build();
  }
}
