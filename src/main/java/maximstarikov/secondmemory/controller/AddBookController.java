package maximstarikov.secondmemory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/addbook")
public class AddBookController {

    @PostMapping
    public String addBook(@RequestParam String name, Model model) {
        model.addAttribute("bookName", name);
        return "addBook";
    }

    @GetMapping
    public String showAddBookPage(Model model) {
        model.addAttribute("bookName", "пока никакая");
        return "addBook";
    }


}
