package com.ldce.Model.Student;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ldce.Main.Token;
import com.ldce.Model.FeeRefund.FeeRefundDetails;
import com.ldce.Model.Request.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Student implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long student_id;
	@NotEmpty
	@Column(unique = true)
	String enrollment;
	@NotEmpty
	String first_name;
	@NotEmpty
	String middle_name;
	String last_name;
	@Email
	@NotEmpty
	@Column(unique = true)
	String email;
	@NotEmpty
	String contact;
	@JsonIgnore
	String password;
	@NotEmpty
	String gender;
	@NotEmpty
	String caste;
	@NotEmpty
	String religion;
	@NotNull
	int addmission_year;
	int graduation_year;
	@NotNull
	int semester;
	@NotNull
	int branch;
	@NotEmpty
	String course;

	String faculty_comment;
	@NotEmpty
	String admission_category;

	String role = "ROLE_STUDENT";

	String photo_name;
	String photo_url;
	String photo_size;
	String photo_type;


	String sign_name;
	String sign_url;
	String sign_size;
	String sign_type;


	Boolean isactive = true;
	int faculty_approve = 0;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	Token token;

	@OneToOne(cascade = CascadeType.ALL)
	Student_info info;
	@OneToOne(cascade = CascadeType.ALL)
	Student_guardian guardian;

	@OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
	private List<Request> request;



	@OneToMany(mappedBy = "student",fetch=FetchType.LAZY)
	private List<FeeRefundDetails> feerefunddetails;



	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	public String getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Student_info getInfo() {
		return info;
	}

	public void setInfo(Student_info info) {
		this.info = info;
	}

	public Student_guardian getGuardian() {
		return guardian;
	}

	public void setGuardian(Student_guardian guardian) {
		this.guardian = guardian;
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

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public int getAddmission_year() {
		return addmission_year;
	}

	public void setAddmission_year(int addmission_year) {
		this.addmission_year = addmission_year;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
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

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getFaculty_approve() {
		return faculty_approve;
	}

	public void setFaculty_approve(int faculty_approve) {
		this.faculty_approve = faculty_approve;
	}

	public List<FeeRefundDetails> getFeerefunddetails() {
		return feerefunddetails;
	}

	public void setFeerefunddetails(List<FeeRefundDetails> feerefunddetails) {
		this.feerefunddetails = feerefunddetails;
	}

	public List<Request> getRequest() {
		return request;
	}

	public void setRequest(List<Request> request) {
		this.request = request;
	}

	public String getFaculty_comment() {
		return faculty_comment;
	}

	public void setFaculty_comment(String faculty_comment) {
		this.faculty_comment = faculty_comment;
	}

	public String getAdmission_category() {
		return admission_category;
	}

	public void setAdmission_category(String admission_category) {
		this.admission_category = admission_category;
	}

	public int getGraduation_year() {
		return graduation_year;
	}

	public void setGraduation_year(int graduation_year) {
		this.graduation_year = graduation_year;
	}



}
