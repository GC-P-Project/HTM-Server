package com.gachon.htm.api.controller;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gachon.htm.api.aop.Authentication;
import com.gachon.htm.api.model.AuthorizationContext;
import com.gachon.htm.api.model.request.TestRequest;
import com.gachon.htm.api.model.response.TestResponse;
import com.gachon.htm.api.service.TestService;

@RestController
@RequestMapping(path = "/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = Objects.requireNonNull(testService, "testService");
    }

    @Authentication(doAuthentication = true)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity testModel(@RequestAttribute("authorizationContext") AuthorizationContext authorizationContext,
                                    @PathVariable("id") String id) {
        return ResponseEntity.ok().build();
    }

    @Authentication(doAuthentication = true)
    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ResponseEntity<TestResponse> insertTestModel(@RequestBody TestRequest testRequest) {
        return ResponseEntity.ok(new TestResponse());
    }
}
