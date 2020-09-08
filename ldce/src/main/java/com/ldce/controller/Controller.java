package com.ldce.controller;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.print.Doc;
import javax.validation.Valid;
import javax.validation.ValidationException;

import com.ldce.Data.DocumentData;
import com.ldce.Data.RequestDto;
import com.ldce.Model.Authentication.AuthenticationRequest;
import com.ldce.Model.Authentication.AuthenticationResponce;
import com.ldce.Model.Request.Request;
import com.ldce.Model.Request.RequestRepository;
import com.ldce.Model.Student.Student;
import com.ldce.Model.Student.StudentRepository;
import com.ldce.Model.Student.Student_guardian;
import com.ldce.Model.Student.Student_info;
import com.ldce.SearchSpecification.ObjectMapperUtils;
import com.ldce.SearchSpecification.StudentSpecification;
import com.ldce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ldce.Dao.Dao;
import com.ldce.Model.Admin.Admin;
import com.ldce.exception.ValidationFailException;
import com.ldce.security.userdetailservice;

@CrossOrigin
@RequestMapping("/api")
@RestController

public class Controller {
	public static String uploadDirectory = System.getProperty("user.dir")+"\\uploads";
	@Autowired
	Dao dao;
	
	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	StudentRepository strp;
	@Autowired
	RequestRepository repo;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private userdetailservice myUserDetailsService;
	@Autowired
	private JwtUtil jwtUtil;

	Request request;


	@CrossOrigin
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try{
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername()+","+authenticationRequest.getType(),authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e){
			HashMap<String,String> error = new HashMap<>();
			error.put("error","InCorrent userName or Password");
			return new  ResponseEntity(error,HttpStatus.UNAUTHORIZED);
		}
		final UserDetails userDetails =  myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername()+","+authenticationRequest.getType());

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponce(jwt,authenticationRequest.getType()));
	}






	//faculty post data mapping
	@PostMapping("/registerFaculty")
	public @ResponseBody String facultyAdd(Admin admin,@RequestParam("photo")MultipartFile ph,@RequestParam("sign")MultipartFile si)
	{ 
		try {
			dao.save(admin, ph, si);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "done";
		
	}

	//forgot password post mapping
	@PostMapping("/forgotPassword")
	public ResponseEntity<?>  forgotPassword(@RequestParam("username") String username,@RequestParam("type") String type) throws Exception{

		String email = dao.resetPassword(username,type);
		HashMap<String,String> res = new HashMap<>();
		if(email == null) {
			res.put("error","Username Not Found");
			return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
		}
		else {
			res.put("email",email);
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
	}
	

	@CrossOrigin
	@PostMapping("/registerStudent")
	public ResponseEntity<?> beAdd(@Valid Student student, BindingResult E, @Valid Student_info info, @Valid Student_guardian guardian, @RequestParam("photo")MultipartFile ph, @RequestParam("sign")MultipartFile si) {
		System.out.println("hear");
		if (E.hasErrors()) {
			throw new ValidationException();
		}
		else {
	 		try {
	 			dao.save(student,info,guardian,ph,si);
	 		} catch (IOException e) {
	 			throw new ValidationFailException("data is not valid");
	 		} catch (Exception e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok(new String("Success"));
	 	}	
	}
			

	@GetMapping("/test")
	public String test (){
return "working";
	}
	
	
}
		
		
