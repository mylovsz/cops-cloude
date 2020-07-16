package com.cops.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CopsQuartzServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopsQuartzServerApplication.class, args);
    }

}
