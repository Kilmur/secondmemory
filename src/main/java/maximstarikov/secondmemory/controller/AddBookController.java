package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.repository.BookRepository;
import maximstarikov.secondmemory.repository.UserRepository;
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

    private UserRepository userRepository;
    private BookRepository bookRepository;

    @PostMapping
    public String addBook(Book newBook, Model model) {
        // TODO : посмотреть как можно сделать поиск автоматически при добавлении
        Book bookFromDb = bookRepository.findBookByNameAndAuthor(newBook.getName(), newBook.getAuthor());
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (bookFromDb != null) {
            user.getBooks().add(bookFromDb);
        } else {
            user.getBooks().add(newBook);
        }
        userRepository.save(user);
        model.addAttribute("newBook", newBook);
        return "addBook";
    }

    @GetMapping
    public String showAddBookPage(Model model) {
        return "addBook";
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
