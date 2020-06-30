package maximstarikov.secondmemory.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {

    OK("200"),
    SERVER_ERROR("500");

    private String code;

    ResponseCode(String code) {
        this.code = code;
    }

}
