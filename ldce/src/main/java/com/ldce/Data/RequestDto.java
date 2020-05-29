package com.ldce.Data;

import java.util.List;

import com.ldce.Main.Request;

public class RequestDto {
	String faculty_comment;
	int faculty_approve;
	List<Request> request;
	public String getFaculty_comment() {
		return faculty_comment;
	}
	public void setFaculty_comment(String faculty_comment) {
		this.faculty_comment = faculty_comment;
	}
	public int getFaculty_approve() {
		return faculty_approve;
	}
	public void setFaculty_approve(int faculty_approve) {
		this.faculty_approve = faculty_approve;
	}
	public List<Request> getRequest() {
		return request;
	}
	public void setRequest(List<Request> request) {
		this.request = request;
	}
	  
	
	
	
	
	
}
