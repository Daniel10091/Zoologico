package com.example.zoologico.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.zoologico.config, com.example.zoologico.domain.controller, com.example.zoologico.domain.service, com.example.zoologico.domain.mapper, com.example.zoologico.domain.exception, com.example.zoologico.domain.dto, com.example.zoologico.domain.model, com.example.zoologico.domain.repository, com.example.zoologico.domain.util, com.example.zoologico.domain.util.*"})
@EntityScan(basePackages = "com.example.zoologico.domain")
@EnableJpaRepositories(basePackages = "com.example.zoologico.domain.repository")
public class WebServiceApplication extends SpringBootServletInitializer {

}
