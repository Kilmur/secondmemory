package maximstarikov.secondmemory.repository;

import maximstarikov.secondmemory.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findBookByNameAndAuthor(String name, String author);
}
