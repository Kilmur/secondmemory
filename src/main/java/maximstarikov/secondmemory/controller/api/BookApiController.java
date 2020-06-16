package maximstarikov.secondmemory.controller.api;

import maximstarikov.secondmemory.model.dto.AddBookRequest;
import maximstarikov.secondmemory.model.dto.BasicBookResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookApiController {

    @GetMapping("all")
    public BasicBookResponse getAllUserBook() {

        return new BasicBookResponse("200", "Ok from GET ..../all");
    }

    @PostMapping("add")
    public BasicBookResponse addBook(@RequestBody AddBookRequest request) {

        return new BasicBookResponse("200", "Ok from POST ..../add");
    }
    

}
