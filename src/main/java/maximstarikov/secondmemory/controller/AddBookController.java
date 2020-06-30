package maximstarikov.secondmemory.controller;

import lombok.RequiredArgsConstructor;
import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.services.dao.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addbook")
@RequiredArgsConstructor
public class AddBookController {

    private final BookService bookService;

    @PostMapping
    public String addBook(Book newBook, Model model) {
        ServiceResult<User> addBookResult = bookService.addBookForCurrentUser(newBook);
        if (!addBookResult.isOk()) {
            model.addAttribute("errorMessage", "Книга не добавлена, попробуйте снова");
        }
        model.addAttribute("newBook", newBook);
        return "addBook";
    }

    @GetMapping
    public String showAddBookPage(Model model) {
        return "addBook";
    }

}
