package com.forezp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceFileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFileUploadApplication.class, args);
    }

}
