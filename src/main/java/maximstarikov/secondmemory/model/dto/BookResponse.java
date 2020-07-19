package maximstarikov.secondmemory.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maximstarikov.secondmemory.enums.ResponseCodes;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {

    private String code;
    private String message;
    private List<BookResponseDto> result;

    public static BookResponse error(ResponseCodes responseCode, String message) {
        return BookResponse.builder().code(responseCode.getCode()).message(message).result(Collections.emptyList()).build();
    }

}
