package com.ums.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
public class userconfig {
private JWTResponseFilter jwtResponseFilter;

    public userconfig(JWTResponseFilter jwtResponseFilter) {
        this.jwtResponseFilter = jwtResponseFilter;
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().cors().disable();
        httpSecurity.addFilterBefore(jwtResponseFilter,AuthorizationFilter.class);


        httpSecurity.authorizeRequests()
                .requestMatchers("/api/v1/auth/login","/api/v1/auth/adduser")
                .permitAll()
                .anyRequest()
                .authenticated();
        return httpSecurity.build();






    }
}
