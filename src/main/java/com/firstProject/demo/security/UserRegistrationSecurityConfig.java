package com.firstProject.demo.security;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableEncryptableProperties
@EnableWebSecurity
public class UserRegistrationSecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }


  @Bean
  public SecurityFilterChain filterChainChain(HttpSecurity http) throws Exception {
    return http.cors()
            .and().csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/register/**")
            .permitAll()
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/users/**")
            .hasAnyAuthority("USER", "ADMIN")
            .and().formLogin().and().build();
  }
}