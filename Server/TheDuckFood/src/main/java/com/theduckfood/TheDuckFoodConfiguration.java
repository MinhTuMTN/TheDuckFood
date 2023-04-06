package com.theduckfood;

import com.theduckfood.filter.SimpleFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TheDuckFoodConfiguration {
    @Bean
    public FilterRegistrationBean<SimpleFilter> loggingFilter(){
        FilterRegistrationBean<SimpleFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SimpleFilter());
        registrationBean.addUrlPatterns("/api/users/*");
        return registrationBean;
    }
}
