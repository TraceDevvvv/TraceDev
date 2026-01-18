package com.example.insertdelayadmin.security;

import com.example.insertdelayadmin.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for Spring Security.
 * This class defines security rules, authentication providers, password encoder,
 * and integrates JWT authentication into the security filter chain.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /**
     * Constructor for SecurityConfig, injecting required dependencies.
     *
     * @param customUserDetailsService Service to load user-specific data.
     * @param jwtRequestFilter Custom filter for JWT authentication.
     * @param jwtAuthenticationEntryPoint Entry point for handling authentication failures.
     */
    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                          JwtRequestFilter jwtRequestFilter,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    /**
     * Configures the security filter chain, defining authorization rules,
     * session management, and adding the JWT authentication filter.
     *
     * @param http The HttpSecurity object to configure.
     * @return A configured SecurityFilterChain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for stateless REST APIs
            .csrf(csrf -> csrf.disable())
            // Configure authorization rules
            .authorizeHttpRequests(authorize -> authorize
                // Allow unauthenticated access to the login endpoint
                .requestMatchers("/api/auth/login").permitAll()
                // All other API requests require authentication
                .requestMatchers("/api/**").authenticated()
                // Deny all other requests by default
                .anyRequest().denyAll()
            )
            // Configure exception handling for authentication failures
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            )
            // Configure session management to be stateless (no session will be created or used)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        // Add the JWT authentication filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Provides a DaoAuthenticationProvider bean.
     * This provider uses the CustomUserDetailsService to load user details
     * and the BCryptPasswordEncoder to verify passwords.
     *
     * @return A configured DaoAuthenticationProvider.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Provides an AuthenticationManager bean.
     * This manager is used to authenticate users.
     *
     * @param authenticationConfiguration The AuthenticationConfiguration to get the manager from.
     * @return An AuthenticationManager instance.
     * @throws Exception if an error occurs while getting the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Provides a BCryptPasswordEncoder bean.
     * BCrypt is a strong hashing algorithm for passwords.
     *
     * @return A BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}