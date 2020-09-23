package com.ldce.Data;

import java.util.List;

import com.ldce.Model.Request.Request;

public class RequestDto {
	String faculty_comment;
	int faculty_approve;
	String photo_url;
	String enrollment;
	String first_name;
	String middle_name;
	String last_name;
	int addmission_year;
	int graduation_year;
	int semester;
	int branch;
	String course;
	List<Request> request;

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setAddmission_year(int addmission_year) {
		this.addmission_year = addmission_year;
	}

	public void setGraduation_year(int graduation_year) {
		this.graduation_year = graduation_year;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getEnrollment() {
		return enrollment;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public int getAddmission_year() {
		return addmission_year;
	}

	public int getGraduation_year() {
		return graduation_year;
	}

	public int getSemester() {
		return semester;
	}

	public int getBranch() {
		return branch;
	}

	public String getCourse() {
		return course;
	}



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
