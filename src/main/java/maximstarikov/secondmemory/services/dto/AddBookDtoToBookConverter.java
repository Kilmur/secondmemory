package maximstarikov.secondmemory.services.dto;

import maximstarikov.secondmemory.model.Book;
import maximstarikov.secondmemory.model.dto.AddBookDto;

public interface AddBookDtoToBookConverter {

    Book convert(AddBookDto dto);
}
