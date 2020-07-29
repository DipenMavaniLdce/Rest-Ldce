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
public class FeeRefundDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long refund_id;
	String fees_type;
	String bank_name;
	String bank_ifsc;
	String bank_branch;
	String bank_ac_no;
	String transaction_no;
	int status1 = 0;
	int status2 = 0;
	int status3 = 0;
	boolean live = false;
	String comment;

	String document_name;
	String document_url;
	String document_size;
	String document_type;

	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}

	public void setDocument_url(String document_url) {
		this.document_url = document_url;
	}

	public void setDocument_size(String document_size) {
		this.document_size = document_size;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getDocument_name() {
		return document_name;
	}

	public String getDocument_url() {
		return document_url;
	}

	public String getDocument_size() {
		return document_size;
	}

	public String getDocument_type() {
		return document_type;
	}



	@Lob
	byte Fee_Receipt[];
	Date Applied_date = new Date();
	@ManyToOne(fetch = FetchType.LAZY)

	@JoinColumn(name = "fee_refund_enrollment", referencedColumnName = "enrollment")
	Student student;

	public FeeRefundDetails() {

		status1 = 0;
		status2 = 0;
		status3 = 0;
		Applied_date = new Date();
		comment = null;
		student = null;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Long getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(Long refund_id) {
		this.refund_id = refund_id;
	}

	public String getFees_type() {
		return fees_type;
	}

	public void setFees_type(String fees_type) {
		this.fees_type = fees_type;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_ifsc() {
		return bank_ifsc;
	}

	public void setBank_ifsc(String bank_ifsc) {
		this.bank_ifsc = bank_ifsc;
	}

	public String getBank_branch() {
		return bank_branch;
	}

	public void setBank_branch(String bank_branch) {
		this.bank_branch = bank_branch;
	}

	public String getBank_ac_no() {
		return bank_ac_no;
	}

	public void setBank_ac_no(String bank_ac_no) {
		this.bank_ac_no = bank_ac_no;
	}

	public String getTransaction_no() {
		return transaction_no;
	}

	public void setTransaction_no(String transaction_no) {
		this.transaction_no = transaction_no;
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

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public byte[] getFee_Receipt() {
		return Fee_Receipt;
	}

	public void setFee_Receipt(byte[] fee_Receipt) {
		Fee_Receipt = fee_Receipt;
	}

	public Date getApplied_date() {
		return Applied_date;
	}

	public void setApplied_date(Date applied_date) {
		Applied_date = applied_date;
	}

}
