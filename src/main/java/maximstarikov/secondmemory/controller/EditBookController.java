package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public String showEditBookPage(@RequestParam int id, Model model) {
        ServiceResult<Book> getBookResult = bookService.getAccessBookForCurrentUser(id);
        if (!getBookResult.isOk()) {
            model.addAttribute("message", getBookResult.getErrorMessage());
            return "editBook";
        }
        model.addAttribute("book", getBookResult.get());
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
