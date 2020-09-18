package com.ldce.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import com.ldce.Data.FeeRefundData;
import com.ldce.Data.RequestDto;
import com.ldce.Model.Admin.Admin;
import com.ldce.SearchSpecification.ObjectMapperUtils;
import com.ldce.SearchSpecification.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ldce.Dao.Dao;
import com.ldce.Data.DocumentData;
import com.ldce.Model.Request.RequestRepository;
import com.ldce.Model.Student.Student;
import com.ldce.Model.Student.StudentRepository;
import com.ldce.SearchSpecification.CountSpecification;
import com.ldce.SearchSpecification.ReqCountSpecification;
import com.ldce.exception.RecordNotFoundException;
import com.ldce.security.userdetails;

@CrossOrigin(origins = "http://localhost:3000")
@Secured(value = { "ROLE_DEPARTMENT", "ROLE_SSMENTOR", "ROLE_SSHEAD" })
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	StudentRepository strp;

	@Autowired
	RequestRepository reqrepo;

	@Autowired
	Dao dao;

	@CrossOrigin
	@GetMapping("/adminDashbord")
	public ResponseEntity<?> getdashBoard() {
		Map<String, Long> map = new HashMap<String, Long>();
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails.getRole().equals("ROLE_DEPARTMENT")) {
			map.put("Registered Student", strp.count(Specification.where(CountSpecification
					.CountByBranch(userDetails.getBranch()).and(CountSpecification.CountByFaculty_approve(0)))));
			map.put("Applied Document", strp.countByStatus1(userDetails.getBranch()));
			return ResponseEntity.ok(map);
		} else if (userDetails.getRole().equals("ROLE_SSMENTOR")) {
			map.put("Applied Document", reqrepo.count(Specification
					.where(ReqCountSpecification.CountBystatus1(1).and(ReqCountSpecification.CountBystatus2(0)))));
			return ResponseEntity.ok(map);
		} else if (userDetails.getRole().equals("ROLE_SSHEAD")) {
			map.put("Applied Document", reqrepo.count(Specification.where(ReqCountSpecification.CountBystatus1(1)
					.and(ReqCountSpecification.CountBystatus2(1)).and(ReqCountSpecification.CountBystatus3(0)))));
			return ResponseEntity.ok(map);
		} else {
			HashMap<String, String> res = new HashMap<String, String>();
			res.put("error","invalid role");
			return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
		}
	}

	// json data to logged in user
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/data")
	public ResponseEntity<?> getData() {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Admin admin = dao.adminCrenditials(userDetails.getEmail());
		return ResponseEntity.ok(admin);
	}
	@PostMapping("/FeeRefundApprove")
	public ResponseEntity<?> feeRefundApprove(String enrollment, Integer status, String comment){
		HashMap<String, String> res = new HashMap<String, String>();
		if(enrollment==null || status == null) {
			res.put("error","Type, Enrollment and Status is Required");
			return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
		}
		if(status==2 && comment==null) {
			res.put("error","Comment is Required");
			return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
		}
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!dao.UpdateFeeRefundStatus(userDetails, enrollment, status, comment)) {
			throw new RecordNotFoundException("student data is not found");
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}

	}

	@PostMapping("/DocumentApprove")
	public ResponseEntity<?> documentApprove(String enrollment, String type, Integer status, String comment, String rank) {
		System.out.println(enrollment + type + status+comment+rank);

		HashMap<String, String> res = new HashMap<String, String>();
		if(enrollment==null || type == null || status == null) {
			res.put("error","Type, Enrollment and Status is Required");
			return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
		}
		if(type.equals("rank") && rank ==null) {
			res.put("error","Rank is Required");
			return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
		}
		if(status==2 && comment==null) {
			res.put("error","Comment is Required");
			return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
		}
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!dao.UpdateStatus(userDetails, enrollment, type, status, comment,rank)) {
			throw new RecordNotFoundException("student data is not found");
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PostMapping("/facultyApprove")
	public ResponseEntity studentApprove(String enrollment, Integer status, String comment) {
		if (!dao.save(enrollment, status, comment)) {
			throw new RecordNotFoundException("student data is not found");
		} else {
			return new ResponseEntity(HttpStatus.OK);
		}
	}

	@CrossOrigin
	@GetMapping("/searchStudent")
	public List<Student> searchStudent(@RequestParam(defaultValue = "ALL") String caste,
			@RequestParam(defaultValue = "0")  Integer addmission_year,
			@RequestParam(defaultValue = "ALL") String gender,
			@RequestParam(defaultValue = "0") Integer semester,
			@RequestParam(defaultValue = "0") Integer branch,
			@RequestParam(defaultValue = "ALL") String course) {

		return dao.findAllStudent(caste, addmission_year, gender, semester, branch, course);
	}

	// list json data to admin
	@CrossOrigin
	@GetMapping("/pendingRegList")
	public List<Student> getStudentData() {

		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Student> student = dao.pendingRegistration(userDetails.getBranch(),userDetails.getCourse());
		return student;
	}

	@CrossOrigin
	@GetMapping("/pendingDocument")
	public List<DocumentData> getDocApprove() {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<DocumentData> students = dao.penndingDocument(userDetails);
		return students;
	}



	@CrossOrigin
	@GetMapping("/pendingFeeRefund")
	public List<FeeRefundData> getFeeApprove() {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<FeeRefundData> details = dao.penddingFeeRefund(userDetails);
		return details;
	}

	@CrossOrigin
	@GetMapping("/acceptedDocument")
	public List<DocumentData> getAcceptedDocument() {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<DocumentData> students = dao.penndingDocument(userDetails);
		return students;
	}

	@CrossOrigin
	@PostMapping("/findDocument")
	public List<RequestDto> findDocument(Date date,String enrollment) {



	userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String role = userDetails.getRole();

		role = role.equals("ROLE_DEPARTMENT")?userDetails.getBranch()+role:role;

		return dao.findrequest(date,role,enrollment);
	}

	@CrossOrigin
	@GetMapping("/Auth")
	public HashMap<String, String> getAdminAuth() {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		HashMap<String, String> auth = new HashMap<>();
		auth.put("Email", userDetails.getEmail());

		auth.put("Role", userDetails.getRole());
		auth.put("Branch", userDetails.getBranch() + "");
		return auth;

	}

    @PostMapping("/changePhoto")
    public ResponseEntity<?> chnagePhoto(HttpServletRequest request, @RequestParam("photo") MultipartFile adminPhoto)
            throws IOException {

        String username = (String) request.getAttribute("username");

        HashMap<String, String> res = new HashMap<String, String>();
        if (dao.updatephoto(username, adminPhoto,"ADMIN")) {

            res.put("success", "User Photo Changed Successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            res.put("error", "Server Eorror");
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // update sign
    @PostMapping("/changeSign")
    public ResponseEntity<?> chnagesign(HttpServletRequest request, @RequestParam("sign") MultipartFile adminSign)
            throws IOException {
        String username = (String) request.getAttribute("username");
        System.out.println(username);
        HashMap<String, String> res = new HashMap<String, String>();
        if (dao.updatesign(username, adminSign,"ADMIN")) {
            res.put("success", "User Sign Updated Successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            res.put("error", "Server Eorror");
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(HttpServletRequest request) {
		String username = (String) request.getAttribute("username");
		String password = request.getParameter("password");
		String current_password = request.getParameter("current_password");
		HashMap<String, String> res = new HashMap<String, String>();
		String s = dao.changePasswordDao(username, password, current_password,"ADMIN");
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
