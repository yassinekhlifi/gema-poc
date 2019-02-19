package org.gema.imageservice.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RefreshScope
@RestController
@RequestMapping("/")
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class HomeController {
	
	
	private static Map<String, String> env;

	 private static Logger log = LoggerFactory.getLogger(HomeController.class);

	 /**
	  * 
	  * @return
	  * @throws Exception
	  */
	@RequestMapping(value="/images",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(commandKey = "getImages", fallbackMethod = "fallback")	
	public String getImages() throws Exception {
		env = System.getenv();
		String port = (String)env.get("server.port");
	//	if("8662".equals(port)) Thread.sleep(100000);
		  log.info("appel au WS IMAGES"); 
		List<Image> images = Arrays.asList(
			new Image(1, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760",port),
			new Image(2, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272",port),
			new Image(3, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112",port));
		String json = new Gson().toJson(images);
		return json;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fallback() throws Exception {
		env = System.getenv();
		String port = (String)env.get("server.port");
		List<Image> images = Arrays.asList(
				new Image(0, "Le service ne répond pas !!! ", "Contact  Administrateur ",port));
		String json = new Gson().toJson(images);
		return json;
			
	}
 
}

