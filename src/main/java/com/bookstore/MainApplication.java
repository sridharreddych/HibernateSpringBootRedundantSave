package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("Redundant save:");
            bookstoreService.updateAuthorRedundantSave();

            System.out.println("\nRecommended:");
            bookstoreService.updateAuthorRecommended();
        };
    }
}

/*
 * 
 * Avoid Spring Redundant save() Call

Description: This application is an example when calling save() for an entity is redundant (not necessary).

Key points:

at flush time, Hibernate relies on dirty checking mechanism to determine the potential modifications in entities
for each modification, Hibernate automatically triggers the corresponding UPDATE statement without the need to explicitly call the save() method
behind the scene, this redundancy (calling save() when is not necessarily) doesn't affect the number of triggered queries, but it implies a performance penalty in the underlying Hibernate processes
 * 
 * 
 */
