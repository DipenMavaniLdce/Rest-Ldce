package com.ldce.Dao;

import java.io.*;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import com.ldce.Data.DocumentData;
import com.ldce.Data.FeeRefundData;
import com.ldce.Data.RequestDto;
import com.ldce.Main.LdceApplication;
import com.ldce.SearchSpecification.ObjectMapperUtils;
import com.ldce.SearchSpecification.StudentSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ldce.Email.EmailSender;
import com.ldce.Model.FeeRefund.FeeRefundDetails;
import com.ldce.Model.FeeRefund.FeeRefundDetailsRepository;
import com.ldce.Model.Request.Request;
import com.ldce.Model.Request.RequestRepository;
import com.ldce.Model.Student.Student;
import com.ldce.Model.Student.StudentRepository;
import com.ldce.Model.Student.Student_guardian;
import com.ldce.Model.Student.Student_info;
import com.ldce.Main.Token;

import com.ldce.Model.Admin.Admin;
import com.ldce.Model.Admin.AdminRepository;
import com.ldce.security.CustomUserDetails;

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

	@Autowired
	PasswordEncoder passwordEncoder;

	Request request;

	// saveStudent
	public void save(Student student, Student_info info, Student_guardian guardian, MultipartFile photo,
			MultipartFile sign) throws Exception {
		Map<String, String> PHOTO = createStorage(photo, student.getEnrollment(), "photo", "student");
		Map<String, String> SIGN = createStorage(sign, student.getEnrollment(), "sign", "student");

		student.setPhoto_name(PHOTO.get("file_name"));
		student.setPhoto_url(PHOTO.get("file_url"));
		student.setPhoto_size(PHOTO.get("file_size"));
		student.setPhoto_type(PHOTO.get("file_type"));

		student.setSign_name(SIGN.get("file_name"));
		student.setSign_url(SIGN.get("file_url"));
		student.setSign_size(SIGN.get("file_size"));
		student.setSign_type(SIGN.get("file_type"));

		boolean isPhotoStored = storeFile(photo, PHOTO.get("file_path"), PHOTO.get("file_name"));
		boolean isSignStored = storeFile(sign, SIGN.get("file_path"), SIGN.get("file_name"));

		student.setPassword(passwordEncoder.encode(student.getPassword()));
		student.setToken(new Token());
		student.setInfo(info);
		student.setGuardian(guardian);
		try {
			if (isPhotoStored && isSignStored) {
				studentRepo.save(student);
			}

		} catch (Exception E) {
			deleteOldFile(LdceApplication.uploadDirectory + "/" + student.getSign_url());
			deleteOldFile(LdceApplication.uploadDirectory + "/" + student.getPhoto_url());
			System.out.println("in Exception");

			throw E;
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
	public void save(Admin admin, MultipartFile photo, MultipartFile sign) throws Exception {

		Map<String, String> PHOTO = createStorage(photo, admin.getFaculty_id(), "photo", "admin");
		Map<String, String> SIGN = createStorage(sign, admin.getFaculty_id(), "sign", "admin");

		admin.setPhoto_name(PHOTO.get("file_name"));
		admin.setPhoto_url(PHOTO.get("file_url"));
		admin.setPhoto_size(PHOTO.get("file_size"));
		admin.setPhoto_type(PHOTO.get("file_type"));

		admin.setSign_name(SIGN.get("file_name"));
		admin.setSign_url(SIGN.get("file_url"));
		admin.setSign_size(SIGN.get("file_size"));
		admin.setSign_type(SIGN.get("file_type"));

		boolean isPhotoStored = storeFile(photo, PHOTO.get("file_path"), PHOTO.get("file_name"));
		boolean isSignStored = storeFile(sign, SIGN.get("file_path"), SIGN.get("file_name"));

		admin.setPassword(passwordEncoder.encode(admin.getPassword()));

		try {
			if (isPhotoStored && isSignStored) {
				adminrepo.save(admin);
			}
		} catch (Exception E) {
			throw E;
		}

	}

//reset password email sender
	public String resetPassword(String username, String type) {
		String email;
		String password;
		if (type.equals("STUDENT")) {
			Student student = studentRepo.findByEnrollment(username);
			if (student == null) {
				return null;
			} else {
				password = generateCommonLangPassword();
				student.setPassword(passwordEncoder.encode(password));
				studentRepo.save(student);
				System.out.println("save");
				email = student.getEmail();
			}

		} else {
			Admin admin = adminrepo.findByEmail(username);
			if (admin == null) {
				return null;
			} else {
				password = generateCommonLangPassword();
				admin.setPassword(passwordEncoder.encode(password));
				adminrepo.save(admin);
				email = username;
			}
		}

		emailSender.createResetPasswordMail(email, username, password);
		return email;
	}

	// reset password update

	public List<Student> searchList(int branch, String course) {

		List<Student> student = studentRepo.findByBranchActive(branch, course);

		return student;
	}

	public boolean updatesign(String username, MultipartFile sign, String type) throws IOException {

		Map<String, String> SIGN;
		String OldFileName;
		if (type.equals("ADMIN")) {
			Admin admin = adminrepo.findByEmail(username);

			if (admin != null) {
				OldFileName = admin.getSign_url();
				SIGN = createStorage(sign, username, "sign", "admin");
				admin.setSign_name(SIGN.get("file_name"));
				admin.setSign_url(SIGN.get("file_url"));
				admin.setSign_size(SIGN.get("file_size"));
				admin.setSign_type(SIGN.get("file_type"));

				boolean isSignStored = storeFile(sign, SIGN.get("file_path"), SIGN.get("file_name"));
				if (isSignStored) {
					adminrepo.save(admin);
					deleteOldFile(LdceApplication.uploadDirectory + "/" + OldFileName);
				}
				return true;
			} else
				return false;
		} else {
			Student student = studentRepo.findByEnrollment(username);
			if (student != null) {
				OldFileName = student.getSign_url();
				SIGN = createStorage(sign, username, "sign", "student");
				student.setSign_name(SIGN.get("file_name"));
				student.setSign_url(SIGN.get("file_url"));
				student.setSign_size(SIGN.get("file_size"));
				student.setSign_type(SIGN.get("file_type"));

				boolean isSignStored = storeFile(sign, SIGN.get("file_path"), SIGN.get("file_name"));
				if (isSignStored) {
					studentRepo.save(student);
					deleteOldFile(LdceApplication.uploadDirectory + "/" + OldFileName);
				}
				return true;
			} else
				return false;
		}

	}

	public boolean updatephoto(String username, MultipartFile photo, String type) throws IOException {
		Map<String, String> PHOTO;
		String OldFileName;
		if (type.equals("ADMIN")) {

			Admin admin = adminrepo.findByEmail(username);
			if (admin != null) {
				PHOTO = createStorage(photo, username, "photo", "admin");
				OldFileName = admin.getPhoto_url();
				admin.setPhoto_name(PHOTO.get("file_name"));
				admin.setPhoto_url(PHOTO.get("file_url"));
				admin.setPhoto_size(PHOTO.get("file_size"));
				admin.setPhoto_type(PHOTO.get("file_type"));

				boolean isPhotoStored = storeFile(photo, PHOTO.get("file_path"), PHOTO.get("file_name"));
				if (isPhotoStored) {
					adminrepo.save(admin);
					deleteOldFile(LdceApplication.uploadDirectory + "/" + OldFileName);
				}
				return true;
			} else
				return false;
		} else {
			Student student = studentRepo.findByEnrollment(username);
			if (student != null) {
				OldFileName = student.getPhoto_url();
				PHOTO = createStorage(photo, username, "photo", "student");
				student.setPhoto_name(PHOTO.get("file_name"));
				student.setPhoto_url(PHOTO.get("file_url"));
				student.setPhoto_size(PHOTO.get("file_size"));
				student.setPhoto_type(PHOTO.get("file_type"));
				boolean isPhotoStored = storeFile(photo, PHOTO.get("file_path"), PHOTO.get("file_name"));
				if (isPhotoStored) {
					studentRepo.save(student);
					deleteOldFile(LdceApplication.uploadDirectory + "/" + OldFileName);
				}
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

		student.setSign_name(existstudent.getSign_name());
		student.setSign_url(existstudent.getSign_url());
		student.setSign_size(existstudent.getSign_size());
		student.setSign_type(existstudent.getSign_type());

		student.setPhoto_name(existstudent.getPhoto_name());
		student.setPhoto_url(existstudent.getPhoto_url());
		student.setPhoto_size(existstudent.getPhoto_size());
		student.setPhoto_type(existstudent.getPhoto_type());

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
		Logger logger = LoggerFactory.getLogger(Dao.class);
		Student student = null;
		student = studentRepo.findByEnrollment(email);
		if (student != null)
			logger.trace("student data found");
		else
			logger.warn("student data not found");
		return student;
	}

	public Admin adminCrenditials(String email) {
		Admin admin = null;
		Logger logger = LoggerFactory.getLogger(Dao.class);
		admin = adminrepo.findByEmail(email);
		logger.trace("admin found");
		return admin;
	}

	public boolean save(String enrollment, int status, String comment) {
		// TODO Auto-generated method stub

		Student student = studentRepo.findByEnrollment(enrollment);
		Logger logger = LoggerFactory.getLogger(Dao.class);
		if (student != null) {
			if (status == 1) {
				student.setFaculty_approve(1);
				logger.trace("Student registration profile approved by faculty");
			} else {
				student.setFaculty_approve(2);
				logger.trace("Student registration profile rejected by faculty");
			}
			student.setFaculty_comment(comment);
			logger.trace("Faculty added a comment");
			studentRepo.save(student);
			logger.trace("Student profile updated");
			return true;
		} else {
			logger.trace("Student no found");
			return false;
		}

	}

	public int saveFeeRefundDetails(FeeRefundDetails fee, String enrollment, MultipartFile request_document)
			throws IOException {
		Student student = studentRepo.findByEnrollment(enrollment);
		FeeRefundDetails feeRefund = feerefunddetailsRepository.findByEnrollment(enrollment);
		if (feeRefund == null || feeRefund != null && !feeRefund.isLive()) {

			fee.setStudent(student);

			Map<String, String> DOCUMENT = createStorage(request_document, enrollment, "feereceipt",
					"student/request/feereceipt");
			fee.setFee_document_name(DOCUMENT.get("file_name"));
			fee.setFee_document_url(DOCUMENT.get("file_url"));
			fee.setFee_document_size(DOCUMENT.get("file_size"));
			fee.setFee_document_type(DOCUMENT.get("file_type"));
			boolean isDocumentStored = storeFile(request_document, DOCUMENT.get("file_path"),
					DOCUMENT.get("file_name"));

			if (isDocumentStored) {
				feerefunddetailsRepository.save(fee);
			} else {
				return 400;
			}

			return 200;
		} else
			return 409;

	}

	public int saveRequest(String type, String enrollment, MultipartFile request_document, double cgpa,
			int graduation_year) throws IOException {
		Student student = studentRepo.findByEnrollment(enrollment);
		Logger logger = LoggerFactory.getLogger(Dao.class);

		int isApproved = student.getFaculty_approve();

		if (isApproved == 2 || isApproved == 0) {
			return 400;
		}

		Request Document = requestRepository.findByReq(type, enrollment);

		if (Document == null) {
			// new document is created
			Document = getReq();
			Document.setLive(true);
			Document.setType(type);
			Document.setStudent(student);
			Map<String, String> DOCUMENT = createStorage(request_document, student.getEnrollment(), type,
					"student/request/");

			Document.setDocument_name(DOCUMENT.get("file_name"));
			Document.setDocument_url(DOCUMENT.get("file_url"));
			Document.setDocument_size(DOCUMENT.get("file_size"));
			Document.setDocument_type(DOCUMENT.get("file_type"));

			boolean isDocumentStored = storeFile(request_document, DOCUMENT.get("file_path"),
					DOCUMENT.get("file_name"));

			Document.setCgpa(cgpa);
			if (graduation_year != 0 && student.getGraduation_year() != graduation_year) {

				student.setGraduation_year(graduation_year);
				studentRepo.save(student);
			}
			if (isDocumentStored) {
				if (!type.equals("character")) {
					Document.setStatus1(1);
				}
				requestRepository.save(Document);
			} else {
				return 400;
			}

			return 200;
		} else {
			if (Document.isLive() || Document.getStatus3() == 1) {
				return 409;
			} else {
				resetRequest(Document, request_document, cgpa, student.getEnrollment(), type);
				if (graduation_year != 0 && student.getGraduation_year() != graduation_year) {
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

	public List<Student> pendingRegistration(int branch, String course) {
		List<Student> students = null;
		students = searchList(branch, course);
		return students;
	}

	public List<FeeRefundData> penddingFeeRefund(CustomUserDetails userDetails) {
		String role = userDetails.getRole();
		int branch = userDetails.getBranch();
		if (role.equals("ROLE_DEPARTMENT")) {
			return studentRepo.findByfeerefundStatus1(branch);
		} else if (role.equals("ROLE_SSMENTOR")) {
			return studentRepo.findByfeerefundStatus2();
		} else if (role.equals("ROLE_SSHEAD")) {
			return studentRepo.findByfeerefundStatus3();
		} else
			return null;
	}

	public List<DocumentData> penndingDocument(CustomUserDetails userDetails) {
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

	public boolean UpdateFeeRefundStatus(CustomUserDetails userDetails, String enrollment, Integer status,
			String comment) {
		String role = userDetails.getRole();
		FeeRefundDetails fee = feerefunddetailsRepository.findByEnrollment(enrollment);
		if (status == 1) {
			if (role.equals("ROLE_DEPARTMENT")) {
				fee.setStatus1(1);
				feerefunddetailsRepository.save(fee);
				return true;
			} else if (role.equals("ROLE_SSMENTOR")) {
				fee.setStatus2(1);
				feerefunddetailsRepository.save(fee);
				return true;
			} else if (role.equals("ROLE_SSHEAD")) {
				fee.setStatus3(1);
				fee.setLive(false);
				feerefunddetailsRepository.save(fee);
				return true;
			} else
				return false;

		} else {
			if (role.equals("ROLE_DEPARTMENT")) {
				fee.setStatus1(2);
				fee.setStatus2(2);
				fee.setStatus3(2);
				fee.setComment(comment);
				fee.setLive(false);
				feerefunddetailsRepository.save(fee);
				return true;
			} else if (role.equals("ROLE_SSMENTOR")) {
				fee.setStatus2(2);
				fee.setStatus3(2);
				fee.setComment(comment);
				fee.setLive(false);
				feerefunddetailsRepository.save(fee);
				return true;
			} else if (role.equals("ROLE_SSHEAD")) {
				fee.setStatus3(2);
				fee.setComment(comment);
				fee.setLive(false);
				feerefunddetailsRepository.save(fee);
				return true;
			} else
				return false;
		}
	}

	public boolean UpdateStatus(CustomUserDetails userDetails, String enrollment, String type, Integer status,
			String comment, String rank) {
		String role = userDetails.getRole();

		Request request = requestRepository.findByReq(type, enrollment);
		System.out.println(request.getType() + ".");
		if (role.equals("ROLE_DEPARTMENT"))
			request.setLast_modified_by(userDetails.getBranch() + role);
		else
			request.setLast_modified_by(role);

		if (status == 1) {
			if (role.equals("ROLE_DEPARTMENT")) {

				request.setStatus1(1);
				if (rank != null)
					request.setRanks(rank);
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

	public List<DocumentData> findrequest(Date start, Date end, String role, String enrollment) {

		if ((start == null || end == null) && enrollment == null)
			return null;

		// List<Student> students = studentRepo.findAll(
		// Specification.where(StudentSpecification.getRequestData(start,end ,role) ));
		// .and(StudentSpecification.getStudentByEnrollment(enrollment))
//						.and(StudentSpecification.getStudentByfirstlevel(role))
//						
//		if(start!=null)start.setTime(0);
//		if(end!=null)end.setTime(0);
		System.out.println("............................okkkkkk..................................");
		System.out.println(enrollment);

		List<DocumentData> students = studentRepo.findDocument(start, end, enrollment);

		System.out.println("..............................................................");
		// List<RequestDto> data = ObjectMapperUtils.mapAll(students, RequestDto.class);

		return students;

	}

	public List<Student> findAllStudent(String caste, Integer addmission_year, String gender, Integer semester,
			Integer branch, String course, String admission_category) {

		List<Student> students = studentRepo.findAll(Specification.where(StudentSpecification.getStudentByBranch(branch)
				.and(StudentSpecification.getStudentByCaste(caste)).and(StudentSpecification.getStudentByCourse(course))
				.and(StudentSpecification.getStudentByGender(gender))
				.and(StudentSpecification.getStudentByAddmissionYear(addmission_year))
				.and(StudentSpecification.getStudentByAdmissionCategory(admission_category))
				.and(StudentSpecification.getStudentBySem(semester))));

		return students;
	}

	public int UpdateSemester(int semester) {

		int i = studentRepo.updateSemester(8);

		System.out.println("called");

		return i;
	}

	public RequestDto getStudentDashbord(String enrollment) {
		RequestDto reqdto = ObjectMapperUtils.map(studentRepo.findByEnrollment(enrollment), RequestDto.class);
		return reqdto;
	}

	public FeeRefundDetails feerefund(String enrollment) {
		FeeRefundDetails temp = feerefunddetailsRepository.findByEnrollment(enrollment);
		System.out.println(temp);
		return temp;
	}

	public void resetRequest(Request request, MultipartFile request_document, Double cgpa, String username, String type)
			throws IOException {

		request.setStatus1(0);
		request.setStatus2(0);
		request.setStatus3(0);
		Map<String, String> DOCUMENT = createStorage(request_document, username, type, "student/request/");
		request.setDocument_name(DOCUMENT.get("file_name"));
		request.setDocument_url(DOCUMENT.get("file_url"));
		request.setDocument_size(DOCUMENT.get("file_size"));
		request.setDocument_type(DOCUMENT.get("file_type"));

		boolean isDocumetStored = storeFile(request_document, DOCUMENT.get("file_path"), DOCUMENT.get("file_name"));
		if (cgpa != null)
			request.setCgpa(cgpa);
		request.setLive(true);
	}

	public boolean transferStudent(CustomUserDetails userDetails, int from, int to) {
		String role = userDetails.getRole();
		if (role.equals("ROLE_DEPARTMENT")) {

		}
		return false;
	}

	public String changePasswordDao(String username, String password, String current_password, String type) {
		if (type.equals("ADMIN")) {
			Admin admin = adminrepo.findByEmail(username);
			if (!passwordEncoder.matches(current_password, admin.getPassword())) {
				return "false";
			} else {
				try {
					admin.setPassword(passwordEncoder.encode(password));
					adminrepo.save(admin);
					return "true";
				} catch (Exception e) {
					System.out.println(e.toString());
					return null;
				}
			}
		} else {
			Student student = studentRepo.findByEnrollment(username);
			if (!passwordEncoder.matches(current_password, student.getPassword())) {
				return "false";
			} else {
				try {
					student.setPassword(passwordEncoder.encode(password));
					studentRepo.save(student);
					return "true";
				} catch (Exception e) {
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
		String combinedChars = upperCaseLetters.concat(lowerCaseLetters).concat(numbers).concat(specialChar)
				.concat(totalChars);
		List<Character> pwdChars = combinedChars.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		Collections.shuffle(pwdChars);
		String password = pwdChars.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				.toString();
		return password;
	}

	public HashMap<String, String> createStorage(MultipartFile file, String id, String type, String domain) {
		// types === photo/sign/feereceipt/marksheet

		// domain student,admin,student/request etc...
		HashMap<String, String> fileData = new HashMap<String, String>();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String file_name = type + "_" + id + "_" + timestamp.getTime() + "_" + file.getOriginalFilename();
		String file_path = Paths.get(LdceApplication.uploadDirectory, domain, type).toString();
		String file_url = Paths.get(domain, type, file_name).toString();
		String file_type = file.getContentType();
		Long file_Size = file.getSize();
		String file_size = file_Size.toString();
		fileData.put("file_name", file_name);
		fileData.put("file_path", file_path);
		fileData.put("file_type", file_type);
		fileData.put("file_size", file_size);
		fileData.put("file_url", file_url);
		return fileData;
	}

	public boolean storeFile(MultipartFile file, String file_path, String file_name) throws IOException {
		Logger logger = LoggerFactory.getLogger(Dao.class);
		logger.info("request come for store file at path:  " + file_path);
		logger.info("Name of the file is  " + file_name);
		boolean fileExists = true;
		File f = new File(file_path);
		if (!f.exists()) {
			logger.info("file path does not exist" + file_path);
			fileExists = f.mkdirs();
			if (fileExists)
				logger.info("path created successfully " + file_path);
			else
				logger.info("unable to create filepath successfully " + file_name);
		}
		if (fileExists) {
			try {

				BufferedOutputStream stream1 = new BufferedOutputStream(
						new FileOutputStream(new File(Paths.get(file_path, file_name).toString())));
				logger.info("stream created at " + file_path);
				stream1.write(file.getBytes());
				stream1.close();
				logger.info("stream closed at " + file_path);

			} catch (Exception e) {
				logger.error("Exception occured during storing a file: ", file_name);
				logger.error(e.toString());
				return false;
			}
			return true;
		}
		return false;
	}

	public void deleteOldFile(String filename) {
		Logger logger = LoggerFactory.getLogger(Dao.class);
		logger.info("request come for delete old file " + filename);
		try {
			boolean delete = new File(filename).delete();
			if (delete) {
				logger.info(filename + " deleted success fully");

			} else {
				logger.info(filename + " is not deleted");
			}
		} catch (Exception e) {
			logger.error("exception occure for deleting old file " + filename);
			logger.error(e.toString());

		}

	}

}