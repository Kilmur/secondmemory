package maximstarikov.secondmemory.enums;

import lombok.Getter;

@Getter
public enum ResponseCodes {

    OK("200"),
    SERVER_ERROR("500");

    private String code;

    ResponseCodes(String code) {
        this.code = code;
    }
}
