package maximstarikov.secondmemory.services.dto;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.dto.BookResponseWithResult;

import java.util.Set;

public interface BookEnricher {

    BookResponseWithResult createResponse(Set<Book> books);

}
