package com.ldce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserNotAllowedToAccess extends RuntimeException {
	public UserNotAllowedToAccess(String exception) {
		super(exception);
	}
}