package com.etour.banner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 * Spring configuration class for application-wide beans.
 * This class defines beans that are used across the application,
 * such as the MultipartResolver for handling file uploads.
 */
@Configuration
public class AppConfig {

    /**
     * Configures a StandardServletMultipartResolver for handling multipart file uploads.
     * This bean is essential for Spring MVC to process files sent via multipart/form-data.
     *
     * @return A configured MultipartResolver instance.
     */
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}