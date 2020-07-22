package maximstarikov.secondmemory.services.dto.impl;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.dto.BookResponseDto;
import maximstarikov.secondmemory.services.dto.BookToBookResponseDtoConverter;
import org.springframework.stereotype.Service;

@Service
public class BookToBookResponseDtoConverterImpl implements BookToBookResponseDtoConverter {

    @Override
    public BookResponseDto convert(Book book) {
        return new BookResponseDto(book.getName(), book.getAuthor());
    }
}
