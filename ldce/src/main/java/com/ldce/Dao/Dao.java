package com.ldce.Dao;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.print.Doc;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ldce.Data.DocumentData;
import com.ldce.Data.RequestDto;
import com.ldce.Email.EmailSender;
import com.ldce.Main.FeeRefundDetails;
import com.ldce.Main.FeeRefundDetailsRepository;
import com.ldce.Main.Request;
import com.ldce.Main.RequestRepository;
import com.ldce.Main.Student;
import com.ldce.Main.StudentRepository;
import com.ldce.Main.Student_guardian;
import com.ldce.Main.Student_info;
import com.ldce.Main.Token;
import com.ldce.SearchSpecification.ObjectMapperUtils;
import com.ldce.SearchSpecification.StudentSpecification;
import com.ldce.admin.Admin;
import com.ldce.admin.AdminRepository;
import com.ldce.security.userdetails;

@Component
@Scope("prototype")
public class Dao {

	@Autowired
	EmailSender emailSender;

	@Autowired
	AdminRepository adminrepo;

	@Autowired
	StudentRepository studentRepo;

	@Autowired
	RequestRepository requestRepository;

	@Autowired
	FeeRefundDetailsRepository feerefunddetailsRepository;

	Request request;

	// saveStudent
	public void save(Student student, Student_info info, Student_guardian guardian, MultipartFile photo,
			MultipartFile sign) throws IOException {
		student.setStudent_photo(photo.getBytes());
		student.setStudent_sign(sign.getBytes());
		student.setToken(new Token());
		student.setInfo(info);
		student.setGuardian(guardian);
		try {
			studentRepo.save(student);
		} catch (DataIntegrityViolationException E) {
			throw new DataIntegrityViolationException("Duplicate entry");
		}
		// emailSender.createMail(student.getEmail(),student.getToken().getTokenValue());

	}

	// confirm-account
	public boolean validateEmail(String tokenValue) {
		Student existstudent = studentRepo.findBytokenValue(tokenValue);
		if (existstudent != null) {
			existstudent.setIsactive(true);
			existstudent.getToken().newTokenValue();
			studentRepo.save(existstudent);
			return true;
		} else {
			return false;
		}
	}

	// save admin data
	public void save(@Valid Admin admin, MultipartFile ph, MultipartFile si) throws IOException {
		admin.setFaculty_photo(ph.getBytes());
		admin.setFaculty_sign(si.getBytes());
		adminrepo.save(admin);

	}

//reset password email sender
	public String resetPassword(String username,String type) {
		String email;
		String password;
		if(type.equals("STUDENT")){
            Student student = studentRepo.findByEnrollment(username);
            if(student == null){
                return null;
            }else{
				password= generateCommonLangPassword();
				student.setPassword(password);
				studentRepo.save(student);
				System.out.println("save");
            	email = student.getEmail();
			}

        }else{
	        Admin admin = adminrepo.findByEmail(username);
	        if(admin == null){
	            return null;
            }
	        else{
				password= generateCommonLangPassword();
				admin.setPassword(password);
				adminrepo.save(admin);
	        	email = username;
			}
        }

		emailSender.createResetPasswordMail(email,username,password);
		return email;
	}

	//reset password update



	public List<Student> searchList(int branch) {
		System.out.println("start");
		List<Student> student = studentRepo.findByBranchActive(branch);

		return student;
	}

	public boolean updatesign(String username, MultipartFile sign,String type) throws IOException {
		if(type.equals("ADMIN")){
			Admin admin = adminrepo.findByEmail(username);
			if (admin != null) {
				admin.setFaculty_sign(sign.getBytes());
				adminrepo.save(admin);
				return true;
			} else
				return false;
		}
		else{
			Student student = studentRepo.findByEnrollment(username);
			if (student != null) {
				student.setStudent_sign(sign.getBytes());
				studentRepo.save(student);
				return true;
			} else
				return false;
		}

	}

