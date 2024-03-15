package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import javax.sql.DataSource;

//@Configuration
public class ApplicationSecurity extends WebSecurityConfiguration {


   /* @Autowired
    public void initialize(AuthenticationManagerBuilder builder, DataSource dataSource) {
        builder.jdbcAuthentication().dataSource(dataSource).withUser("dave")
            .password("secret").roles("USER");
    }
*/
   /* @Autowired
    DataSource dataSource;

    @Override
    public void initialize(AuthenticationManagerBuilder builder) {
        builder.jdbcAuthentication().dataSource(dataSource).withUser("dave")
            .password("secret").roles("USER");
    }*/
}