package com.atastaff.absencesystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration for the InsertAbsencesSystem.
 * This class defines authentication and authorization rules,
 * user details service, and password encoder.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for HTTP requests.
     *
     * @param http The HttpSecurity object to configure.
     * @return A SecurityFilterChain instance.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF protection for stateless REST APIs.
            // In a real-world application with a browser-based UI, CSRF should be enabled
            // and handled appropriately (e.g., using CSRF tokens).
            .csrf(csrf -> csrf.disable())
            // Configure authorization rules for different endpoints.
            .authorizeHttpRequests(authz -> authz
                // Allow unauthenticated access to H2 console (for development/testing)
                // and actuator endpoints (if enabled).
                .requestMatchers("/h2-console/**").permitAll()
                // All API endpoints require authentication and ATA_STAFF role.
                .requestMatchers("/api/**").hasRole("ATA_STAFF")
                // Any other request requires authentication.
                .anyRequest().authenticated()
            )
            // Configure HTTP Basic authentication.
            .httpBasic(httpBasic -> {})
            // Configure session management to be stateless.
            // This is typical for REST APIs where each request carries its own authentication information.
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Allow H2 console frames to be displayed in a browser.
        // This is necessary when using H2 console with Spring Security.
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }

    /**
     * Configures an in-memory user details service for demonstration purposes.
     * In a production environment, user details would typically be loaded from a database.
     *
     * @return An InMemoryUserDetailsManager instance with a predefined ATA staff user.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // Create a sample ATA staff user with a username, encoded password, and role.
        // The password "password" is encoded using BCryptPasswordEncoder.
        UserDetails ataStaff = User.builder()
            .username("atastaff")
            .password(passwordEncoder().encode("password")) // Encode the password
            .roles("ATA_STAFF") // Assign the ATA_STAFF role
            .build();
        return new InMemoryUserDetailsManager(ataStaff);
    }

    /**
     * Provides a BCryptPasswordEncoder bean for encoding passwords.
     * BCrypt is a strong hashing algorithm recommended for password storage.
     *
     * @return A BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}