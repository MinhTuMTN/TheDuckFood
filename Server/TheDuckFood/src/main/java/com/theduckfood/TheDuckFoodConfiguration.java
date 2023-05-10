package com.theduckfood;

import com.theduckfood.filter.SimpleFilter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class TheDuckFoodConfiguration extends WebMvcConfigurationSupport {
    @Bean
    public FilterRegistrationBean<SimpleFilter> loggingFilter(){
        FilterRegistrationBean<SimpleFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SimpleFilter());
        registrationBean.addUrlPatterns("/api/users/*");
        return registrationBean;
    }
}
