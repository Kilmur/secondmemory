package maximstarikov.secondmemory.services.impl;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.repository.BookRepository;
import maximstarikov.secondmemory.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public Book getByNameAndAuthor(String name, String author) {
        Optional<Book> optionalBook = bookRepository.findBookByNameAndAuthor(name, author);
        return optionalBook.orElse(null);
    }

    public Book getById(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
