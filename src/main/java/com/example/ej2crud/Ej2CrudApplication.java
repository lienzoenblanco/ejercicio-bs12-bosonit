package com.example.ej2crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Ej2CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ej2CrudApplication.class, args);
	}

}
