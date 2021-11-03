package com.example.circuitbreaker.app.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class FreightService2 {

    @Autowired
    private RestTemplate restTemplate;

    private static final String RESILIENCE4J_INSTANCE_NAME = "example";
    private static final String FALLBACK_METHOD = "fallback";

    @CircuitBreaker(name = RESILIENCE4J_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD)
    public String getQuote(final String url) {

        log.info("----------------------- URL: {} -----------------------", url);

        return restTemplate.getForObject(url, String.class);
    }

    public String fallback(Exception ex) {
        String url = "https://jsonplaceholder.typicode.com/albums";

        log.info("----------------------- Running contingency. -----------------------");

        return restTemplate.getForObject(url, String.class);
    }

}
