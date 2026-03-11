package ro.unibuc.prodeng.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.unibuc.prodeng.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}