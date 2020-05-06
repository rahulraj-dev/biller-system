package com.setu.biller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BillerApplication {

	public static void main(String[] args) {
	  System.out.println("Welcome to biller System.");
		SpringApplication.run(BillerApplication.class, args);
	}

}
