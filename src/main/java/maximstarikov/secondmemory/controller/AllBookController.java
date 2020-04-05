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

@Controller
public class AllBookController {

    private UserRepository userRepository;

    @GetMapping("/allbooks")
    public String showAllBooks(Model model) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("books", user.getBooks());
        return "allbooks";
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
