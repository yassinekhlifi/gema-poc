package org.gema.zuul.configuration;

import org.gema.zuul.filtres.ErrorFilter;
import org.gema.zuul.filtres.PostFilter;
import org.gema.zuul.filtres.PreFilter;
import org.gema.zuul.filtres.RouteFilter;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Configuration
public class ZuulFallbackConfiguration {

	@Bean
	PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	RouteFilter routeFilter() {
		return new RouteFilter();
	}
	@Bean
	ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
    public FallbackProvider  zuulFallbackProvider() {
        return new FallbackProvider () {
 
            public String getRoute() {
                // Might be confusing: it's the serviceId property and not the route
                return null;
            }

			@Override
			public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
				return new ClientHttpResponse() {
                    @Override
                    public HttpStatus getStatusCode() throws IOException {
                        return HttpStatus.OK;
                    }
 
                    @Override
                    public int getRawStatusCode() throws IOException {
                        return HttpStatus.OK.value();
                    }
 
                    @Override
                    public String getStatusText() throws IOException {
                        return HttpStatus.OK.toString();
                    }
 
                    @Override
                    public void close() {}
 
                    @Override
                    public InputStream getBody() throws IOException {
                        return new ByteArrayInputStream("{\"EchecA\":\"Désolé, Service Ko!\",\"\":\"?\",\"id\":null}".getBytes());
                    }
 
                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        return headers;
                    }
                };
			}
        };
    }
}
