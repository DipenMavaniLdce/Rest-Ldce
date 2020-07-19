package com.ldce.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ldce.Main.Student;
import com.ldce.admin.Admin;

public class userdetails implements UserDetails {

String email;
String password;
boolean isactive;
String role;
int branch;
String enrollment;

public userdetails(){
	this.email=null;
	this.password=null;
	this.isactive=false;
	this.role=null;
	this.enrollment=null;
	
}


public userdetails(Student student) {
this.email=student.getEmail();
this.password=student.getPassword();
this.isactive=true;
this.role=student.getRole();
this.branch=student.getBranch();
this.enrollment=student.getEnrollment();
}


public userdetails(Admin admin) {
this.email=admin.getEmail();
this.password=admin.getPassword();
this.isactive=true;
this.role=admin.getRole();
this.branch=admin.getBranch();
this.enrollment=null;
}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println(Collections.singleton(new SimpleGrantedAuthority(role)));
		return Collections.singleton(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
			if(role.equals("ROLE_STUDENT")){
				return enrollment;
			}
			return email;


	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isactive;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	

	public int getBranch() {
		return branch;
	}


	public void setBranch(int branch) {
		this.branch = branch;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getEnrollment() {
		return enrollment;
	}


	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}
	
	
}
