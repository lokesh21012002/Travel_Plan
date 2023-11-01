package com.example.travelplan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;




@SpringBootApplication
public class TravelPlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelPlanApplication.class, args);
	}

}
