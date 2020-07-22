package maximstarikov.secondmemory.services.dto.impl;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.dto.AddBookDto;
import maximstarikov.secondmemory.services.dto.AddBookDtoToBookConverter;
import org.springframework.stereotype.Service;

@Service
public class AddBookDtoToBookConverterImpl implements AddBookDtoToBookConverter {

    @Override
    public Book convert(AddBookDto dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        return book;
    }
}
