package maximstarikov.secondmemory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AllBookController {

    @GetMapping("/allbooks")
    public String showAllBooks(Model model) {
        model.addAttribute("book1", "1984");
        model.addAttribute("book2", "Три товарища");
        model.addAttribute("book3", "Анна Каренина");
        return "aaa";
    }




}
