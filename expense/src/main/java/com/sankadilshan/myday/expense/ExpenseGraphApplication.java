package com.sankadilshan.myday.expense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@SpringBootApplication
public class ExpenseGraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseGraphApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Clock clock() {
		return Clock.systemUTC();
	}

}
