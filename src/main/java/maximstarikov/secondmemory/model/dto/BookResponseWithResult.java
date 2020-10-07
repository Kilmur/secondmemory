package maximstarikov.secondmemory.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class BookResponseWithResult extends BasicBookResponse {

    private List<BookDto> result;

}
