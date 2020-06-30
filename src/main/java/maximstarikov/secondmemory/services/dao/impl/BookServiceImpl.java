package maximstarikov.secondmemory.services.dao.impl;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.repository.BookRepository;
import maximstarikov.secondmemory.services.dao.BookService;
import maximstarikov.secondmemory.services.dao.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);

    private BookRepository bookRepository;
    private UserService userService;

    private static final String NOT_ACCESS_BOOK_WARNING_MESSAGE = "Нет доступа к запрашиваемой книге. В начале добавьте ее в свою коллекцию";
    private final static String NOT_FOUND_BY_NAME_AND_AUTHOR_MASK = "Не найдена книга с названием %s и автором %s в БД";

    public ServiceResult<User> addBookForCurrentUser(Book newBook) {
        ServiceResult<Book> bookFromDbResult = getByNameAndAuthor(newBook.getName(), newBook.getAuthor());
        ServiceResult<User> userByNameResult = userService.getCurrentUser();
        if (!userByNameResult.isOk()) {
            LOGGER.error(userByNameResult.getErrorMessage());
            return userByNameResult;
        }
        User user = userByNameResult.get();
        if (bookFromDbResult.isOk()) {
            user.getBooks().add(bookFromDbResult.get());
        } else {
            LOGGER.info(bookFromDbResult.getErrorMessage());
            user.getBooks().add(newBook);
        }
        userService.save(user);
        return ServiceResult.success(user);
    }

    public ServiceResult<User> deleteBookFromCurrentUser(int id) {
        ServiceResult<User> currentUserResult = userService.getCurrentUser();
        if (!currentUserResult.isOk()) {
            LOGGER.error(currentUserResult.getErrorMessage());
            return currentUserResult;
        }
        User user = currentUserResult.get();
        user.getBooks().removeIf(book -> book.getId() == id);
        userService.save(user);
        return ServiceResult.success(user);
    }

    public ServiceResult<Book> getAccessBookForCurrentUser(int id) {
        ServiceResult<Book> bookResult = getById(id);
        if (!bookResult.isOk()) {
            LOGGER.error(bookResult.getErrorMessage());
            return ServiceResult.error("Кто-то всё сломал, попробуйте позже");
        }
        Book bookFromDb = bookResult.get();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isBookAccessThisUser = bookFromDb.getUsers().stream().anyMatch(user -> user.getUsername().equals(userName));
        if (!isBookAccessThisUser) {
            return ServiceResult.error(NOT_ACCESS_BOOK_WARNING_MESSAGE);
        }
        return ServiceResult.success(bookFromDb);
    }

    public ServiceResult<Book> getByNameAndAuthor(String name, String author) {
        Optional<Book> optionalBook = bookRepository.findBookByNameAndAuthor(name, author);
        if (!optionalBook.isPresent()) {
            return ServiceResult.error(String.format(NOT_FOUND_BY_NAME_AND_AUTHOR_MASK, name, author));
        }
        return ServiceResult.success(optionalBook.get());
    }

    public ServiceResult<Book> getById(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            return ServiceResult.error("Not found book by id - " + id);
        }
        return ServiceResult.success(optionalBook.get());
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
