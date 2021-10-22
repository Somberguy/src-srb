package com.tanbo.srb.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.tanbo.srb"})
public class ServiceOSSApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOSSApplication.class, args);
    }
}