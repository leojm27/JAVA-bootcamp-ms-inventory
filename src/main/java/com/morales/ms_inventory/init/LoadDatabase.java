package com.morales.ms_inventory.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class LoadDatabase {

    @Transactional
    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            System.out.println("Base de Datos inicializada correctamente.");
        };
    }
}

