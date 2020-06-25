package com.ldce.admin;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@Email
	String email;
	String contact;
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
	 
	 	@Lob
		byte faculty_photo[];
		@Transient
		String faculty_photobase64;
		@Lob
		byte faculty_sign[];
		@Transient
		String faculty_signbase64;
		
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
		public byte[] getFaculty_photo() {
			return faculty_photo;
		}
		public void setFaculty_photo(byte[] faculty_photo) {
			this.faculty_photo = faculty_photo;
		}
		public String getFaculty_photobase64() {
			return faculty_photobase64;
		}
		public void setFaculty_photobase64(String faculty_photobase64) {
			this.faculty_photobase64 = faculty_photobase64;
		}
		public byte[] getFaculty_sign() {
			return faculty_sign;
		}
		public void setFaculty_sign(byte[] faculty_sign) {
			this.faculty_sign = faculty_sign;
		}
		public String getFaculty_signbase64() {
			return faculty_signbase64;
		}
		public void setFaculty_signbase64(String faculty_signbase64) {
			this.faculty_signbase64 = faculty_signbase64;
		}
		@Override
		public String toString() {
			return "Admin [faculty_id=" + faculty_id + ", first_name=" + first_name + ", middle_name=" + middle_name
					+ ", last_name=" + last_name + ", designation=" + designation + ", role=" + role + ", branch="
					+ branch + ", branch_year=" + branch_year + ", email=" + email + ", contact=" + contact
					+ ", password=" + password + ", gender=" + gender + ", date_of_birth=" + date_of_birth
					+ ", blood_group=" + blood_group + ", re_add_l1=" + re_add_l1 + ", re_add_l2=" + re_add_l2
					+ ", re_add_city=" + re_add_city + ", re_add_state=" + re_add_state + ", re_add_pin_code="
					+ re_add_pin_code + ", re_add_country=" + re_add_country + "]";
		}
	
	

}