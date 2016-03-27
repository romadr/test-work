package ru.roman.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException {
    public BadRequest() {
    }

    public BadRequest(String message) {
        super(message);
    }
}
