package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.services.UserService;
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

    private UserService userService;

    @GetMapping
    public String showAllBooks(Model model) {
        User user = userService.getByName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("books", user.getBooks());
        return "allbooks";
    }

    @GetMapping("delete")
    public String deleteBook(@RequestParam int id, Model model) {
        User user = userService.getByName(SecurityContextHolder.getContext().getAuthentication().getName());
        user.getBooks().removeIf(book -> book.getId() == id);
        userService.save(user);
        model.addAttribute("books", user.getBooks());
        return "allbooks";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
