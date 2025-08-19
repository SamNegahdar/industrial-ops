package com.industrial.warehouseservice.infrastructure;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {
  @Bean SecurityFilterChain security(HttpSecurity http) throws Exception {
    http.csrf(csrf->csrf.disable());
    http.authorizeHttpRequests(a->a.requestMatchers("/actuator/**").permitAll().anyRequest().authenticated());
    http.oauth2ResourceServer(o->o.jwt(Customizer.withDefaults()));
    return http.build();
  }
}
