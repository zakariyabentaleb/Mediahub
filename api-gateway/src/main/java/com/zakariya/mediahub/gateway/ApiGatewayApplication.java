package com.zakariya.mediahub.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication: marks this as the Spring Boot entry point.
// No extra annotation needed — Spring Cloud Gateway auto-configures itself
// as long as the dependency is on the classpath.
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
