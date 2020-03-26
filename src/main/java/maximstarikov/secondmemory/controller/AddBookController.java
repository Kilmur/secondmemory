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
@RequestMapping("/addbook")
public class AddBookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public String addBook(@RequestParam String name, @RequestParam String author, Model model) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        bookRepository.save(book);
        model.addAttribute("bookName", name);
        return "addBook";
    }

    @GetMapping
    public String showAddBookPage(Model model) {
        model.addAttribute("bookName", "пока никакая");
        return "addBook";
    }


}
