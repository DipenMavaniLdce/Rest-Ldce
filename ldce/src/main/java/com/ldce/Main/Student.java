package com.ldce.Main;

import java.io.Serializable;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.UniqueElements;

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
	String middle_name;
	String last_name;
	@Email
	@Column(unique = true)
	String email;
	String contact;
	@JsonIgnore
	String password;
	String gender;
	String caste;
	String religion;
	int addmission_year;
	int graduation_year;
	int semester;
	int branch;
	String course;
	String faculty_comment;
	String admission_category;

	String role = "ROLE_STUDENT";
	@Lob
	byte student_photo[];

	@Lob
	byte student_sign[];

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

	public byte[] getStudent_photo() {
		return student_photo;
	}

	public byte[] getStudent_sign() {
		return student_sign;
	}

	public void setStudent_photo(byte[] student_photo) {
		this.student_photo = student_photo;
	}

	public void setStudent_sign(byte[] student_sign) {
		this.student_sign = student_sign;
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

	@Override
	public String toString() {
		return "Student [enrollment=" + enrollment + ", first_name=" + first_name + ", middle_name=" + middle_name
				+ ", last_name=" + last_name + "faculty app" + faculty_approve + ", email=" + email + ", contact="
				+ contact + ", password=" + password + ", gender=" + gender + ", cast=" + caste + ", religion="
				+ religion + ", addmission_year=" + addmission_year + ", semester=" + semester + ", branch=" + branch
				+ ", course=" + course + ", info=" + info + ", guardian=" + guardian + "]";
	}

}
