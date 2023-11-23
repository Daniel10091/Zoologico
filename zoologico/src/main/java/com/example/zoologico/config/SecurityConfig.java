package com.example.zoologico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// @EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests()
        .anyRequest()
          .anonymous();
    
    http
      .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.cors(corsConfig -> {
      corsConfig
        .configurationSource(request -> {
          var cors = new org.springframework.web.cors.CorsConfiguration();
          cors.setAllowedOrigins(java.util.List.of("*"));
          cors.setAllowedMethods(java.util.List.of("*"));
          cors.setAllowedHeaders(java.util.List.of("*"));
          return cors;
        });
    });
        
    return http.build();
  }

}
