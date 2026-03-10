package ro.unibuc.prodeng.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.prodeng.model.Book;
import ro.unibuc.prodeng.request.BookRequest;
import ro.unibuc.prodeng.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookRequest request) {
        return new ResponseEntity<>(bookService.addBook(request), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<Book> borrowBook(@PathVariable String id) {
        return ResponseEntity.ok(bookService.borrowBook(id));
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<Book> returnBook(@PathVariable String id) {
        return ResponseEntity.ok(bookService.returnBook(id));
    }
}