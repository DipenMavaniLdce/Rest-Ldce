package com.ldce.controller;

import com.ldce.Dao.SaveQueryDao;
import com.ldce.Dao.UpdateQueryDao;
import com.ldce.Model.Admin.Admin;
import com.ldce.Model.Authentication.AuthenticationRequest;
import com.ldce.Model.Authentication.AuthenticationResponce;
import com.ldce.Model.Request.RequestRepository;
import com.ldce.Model.Student.Student;
import com.ldce.Model.Student.StudentRepository;
import com.ldce.Model.Student.Student_guardian;
import com.ldce.Model.Student.Student_info;
import com.ldce.exception.ValidationFailException;
import com.ldce.security.CustomUserDetailService;
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

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Date;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController
public class Controller {

	@Autowired
	UpdateQueryDao updateQueryDao;

	@Autowired
	SaveQueryDao saveQueryDao;

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	StudentRepository strp;

	@Autowired
	RequestRepository repo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername() + "," + authenticationRequest.getType(),
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			HashMap<String, String> error = new HashMap<>();
			error.put("error", "InCorrent userName or Password");
			return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
		}
		final UserDetails userDetails = myUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername() + "," + authenticationRequest.getType());

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponce(jwt, authenticationRequest.getType()));
	}

	// faculty post data mapping
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/registerFaculty")
	public ResponseEntity<?> facultyAdd(Admin admin, @RequestParam("photo") MultipartFile ph,
			@RequestParam("sign") MultipartFile si) {

		try {
			saveQueryDao.saveAdmin(admin, ph, si);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ValidationFailException("data is not valid");
		}
		HashMap<String, String> res = new HashMap<>();
		res.put("message", "Registration done");
		return new ResponseEntity<>(res, HttpStatus.OK);

	}

	// forgot password post mapping
	@PostMapping("/forgotPassword")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> forgotPassword(@RequestParam("username") String username,
			@RequestParam("type") String type) throws Exception {

		String email = updateQueryDao.resetPassword(username, type);
		HashMap<String, String> res = new HashMap<>();
		if (email == null) {
			res.put("error", "Username Not Found");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		} else {
			res.put("email", email);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/registerStudent")
	public ResponseEntity<?> studentAdd(@Valid Student student, BindingResult E, @Valid Student_info info,
			@Valid Student_guardian guardian, @RequestParam("photo") MultipartFile ph,
			@RequestParam("sign") MultipartFile si) {

		if (E.hasErrors()) {
			System.out.println(E);
			throw new ValidationException();
		} else {
			try {
				saveQueryDao.saveStudent(student, info, guardian, ph, si);

			} catch (Exception e) {

				e.printStackTrace();
				throw new ValidationFailException("data is not valid");
			}

			HashMap<String, String> res = new HashMap<>();
			res.put("message", "Registration done");
			return new ResponseEntity<>(res, HttpStatus.OK);

		}
	}
	

	
	

}
