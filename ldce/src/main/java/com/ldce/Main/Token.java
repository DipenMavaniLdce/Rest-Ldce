package com.ldce.Main;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

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
