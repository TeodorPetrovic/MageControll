package com.masofino.magecontroll.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(
            (requests) -> requests
                    .requestMatchers(
                            "/*",
                            "/users/*",
                            "/students",
                            "/students/*",

                            "/actions",
                            "/actions/*",
                            "/databases",
                            "/databases/*",
                            "/emailsend",
                            "/emailsend/*",
                            "/status",
                            "/status/*",
                            "/faculty",
                            "/faculty/*",
                            "/logs",
                            "/logs/*",
                            "/assign",
                            "/assign/*",
                            "/subjects",
                            "/subjects/*"

                    ).permitAll()
                    .anyRequest().authenticated()
            )
            .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
