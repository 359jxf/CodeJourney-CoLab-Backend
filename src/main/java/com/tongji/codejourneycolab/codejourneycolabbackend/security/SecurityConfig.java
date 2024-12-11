package com.tongji.codejourneycolab.codejourneycolabbackend.security;

import com.tongji.codejourneycolab.codejourneycolabbackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserMapper userMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(CsrfConfigurer::disable)
                .cors(cors -> {
                    cors.configurationSource(request -> {
                        var corsConfiguration = new CorsConfiguration();
                        corsConfiguration.setAllowedOrigins(List.of("*"));
                        corsConfiguration.setAllowedMethods(List.of("*"));
                        corsConfiguration.setAllowedHeaders(List.of("*"));
                        return corsConfiguration;
                    });
                })
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(authorizedRoutes().toArray(new String[0])).authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(FormLoginConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (username -> {
            return new CJUserDetails(userMapper.selectByUsername(username));
        });
    }

    @Bean
    public List<String> authorizedRoutes() {
        return List.of("/helloauthorized");
    }

}
