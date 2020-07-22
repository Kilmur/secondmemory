package maximstarikov.secondmemory.services.dto;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.dto.BookResponse;

import java.util.Set;

public interface BookResponseCreator {

    BookResponse createResponse(Set<Book> books);

    BookResponse createResponse(Book book);

}
