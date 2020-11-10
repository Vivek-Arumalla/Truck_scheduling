package com.cognizant.zuulservice;

import com.cognizant.zuulservice.filter.SimpleFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class ZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServiceApplication.class, args);
	}

	@Bean
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}

}
