package com.mypet.mungmoong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MungmoongApplication {

	public static void main(String[] args) {
		SpringApplication.run(MungmoongApplication.class, args);
	}

}
