package com.com.websockets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(value = {"com.com"})
@SpringBootApplication
@EnableScheduling
public class WebsocketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketsApplication.class, args);
	}

}
