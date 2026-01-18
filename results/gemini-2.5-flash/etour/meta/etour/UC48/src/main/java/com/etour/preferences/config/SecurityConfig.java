package com.etour.preferences.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Security configuration for the Spring Boot application.
 * This class defines authentication and authorization rules.
 * For simplicity, this configuration uses in-memory authentication.
 * In a production environment, this would typically integrate with
 * a more robust authentication provider (e.g., OAuth2, JWT, LDAP, database).
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configures HTTP security, including authorization rules and session management.
     *
     * @param http The HttpSecurity object to configure.
     * @throws Exception if an error occurs during configuration.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for simplicity in API development; enable in production with proper handling
            .authorizeRequests()
                // Allow unauthenticated access to H2 console for development purposes
                // In production, this should be secured or disabled.
                .antMatchers("/h2-console/**").permitAll()
                // All other API requests require authentication
                .antMatchers("/api/preferences/**").authenticated()
                .anyRequest().permitAll() // Permit all other requests (e.g., static content, root)
            .and()
            .httpBasic() // Enable HTTP Basic Authentication
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Use stateless sessions (e.g., for REST APIs)

        // Required for H2 console to work with Spring Security
        http.headers().frameOptions().disable();
    }

    /**
     * Configures an in-memory user details service for demonstration purposes.
     * This defines a single user "tourist" with password "password" and role "USER".
     *
     * @return A UserDetailsService bean.
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        // Create a dummy user for testing authentication
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("tourist")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}