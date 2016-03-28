package ru.roman.errors;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalError extends RuntimeException {
    public InternalError() {
    }

    public InternalError(String message) {
        super(message);
    }

    public InternalError(Throwable throwable) {
        super(throwable);
    }
}
