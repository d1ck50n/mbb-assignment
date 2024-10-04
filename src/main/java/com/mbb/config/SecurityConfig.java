package com.mbb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable(); // allow h2 to connect via browser
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/transactions/**").authenticated()
                .and()
                .httpBasic(); // Simple Basic Auth for demonstration
    }
}
