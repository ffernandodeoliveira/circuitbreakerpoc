package com.example.circuitbreaker.app.service;

import com.example.circuitbreaker.app.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        var circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        final var result = circuitBreaker.run(() -> getDefaultQuote(url), throwable -> getQuoteContingency());

        return "Running by contingency = " + result.isContingency();
    }

    public ResponseDTO getDefaultQuote(String url) {
        final var data = restTemplate.getForObject(url, String.class);

        return ResponseDTO.builder()
                .isContingency(false)
                .data(data)
                .build();
    }

    public ResponseDTO getQuoteContingency() {
        final var url = "https://jsonplaceholder.typicode.com/albums";
        final var data = restTemplate.getForObject(url, String.class);

        return ResponseDTO.builder()
                .isContingency(true)
                .data(data)
                .build();
    }

}
