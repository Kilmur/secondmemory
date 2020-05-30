package maximstarikov.secondmemory.controller.api;

import maximstarikov.secondmemory.model.dto.AddBookRequest;
import maximstarikov.secondmemory.model.dto.AddBookResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addbook")
public class AddBookApiController {

    @PostMapping
    public AddBookResponse addBook(@RequestBody AddBookRequest request) {

        return new AddBookResponse("200", "Ok");
    }
    

}
