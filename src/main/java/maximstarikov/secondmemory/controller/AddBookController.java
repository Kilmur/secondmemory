package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.repository.BookRepository;
import maximstarikov.secondmemory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/addbook")
public class AddBookController {

    private UserRepository userRepository;

    @PostMapping
    public String addBook(@RequestParam String name, @RequestParam String author, Model model) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.getBooks().add(book);
        userRepository.save(user);
        model.addAttribute("bookName", name);
        return "addBook";
    }

    @GetMapping
    public String showAddBookPage(Model model) {
        return "addBook";
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
