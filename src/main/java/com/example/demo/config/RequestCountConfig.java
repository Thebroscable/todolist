package com.example.demo.config;

import com.example.demo.filters.RequestCountFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestCountConfig {

    @Bean
    public FilterRegistrationBean<RequestCountFilter> requestCountFilter() {
        FilterRegistrationBean<RequestCountFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestCountFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
