package com.emlakjet.demo.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmlakjetException extends RuntimeException {
    private int code;

    public EmlakjetException() {
        super();
    }

    public EmlakjetException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmlakjetException(String message) {
        super(message);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public EmlakjetException(int code, String message) {
        super(message);
        this.code = code;
    }

    public EmlakjetException(Throwable cause) {
        super(cause);
    }
}
