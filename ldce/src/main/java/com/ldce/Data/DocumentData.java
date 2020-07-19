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

	public byte[] getStudent_photo();

	public int getSemester();

	public String getPr_add_l1();

	public String getPr_add_l2();

	public String getPr_add_city();

	public String getPr_add_state();

	public String getPr_add_pin_code();

	public String getPr_add_country();

	public String getRe_add_l1();

	public String getRe_add_l2();

	public String getRe_add_city();

	public String getRe_add_state();

	public String getRe_add_pin_code();

	public String getRe_add_country();

	public long getRequest_id();

	public byte[] getFee_Receipt();

}
