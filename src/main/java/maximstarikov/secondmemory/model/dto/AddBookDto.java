package maximstarikov.secondmemory.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddBookDto {

    @NotBlank
    private String name;
    @NotBlank
    private String author;

}
