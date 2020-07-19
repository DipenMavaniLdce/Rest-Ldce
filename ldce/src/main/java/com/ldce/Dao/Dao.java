package com.ldce.Dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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
	public boolean resetPassword(String email) {
		Student student = studentRepo.findByEmail(email);
		if (student == null) {
			return false;
		} else {
			emailSender.createResetPasswordMail(email, student.getToken().getTokenValue());
			return true;
		}
	}

//reset password update
	public boolean updatePassword(String tokenValue, String password) {
		Student existstudent = studentRepo.findBytokenValue(tokenValue);

		if (existstudent != null) {
			existstudent.setIsactive(true);
			existstudent.setPassword(password);
			existstudent.getToken().newTokenValue();
			studentRepo.save(existstudent);
			return true;
		} else {
			return false;
		}

	}

	public List<Student> searchList(int branch) {
		System.out.println("start");
		List<Student> student = studentRepo.findByBranchActive(branch);

		return student;
	}

	public boolean updatesign(String email, MultipartFile sign) throws IOException {

		Student student = studentRepo.findByEmail(email);
		if (student != null) {
			student.setStudent_sign(sign.getBytes());
			studentRepo.save(student);
			return true;
		} else
			return false;
	}

	public boolean updatephoto(String email, MultipartFile photo) throws IOException {

		Student student = studentRepo.findByEmail(email);
		if (student != null) {
			student.setStudent_photo(photo.getBytes());
			studentRepo.save(student);
			return true;
		} else
			return false;
	}

	public boolean updateprofile(String email, Student student, Student_info info, Student_guardian guardian) {

		Student existstudent = studentRepo.findByEmail(email);
		student.setStudent_id(existstudent.getStudent_id());
		info.setId(existstudent.getInfo().getId());
		student.setPassword(existstudent.getPassword());
		student.setInfo(info);
		student.setStudent_photo(existstudent.getStudent_photo());
		student.setStudent_sign(existstudent.getStudent_sign());
		guardian.setId(existstudent.getGuardian().getId());
		student.setGuardian(guardian);
		if (!(student.getEmail().equals(existstudent.getEmail()))) {
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
		student = studentRepo.findByEmail(email);
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

	public boolean saveRequest(String type, String enrollment, MultipartFile fee_Receipt, MultipartFile marksheet,
			double cgpa, String rank) throws IOException {
		if (type.equals("character") || type.equals("rank") || type.equals("conduct") || type.equals("bonafide")) {
			Student student = studentRepo.findByEnrollment(enrollment);
			Request Document = requestRepository.findByReq(type, enrollment);
			if (Document == null) {
				Document = getReq();
				Document.setLive(true);
				Document.setType(type);
				Document.setStudent(student);
				Document.setFee_Receipt(fee_Receipt.getBytes());
				Document.setMarksheet(marksheet.getBytes());
				Document.setCgpa(cgpa);
				Document.setRanks(rank);
				requestRepository.save(Document);
				return true;
			} else {
				if (Document.isLive()) {
					return false;
				} else {
					resetRequest(Document, fee_Receipt, marksheet, cgpa, rank);
					Document.setStudent(student);
					requestRepository.save(Document);
					return true;
				}
			}
		} else
			return false;
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

	public List<Student> findAllStudent(String caste, int addmission_year, String gender, int semester, int branch,
			String course) {
		List<Student> students = studentRepo.findAll(Specification.where(StudentSpecification.getStudentByBranch(branch)
				.and(StudentSpecification.getStudentByCaste(caste).and(StudentSpecification.getStudentByCourse(course)
						.and(StudentSpecification.getStudentByGender(gender))))));

		// List<StudentDto> studentdto = ObjectMapperUtils.mapAll(students,
		// StudentDto.class);
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

	public void resetRequest(Request request, MultipartFile fee_Receipt, MultipartFile marksheet, double cgpa,
			String rank) throws IOException {
		request.setApplied_date(new Date());
		request.setComment(null);
		request.setStatus1(0);
		request.setStatus2(0);
		request.setStatus3(0);
		request.setFee_Receipt(fee_Receipt.getBytes());
		if (marksheet != null)
			request.setMarksheet(marksheet.getBytes());
		request.setCgpa(cgpa);
		request.setRanks(rank);
		request.setLive(true);
	}

	public boolean transferStudent(userdetails userDetails, int from, int to) {
		String role = userDetails.getRole();
		if (role.equals("ROLE_DEPARTMENT")) {

		}
		return false;
	}
}