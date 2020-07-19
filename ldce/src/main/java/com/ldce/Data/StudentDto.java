package com.ldce.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.aspectj.weaver.ast.Var;

import com.ldce.Main.Request;
import com.ldce.Main.Student;
import com.ldce.Main.Student_guardian;
import com.ldce.Main.Student_info;
import com.ldce.Main.Token;

public class StudentDto implements Serializable {

	long student_id;
	String enrollment;

	String first_name;
	String middle_name;
	String last_name;

	String email;
	String contact;

	String gender;
	String caste;
	String religion;
	int addmission_year;
	int semester;
	int branch;
	String course;

	String role = "ROLE_STUDENT";

	byte student_photo[];

	byte student_sign[];

	Boolean isactive = true;
	int faculty_approve;

	Student_info info;

	Student_guardian guardian;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public byte[] getStudent_photo() {
		return student_photo;
	}

	public void setStudent_photo(byte[] student_photo) {
		this.student_photo = student_photo;
	}

	public byte[] getStudent_sign() {
		return student_sign;
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

	public int getFaculty_approve() {
		return faculty_approve;
	}

	public void setFaculty_approve(int faculty_approve) {
		this.faculty_approve = faculty_approve;
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

}
