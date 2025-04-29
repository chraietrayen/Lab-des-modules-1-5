package com.exemple.bookcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookcatalogApplication {  // Note lowercase 'c' in 'catalog'
    public static void main(String[] args) {
        SpringApplication.run(BookcatalogApplication.class, args);
    }
}