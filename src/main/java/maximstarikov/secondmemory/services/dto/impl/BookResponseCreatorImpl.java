package maximstarikov.secondmemory.services.dto.impl;

import lombok.RequiredArgsConstructor;
import maximstarikov.secondmemory.enums.ResponseCodes;
import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.dto.BookResponseDto;
import maximstarikov.secondmemory.model.dto.BookResponse;
import maximstarikov.secondmemory.services.dto.BookResponseCreator;
import maximstarikov.secondmemory.services.dto.BookToBookResponseDtoConverter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookResponseCreatorImpl implements BookResponseCreator {

    private final BookToBookResponseDtoConverter bookToBookResponseDtoConverter;

    public BookResponse createResponse(Set<Book> books) {
        BookResponse response = new BookResponse();
        response.setCode(ResponseCodes.OK.getCode());
        response.setMessage(ResponseCodes.OK.name());
        response.setResult(books.stream().map(bookToBookResponseDtoConverter::convert).collect(Collectors.toList()));
        return response;
    }

    @Override
    public BookResponse createResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setCode(ResponseCodes.OK.getCode());
        response.setMessage(ResponseCodes.OK.name());
        response.setResult(Arrays.asList(bookToBookResponseDtoConverter.convert(book)));
        return response;
    }
}
