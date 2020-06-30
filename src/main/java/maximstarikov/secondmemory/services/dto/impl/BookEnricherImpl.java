package maximstarikov.secondmemory.services.dto.impl;

import maximstarikov.secondmemory.enums.ResponseCode;
import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.dto.BookDto;
import maximstarikov.secondmemory.model.dto.BookResponseWithResult;
import maximstarikov.secondmemory.services.dto.BookEnricher;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookEnricherImpl implements BookEnricher {

    public BookResponseWithResult createResponse(Set<Book> books) {
        BookResponseWithResult response = new BookResponseWithResult();
        response.setCode(ResponseCode.OK.getCode());
        response.setMessage(ResponseCode.OK.name());
        response.setResult(books.stream().map(BookEnricherImpl::convertBookToDto).collect(Collectors.toList()));
        return response;
    }

    private static BookDto convertBookToDto(Book book) {
        return new BookDto(book.getName(), book.getAuthor());
    }
}
