package com.microservice.cloudgateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/order/**")
                        .filters(f -> f.hystrix(h -> h.setName("CircuitBreaker")
                                .setFallbackUri("forward:/orderFallBack")))
                        .uri("lb://ORDER-SERVICE")
                        .id("order-service"))
                .route(r -> r.path("/payment/**")
                        .filters(f -> f.hystrix(h -> h.setName("CircuitBreaker")
                                .setFallbackUri("forward:/paymentFallBack")))
                        .uri("lb://PAYMENT-SERVICE")
                        .id("payment-service"))
                .build();
    }

}
