package maximstarikov.secondmemory.services.dto;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.dto.BookResponseDto;

public interface BookToBookResponseDtoConverter {

    BookResponseDto convert(Book book);
}
