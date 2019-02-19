package org.gema.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
@EnableEurekaServer 	// Activer le serveur eureka
@SpringBootApplication
@ComponentScan
public class SpringBootEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEurekaApplication.class, args);
	}
}


