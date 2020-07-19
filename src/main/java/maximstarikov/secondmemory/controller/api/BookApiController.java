package maximstarikov.secondmemory.controller.api;

import lombok.RequiredArgsConstructor;
import maximstarikov.secondmemory.facades.BookFacade;
import maximstarikov.secondmemory.model.dto.AddBookRequest;
import maximstarikov.secondmemory.model.dto.BookResponse;
import maximstarikov.secondmemory.services.dao.UserService;
import maximstarikov.secondmemory.services.dto.BookEnricher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookApiController {

    private final UserService userService;
    private final BookEnricher bookEnricher;
    private final BookFacade bookFacade;

    @GetMapping
    public BookResponse getAllUserBooks() {
        return bookFacade.getAllUserBooks();
    }

    @GetMapping("{id}")
    public BookResponse getUserBook(@PathVariable("id") Integer id) {
        return bookFacade.getBookById(id);
    }

    @PostMapping
    public BookResponse addBook(@RequestBody AddBookRequest request) {

        return new BookResponse();
    }

    @PutMapping("{id}")
    public BookResponse changeBook(@RequestBody AddBookRequest request, @PathVariable int id) {

        return new BookResponse();
    }

    @DeleteMapping("{id}")
    public BookResponse deleteBook(@PathVariable int id) {

        return new BookResponse();
    }


}
