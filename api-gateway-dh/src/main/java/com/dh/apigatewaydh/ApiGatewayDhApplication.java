package com.dh.apigatewaydh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayDhApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayDhApplication.class, args);
    }

}
