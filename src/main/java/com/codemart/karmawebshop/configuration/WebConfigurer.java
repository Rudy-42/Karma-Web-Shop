package com.codemart.karmawebshop.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfigurer implements WebMvcConfigurer {
    public static String uploadDirectory= System.getProperty("user.home") + "\\karma_storage\\products";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/products/**", "/css/**", "/img/**", "/js/**", "/fonts/**")
                .addResourceLocations("file:" + uploadDirectory + "/", "classpath:/static/css/","classpath:/static/img/",
                        "classpath:/static/js/", "classpath:/static/fonts/");
    }
}
