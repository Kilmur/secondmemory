package maximstarikov.secondmemory.services;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;

public interface BookService {

    ServiceResult<User> addBookForCurrentUser(Book newBook);

    ServiceResult<User> deleteBookFromCurrentUser(int id);

    ServiceResult<Book> getByNameAndAuthor(String name, String author);

    ServiceResult<Book> getAccessBookForCurrentUser(int id);

    ServiceResult<Book> getById(int id);

    void save(Book book);

}
