package maximstarikov.secondmemory.controller.api;

import lombok.RequiredArgsConstructor;
import maximstarikov.secondmemory.facades.BookFacade;
import maximstarikov.secondmemory.model.dto.AddBookDto;
import maximstarikov.secondmemory.model.dto.BookResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookApiController {

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
    public BookResponse addBook(@RequestBody @Valid AddBookDto bookDto) {
        return bookFacade.addBook(bookDto);
    }

    @PutMapping("{id}")
    public BookResponse changeBook(@RequestBody AddBookDto request, @PathVariable int id) {

        return new BookResponse();
    }

    @DeleteMapping("{id}")
    public BookResponse deleteBook(@PathVariable int id) {

        return new BookResponse();
    }


}
