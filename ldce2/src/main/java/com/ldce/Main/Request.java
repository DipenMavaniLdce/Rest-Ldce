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
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	Long request_id;
	
	String type;
	
	int status1=0;
	int status2=0;
	int status3=0;
	boolean live=false;
	String comment;
	@Lob
	byte Fee_Receipt[];
	Date Applied_date=new Date();	
	@ManyToOne(fetch = FetchType.LAZY)

	@JoinColumn(name = "request_enrollment",referencedColumnName = "enrollment")
	Student student;
 
	public Request() {

		 status1=0;
		 status2=0;
		 status3=0;
		 Applied_date =new Date();
		 comment=null;
		 student=null;
	}


	
	public byte[] getFee_Receipt() {
		return Fee_Receipt;
	}



	public void setFee_Receipt(byte[] fee_Receipt) {
		Fee_Receipt = fee_Receipt;
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
		return "Request [request_id=" + request_id + ", type=" + type + ", status1=" + status1 + ", status2=" + status2 + ", status3="
				+ status3 + ", comment=" + comment + ", Applied_date=" + Applied_date + "]";
	}
	
	
	
	
}
