package maximstarikov.secondmemory.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookResponseWithResult extends BasicBookResponse {

    private List<BookDto> result;

}
