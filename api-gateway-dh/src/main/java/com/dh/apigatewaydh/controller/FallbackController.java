package com.dh.apigatewaydh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @RequestMapping("/newsFallback")
    public Mono<String> newsServiceFallback() {
        return Mono.just("News Service is taking too much time to respond or is down, Please try again later");
    }

    @RequestMapping("/usersFallback")
    public Mono<String> usersServiceFallback() {
        return Mono.just("User Service is taking too much time to respond or is down, Please try again later");
    }
}
