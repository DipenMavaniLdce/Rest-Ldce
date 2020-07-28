package com.ldce.Data;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.ldce.Main.Request;

public interface DocumentData {
	public long getStudent_id();

	public String getEnrollment();

	public String getFirst_name();

	public String getLast_name();

	public String getMiddle_name();

	public String getEmail();

	public String getContact();

	public String getGender();

	public String getType();

	public int getBranch();

	public String getCourse();


	public String getPhoto_url();
	public String getPhoto_type();
	public String getPhoto_size();
	public String getPhoto_name();

	public String getDocument_size();
	public String getDocument_url();
	public String getDocument_name();
	public String getDocument_type();

	public int getSemester();

	public long getRequest_id();


	public String getRanks();

	public int getAddmission_year();

	public int getGraduation_year();

}
