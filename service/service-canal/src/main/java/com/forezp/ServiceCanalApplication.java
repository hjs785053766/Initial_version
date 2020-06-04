package com.forezp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.forezp.api.mapper")
public class ServiceCanalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCanalApplication.class, args);
    }

}
