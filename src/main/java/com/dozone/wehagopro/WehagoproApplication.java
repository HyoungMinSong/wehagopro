package com.dozone.wehagopro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class WehagoproApplication {

	public static void main(String[] args) {
		SpringApplication.run(WehagoproApplication.class, args);
	}

}
