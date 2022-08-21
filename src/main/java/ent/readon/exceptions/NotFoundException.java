package ent.readon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private int code;

    public NotFoundException(String message) {
        super(message);
        code = 404;
    }
}
