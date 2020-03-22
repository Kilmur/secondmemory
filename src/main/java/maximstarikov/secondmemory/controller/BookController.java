package maximstarikov.secondmemory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/allbooks")
public class BookController {

    @GetMapping
    public String showAllBooks(Model model) {
        model.addAttribute("book1", "1984");
        model.addAttribute("book2", "Три товарища");
        model.addAttribute("book3", "Анна Каренина");
        return "aaa";
    }

    @PostMapping("add")
    public String addBook(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "addBook";
    }


}
