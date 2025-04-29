package com.exemple.spring_boot_redis.repository;

import com.exemple.spring_boot_redis.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookRepository {
    private final Map<Long, Book> bookStore = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        bookStore.put(1L, new Book(1L, "To Kill a Mockingbird", "Harper Lee", "978-0061120084", 14.99));
        bookStore.put(2L, new Book(2L, "1984", "George Orwell", "978-0451524935", 12.99));
        bookStore.put(3L, new Book(3L, "The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", 11.99));
    }

    public Collection<Book> findAll() {
        return bookStore.values();
    }

    public Optional<Book> findById(Long id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return Optional.empty();
        }
        System.out.println("Fetching book from DB: " + id);
        return Optional.ofNullable(bookStore.get(id));
    }

    public Book save(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        bookStore.put(book.getId(), book);
        return book;
    }

    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        bookStore.remove(id);
    }

    public boolean existsById(Long id) {
        return bookStore.containsKey(id);
    }
}