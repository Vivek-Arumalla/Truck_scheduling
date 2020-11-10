package com.cognizant.dcservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DcserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcserviceApplication.class, args);
	}

}
