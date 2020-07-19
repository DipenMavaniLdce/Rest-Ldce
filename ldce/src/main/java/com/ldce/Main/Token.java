package com.ldce.Main;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long tokenid;

	private String tokenValue;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public Token() {

		this.tokenValue = UUID.randomUUID().toString();
		this.createdDate = new Date();
	}

	public long getTokenid() {
		return tokenid;
	}

	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void newTokenValue() {
		this.tokenValue = UUID.randomUUID().toString();
	}
}