	public boolean updatephoto(String username, MultipartFile photo,String type) throws IOException {
		if(type.equals("ADMIN")){
			Admin admin = adminrepo.findByEmail(username);
			if (admin != null) {
				admin.setFaculty_photo(photo.getBytes());
				adminrepo.save(admin);
				return true;
			} else
				return false;
		}
		else{
			Student student = studentRepo.findByEnrollment(username);
			if (student != null) {
				student.setStudent_photo(photo.getBytes());
				studentRepo.save(student);
				return true;
			} else
				return false;
		}

	}

	public boolean updateprofile(String enrollment, Student student, Student_info info, Student_guardian guardian) {

		Student existstudent = studentRepo.findByEnrollment(enrollment);
		student.setStudent_id(existstudent.getStudent_id());
		info.setId(existstudent.getInfo().getId());
		student.setPassword(existstudent.getPassword());
		student.setInfo(info);
		student.setStudent_photo(existstudent.getStudent_photo());
		student.setStudent_sign(existstudent.getStudent_sign());
		guardian.setId(existstudent.getGuardian().getId());
		student.setGuardian(guardian);
		if (!(student.getEnrollment().equals(existstudent.getEnrollment()))) {
			existstudent.getToken().newTokenValue();
			existstudent.setIsactive(false);
		}
		student.setToken(existstudent.getToken());
		try {
			studentRepo.save(student);
		} catch (DataIntegrityViolationException E) {
			throw new DataIntegrityViolationException("Duplicate entry");
		}
		return true;

	}

	public Student search(String email) {
		Student student = null;
		student = studentRepo.findByEnrollment(email);
		return student;
	}
	public  Admin adminCrenditials(String email){
		Admin admin = null;
		admin = adminrepo.findByEmail(email);
		return  admin;
	}
	public boolean save(String enrollment, int status, String comment) {
		// TODO Auto-generated method stub
		System.out.println(enrollment);
		Student student = studentRepo.findByEnrollment(enrollment);

		if (student != null) {
			if (status == 1) {
				student.setFaculty_approve(1);
			} else {
				student.setFaculty_approve(2);
			}
			student.setFaculty_comment(comment);
			studentRepo.save(student);
			return true;
		} else {
			return false;
		}

	}

