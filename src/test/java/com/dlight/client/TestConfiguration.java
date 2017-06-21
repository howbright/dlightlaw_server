package com.dlight.client;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.dlight.client" })
@Configuration
public class TestConfiguration {
	
}
