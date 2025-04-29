package com.exemple.bookcatalog.config;

import com.exemple.bookcatalog.model.Book;
import com.exemple.bookcatalog.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    @Profile("!prod")
    public CommandLineRunner loadData(BookRepository bookRepository) {
        return args -> {
            if (bookRepository.count() == 0) {
                bookRepository.save(new Book(
                        "To Kill a Mockingbird",
                        "Harper Lee",
                        "9780061120084",
                        LocalDate.of(1960, 7, 11),
                        "A novel about racial injustice...",
                        281
                ));

                bookRepository.save(new Book(
                        "1984",
                        "George Orwell",
                        "9780451524935",
                        LocalDate.of(1949, 6, 8),
                        "Dystopian novel...",
                        328
                ));

                System.out.println("Sample books loaded into database!");
            }
        };
    }
}