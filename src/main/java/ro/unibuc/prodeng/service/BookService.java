package ro.unibuc.prodeng.service;

import org.springframework.stereotype.Service;
import ro.unibuc.prodeng.exception.BadRequestException;
import ro.unibuc.prodeng.exception.ResourceNotFoundException;
import ro.unibuc.prodeng.model.Book;
import ro.unibuc.prodeng.repository.BookRepository;
import ro.unibuc.prodeng.request.BookRequest;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(BookRequest request) {
        Book book = new Book(request.getTitle(), request.getAuthor(), request.getIsbn(), request.getTotalCopies());
        return bookRepository.save(book);
    }

    public Book borrowBook(String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new BadRequestException("No copies available for borrowing");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        return bookRepository.save(book);
    }

    public Book returnBook(String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (book.getAvailableCopies() >= book.getTotalCopies()) {
            throw new BadRequestException("All copies are already returned");
        }

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        return bookRepository.save(book);
    }
}