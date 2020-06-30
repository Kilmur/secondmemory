package maximstarikov.secondmemory.controller.api;

import lombok.RequiredArgsConstructor;
import maximstarikov.secondmemory.enums.ResponseCode;
import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.model.dto.AddBookRequest;
import maximstarikov.secondmemory.model.dto.BasicBookResponse;
import maximstarikov.secondmemory.model.dto.BookDto;
import maximstarikov.secondmemory.model.dto.BookResponseWithResult;
import maximstarikov.secondmemory.services.dao.UserService;
import maximstarikov.secondmemory.services.dto.BookEnricher;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookApiController {

    private final UserService userService;
    private final BookEnricher bookEnricher;

    @GetMapping
    public BasicBookResponse getAllUserBooks() {
        ServiceResult<User> currentUserResult = userService.getCurrentUser();
        if (!currentUserResult.isOk()) {
            return new BasicBookResponse(ResponseCode.SERVER_ERROR.getCode(), "A little problems");
        }
        Set<Book> userBooks = currentUserResult.get().getBooks();
        return bookEnricher.createResponse(userBooks);
    }

    @GetMapping("{id}")
    public BasicBookResponse getUserBook(@PathVariable("id") Book book) {

        return new BasicBookResponse("200", "Ok from GET getUserBook");
    }

    @PostMapping
    public BasicBookResponse addBook(@RequestBody AddBookRequest request) {

        return new BasicBookResponse("200", "Ok from POST ");
    }

    @PutMapping("{id}")
    public BasicBookResponse changeBook(@RequestBody AddBookRequest request, @PathVariable int id) {

        return new BasicBookResponse("200", "Ok from PUT ");
    }

    @DeleteMapping("{id}")
    public BasicBookResponse deleteBook(@PathVariable int id) {

        return new BasicBookResponse("200", "OK from delete method");
    }
    

}
