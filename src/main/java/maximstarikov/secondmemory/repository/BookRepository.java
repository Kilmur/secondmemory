package maximstarikov.secondmemory.repository;

import maximstarikov.secondmemory.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookByNameAndAuthor(String name, String author);
}
