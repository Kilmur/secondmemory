package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllBookController {

    private BookRepository bookRepository;

    @GetMapping("/allbooks")
    public String showAllBooks(Model model) {
        Iterable<Book> allBooks = bookRepository.findAll();
        model.addAttribute("books", allBooks);
        return "allbooks";
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
