package com.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Bad 404~~~~~~~~~~")
public class MyException extends RuntimeException {
    private String errorCode = "ERROR_UNKNOWN";


    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message, Throwable e) {
        super(message, e);
    }

    public MyException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public MyException(String errorCode, String message, Throwable e) {
        super(message, e);
        this.errorCode = errorCode;
    }

}
