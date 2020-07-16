package com.cops.nc65;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CopsNc65ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopsNc65ServerApplication.class, args);
    }

}
