package com.project.flightDataMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class FlightDataMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightDataMicroServiceApplication.class, args);
	}
	

}

