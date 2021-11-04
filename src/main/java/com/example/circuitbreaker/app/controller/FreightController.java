package com.example.circuitbreaker.app.controller;

import com.example.circuitbreaker.app.service.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotes")
public class FreightController {

    @Autowired
    private FreightService service;

    @GetMapping
    public String listQuotes(@RequestParam(required = false, defaultValue = "http://localhost:1234/not-real") String url) {
        return service.getQuote(url);
    }

}
