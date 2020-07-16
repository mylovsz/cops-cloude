package com.cops.scada;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableAsync
public class CopsScadaApplication {
	public static void main(String[] args) {
		SpringApplication.run(CopsScadaApplication.class, args);
	}
}

//@SpringBootApplication
//@MapperScan("com.cops.scada.dao")
//public class CopsScadaApplication extends SpringBootServletInitializer {
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(CopsScadaApplication.class);
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(CopsScadaApplication.class, args);
//	}
//}
