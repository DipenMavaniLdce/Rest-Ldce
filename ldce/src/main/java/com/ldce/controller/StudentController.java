package com.ldce.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
		String username= (String)request.getAttribute("username");
		System.out.println(username);
        HashMap<String,String> res = new HashMap<String,String>();
		if (dao.updatephoto(username, studentPhoto)) {

            res.put("success","User Photo Changed Successfully");
            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK
            );
		}
		else{
		    res.put("error","Server Eorror");
            return new ResponseEntity<>(
                    res,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

	}

	// update sign
	@PostMapping("/changeSign")
	public ResponseEntity<?> chnagesign(HttpServletRequest request, @RequestParam("sign") MultipartFile studentSign)
			throws IOException {
	    String username = (String)request.getAttribute("username");
		System.out.println(username);
        HashMap<String,String> res = new HashMap<String,String>();
		if (dao.updatesign(username, studentSign)) {
            res.put("success","User Sign Updated Successfully");
            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK
            );
		}
		else{
            res.put("error","Server Eorror");
            return new ResponseEntity<>(
                    res,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }


	}

	@PostMapping("/updateStudent")
	public ModelAndView updateProfile(HttpServletRequest request, @Valid Student Student, @Valid Student_info info,
			@Valid Student_guardian guardian) throws IOException {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (dao.updateprofile(userDetails.getEmail(), Student, info, guardian)) {
		}
		return new ModelAndView("redirect:/student/view_profile");

	}

	// json data to logged in user
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/data")
	public Student getData() {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Student student = dao.search(userDetails.getEmail());
		System.out.println(student);
		return student;
	}

	@PostMapping("/DocumentSubmit/bonafide")
	public ModelAndView postBonafide(@RequestParam("feeReceipt") MultipartFile feeReceipt) throws IOException {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (dao.saveRequest("bonafide", userDetails.getEnrollment(), feeReceipt, null, 0, null)) {
			return new ModelAndView("redirect:/student/");
		} else {
			return new ModelAndView("redirect:/student/");
		}
	}

	@PostMapping("/DocumentSubmit/character")
	public ModelAndView postCharacter(@RequestParam("feeReceipt") MultipartFile feeReceipt) throws IOException {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (dao.saveRequest("character", userDetails.getEnrollment(), feeReceipt, null, 0, null)) {
			return new ModelAndView("redirect:/student/");
		} else {
			return new ModelAndView("redirect:/student/");
		}
	}

	@PostMapping("/DocumentSubmit/conduct")
	public ModelAndView postConduct(@RequestParam("feeReceipt") MultipartFile feeReceipt,
			@RequestParam("marksheet") MultipartFile marksheet, @RequestParam("cgpa") double cgpa) throws IOException {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (dao.saveRequest("conduct", userDetails.getEnrollment(), feeReceipt, marksheet, cgpa, null)) {
			return new ModelAndView("redirect:/student/");
		} else {
			return new ModelAndView("redirect:/student/");
		}
	}

	@PostMapping("/DocumentSubmit/rank")
	public ModelAndView postRank(@RequestParam("feeReceipt") MultipartFile feeReceipt,
			@RequestParam("marksheet") MultipartFile marksheet, @RequestParam("cgpa") double cgpa) throws IOException {
		userdetails userDetails = (userdetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (dao.saveRequest("rank", userDetails.getEnrollment(), feeReceipt, marksheet, cgpa, null)) {
			return new ModelAndView("redirect:/student/");
		} else {
			return new ModelAndView("redirect:/student/");
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

}