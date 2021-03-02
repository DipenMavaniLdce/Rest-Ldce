package com.ldce.Model.Admin;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.time.LocalDate;


@Entity
public class Admin {
	@Id
	String faculty_id;
	String first_name;
	String middle_name;
	String last_name;
	String designation;

	String role;
	int branch;
	int branch_year;
	String course;

	String photo_name;
	String photo_url;
	String photo_size;
	String photo_type;

	String sign_name;
	String sign_url;
	String sign_size;
	String sign_type;
	@Email
	String email;
	String contact;
	@JsonIgnore
	String password;
	String gender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate date_of_birth;

	String blood_group;

	String re_add_l1;
	String re_add_l2;
	String re_add_city;
	String re_add_state;
	String re_add_pin_code;
	String re_add_country;




	public String getFaculty_id() {
		return faculty_id;
	}

	public void setFaculty_id(String faculty_id) {
		this.faculty_id = faculty_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public int getBranch_year() {
		return branch_year;
	}

	public void setBranch_year(int branch_year) {
		this.branch_year = branch_year;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public String getRe_add_l1() {
		return re_add_l1;
	}

	public void setRe_add_l1(String re_add_l1) {
		this.re_add_l1 = re_add_l1;
	}

	public String getRe_add_l2() {
		return re_add_l2;
	}

	public void setRe_add_l2(String re_add_l2) {
		this.re_add_l2 = re_add_l2;
	}

	public String getRe_add_city() {
		return re_add_city;
	}

	public void setRe_add_city(String re_add_city) {
		this.re_add_city = re_add_city;
	}

	public String getRe_add_state() {
		return re_add_state;
	}

	public void setRe_add_state(String re_add_state) {
		this.re_add_state = re_add_state;
	}

	public String getRe_add_pin_code() {
		return re_add_pin_code;
	}

	public void setRe_add_pin_code(String re_add_pin_code) {
		this.re_add_pin_code = re_add_pin_code;
	}

	public String getRe_add_country() {
		return re_add_country;
	}

	public void setRe_add_country(String re_add_country) {
		this.re_add_country = re_add_country;
	}



	public String getPhoto_name() {
		return photo_name;
	}

	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}



	public String getPhoto_size() {
		return photo_size;
	}

	public void setPhoto_size(String photo_size) {
		this.photo_size = photo_size;
	}

	public String getPhoto_type() {
		return photo_type;
	}

	public void setPhoto_type(String photo_type) {
		this.photo_type = photo_type;
	}

	public String getSign_name() {
		return sign_name;
	}

	public void setSign_name(String sign_name) {
		this.sign_name = sign_name;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public String getSign_url() {
		return sign_url;
	}

	public void setSign_url(String sign_url) {
		this.sign_url = sign_url;
	}

	public String getSign_size() {
		return sign_size;
	}

	public void setSign_size(String sign_size) {
		this.sign_size = sign_size;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}



	@Override
	public String toString() {
		return "Admin [faculty_id=" + faculty_id + ", first_name=" + first_name + ", middle_name=" + middle_name
				+ ", last_name=" + last_name + ", designation=" + designation + ", role=" + role + ", branch=" + branch
				+ ", branch_year=" + branch_year + ", email=" + email + ", contact=" + contact + ", password="
				+ password + ", gender=" + gender + ", date_of_birth=" + date_of_birth + ", blood_group=" + blood_group
				+ ", re_add_l1=" + re_add_l1 + ", re_add_l2=" + re_add_l2 + ", re_add_city=" + re_add_city
				+ ", re_add_state=" + re_add_state + ", re_add_pin_code=" + re_add_pin_code + ", re_add_country="
				+ re_add_country + "]";
	}

}
