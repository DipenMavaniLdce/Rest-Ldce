package com.ldce.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ldce.Dao.Dao;
import com.ldce.Data.RequestDto;
import com.ldce.Data.StudentDto;
import com.ldce.Main.Student;
import com.ldce.Main.Student_guardian;
import com.ldce.Main.Student_info;
import com.ldce.security.userdetails;

@RestController()
@RequestMapping("/student")

public class StudentController {
	@Autowired
	Dao dao;

	//looged user-detail
	@GetMapping("/view_profile")
    public  ModelAndView currentUserNameSimple(HttpServletRequest request) { 
       return new ModelAndView("view_profile.html");
    }
	
	 @GetMapping("/bonafide")
	 public ModelAndView getBonafide() {
		 return new ModelAndView("bonafide.html");
	 }
	 
	 
	 @GetMapping("/character")
	 public ModelAndView getCharacter() {
		 return new ModelAndView("character.html");
	 }
	
	

	@GetMapping("/")
	 public ModelAndView getStatus() {
		 return new ModelAndView("StudentDashBoard.html");
	 }
	
	
	//account confirmation using email
	@GetMapping("/confirm-account")
	public ModelAndView confirmStudentAccount(@RequestParam("token")String tokenValue){
	      if(dao.validateEmail(tokenValue)){ 
	    	  return new ModelAndView("redirect:/login");
	      }
	      else {
	    	  return new ModelAndView("error.html");
	      }
  	}
	
	@CrossOrigin
	@GetMapping("studentDashboard")
	public RequestDto getdashBoard() {
		userdetails userDetails =(userdetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return dao.ttmp(userDetails.getEnrollment());
	}
	
	
	//change photo	
	@PostMapping("/changePhoto")
	public ModelAndView chnagePhoto(HttpServletRequest request,@RequestParam("photo")MultipartFile studentPhoto) throws IOException {
		userdetails userDetails =(userdetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(dao.updatephoto(userDetails.getEmail(), studentPhoto)) {}
		return new ModelAndView("redirect:/student/view_profile");
		
	}
		
	//update sign
	@PostMapping("/changeSign")
	public ModelAndView chnagesign(HttpServletRequest request,@RequestParam("sign")MultipartFile studentSign) throws IOException {
		userdetails userDetails =(userdetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(dao.updatesign(userDetails.getEmail(), studentSign)) { }
		return new ModelAndView("redirect:/student/view_profile");
		
		
	}
		
		
	@PostMapping("/updateStudent")
	public ModelAndView updateProfile(HttpServletRequest request,@Valid Student Student,@Valid Student_info info,@Valid Student_guardian guardian) throws IOException {
		userdetails userDetails =(userdetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
			if(dao.updateprofile(userDetails.getEmail(),Student,info,guardian))
			{}
		return new ModelAndView("redirect:/student/view_profile");
		
	}
	
		
		
	//json data to logged in user
	@CrossOrigin
	@GetMapping("/data")
	public Student getData() {
	 userdetails userDetails =(userdetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  Student student =dao.search(userDetails.getEmail());
	  System.out.println(student);
	  return student;
	}
		

		
		 
	 @PostMapping("/DocumentSubmit/{type}")
		public ModelAndView postBonafide(@PathVariable("type")String type,@RequestParam("feeReceipt")MultipartFile feeReceipt) throws IOException {			userdetails userDetails =(userdetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(dao.saveRequest(userDetails.getEnrollment(),type,feeReceipt)) {
				return new ModelAndView("redirect:/student/");
			}
			else {
				return new ModelAndView("redirect:/student/");
			}
		}	
}
