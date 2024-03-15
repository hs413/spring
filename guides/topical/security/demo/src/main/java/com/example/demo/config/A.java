package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(3)
public class A {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("filter");
//		http.antMatcher("/match1/**")
//			.authorizeRequests()
//			.antMatchers("/match1/user").hasRole("USER")
//			.antMatchers("/match1/spam").hasRole("SPAM")
//			.anyRequest().isAuthenticated();

        return http.build();
    }
}