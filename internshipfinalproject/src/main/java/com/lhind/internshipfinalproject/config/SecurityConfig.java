package com.lhind.internshipfinalproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/v1/jobseeker").permitAll()
                                .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}


//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//            .csrf().disable() // Disable CSRF for API testing
//            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/admin/**").hasRole("ADMIN")
//                    .requestMatchers("/employer/**").hasRole("EMPLOYER")
//                    .requestMatchers("/jobseeker/**").hasRole("JOB_SEEKER")
//                    .requestMatchers("/auth/register").permitAll()
//                    .anyRequest().authenticated()
//            )
//            .httpBasic(); // Use Basic Authentication for simplicity
//
//    return http.build();
//}
//
//@Bean
//public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//}