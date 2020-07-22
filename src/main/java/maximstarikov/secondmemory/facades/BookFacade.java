package maximstarikov.secondmemory.facades;

import maximstarikov.secondmemory.model.dto.AddBookDto;
import maximstarikov.secondmemory.model.dto.BookResponse;

public interface BookFacade {

    BookResponse getBookById(Integer id);

    BookResponse getAllUserBooks();

    BookResponse addBook(AddBookDto bookDto);
}