	public boolean saveFeeRefundDetails(FeeRefundDetails fee, String enrollment, MultipartFile fee_Receipt) {
		Student student = studentRepo.findByEnrollment(enrollment);
		fee.setStudent(student);
		System.out.println(fee);
		try {
			fee.setFee_Receipt(fee_Receipt.getBytes());
			feerefunddetailsRepository.save(fee);

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public int saveRequest(String type, String enrollment, MultipartFile fee_Receipt, MultipartFile marksheet, double cgpa, int graduation_year) throws IOException {
		Student student = studentRepo.findByEnrollment(enrollment);
		Request Document = requestRepository.findByReq(type, enrollment);
		if (Document == null) {
			Document = getReq();
			Document.setLive(true);
			Document.setType(type);
			Document.setStudent(student);
			Document.setFee_Receipt(fee_Receipt.getBytes());
			if(marksheet != null)
				Document.setMarksheet(marksheet.getBytes());
			Document.setCgpa(cgpa);
			if(student.getGraduation_year() != graduation_year) {
				System.out.println("in in");
				student.setGraduation_year(graduation_year);
				studentRepo.save(student);
			}
			requestRepository.save(Document);
			return 200;
		} else {
			if (Document.isLive()) {
				return 409;
			} else {
				resetRequest(Document, fee_Receipt, marksheet, cgpa);
				if(student.getGraduation_year() != graduation_year) {
					student.setGraduation_year(graduation_year);
					studentRepo.save(student);
				}
				Document.setStudent(student);
				requestRepository.save(Document);
				return 200;
			}
		}
	}

	private Request getReq() {
		return new Request();
	}

	public List<Student> pendingRegistration(int branch) {
		List<Student> students = null;
		students = searchList(branch);
		return students;
	}

	public List<DocumentData> penndingDocument(userdetails userDetails) {
		String role = userDetails.getRole();
		int branch = userDetails.getBranch();
		if (role.equals("ROLE_DEPARTMENT")) {
			return studentRepo.findByStatus1(branch);
		} else if (role.equals("ROLE_SSMENTOR")) {
			return studentRepo.findByStatus2();
		} else if (role.equals("ROLE_SSHEAD")) {
			return studentRepo.findByStatus3();
		} else
			return null;
	}

	public boolean UpdateStatus(userdetails userDetails, String enrollment, String type, String status,
			String comment) {
		String role = userDetails.getRole();

		Request request = requestRepository.findByReq(type, enrollment);
		if (status.equals("approve")) {
			if (role.equals("ROLE_DEPARTMENT")) {
				request.setStatus1(1);
				requestRepository.save(request);
				return true;
			} else if (role.equals("ROLE_SSMENTOR")) {
				request.setStatus2(1);
				requestRepository.save(request);
				return true;
			} else if (role.equals("ROLE_SSHEAD")) {
				request.setStatus3(1);
				request.setLive(false);
				requestRepository.save(request);
				return true;
			} else
				return false;
		} else {
			if (role.equals("ROLE_DEPARTMENT")) {
				request.setStatus1(2);
				request.setStatus2(2);
				request.setStatus3(2);
				request.setComment(comment);
				request.setLive(false);
				requestRepository.save(request);
				return true;
			} else if (role.equals("ROLE_SSMENTOR")) {
				request.setStatus2(2);
				request.setStatus3(2);
				request.setComment(comment);
				request.setLive(false);
				requestRepository.save(request);
				return true;
			} else if (role.equals("ROLE_SSHEAD")) {
				request.setStatus3(2);
				request.setComment(comment);
				request.setLive(false);
				requestRepository.save(request);
				return true;
			} else
				return false;
		}
	}

	public List<Student> findAllStudent(String caste, Integer addmission_year, String gender, Integer semester, Integer branch,
			String course) {
		
		List<Student> students = studentRepo.findAll(Specification.where(
				StudentSpecification.getStudentByBranch(branch)
				.and(StudentSpecification.getStudentByCaste(caste))
				.and(StudentSpecification.getStudentByCourse(course))
				.and(StudentSpecification.getStudentByGender(gender))
				.and(StudentSpecification.getStudentByAddmissionYear(addmission_year))
				.and(StudentSpecification.getStudentBySem(semester))));
		
		return students;
	}

	public int UpdateSemester(int semester) {

		int i = studentRepo.updateSemester(8);

		System.out.println("called");

		return i;
	}

	public RequestDto ttmp(String enrollment) {
		System.out.println(enrollment);
		RequestDto reqdto = ObjectMapperUtils.map(studentRepo.findByEnrollment(enrollment), RequestDto.class);
		return reqdto;
	}

	public FeeRefundDetails feerefund(String enrollment) {
		FeeRefundDetails temp = feerefunddetailsRepository.findByEnrollment(enrollment);
		System.out.println(temp);
		return temp;
	}

	public void resetRequest(Request request, MultipartFile fee_Receipt, MultipartFile marksheet, double cgpa) throws IOException {
		request.setApplied_date(new Date());
		request.setComment(null);
		request.setStatus1(0);
		request.setStatus2(0);
		request.setStatus3(0);
		request.setFee_Receipt(fee_Receipt.getBytes());
		if (marksheet != null)
			request.setMarksheet(marksheet.getBytes());
		request.setCgpa(cgpa);
		request.setLive(true);
	}

	public boolean transferStudent(userdetails userDetails, int from, int to) {
		String role = userDetails.getRole();
		if (role.equals("ROLE_DEPARTMENT")) {

		}
		return false;
	}

	public String changePasswordDao(String username,String password, String current_password,String type){
		if(type.equals("ADMIN")) {
			Admin admin = adminrepo.findByEmail(username);
			if(!admin.getPassword().equals(current_password)){
				return "false";
			}else {
				try{
					admin.setPassword(password);
					adminrepo.save(admin);
					return "true";
				}
				catch (Exception e){
					System.out.println(e.toString());
					return null;
				}
			}
		}
		else {
			Student student = studentRepo.findByEnrollment(username);
			if(!student.getPassword().equals(current_password)){
				return "false";
			}else {
				try{
					student.setPassword(password);
					studentRepo.save(student);
					return "true";
				}
				catch (Exception e){
					System.out.println(e.toString());
					return null;
				}
			}
		}

	}

    public String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 39, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }
}