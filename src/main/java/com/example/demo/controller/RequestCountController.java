package com.example.demo.controller;

import com.example.demo.filters.RequestCountFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestCountController {

    @Autowired
    private FilterRegistrationBean<RequestCountFilter> requestCountFilterBean;

    @GetMapping("/request-count")
    public ResponseEntity<Long> getRequestCount() {
        RequestCountFilter requestCountFilter = requestCountFilterBean.getFilter();
        return ResponseEntity.ok(requestCountFilter.getRequestCount());
    }
}
