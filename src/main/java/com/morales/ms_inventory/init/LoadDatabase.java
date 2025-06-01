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
            System.out.println("Initializing Inventory Database...");
            /*
            try {
                if(categoriaRepository.findAll().isEmpty()){
                    System.out.println("Creando registros de categorias...");
                    Categoria categoria1 = new Categoria("Hogar","Artículos para el hogar y la cocina.");

                    System.out.println("Categoria Hogar: " + categoriaRepository.save(categoria1));
                } else {
                    System.out.println("La base de datos ya contiene registros, no se realizarán inserciones iniciales.");
                }
            } catch (Exception e){
                e.printStackTrace();
                System.err.println("Error durante inicialización de Base de Datos" + e.getMessage());
            }
            */
        };
    }
}

