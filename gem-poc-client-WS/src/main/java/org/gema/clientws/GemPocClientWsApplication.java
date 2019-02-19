package org.gema.clientws;

import java.util.Map;

import org.gema.clientws.controller.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class GemPocClientWsApplication {

	
	private static Logger log = LoggerFactory.getLogger(GemPocClientWsApplication.class);
	 
	 @Autowired 
	 private RestTemplate restTemplate;
	 
	 
	 protected static Map<String, String> env;
	
	public static void main(String[] args) {
		SpringApplication.run(GemPocClientWsApplication.class, args);
	}

	@RequestMapping(value="/clientWS",produces=MediaType.APPLICATION_JSON_VALUE) 
	public String callHome() { 

		log.info("Appel interne au WS IMAGES"); 
		  return restTemplate.getForObject("http://localhost:8662/images", String.class); 

		}
	
	@FeignClient(name = "images", fallback = HystrixClientFallback.class)
	protected interface HystrixClient {
	    @RequestMapping(method = RequestMethod.GET, value = "/clientWS")
	    Image iFailSometimes();
	}

	static class HystrixClientFallback implements HystrixClient {
	    @Override
	    public Image iFailSometimes() {
	    	env = System.getenv();
	    	String port = (String) env.get("server.port");
	        return new Image(1, "Timeout", "Timeout !!!",port);
	    
	}
	}
	
	@Bean 
	public RestTemplate getRestTemplate() { 

		  return new RestTemplate(); 

		} 
}

