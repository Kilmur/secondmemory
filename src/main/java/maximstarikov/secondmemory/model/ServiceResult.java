package maximstarikov.secondmemory.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class ServiceResult<T> {

    private boolean ok;
    private String errorMessage;
    @Getter(AccessLevel.NONE)
    private T result;

    private ServiceResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private ServiceResult(T result) {
        this.result = result;
        ok = true;
    }

    public T get() {
        return result;
    }

    public static ServiceResult error() {
        return new ServiceResult();
    }

    public static ServiceResult error(String message) {
        return new ServiceResult(message);
    }

    public static <T> ServiceResult<T> success(@NotNull T result) {
        return new ServiceResult<T>(result);
    }

}
