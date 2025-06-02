package com.morales.ms_inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MsInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsInventoryApplication.class, args);
		System.out.println("Microservicio 'ms-inventory' iniciado correctamente.");
	}

}
