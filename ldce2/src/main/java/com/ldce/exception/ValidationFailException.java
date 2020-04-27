package com.ldce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ValidationFailException extends RuntimeException {
	public ValidationFailException(String exception) {
        super(exception);
    }
}