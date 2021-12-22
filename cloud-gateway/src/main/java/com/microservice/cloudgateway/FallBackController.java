package com.microservice.cloudgateway;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Component
@EnableCircuitBreaker
public class FallBackController {

    @RequestMapping("/orderFallBack")
    public Mono<String> orderServiceFallBack() {
        return Mono.just("Order Service is taking too long to respond OR is Down. Please try again later.");
    }

    @RequestMapping("/paymentFallBack")
    public Mono<String> paymentServiceFallBack() {
        return Mono.just("Payment Service is taking too long to respond OR is Down. Please try again later.");
    }
}
