package com.ldce.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ldce.Dao.SaveQueryDao;
import com.ldce.Dao.SearchQueryDao;
import com.ldce.Dao.UpdateQueryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.ldce.Data.RequestDto;
import com.ldce.Model.FeeRefund.FeeRefundDetails;
import com.ldce.Model.FeeRefund.FeeRefundDetailsRepository;
import com.ldce.Model.Student.Student;
import com.ldce.Model.Student.Student_guardian;
import com.ldce.Model.Student.Student_info;
import com.ldce.security.CustomUserDetails;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/student")
public class StudentController {
	@Autowired
	UpdateQueryDao updateQueryDao;

	@Autowired
	SaveQueryDao saveQueryDao;

	@Autowired
	SearchQueryDao searchQueryDao;

	@Autowired
	FeeRefundDetailsRepository frdr;

	// account confirmation using email
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/confirm-account")
	public ModelAndView confirmStudentAccount(@RequestParam("token") String tokenValue) {
		if (updateQueryDao.validateEmail(tokenValue)) {
			return new ModelAndView("redirect:/login");
		} else {
			return new ModelAndView("error.html");
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("studentDashboard")
	public RequestDto getdashBoard(@RequestAttribute("username") String username) {
		return searchQueryDao.getStudentDashbord(username);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("feeRefund")
	public FeeRefundDetails getfeerefund() {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails);
		return searchQueryDao.feerefund(userDetails.getEnrollment());
	}

	// change photo
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/changePhoto")
	public ResponseEntity<?> chnagePhoto(HttpServletRequest request, @RequestParam("photo") MultipartFile studentPhoto)
			throws IOException {
		String username = (String) request.getAttribute("username");
		System.out.println(username);
		HashMap<String, String> res = new HashMap<>();
		if (updateQueryDao.updatephoto(username, studentPhoto,"STUDENT")) {

			res.put("success", "User Photo Changed Successfully");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			res.put("error", "Server Eorror");
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// update sign
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/changeSign")
	public ResponseEntity<?> chnagesign(HttpServletRequest request, @RequestParam("sign") MultipartFile studentSign)
			throws IOException {
		String username = (String) request.getAttribute("username");
		System.out.println(username);
		HashMap<String, String> res = new HashMap<>();
		if (updateQueryDao.updatesign(username, studentSign,"STUDENT")) {
			res.put("success", "User Sign Updated Successfully");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			res.put("error", "Server Eorror");
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/updateStudent")
	public ResponseEntity<?> updateProfile(HttpServletRequest request, @Valid Student Student, @Valid Student_info info,
			@Valid Student_guardian guardian) throws IOException {
		String username = (String) request.getAttribute("username");
		HashMap<String, String> res = new HashMap<>();
		if (updateQueryDao.updateprofile(username, Student, info, guardian)) {
			res.put("success", "Data Updated SuccessFully");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			res.put("error", "server Error");
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// json data to logged in user
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/data")
	public ResponseEntity<?> getData(@RequestAttribute("username") String username) {
		Student student = searchQueryDao.search(username);
		return ResponseEntity.ok(student);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/DocumentSubmit/{type}")
	public ResponseEntity<?> requestCertificate(@PathVariable("type") String type,
												@RequestAttribute("username") String username,
												@RequestParam(name = "request_document") MultipartFile request_document,
												@RequestParam(name = "cgpa", required = false, defaultValue = "0") Double cgpa,
												@RequestParam(name = "graduation_year", required = false, defaultValue = "0") Integer graduation_year) throws IOException {

		HashMap<String, String> res = new HashMap<>();
		System.out.println(type);

		System.out.println(request_document);
		System.out.println(cgpa);
		System.out.println(graduation_year);
		
		switch (type) {
		case "bonafide":
			if (request_document == null) {
				res.put("error", "Document required Required!");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			break;
		case "character":
			if (request_document == null || graduation_year == null) {
				res.put("error", "Please give all required details!");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			break;
		case "conduct":
		case "rank":
			if (request_document == null  || cgpa == null || graduation_year == null) {
				res.put("error", "Please give all required details!");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			break;
		default:
			res.put("error", "Wrong Document Request!");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}

		int code = saveQueryDao.saveRequest(type, username, request_document, cgpa, graduation_year);
		if (code == 409) {
			res.put("error", "Document Request Already Exist!");
			return new ResponseEntity<>(res, HttpStatus.CONFLICT);
		} else if (code == 200) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}else if(code == 400){
			res.put("error", "profile is not approved yet");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
		else {
			res.put("error", "Server Error");
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/feeRefund")
	public ResponseEntity<?> feeRefund(@Valid FeeRefundDetails feerefund,
			@RequestAttribute("username") String username,
			@RequestParam(name="request_document") MultipartFile request_document) {
		HashMap<String, String> res = new HashMap<>();
		try {
			int code = saveQueryDao.saveFeeRefundDetails(feerefund, username, request_document);
			if (code == 409) {
				res.put("error", " Request Already Exist!");
				return new ResponseEntity<>(res, HttpStatus.CONFLICT);
			} else if (code == 200) {
				return new ResponseEntity<>(res, HttpStatus.OK);
			} else {
				res.put("error", "Server Error");
				return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (IOException e) {
			res.put("error", "Server Error");
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(HttpServletRequest request) {
		String username = (String) request.getAttribute("username");
		String password = request.getParameter("password");
		String current_password = request.getParameter("current_password");
		HashMap<String, String> res = new HashMap<>();
		String s = updateQueryDao.changePasswordDao(username, password, current_password,"STUDENT");
		if (s == null) {
			res.put("error", "Server error");
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (s.equals("true")) {
			res.put("success", "true");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			res.put("error", "false");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}

	}
}