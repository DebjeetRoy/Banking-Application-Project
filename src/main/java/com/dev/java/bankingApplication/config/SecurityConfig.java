package com.dev.java.bankingApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {

    /*
     * Spring container creates object as and when required
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder().username("dev")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder().username("raj")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    /*
     * TO Encode Password for enhanced security
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     *
     * @param http
     * @return SecurityFilterChain
     * @implNote First provide those URLs details which has permit all access and then we can have role based access for certain URLs
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/v3/api-docs/**").permitAll() // Allow all users to access "/v3/api-docs"
                                .requestMatchers("/actuator/**").permitAll() // Allow all users to access Actuator endpoints
                                .requestMatchers(HttpMethod.POST).hasRole("ADMIN") // Restrict POST to ADMIN role
                                .requestMatchers(HttpMethod.GET).hasRole("USER") // Restrict GET to USER role
                                .anyRequest().authenticated()) // All other requests require authentication
                .httpBasic(withDefaults()) // Use basic authentication
                .csrf(csrf -> csrf.disable()).build(); // Disable CSRF protection
    }
}
