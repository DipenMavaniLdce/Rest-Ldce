package com.ldce.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ldce.Dao.Dao;
import com.ldce.Data.RequestDto;
import com.ldce.Main.FeeRefundDetails;
import com.ldce.Main.FeeRefundDetailsRepository;
import com.ldce.Main.Student;
import com.ldce.Main.Student_guardian;
import com.ldce.Main.Student_info;
import com.ldce.security.userdetails;

@RestController()
@RequestMapping("/api/student")
public class StudentController {
	@Autowired
	Dao dao;

	@Autowired
	FeeRefundDetailsRepository frdr;

	@GetMapping("/")
	public ModelAndView getStatus() {
		return new ModelAndView("StudentDashBoard.html");
	}

	// account confirmation using email
	@GetMapping("/confirm-account")
	public ModelAndView confirmStudentAccount(@RequestParam("token") String tokenValue) {
		if (dao.validateEmail(tokenValue)) {
			return new ModelAndView("redirect:/login");
		} else {
			return new ModelAndView("error.html");
		}
	}

	@GetMapping("studentDashboard")
	public RequestDto getdashBoard() {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return dao.ttmp(userDetails.getEnrollment());
	}

	@GetMapping("feeRefund")
	public FeeRefundDetails getfeerefund() {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails);
		return dao.feerefund(userDetails.getEnrollment());
	}

	// change photo
	@PostMapping("/changePhoto")
	public ResponseEntity<?> chnagePhoto(HttpServletRequest request, @RequestParam("photo") MultipartFile studentPhoto)
			throws IOException {
		String username = (String) request.getAttribute("username");
		System.out.println(username);
		HashMap<String, String> res = new HashMap<String, String>();
		if (dao.updatephoto(username, studentPhoto,"STUDENT")) {

			res.put("success", "User Photo Changed Successfully");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			res.put("error", "Server Eorror");
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// update sign
	@PostMapping("/changeSign")
	public ResponseEntity<?> chnagesign(HttpServletRequest request, @RequestParam("sign") MultipartFile studentSign)
			throws IOException {
		String username = (String) request.getAttribute("username");
		System.out.println(username);
		HashMap<String, String> res = new HashMap<String, String>();
		if (dao.updatesign(username, studentSign,"STUDENT")) {
			res.put("success", "User Sign Updated Successfully");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			res.put("error", "Server Eorror");
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/updateStudent")
	public ResponseEntity<?> updateProfile(HttpServletRequest request, @Valid Student Student, @Valid Student_info info,
			@Valid Student_guardian guardian) throws IOException {
		String username = (String) request.getAttribute("username");
		HashMap<String, String> res = new HashMap<>();
		if (dao.updateprofile(username, Student, info, guardian)) {
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
	public Student getData(@RequestAttribute("username") String username) {
		Student student = dao.search(username);
		return student;
	}

	@PostMapping("/DocumentSubmit/{type}")
	public ResponseEntity<?> requestCertificate(@PathVariable("type") String type,
			@RequestAttribute("username") String username,
			@RequestParam("feeReceipt") MultipartFile feeReceipt,
			@RequestParam(name = "marksheet", required = false) MultipartFile marksheet,
			@RequestParam(name = "cgpa", required = false, defaultValue = "0") Double cgpa,
			@RequestParam(name = "graduation_year", required = false, defaultValue = "0") Integer graduation_year, HttpServletRequest request) throws IOException {

		HashMap<String, String> res = new HashMap<String, String>();
		System.out.println(type);
		System.out.println(feeReceipt);
		System.out.println(marksheet);
		System.out.println(cgpa);
		System.out.println(graduation_year);
		
		switch (type) {
		case "bonafide":
			if (feeReceipt == null) {
				res.put("error", "Fee Receipt Required!");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			break;
		case "character":
			if (feeReceipt == null || graduation_year == null) {
				res.put("error", "Please give all required details!");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			break;
		case "conduct":
		case "rank":
			if (feeReceipt == null || marksheet == null || cgpa == null || graduation_year == null) {
				res.put("error", "Please give all required details!");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			break;
		default:
			res.put("error", "Wrong Document Request!");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}

		int code = dao.saveRequest(type, username, feeReceipt, marksheet, cgpa, graduation_year);
		if (code == 409) {
			res.put("error", "Document Request Already Exist!");
			return new ResponseEntity<>(res, HttpStatus.CONFLICT);
		} else if (code == 200) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			res.put("error", "Server Error");
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/feeRefund")
	public ModelAndView feeRefund(@Valid FeeRefundDetails feerefund,
			@RequestParam("fee_recipt") MultipartFile fee_recipt) {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(feerefund);
		if (dao.saveFeeRefundDetails(feerefund, userDetails.getEnrollment(), fee_recipt)) {
			return new ModelAndView("redirect:/student/");
		} else {
			return new ModelAndView("redirect:/student/");
		}

	}

	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(HttpServletRequest request) {
		String username = (String) request.getAttribute("username");
		String password = request.getParameter("password");
		String current_password = request.getParameter("current_password");
		HashMap<String, String> res = new HashMap<String, String>();
		String s = dao.changePasswordDao(username, password, current_password,"STUDENT");
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