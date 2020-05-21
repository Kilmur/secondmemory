package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.services.BookService;
import maximstarikov.secondmemory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addbook")
public class AddBookController {

    private BookService bookService;

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

    @Autowired
    public void setUserRepository(BookService bookService) {
        this.bookService = bookService;
    }

}
