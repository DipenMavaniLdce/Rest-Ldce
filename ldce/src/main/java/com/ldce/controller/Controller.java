package com.ldce.controller;


import java.io.IOException;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ldce.Dao.Dao;
import com.ldce.Main.Request;
import com.ldce.Main.RequestRepository;
import com.ldce.Main.Student;
import com.ldce.Main.StudentRepository;
import com.ldce.Main.Student_guardian;
import com.ldce.Main.Student_info;
import com.ldce.admin.Admin;
import com.ldce.exception.ValidationFailException;


@RestController
public class Controller {

	@Autowired
	Dao dao;
	
	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	StudentRepository strp;
	@Autowired
	RequestRepository repo;
	
	Request request;
	
	
	
	//return main page
	@GetMapping("/")
	public  ModelAndView home(){	
		return new ModelAndView("index.html");
	}
	
	
	@GetMapping("/forgot")
	public ModelAndView getforgot() {
	return  new ModelAndView("forgot.html");
	} 
	
	
	//faculty registration
	@GetMapping("/registerFaculty")
	public ModelAndView registerGetfaculty() {
	return new ModelAndView("facultyRegForm.html");
	}
	
	
	//return login page
	@GetMapping("/login")
	public ModelAndView loginGet() {
	return new ModelAndView("login.html");
	}
		
	
	//return registration form
	@GetMapping("/registerStudent")
	public ModelAndView registerGetstudent() {
	return new ModelAndView("regForm.html");
	}
	
	
	//faculty post data mapping
	@PostMapping("/registerFaculty")
	public @ResponseBody String facultyAdd( @Valid Admin admin,@RequestParam("photo")MultipartFile ph,@RequestParam("sign")MultipartFile si) 
	{ 
		try {
			dao.save(admin,ph,si);
		} catch (IOException e) {
		
		}
		return "done";
		
	}
	
	//forgot password page
	
	
	//forgot password post mapping
	@PostMapping("/forgot")
	public @ResponseBody String  postforgot(@RequestParam("email")String email) {
		if(dao.resetPassword(email)) { 
			return "sucess...!";
		}
		
		else {
			return "no user found";
		}
	}
	
	//new password saving to database
	@GetMapping("/reset-password")
	public ModelAndView resetPassword(@RequestParam("token")String tokenValue) {
	     return  new ModelAndView("resetPassword.jsp").addObject("token",tokenValue);
	  }

	@PostMapping("/newpassword")
	public ModelAndView savepassword(@RequestParam("new_password")String password ,@RequestParam("token") String token) {
		if(dao.updatePassword(token, password)) {
			return new ModelAndView("redirect:/login");
	      }
	      else return new ModelAndView("error.html");
	}
				
	
	@PostMapping("/registerStudent")
	public ModelAndView beAdd( @Valid Student student,BindingResult E,@Valid Student_info info,@Valid Student_guardian guardian,@RequestParam("photo")MultipartFile ph,@RequestParam("sign")MultipartFile si) {
		if (E.hasErrors()) {
			throw new ValidationException();
		}
		else {
	 		try {
	 			dao.save(student,info,guardian,ph,si);
	 		} catch (IOException e) {
	 			throw new ValidationFailException("data is not valid");
	 		}
	 		return new ModelAndView("redirect:/login");
	 	}	
	}
			
			
			
	
	
}
		
		
