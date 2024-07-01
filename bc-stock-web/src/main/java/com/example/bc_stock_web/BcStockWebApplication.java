package com.example.bc_stock_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BcStockWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcStockWebApplication.class, args);
	}

}
