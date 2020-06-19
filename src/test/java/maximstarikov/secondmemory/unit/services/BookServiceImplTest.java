package maximstarikov.secondmemory.unit.services;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.repository.BookRepository;
import maximstarikov.secondmemory.services.UserService;
import maximstarikov.secondmemory.services.impl.BookServiceImpl;
import org.apache.log4j.BasicConfigurator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private UserService userService;

    @MockBean
    private BookRepository bookRepository;

    @BeforeClass
    public static void before() {
        BasicConfigurator.configure();
    }

    @Test
    public void addBookForCurrentUser() {
        Book newBook = new Book();
        Mockito.when(userService.getCurrentUser()).thenReturn(ServiceResult.success(new User()));

        ServiceResult<User> userServiceResult = bookService.addBookForCurrentUser(newBook);
        User user = userServiceResult.get();

        Assert.assertTrue(userServiceResult.isOk());
        Assert.assertTrue(user.getBooks().size() == 1);
        Assert.assertTrue(user.getBooks().contains(newBook));
        Mockito.verify(userService, Mockito.times(1)).save(user);
    }

    @Test
    public void testNotUserForAddBookForCurrentUser() {
        String errorMessage = "Test error message";
        Mockito.when(userService.getCurrentUser()).thenReturn(ServiceResult.error(errorMessage));

        ServiceResult<User> userServiceResult = bookService.addBookForCurrentUser(new Book());

        Assert.assertFalse(userServiceResult.isOk());
        Assert.assertTrue(CoreMatchers.is(userServiceResult.getErrorMessage()).matches(errorMessage));
        Mockito.verify(userService, Mockito.times(0)).save(any());
    }


}
