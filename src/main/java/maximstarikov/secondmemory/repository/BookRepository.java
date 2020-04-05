package maximstarikov.secondmemory.repository;

import maximstarikov.secondmemory.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findBookByNameAndAuthor(String name, String author);
}
