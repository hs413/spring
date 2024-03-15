package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1)
public class B {
    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        System.out.println("filter2");
//		http.antMatcher("/match1/**")
//			.authorizeRequests()
//			.antMatchers("/match1/user").hasRole("USER")
//			.antMatchers("/match1/spam").hasRole("SPAM")
//			.anyRequest().isAuthenticated();

        return http.build();
    }
}