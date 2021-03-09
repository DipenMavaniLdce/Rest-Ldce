package com.ldce.Dao;

import com.ldce.Email.EmailSender;
import com.ldce.Main.LdceApplication;
import com.ldce.Model.Admin.Admin;
import com.ldce.Model.Admin.AdminRepository;
import com.ldce.Model.FeeRefund.FeeRefundDetails;
import com.ldce.Model.FeeRefund.FeeRefundDetailsRepository;
import com.ldce.Model.Request.Request;
import com.ldce.Model.Request.RequestRepository;
import com.ldce.Model.Student.Student;
import com.ldce.Model.Student.StudentRepository;
import com.ldce.Model.Student.Student_guardian;
import com.ldce.Model.Student.Student_info;
import com.ldce.security.CustomUserDetails;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Service
@Transactional
@Scope("prototype")
public class UpdateQueryDao {

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

	@Autowired
	FileHandalingDao fileHandalingDao;

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

	// reset password email sender
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

	public boolean updatesign(String username, MultipartFile sign, String type) throws IOException {

		Map<String, String> SIGN;
		String OldFileName;
		if (type.equals("ADMIN")) {
			Admin admin = adminrepo.findByEmail(username);

			if (admin != null) {
				OldFileName = admin.getSign_url();
				SIGN = fileHandalingDao.createStorage(sign, username, "sign", "admin");
				admin.setSign_name(SIGN.get("file_name"));
				admin.setSign_url(SIGN.get("file_url"));
				admin.setSign_size(SIGN.get("file_size"));
				admin.setSign_type(SIGN.get("file_type"));

				boolean isSignStored = fileHandalingDao.storeFile(sign, SIGN.get("file_path"), SIGN.get("file_name"));
				if (isSignStored) {
					adminrepo.save(admin);
					fileHandalingDao.deleteOldFile(LdceApplication.uploadDirectory + "/" + OldFileName);
				}
				return true;
			} else
				return false;
		} else {
			Student student = studentRepo.findByEnrollment(username);
			if (student != null) {
				OldFileName = student.getSign_url();
				SIGN = fileHandalingDao.createStorage(sign, username, "sign", "student");
				student.setSign_name(SIGN.get("file_name"));
				student.setSign_url(SIGN.get("file_url"));
				student.setSign_size(SIGN.get("file_size"));
				student.setSign_type(SIGN.get("file_type"));

				boolean isSignStored = fileHandalingDao.storeFile(sign, SIGN.get("file_path"), SIGN.get("file_name"));
				if (isSignStored) {
					studentRepo.save(student);
					fileHandalingDao.deleteOldFile(LdceApplication.uploadDirectory + "/" + OldFileName);
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
				PHOTO = fileHandalingDao.createStorage(photo, username, "photo", "admin");
				OldFileName = admin.getPhoto_url();
				admin.setPhoto_name(PHOTO.get("file_name"));
				admin.setPhoto_url(PHOTO.get("file_url"));
				admin.setPhoto_size(PHOTO.get("file_size"));
				admin.setPhoto_type(PHOTO.get("file_type"));

				boolean isPhotoStored = fileHandalingDao.storeFile(photo, PHOTO.get("file_path"),
						PHOTO.get("file_name"));
				if (isPhotoStored) {
					adminrepo.save(admin);
					fileHandalingDao.deleteOldFile(LdceApplication.uploadDirectory + "/" + OldFileName);
				}
				return true;
			} else
				return false;
		} else {
			Student student = studentRepo.findByEnrollment(username);
			if (student != null) {
				OldFileName = student.getPhoto_url();
				PHOTO = fileHandalingDao.createStorage(photo, username, "photo", "student");
				student.setPhoto_name(PHOTO.get("file_name"));
				student.setPhoto_url(PHOTO.get("file_url"));
				student.setPhoto_size(PHOTO.get("file_size"));
				student.setPhoto_type(PHOTO.get("file_type"));
				boolean isPhotoStored = fileHandalingDao.storeFile(photo, PHOTO.get("file_path"),
						PHOTO.get("file_name"));
				if (isPhotoStored) {
					studentRepo.save(student);
					fileHandalingDao.deleteOldFile(LdceApplication.uploadDirectory + "/" + OldFileName);
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

	public boolean UpdateFeeRefundStatus(CustomUserDetails userDetails, String enrollment, Integer status,
			String comment) {
		String role = userDetails.getRole();
		FeeRefundDetails fee = feerefunddetailsRepository.findByEnrollment(enrollment);
		if (status == 1) {
			switch (role) {
			case "ROLE_DEPARTMENT":
				fee.setStatus1(1);
				feerefunddetailsRepository.save(fee);
				return true;
			case "ROLE_SSMENTOR":
				fee.setStatus2(1);
				feerefunddetailsRepository.save(fee);
				return true;
			case "ROLE_SSHEAD":
				fee.setStatus3(1);
				fee.setLive(false);
				feerefunddetailsRepository.save(fee);
				return true;
			default:
				return false;
			}

		} else {
			switch (role) {
			case "ROLE_DEPARTMENT":
				fee.setStatus1(2);
				fee.setStatus2(2);
				fee.setStatus3(2);
				fee.setComment(comment);
				fee.setLive(false);
				feerefunddetailsRepository.save(fee);
				return true;
			case "ROLE_SSMENTOR":
				fee.setStatus2(2);
				fee.setStatus3(2);
				fee.setComment(comment);
				fee.setLive(false);
				feerefunddetailsRepository.save(fee);
				return true;
			case "ROLE_SSHEAD":
				fee.setStatus3(2);
				fee.setComment(comment);
				fee.setLive(false);
				feerefunddetailsRepository.save(fee);
				return true;
			default:
				return false;
			}
		}
	}

	public boolean UpdateStatus(CustomUserDetails userDetails, String enrollment, String type, Integer status,
			String comment, String rank) {
		String role = userDetails.getRole();

		Request request = requestRepository.findByReq(type, enrollment);
		System.out.println(request.getType() + ".");
		if (role.equals("ROLE_DEPARTMENT"))
			request.setLast_modified_by(userDetails.getBranch() + "_" + role);
		else
			request.setLast_modified_by(role);

		if (status == 1) {
			switch (role) {
			case "ROLE_DEPARTMENT":

				request.setStatus1(1);
				request.setStatus2(1);
				if (rank != null)
					request.setRanks(rank);
				requestRepository.save(request);

				return true;
			case "ROLE_SSMENTOR":
				request.setStatus2(1);
				requestRepository.save(request);
				return true;
			case "ROLE_SSHEAD":
				request.setStatus3(1);
				request.setLive(false);
				requestRepository.save(request);
				return true;
			default:
				return false;
			}
		} else {
			switch (role) {
			case "ROLE_DEPARTMENT":
				request.setStatus1(2);
				request.setStatus2(2);
				request.setStatus3(2);
				request.setComment(comment);
				request.setLive(false);
				requestRepository.save(request);
				return true;
			case "ROLE_SSMENTOR":
				request.setStatus2(2);
				request.setStatus3(2);
				request.setComment(comment);
				request.setLive(false);
				requestRepository.save(request);
				return true;
			case "ROLE_SSHEAD":
				request.setStatus3(2);
				request.setComment(comment);
				request.setLive(false);
				requestRepository.save(request);
				return true;
			default:
				return false;
			}
		}
	}

	public void resetRequest(Request request, MultipartFile request_document, Double cgpa, String username, String type)
			throws IOException {

		request.setStatus1(0);
		request.setStatus2(0);
		request.setStatus3(0);
		Map<String, String> DOCUMENT = fileHandalingDao.createStorage(request_document, username, type,
				"student/request/");
		request.setDocument_name(DOCUMENT.get("file_name"));
		request.setDocument_url(DOCUMENT.get("file_url"));
		request.setDocument_size(DOCUMENT.get("file_size"));
		request.setDocument_type(DOCUMENT.get("file_type"));

		boolean isDocumetStored = fileHandalingDao.storeFile(request_document, DOCUMENT.get("file_path"),
				DOCUMENT.get("file_name"));
		if (cgpa != null)
			request.setCgpa(cgpa);
		request.setLive(true);
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
		return pwdChars.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	public String progressionBySem(int from, int to, int branchCode, String course) {

		String message = "records updated successfully";
		int count = 0;
		boolean courseCheck = course.equals("BE") || course.equals("PDDC");
		boolean checkGraduation = (courseCheck&& to == 9) || (!courseCheck && to == 5);
		try {
		
			count = studentRepo.progressionBySem(to, from, branchCode, course,checkGraduation);
			message = count + " " + message;
		}

		catch (Exception e) {
			message = "something went wrong please retry";
		}
		return message;
	}

    public HttpStatus detainStudent(String er, CustomUserDetails userDetails) {
		Student student = studentRepo.findByEnrollmentAndCourseAndBranch(er,userDetails.getCourse(),userDetails.getBranch());
		if(student == null)
			return HttpStatus.NOT_FOUND;
		else{
			if(student.isGraduation()){
				student.setGraduation(false);
			}
			if (student.getSemester() < 2) {
				student.setSemester(1);
			} else {
				student.setSemester(student.getSemester() - 2);
			}
			studentRepo.save(student);
			return HttpStatus.OK;
		}
    }
}