package com.etour.banner.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring configuration class for the application.
 * This class enables Spring MVC and component scanning for the application.
 * It also configures resource handlers for serving static content, specifically uploaded images.
 */
@Configuration
@EnableWebMvc // Enables Spring MVC features
@ComponentScan(basePackages = "com.etour.banner") // Scans for Spring components in the specified package
public class AppConfig implements WebMvcConfigurer {

    /**
     * Configures resource handlers to serve static resources.
     * Specifically, it maps requests to "/images/**" to the file system directory
     * where uploaded banner images are stored. This allows the frontend to access
     * the images directly via a URL.
     *
     * @param registry The ResourceHandlerRegistry to configure.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configure a resource handler for images.
        // The path "file:./uploads/images/" should match where ImageService stores images.
        // This makes images accessible via URLs like http://localhost:8080/images/your_banner.jpg
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:./uploads/images/");
    }
}