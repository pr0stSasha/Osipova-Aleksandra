package com.example.MTS1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Mts1Application {

	public static void main(String[] args) {
		SpringApplication.run(Mts1Application.class, args);
	}
}