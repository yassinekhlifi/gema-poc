package org.gema.imageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;
@EnableEurekaClient  // Client service registry
@SpringBootApplication
@EnableHystrix  
@EnableHystrixDashboard
//@EnableTurbine
@EnableCircuitBreaker
@Configuration
public class ServiceImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceImageApplication.class, args);
	}
}
