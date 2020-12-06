package com.gachon.htm.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.gachon.htm.domain.configuration.DatabaseConfiguration;

@Configuration
@Import(DatabaseConfiguration.class)
public class ApplicationConfiguration {

}
