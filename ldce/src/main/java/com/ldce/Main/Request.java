package com.ldce.Main;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long request_id;

	String type;

	int status1 = 0;
	int status2 = 0;
	int status3 = 0;
	boolean live = false;
	String comment;
	String fee_receipt_name;
	String fee_receipt_url;
	String fee_receipt_size;
	String fee_receipt_type;

	String marksheet_url;
	String marksheet_name;
	String marksheet_size;
	String marksheet_type;

	double cgpa;
	String ranks;
	Date Applied_date = new Date();
	@ManyToOne(fetch = FetchType.LAZY)

	@JoinColumn(name = "request_enrollment", referencedColumnName = "enrollment")
	Student student;

	public Request() {

		status1 = 0;
		status2 = 0;
		status3 = 0;
		Applied_date = new Date();
		comment = null;
		student = null;
	}

	public String getFee_receipt_name() {
		return fee_receipt_name;
	}

	public void setFee_receipt_name(String fee_receipt_name) {
		this.fee_receipt_name = fee_receipt_name;
	}

	public String getFee_receipt_url() {
		return fee_receipt_url;
	}

	public void setFee_receipt_url(String fee_receipt_url) {
		this.fee_receipt_url = fee_receipt_url;
	}

	public String getFee_receipt_size() {
		return fee_receipt_size;
	}

	public void setFee_receipt_size(String fee_receipt_size) {
		this.fee_receipt_size = fee_receipt_size;
	}

	public String getFee_receipt_type() {
		return fee_receipt_type;
	}

	public void setFee_receipt_type(String fee_receipt_type) {
		this.fee_receipt_type = fee_receipt_type;
	}

	public String getMarksheet_url() {
		return marksheet_url;
	}

	public void setMarksheet_url(String marksheet_url) {
		this.marksheet_url = marksheet_url;
	}

	public String getMarksheet_name() {
		return marksheet_name;
	}

	public void setMarksheet_name(String marksheet_name) {
		this.marksheet_name = marksheet_name;
	}

	public String getMarksheet_size() {
		return marksheet_size;
	}

	public void setMarksheet_size(String marksheet_size) {
		this.marksheet_size = marksheet_size;
	}

	public String getMarksheet_type() {
		return marksheet_type;
	}

	public void setMarksheet_type(String marksheet_type) {
		this.marksheet_type = marksheet_type;
	}

	public Long getRequest_id() {
		return request_id;
	}

	public void setRequest_id(Long request_id) {
		this.request_id = request_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatus1() {
		return status1;
	}

	public void setStatus1(int status1) {
		this.status1 = status1;
	}

	public int getStatus2() {
		return status2;
	}

	public void setStatus2(int status2) {
		this.status2 = status2;
	}

	public int getStatus3() {
		return status3;
	}

	public void setStatus3(int status3) {
		this.status3 = status3;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public String getRanks() {
		return ranks;
	}

	public void setRanks(String ranks) {
		this.ranks = ranks;
	}

	public Date getApplied_date() {
		return Applied_date;
	}

	public void setApplied_date(Date applied_date) {
		Applied_date = applied_date;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	@Override
	public String toString() {
		return "Request [request_id=" + request_id + ", type=" + type + ", status1=" + status1 + ", status2=" + status2
				+ ", status3=" + status3 + ", comment=" + comment + ", cgpa=" + cgpa + ", ranks=" + ranks
				+ ", Applied_date=" + Applied_date + "]";
	}

}
