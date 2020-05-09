package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editbook")
public class EditBookController {

    private BookService bookService;

    private static final String NOT_ACCESS_BOOK_WARNING_MESSAGE = "Нет доступа к запрашиваемой книге. В начале добавьте ее в свою коллекцию";

    @GetMapping
    public String showEditBookPage(@RequestParam int id, Model model) {
        Book bookFromDb = bookService.getById(id);
        if (bookFromDb == null) {
            return "redirect:/error";
        }
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isBookAccessThisUser = bookFromDb.getUsers().stream().anyMatch(user -> user.getUsername().equals(userName));
        if (!isBookAccessThisUser) {
            model.addAttribute("message", NOT_ACCESS_BOOK_WARNING_MESSAGE);
            return "editBook";
        }
        model.addAttribute("book", bookFromDb);
        return "editBook";
    }

    @PostMapping
    public String changeBook(Book book) {
        bookService.save(book);
        return "redirect:/allbooks";
    }

    @Autowired
    public void setBookRepository(BookService bookService) {
        this.bookService = bookService;
    }
}
