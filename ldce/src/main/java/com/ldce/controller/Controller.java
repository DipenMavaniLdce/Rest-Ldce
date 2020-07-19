package com.ldce.controller;


import java.io.IOException;
import java.util.HashMap;

import javax.validation.Valid;
import javax.validation.ValidationException;

import com.ldce.Main.*;
import com.ldce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ldce.Dao.Dao;
import com.ldce.admin.Admin;
import com.ldce.exception.ValidationFailException;
import com.ldce.security.userdetailservice;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Controller {

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

	@CrossOrigin
	@PostMapping("/registerStudent")
	public ResponseEntity<?> beAdd( @Valid Student student,BindingResult E,@Valid Student_info info,@Valid Student_guardian guardian,@RequestParam("photo")MultipartFile ph,@RequestParam("sign")MultipartFile si) {
		System.out.println("hear");
		if (E.hasErrors()) {
			throw new ValidationException();
		}
		else {
	 		try {
	 			dao.save(student,info,guardian,ph,si);
	 		} catch (IOException e) {
	 			throw new ValidationFailException("data is not valid");
	 		}
			return ResponseEntity.ok(new String("Success"));
	 	}	
	}
			
			
			
	
	
}
		
		
