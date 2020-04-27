package com.ldce.exception;

import java.util.List;

public class ErrorResponse {
	
	String string;
	 List<String> details;
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	public ErrorResponse(String string, List<String> details) {
		super();
		this.string = string;
		this.details = details;
	}
			

}