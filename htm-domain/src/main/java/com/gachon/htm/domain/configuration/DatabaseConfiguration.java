package com.gachon.htm.domain.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages ={ "com.gachon.htm.domain.repository"})
@EntityScan(basePackages = { "com.gachon.htm.domain.model"})
@ComponentScan(basePackages = { "com.gachon.htm.domain.repository"})
public class DatabaseConfiguration {
}
