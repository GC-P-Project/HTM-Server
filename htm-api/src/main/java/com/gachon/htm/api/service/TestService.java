package com.gachon.htm.api.service;

import java.util.Objects;

import com.gachon.htm.domain.repository.test.TestRepository;

public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = Objects.requireNonNull(testRepository, "testRepository");
    }
}
