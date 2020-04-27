package com.ldce.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ldce.Dao.Dao;
import com.ldce.Data.DocumentData;
import com.ldce.Data.RequestDto;
import com.ldce.Data.StudentDto;
import com.ldce.Main.RequestRepository;
import com.ldce.Main.Student;
import com.ldce.Main.StudentRepository;
import com.ldce.SearchSpecification.CountSpecification;
import com.ldce.SearchSpecification.ObjectMapperUtils;
import com.ldce.SearchSpecification.ReqCountSpecification;
import com.ldce.exception.RecordNotFoundException;
import com.ldce.security.userdetails;
@Secured(value = {"ROLE_DEPARTMENT","ROLE_SSMENTOR","ROLE_SSHEAD"})
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	StudentRepository strp;
	
	@Autowired
	RequestRepository reqrepo;
	
	@Autowired
	Dao dao;
	
	@GetMapping("/")
	public ModelAndView admin() {
		return new ModelAndView("AdminDashBoard.html");
	}
	 
	
	@GetMapping("/verifyStudent")
	public ModelAndView getapprove() {
		return new ModelAndView("approve_request.html");
	}
	
	
	@GetMapping("/DocumentApprove")
	public ModelAndView getapproveRequest() {
		return new ModelAndView("DocumentApprove.html");
	}
	
	
	@GetMapping("/sshead")
	public ModelAndView anyanyanya() { 
		return new ModelAndView("SSHeadData.html");
	}
	
	@CrossOrigin
	@GetMapping("/adminDashbord")
	public Map getdashBoard() {
	 Map<String,Long> map=new HashMap<String,Long>();  
		userdetails userDetails =(userdetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userDetails.getRole().equals("ROLE_DEPARTMENT")) {
			map.put("register",strp.count(Specification.where(CountSpecification.CountByBranch(userDetails.getBranch()).and(CountSpecification.CountByFaculty_approve(0)))));
			map.put("document", strp.countByStatus1(userDetails.getBranch()));
			return map;
		}
		else if(userDetails.getRole().equals("ROLE_SSMENTOR")) {
			map.put("document", reqrepo.count(Specification.where(ReqCountSpecification.CountBystatus1(1).and(ReqCountSpecification.CountBystatus2(0)))));
			return map; 
		}
		else if(userDetails.getRole().equals("ROLE_SSHEAD")) {
			map.put("document", reqrepo.count(Specification.where(ReqCountSpecification.CountBystatus1(1).and(ReqCountSpecification.CountBystatus2(1)).and(ReqCountSpecification.CountBystatus3(0)))));
			return map; 
		}
		else {
			return null;
		}
	}

	
	 @CrossOrigin
	 @PostMapping("/DocumentApprove")
		public ResponseEntity documentApprove(String enrollment,String type,String status,String comment) {
		userdetails userDetails =(userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!dao.UpdateStatus(userDetails,enrollment,type,status,comment)) {
				throw new RecordNotFoundException("student data is not found");
		}
		else {
			return new  ResponseEntity(HttpStatus.OK);
		}
		
	}
	 
		
	 @PostMapping("/facultyApprove")
	public ResponseEntity data1(String enrollment,int status,String comment ) {
		if(!dao.save(enrollment,status,comment)) {
			throw new RecordNotFoundException("student data is not found");
		}
		else {
			return new  ResponseEntity(HttpStatus.OK);
		}	
	} 
	
	
	
	@CrossOrigin
	@GetMapping("/ssheaddata")
	public List<Student> anyany(String caste,int addmission_year,String gender,int semester,int branch ,String course) {
		return dao.findAllStudent(caste, addmission_year, gender, semester, branch, course);
	}
	
	
	//list json data to admin
	@CrossOrigin
	@GetMapping("/pendingRegList")
	public List <Student> getStudentData(){
		System.out.println("heeeeee");
		userdetails userDetails =(userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Student> student=dao.pendingRegistration(userDetails.getBranch());
		return student;
	} 
			
			
	@CrossOrigin
	@GetMapping("/pendingDocument")
	public List <DocumentData> getDocApprove(){
		userdetails userDetails =(userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<DocumentData> students=dao.penndingDocument(userDetails);
		return students;
		
	}			  
			 
}
