package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.example.model"})
@SpringBootApplication
public class SwCollegeBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwCollegeBeApplication.class, args);
	}

}
