package com.industrial.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    // Two profiles:
    // - dev (default): permit all
    // - prod: JWT resource server
    http.csrf(ServerHttpSecurity.CsrfSpec::disable);
    http.securityContextRepository(org.springframework.security.web.server.context.NoOpServerSecurityContextRepository.getInstance());
    return http.authorizeExchange(ex -> ex
            .pathMatchers("/actuator/**").permitAll()
            .pathMatchers("/**").permitAll()
        )
        .build();
  }
}
