package com.redis_project.springboot.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry cors) {
                cors
                        .addMapping("/**")
                        .allowedOrigins("http://localhost:3000")// or "*"
                        .allowedMethods("GET", "POST", "PUT", "DELETE")// or "*"
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(new MediaType("application", "vnd.api+json"), MediaType.APPLICATION_JSON);
    }
}
