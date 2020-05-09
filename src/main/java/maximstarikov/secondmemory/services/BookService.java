package maximstarikov.secondmemory.services;

import maximstarikov.secondmemory.model.Book;

public interface BookService {

    Book getByNameAndAuthor(String name, String author);

    Book getById(int id);

    void save(Book book);

}
