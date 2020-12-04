package com.gachon.htm.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gachon.htm.api.service.TestService;
import com.gachon.htm.domain.repository.test.TestRepository;

@Configuration
public class ServiceConfiguration {

    @Bean
    public TestService testService(TestRepository testRepository) {
        return new TestService(testRepository);
    }
}
