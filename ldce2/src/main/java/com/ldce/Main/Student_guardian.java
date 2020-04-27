package com.ldce.Main;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.Table;
@Entity

public class Student_guardian {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	String father_occupation;
	 String off_add_l1;
	 String off_add_l2;
	 String off_add_city;
	 String off_add_state;
	 String off_add_pin_code;
	 String off_add_country;
	 
	String mother_occupation;
	double family_income;
	public String getFather_occupation() {
		return father_occupation;
	}
	public void setFather_occupation(String father_occupation) {
		this.father_occupation = father_occupation;
	}
	public String getOff_add_l1() {
		return off_add_l1;
	}
	public void setOff_add_l1(String off_add_l1) {
		this.off_add_l1 = off_add_l1;
	}
	public String getOff_add_l2() {
		return off_add_l2;
	}
	public void setOff_add_l2(String off_add_l2) {
		this.off_add_l2 = off_add_l2;
	}
	public String getOff_add_city() {
		return off_add_city;
	}
	public void setOff_add_city(String off_add_city) {
		this.off_add_city = off_add_city;
	}
	public String getOff_add_state() {
		return off_add_state;
	}
	public void setOff_add_state(String off_add_state) {
		this.off_add_state = off_add_state;
	}
	public String getOff_add_pin_code() {
		return off_add_pin_code;
	}
	public void setOff_add_pin_code(String off_add_pin_code) {
		this.off_add_pin_code = off_add_pin_code;
	}
	public String getOff_add_country() {
		return off_add_country;
	}
	public void setOff_add_country(String off_add_country) {
		this.off_add_country = off_add_country;
	}
	public String getMother_occupation() {
		return mother_occupation;
	}
	public void setMother_occupation(String mother_occupation) {
		this.mother_occupation = mother_occupation;
	}
	public double getFamily_income() {
		return family_income;
	}
	public void setFamily_income(double family_income) {
		this.family_income = family_income;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Student_guardian [father_occupation=" + father_occupation + ", off_add_l1=" + off_add_l1
				+ ", off_add_l2=" + off_add_l2 + ", off_add_city=" + off_add_city + ", off_add_state=" + off_add_state
				+ ", off_add_pincode=" + off_add_pin_code + ", off_add_country=" + off_add_country
				+ ", mother_occupation=" + mother_occupation + ", family_income=" + family_income + "]";
	}
	
	

}
