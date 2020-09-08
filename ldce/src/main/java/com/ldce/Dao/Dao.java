package com.ldce.Dao;

import java.io.*;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import com.ldce.Data.FeeRefundData;
import com.ldce.controller.Controller;
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
import com.ldce.Model.FeeRefund.FeeRefundDetails;
import com.ldce.Model.FeeRefund.FeeRefundDetailsRepository;
import com.ldce.Model.Request.Request;
import com.ldce.Model.Request.RequestRepository;
import com.ldce.Model.Student.Student;
import com.ldce.Model.Student.StudentRepository;
import com.ldce.Model.Student.Student_guardian;
import com.ldce.Model.Student.Student_info;
import com.ldce.Main.Token;
import com.ldce.SearchSpecification.ObjectMapperUtils;
import com.ldce.SearchSpecification.StudentSpecification;

import com.ldce.Model.Admin.Admin;
import com.ldce.Model.Admin.AdminRepository;
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
			MultipartFile sign) throws Exception {
		Map<String,String> PHOTO=createStorage(photo,student.getEnrollment(),"photo","student");
		Map<String,String> SIGN=createStorage(photo,student.getEnrollment(),"sign","student");

		student.setPhoto_name(PHOTO.get("file_name"));
		student.setPhoto_url(PHOTO.get("file_url"));
		student.setPhoto_size(PHOTO.get("file_size"));
		student.setPhoto_type(PHOTO.get("file_type"));

		student.setSign_name(SIGN.get("file_name"));
		student.setSign_url(SIGN.get("file_url"));
		student.setSign_size(SIGN.get("file_size"));
		student.setSign_type(SIGN.get("file_type"));

		boolean isPhotoStored = storeFile(photo,PHOTO.get("file_path"),PHOTO.get("file_name"));
		boolean isSignStored = storeFile(photo,SIGN.get("file_path"),SIGN.get("file_name"));


		student.setToken(new Token());
		student.setInfo(info);
		student.setGuardian(guardian);
		try {
			if(isPhotoStored && isSignStored){
				studentRepo.save(student);
			}

		} catch (Exception E) {
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

		Map<String,String> PHOTO=createStorage(photo,admin.getFaculty_id(),"photo","admin");
		Map<String,String> SIGN=createStorage(photo,admin.getFaculty_id(),"sign","admin");

		admin.setPhoto_name(PHOTO.get("file_name"));
		admin.setPhoto_url(PHOTO.get("file_url"));
		admin.setPhoto_size(PHOTO.get("file_size"));
		admin.setPhoto_type(PHOTO.get("file_type"));

		admin.setSign_name(SIGN.get("file_name"));
		admin.setSign_url(SIGN.get("file_url"));
		admin.setSign_size(SIGN.get("file_size"));
		admin.setSign_type(SIGN.get("file_type"));

		boolean isPhotoStored = storeFile(photo,PHOTO.get("file_path"),PHOTO.get("file_name"));
		boolean isSignStored = storeFile(photo,SIGN.get("file_path"),SIGN.get("file_name"));


		try {
			if(isPhotoStored && isSignStored) {
				adminrepo.save(admin);
			}
		} catch (Exception E) {
			throw E;
		}


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


		Map<String,String> SIGN;
		if(type.equals("ADMIN")){
			Admin admin = adminrepo.findByEmail(username);

			if (admin != null) {
				SIGN = createStorage(sign,username,"sign","admin");
				admin.setSign_name(SIGN.get("file_name"));
				admin.setSign_url(SIGN.get("file_url"));
				admin.setSign_size(SIGN.get("file_size"));
				admin.setSign_type(SIGN.get("file_type"));

				boolean isSignStored = storeFile(sign,SIGN.get("file_path"),SIGN.get("file_name"));
				if(isSignStored){
					adminrepo.save(admin);
				}
				return true;
			} else
				return false;
		}
		else{
			Student student = studentRepo.findByEnrollment(username);
			if (student != null) {
				SIGN = createStorage(sign,username,"sign","student");
				student.setSign_name(SIGN.get("file_name"));
				student.setSign_url(SIGN.get("file_url"));
				student.setSign_size(SIGN.get("file_size"));
				student.setSign_type(SIGN.get("file_type"));

				boolean isSignStored = storeFile(sign,SIGN.get("file_path"),SIGN.get("file_name"));
				if(isSignStored){
					studentRepo.save(student);
				}
				return true;
			} else
				return false;
		}

	}

	public boolean updatephoto(String username, MultipartFile photo,String type) throws IOException {
		Map<String,String> PHOTO;
		if(type.equals("ADMIN")){

			Admin admin = adminrepo.findByEmail(username);
			if (admin != null) {
				PHOTO = createStorage(photo,username,"photo","admin");

				admin.setPhoto_name(PHOTO.get("file_name"));
				admin.setPhoto_url(PHOTO.get("file_url"));
				admin.setPhoto_size(PHOTO.get("file_size"));
				admin.setPhoto_type(PHOTO.get("file_type"));

				boolean isPhotoStored = storeFile(photo,PHOTO.get("file_path"),PHOTO.get("file_name"));
				if(isPhotoStored){
					adminrepo.save(admin);
				}
				return true;
			} else
				return false;
		}
		else{
			Student student = studentRepo.findByEnrollment(username);
			if (student != null) {
				PHOTO = createStorage(photo,username,"photo","student");
				student.setPhoto_name(PHOTO.get("file_name"));
				student.setPhoto_url(PHOTO.get("file_url"));
				student.setPhoto_size(PHOTO.get("file_size"));
				student.setPhoto_type(PHOTO.get("file_type"));
				boolean isPhotoStored = storeFile(photo,PHOTO.get("file_path"),PHOTO.get("file_name"));
				if(isPhotoStored){
					studentRepo.save(student);
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

	public int  saveFeeRefundDetails(FeeRefundDetails fee, String enrollment, MultipartFile request_document) throws IOException {
		Student student = studentRepo.findByEnrollment(enrollment);
		FeeRefundDetails feeRefund = feerefunddetailsRepository.findByEnrollment(enrollment);
		if(feeRefund==null || feeRefund!=null && !feeRefund.isLive()) {
			fee.setStudent(student);
			System.out.println(fee);
			Map<String, String> DOCUMENT = createStorage(request_document, enrollment, "feereceipt", "student\\request\\feereceipt");
			fee.setFee_document_name(DOCUMENT.get("file_name"));
			fee.setFee_document_url(DOCUMENT.get("file_url"));
			fee.setFee_document_size(DOCUMENT.get("file_size"));
			fee.setFee_document_type(DOCUMENT.get("file_type"));
			boolean isDocumentStored = storeFile(request_document, DOCUMENT.get("file_path"), DOCUMENT.get("file_name"));

			if (isDocumentStored) {
				feerefunddetailsRepository.save(fee);
			} else {
				return 400;
			}

			return 200;
		}
		else
				return 409;


	}

	public int saveRequest(String type, String enrollment, MultipartFile request_document , double cgpa, int graduation_year) throws IOException {
		Student student = studentRepo.findByEnrollment(enrollment);
		Request Document = requestRepository.findByReq(type, enrollment);
		if (Document == null) {
			Document = getReq();
			Document.setLive(true);
			Document.setType(type);
			Document.setStudent(student);
			Map<String,String> DOCUMENT=createStorage(request_document,student.getEnrollment(),type,"student\\request\\"+type);

			Document.setDocument_name(DOCUMENT.get("file_name"));
			Document.setDocument_url(DOCUMENT.get("file_url"));
			Document.setDocument_size(DOCUMENT.get("file_size"));
			Document.setDocument_type(DOCUMENT.get("file_type"));

			boolean isDocumentStored = storeFile(request_document,DOCUMENT.get("file_path"),DOCUMENT.get("file_name"));

			Document.setCgpa(cgpa);
			if(graduation_year != 0 && student.getGraduation_year() != graduation_year) {
				System.out.println("in in");
				student.setGraduation_year(graduation_year);
				studentRepo.save(student);
			}
			if(isDocumentStored){
				requestRepository.save(Document);
			}else{
				return 400;
			}

			return 200;
		} else {
			if (Document.isLive() || Document.getStatus3()==1) {
				return 409;
			} else {
				resetRequest(Document, request_document, cgpa,student.getEnrollment(),type);
				if(graduation_year != 0 && student.getGraduation_year() != graduation_year) {
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

	public List<FeeRefundData> penddingFeeRefund(userdetails userDetails) {
		String role = userDetails.getRole();
		int branch = userDetails.getBranch();
		if (role.equals("ROLE_DEPARTMENT")) {
			return studentRepo.findByfeerefundStatus1(branch);
		}
		else if (role.equals("ROLE_SSMENTOR")) {
			return studentRepo.findByfeerefundStatus2();
		} else if (role.equals("ROLE_SSHEAD")) {
			return studentRepo.findByfeerefundStatus3();
		} else
			return null;
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
public boolean UpdateFeeRefundStatus(userdetails userDetails, String enrollment,Integer status, String comment){
	String role = userDetails.getRole();
	FeeRefundDetails fee = feerefunddetailsRepository.findByEnrollment(enrollment);
	if (status==1){
		if(role.equals("ROLE_DEPARTMENT")){
			fee.setStatus1(1);
			feerefunddetailsRepository.save(fee);
			return true;
		}
		else if(role.equals("ROLE_SSMENTOR")){
			fee.setStatus2(1);
			feerefunddetailsRepository.save(fee);
			return true;
		}
		else if (role.equals("ROLE_SSHEAD")) {
			fee.setStatus3(1);
			fee.setLive(false);
			feerefunddetailsRepository.save(fee);
			return true;
		} else
			return false;

	}
	else{
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
	public boolean UpdateStatus(userdetails userDetails, String enrollment, String type, Integer status, String comment,String rank) {
		String role = userDetails.getRole();

		Request request = requestRepository.findByReq(type, enrollment);
		System.out.println(request.getType() + ".");
if(role.equals("ROLE_DEPARTMENT")) request.setLast_modified_by(userDetails.getBranch()+role);
			else request.setLast_modified_by(role);

		if (status==1) {
			if (role.equals("ROLE_DEPARTMENT")) {

				request.setStatus1(1);
				if (rank!=null) request.setRanks(rank);
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


	public List<RequestDto> findrequest(Date date , String role,String enrollment) {


		List<Student> students = studentRepo.findAll( Specification.where(
				StudentSpecification.getStudentByModifiedDate(	new Date(date.getYear(),date.getMonth(),date.getDay()))
						.and(StudentSpecification.getStudentByEnrollment(enrollment))
						.and(StudentSpecification.getStudentByfirstlevel(role))));


		List<RequestDto> data = ObjectMapperUtils.mapAll(students, RequestDto.class);

		return data;

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

	public RequestDto getStudentDashbord(String enrollment) {
		RequestDto reqdto = ObjectMapperUtils.map(studentRepo.findByEnrollment(enrollment), RequestDto.class);
		return reqdto;
	}

	public FeeRefundDetails feerefund(String enrollment) {
		FeeRefundDetails temp = feerefunddetailsRepository.findByEnrollment(enrollment);
		System.out.println(temp);
		return temp;
	}

	public void resetRequest(Request request,  MultipartFile request_document, Double cgpa,String username,String type) throws IOException {

	if(request.getStatus1()==2){
		request.setStatus1(0);
	}else if(request.getStatus1()==2) request.setStatus1(0);
	else if(request.getStatus3()==2) request.setStatus3(0);

		Map<String,String> DOCUMENT=createStorage(request_document,username,type,"student\\request\\"+type);
		request.setDocument_name(DOCUMENT.get("file_name"));
		request.setDocument_url(DOCUMENT.get("file_url"));
		request.setDocument_size(DOCUMENT.get("file_size"));
		request.setDocument_type(DOCUMENT.get("file_type"));

		boolean isDocumetStored = storeFile(request_document,DOCUMENT.get("file_path"),DOCUMENT.get("file_name"));
		if(cgpa!=null) request.setCgpa(cgpa);
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

    public HashMap<String,String> createStorage(MultipartFile file,String id,String type,String domain){
		//types === photo/sign/feereceipt/marksheet
		//domain student,admin,student/request etc...
		HashMap<String,String> fileData = new HashMap<String, String>();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String file_name= type+"_"+id+"_"+timestamp.getTime()+"_"+file.getOriginalFilename();
		String file_path = Paths.get(Controller.uploadDirectory,domain,type).toString();
		String file_url = Paths.get(domain,type,file_name).toString();
		String file_type = file.getContentType();
		Long file_Size = file.getSize();
		String file_size = file_Size.toString();
		fileData.put("file_name",file_name);
		fileData.put("file_path",file_path);
		fileData.put("file_type",file_type);
		fileData.put("file_size",file_size);
		fileData.put("file_url",file_url);
		return fileData;
	}

	public boolean storeFile(MultipartFile file,String file_path,String file_name ) throws IOException {
		boolean fileExists = true;
		System.out.println("in store file");
		System.out.println(file_path);
		File f = new File(file_path);
		if (!f.exists()) {

			fileExists =  f.mkdirs();
			System.out.println(fileExists+"fileExist");
		}
		if(fileExists){
			BufferedOutputStream stream1 = new BufferedOutputStream(new FileOutputStream(new File(Paths.get(file_path,file_name).toString())));
			stream1.write(file.getBytes());
			stream1.close();
			return true;
		}
		return false;
	}


}