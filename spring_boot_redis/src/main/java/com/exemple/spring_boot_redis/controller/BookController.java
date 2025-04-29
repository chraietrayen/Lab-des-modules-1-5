package com.exemple.spring_boot_redis.controller;

import com.exemple.spring_boot_redis.model.Book;
import com.exemple.spring_boot_redis.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.save(book);
        return ResponseEntity.created(URI.create("/api/books/" + savedBook.getId()))
                .body(savedBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        long start = System.currentTimeMillis();
        var book = bookService.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        System.out.println("Request took: " + (System.currentTimeMillis() - start) + "ms");
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return ResponseEntity.ok(bookService.save(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cache")
    public ResponseEntity<Void> clearCache() {
        bookService.clearCache();
        return ResponseEntity.noContent().build();
    }
}