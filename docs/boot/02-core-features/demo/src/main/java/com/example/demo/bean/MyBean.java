package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class MyBean {
    // properties name 속성
    @Value("${name}")
    private String name;

    @Bean
    public String beanTest() {
        System.out.println(name);
        return name;
    }
}
