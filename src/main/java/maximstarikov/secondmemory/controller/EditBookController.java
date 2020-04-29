package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editbook")
public class EditBookController {

    private BookRepository bookRepository;

    @GetMapping
    public String showEditBookPage(@RequestParam int id, Model model) {
        Book bookFromDb = bookRepository.findById(id).get();
        model.addAttribute("book", bookFromDb);
        return "editBook";
    }

    @PostMapping
    public String changeBook(Book book) {
        bookRepository.save(book);
        return "redirect:/allbooks";
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
