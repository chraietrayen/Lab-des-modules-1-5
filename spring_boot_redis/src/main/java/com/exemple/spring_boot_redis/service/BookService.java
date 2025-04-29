package com.exemple.spring_boot_redis.service;

import com.exemple.spring_boot_redis.model.Book;
import com.exemple.spring_boot_redis.repository.BookRepository;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = "books")
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Optional<Book> findById(Long id) {
        Assert.notNull(id, "Book ID cannot be null");
        return bookRepository.findById(id);
    }

    @CachePut(key = "#book.id")
    public Book save(Book book) {
        Assert.notNull(book, "Book cannot be null");

        // If new book (no ID), generate a new ID
        if (book.getId() == null) {
            Long newId = getNextAvailableId();
            book.setId(newId);
        }

        return bookRepository.save(book);
    }

    private Long getNextAvailableId() {
        // Get the maximum existing ID from the repository
        Long maxId = bookRepository.findAll().stream()
                .map(Book::getId)
                .max(Long::compare)
                .orElse(0L);

        return maxId + 1;
    }

    @CacheEvict(key = "#id")
    public void delete(Long id) {
        Assert.notNull(id, "Book ID cannot be null");
        bookRepository.delete(id);
    }

    @CacheEvict(allEntries = true)
    public void clearCache() {
        // Clears entire 'books' cache
    }
}