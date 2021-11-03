package com.example.circuitbreaker.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class FreightService {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private RestTemplate restTemplate;

    public String getQuote(final String url) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        log.info("----------------------- URL: {} -----------------------", url);

        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class),
                throwable -> getQuoteContingency());
    }

    public String getQuoteContingency() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        String url = "https://jsonplaceholder.typicode.com/albums";

        log.info("----------------------- Running contingency -----------------------");

        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class));
    }

}
