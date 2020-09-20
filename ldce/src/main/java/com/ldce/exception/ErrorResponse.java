package com.ldce.exception;

import java.util.List;

public class ErrorResponse {

	String error;
	List<String> details;

	public String getString() {
		return error;
	}

	public void setString(String string) {
		this.error = string;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public ErrorResponse(String string, List<String> details) {
		super();
		this.error = string;
		this.details = details;
	}

}