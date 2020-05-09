package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.services.BookService;
import maximstarikov.secondmemory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addbook")
public class AddBookController {

    private UserService userService;
    private BookService bookService;

    @PostMapping
    public String addBook(Book newBook, Model model) {
        // TODO : посмотреть как можно сделать поиск автоматически при добавлении
        Book bookFromDb = bookService.getByNameAndAuthor(newBook.getName(), newBook.getAuthor());
        User user = userService.getByName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (bookFromDb != null) {
            user.getBooks().add(bookFromDb);
        } else {
            user.getBooks().add(newBook);
        }
        userService.save(user);
        model.addAttribute("newBook", newBook);
        return "addBook";
    }

    @GetMapping
    public String showAddBookPage(Model model) {
        return "addBook";
    }

    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBookRepository(BookService bookService) {
        this.bookService = bookService;
    }
}
