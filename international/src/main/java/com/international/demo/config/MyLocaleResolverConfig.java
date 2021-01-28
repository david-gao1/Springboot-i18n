package com.international.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

//加载这个bean
@Configuration
public class MyLocaleResolverConfig {
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}