package maximstarikov.secondmemory.services.dto.impl;

import maximstarikov.secondmemory.enums.ResponseCodes;
import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.dto.BookResponseDto;
import maximstarikov.secondmemory.model.dto.BookResponse;
import maximstarikov.secondmemory.services.dto.BookEnricher;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookEnricherImpl implements BookEnricher {

    public BookResponse createResponse(Set<Book> books) {
        BookResponse response = new BookResponse();
        response.setCode(ResponseCodes.OK.getCode());
        response.setMessage(ResponseCodes.OK.name());
        response.setResult(books.stream().map(BookEnricherImpl::convertBookToDto).collect(Collectors.toList()));
        return response;
    }

    @Override
    public BookResponse createResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setCode(ResponseCodes.OK.getCode());
        response.setMessage(ResponseCodes.OK.name());
        response.setResult(Arrays.asList(convertBookToDto(book)));
        return response;
    }

    private static BookResponseDto convertBookToDto(Book book) {
        return new BookResponseDto(book.getName(), book.getAuthor());
    }
}
