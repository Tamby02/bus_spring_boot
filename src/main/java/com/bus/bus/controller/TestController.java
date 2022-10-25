package com.bus.bus.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TestController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/bonjour")
    String hello() {
        return "Hello World! Bonjour";
    }

}