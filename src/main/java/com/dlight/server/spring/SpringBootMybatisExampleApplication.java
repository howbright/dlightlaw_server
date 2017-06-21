package com.dlight.server.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceTransactionManagerAutoConfiguration.class, DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = "com.dlight.server.spring, "
		+ "com.dlight.server.repository, "
		+ "com.dlight.server.controller, "
		+ "com.dlight.server.service, "
		+ "com.dlight.server.data,"
		+ "com.dlight.client,"
		+ "com.dlight.contract")
@SpringBootApplication
public class SpringBootMybatisExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisExampleApplication.class, args);
    }
    
    
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }
}
