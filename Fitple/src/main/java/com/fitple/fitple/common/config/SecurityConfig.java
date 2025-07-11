package com.fitple.fitple.common.config;

import com.fitple.fitple.base.user.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/modify", "/user/remove").hasAnyRole("ADMIN","USER")
                        .anyRequest().permitAll()
                )

                .formLogin(form -> form
                        .loginPage("/user/login")                // 수정: 로그인 페이지 경로
                        .usernameParameter("email")              // 이메일로 로그인
                        .passwordParameter("password")
                        .defaultSuccessUrl("/mypage", true)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/user/logout")              // 수정: 로그아웃 경로
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }
}
