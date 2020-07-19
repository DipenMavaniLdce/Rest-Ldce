package com.ldce.Main;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

public class Student_info {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String father_name;
	String mother_name;
	String aadhar;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate date_of_birth;
	String blood_group;

	String re_add_l1;
	String re_add_l2;
	String re_add_city;
	String re_add_state;
	String re_add_pin_code;
	String re_add_country;

	String pr_add_l1;
	String pr_add_l2;
	String pr_add_city;
	String pr_add_state;
	String pr_add_pin_code;
	String pr_add_country;

	int hsc_year;
	double hsc_pr;
	int ssc_year;
	double ssc_pr;

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public String getMother_name() {
		return mother_name;
	}

	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
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

	public String getPr_add_l1() {
		return pr_add_l1;
	}

	public void setPr_add_l1(String pr_add_l1) {
		this.pr_add_l1 = pr_add_l1;
	}

	public String getPr_add_l2() {
		return pr_add_l2;
	}

	public void setPr_add_l2(String pr_add_l2) {
		this.pr_add_l2 = pr_add_l2;
	}

	public String getPr_add_city() {
		return pr_add_city;
	}

	public void setPr_add_city(String pr_add_city) {
		this.pr_add_city = pr_add_city;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPr_add_state() {
		return pr_add_state;
	}

	public void setPr_add_state(String pr_add_state) {
		this.pr_add_state = pr_add_state;
	}

	public String getPr_add_pin_code() {
		return pr_add_pin_code;
	}

	public void setPr_add_pin_code(String pr_add_pin_code) {
		this.pr_add_pin_code = pr_add_pin_code;
	}

	public String getPr_add_country() {
		return pr_add_country;
	}

	public void setPr_add_country(String pr_add_country) {
		this.pr_add_country = pr_add_country;
	}

	public int getHsc_year() {
		return hsc_year;
	}

	public void setHsc_year(int hsc_year) {
		this.hsc_year = hsc_year;
	}

	public double getHsc_pr() {
		return hsc_pr;
	}

	public void setHsc_pr(double hsc_pr) {
		this.hsc_pr = hsc_pr;
	}

	public int getSsc_year() {
		return ssc_year;
	}

	public void setSsc_year(int ssc_year) {
		this.ssc_year = ssc_year;
	}

	public double getSsc_pr() {
		return ssc_pr;
	}

	public void setSsc_pr(double ssc_pr) {
		this.ssc_pr = ssc_pr;
	}

	@Override
	public String toString() {
		return "Student_info [id=" + id + ", father_name=" + father_name + ", mother_name=" + mother_name + ", aadhar="
				+ aadhar + ", dob=" + date_of_birth + ", blood_group=" + blood_group + ", re_add_l1=" + re_add_l1
				+ ", re_add_l2=" + re_add_l2 + ", re_add_city=" + re_add_city + ", re_add_state=" + re_add_state
				+ ", re_add_pincode=" + re_add_pin_code + ", re_add_country=" + re_add_country + ", pr_add_l1="
				+ pr_add_l1 + ", pr_add_l2=" + pr_add_l2 + ", pr_add_city=" + pr_add_city + ", pr_add_state="
				+ pr_add_state + ", pr_add_pincode=" + pr_add_pin_code + ", pr_add_country=" + pr_add_country
				+ ", hsc_year=" + hsc_year + ", hsc_pr=" + hsc_pr + ", ssc_year=" + ssc_year + ", ssc_pr=" + ssc_pr
				+ "]";
	}

	public String getPerment_address() {
		return this.pr_add_l1;
	}
}
