package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.services.BookService;
import maximstarikov.secondmemory.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/allbooks")
public class AllBookController {

    private static final Logger LOGGER = Logger.getLogger(AllBookController.class);

    private UserService userService;
    private BookService bookService;

    @GetMapping
    public String showAllBooks(Model model) {
        ServiceResult<User> currentUserResult = userService.getCurrentUser();
        if (!currentUserResult.isOk()) {
            LOGGER.error(currentUserResult.getErrorMessage());
            return "redirect:/error";
        }
        model.addAttribute("books", currentUserResult.get().getBooks());
        return "allbooks";
    }

    @GetMapping("delete")
    public String deleteBook(@RequestParam int id, Model model) {
        ServiceResult<User> deleteBookResult = bookService.deleteBookFromCurrentUser(id);
        if (!deleteBookResult.isOk()) {
            model.addAttribute("errorMessage", "Не получилось удалить книгу, попробуйте снова");
            return "allbooks";
        }
        model.addAttribute("books", deleteBookResult.get().getBooks());
        return "allbooks";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}
