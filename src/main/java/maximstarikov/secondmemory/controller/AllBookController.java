package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/allbooks")
public class AllBookController {

    private UserRepository userRepository;

    @GetMapping
    public String showAllBooks(Model model) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("books", user.getBooks());
        return "allbooks";
    }

    @GetMapping("delete")
    public String deleteBook(@RequestParam int id, Model model) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.getBooks().removeIf(book -> book.getId() == id);
        userRepository.save(user);
        model.addAttribute("books", user.getBooks());
        return "allbooks";
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
