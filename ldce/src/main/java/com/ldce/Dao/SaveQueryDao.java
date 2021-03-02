package com.ldce.Dao;

import com.ldce.Main.LdceApplication;
import com.ldce.Main.Token;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class SaveQueryDao {

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    FileHandalingDao fileHandalingDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AdminRepository adminRepo;

    @Autowired
    FeeRefundDetailsRepository feeRefundDetailsRepository;

    @Autowired
    RequestRepository requestRepository;

    public void saveStudent(Student student, Student_info info, Student_guardian guardian, MultipartFile photo,
                     MultipartFile sign) throws Exception {
        Map<String, String> PHOTO = fileHandalingDao.createStorage(photo, student.getEnrollment(), "photo", "student");
        Map<String, String> SIGN = fileHandalingDao.createStorage(sign, student.getEnrollment(), "sign", "student");

        student.setPhoto_name(PHOTO.get("file_name"));
        student.setPhoto_url(PHOTO.get("file_url"));
        student.setPhoto_size(PHOTO.get("file_size"));
        student.setPhoto_type(PHOTO.get("file_type"));

        student.setSign_name(SIGN.get("file_name"));
        student.setSign_url(SIGN.get("file_url"));
        student.setSign_size(SIGN.get("file_size"));
        student.setSign_type(SIGN.get("file_type"));

        boolean isPhotoStored = fileHandalingDao.storeFile(photo, PHOTO.get("file_path"), PHOTO.get("file_name"));
        boolean isSignStored = fileHandalingDao.storeFile(sign, SIGN.get("file_path"), SIGN.get("file_name"));

        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setToken(new Token());
        student.setInfo(info);
        student.setGuardian(guardian);
        try {
            if (isPhotoStored && isSignStored) {
                studentRepo.save(student);
            }

        } catch (Exception E) {
            fileHandalingDao.deleteOldFile(LdceApplication.uploadDirectory + "/" + student.getSign_url());
            fileHandalingDao.deleteOldFile(LdceApplication.uploadDirectory + "/" + student.getPhoto_url());
            System.out.println("in Exception");

            throw E;
        }
        // emailSender.createMail(student.getEmail(),student.getToken().getTokenValue());

    }


    // save admin data
    public void saveAdmin(Admin admin, MultipartFile photo, MultipartFile sign) throws Exception {

        Map<String, String> PHOTO = fileHandalingDao.createStorage(photo, admin.getFaculty_id(), "photo", "admin");
        Map<String, String> SIGN = fileHandalingDao.createStorage(sign, admin.getFaculty_id(), "sign", "admin");

        admin.setPhoto_name(PHOTO.get("file_name"));
        admin.setPhoto_url(PHOTO.get("file_url"));
        admin.setPhoto_size(PHOTO.get("file_size"));
        admin.setPhoto_type(PHOTO.get("file_type"));

        admin.setSign_name(SIGN.get("file_name"));
        admin.setSign_url(SIGN.get("file_url"));
        admin.setSign_size(SIGN.get("file_size"));
        admin.setSign_type(SIGN.get("file_type"));

        boolean isPhotoStored = fileHandalingDao.storeFile(photo, PHOTO.get("file_path"), PHOTO.get("file_name"));
        boolean isSignStored = fileHandalingDao.storeFile(sign, SIGN.get("file_path"), SIGN.get("file_name"));

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        if (isPhotoStored && isSignStored) {
            adminRepo.save(admin);
        }

    }


    public boolean save(String enrollment, int status, String comment) {
        // TODO Auto-generated method stub

        Student student = studentRepo.findByEnrollment(enrollment);
        Logger logger = LoggerFactory.getLogger(SaveQueryDao.class);
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
        FeeRefundDetails feeRefund = feeRefundDetailsRepository.findByEnrollment(enrollment);
        if (feeRefund == null || !feeRefund.isLive()) {

            fee.setStudent(student);

            Map<String, String> DOCUMENT = fileHandalingDao.createStorage(request_document, enrollment, "feereceipt",
                    "student/request/feereceipt");
            fee.setFee_document_name(DOCUMENT.get("file_name"));
            fee.setFee_document_url(DOCUMENT.get("file_url"));
            fee.setFee_document_size(DOCUMENT.get("file_size"));
            fee.setFee_document_type(DOCUMENT.get("file_type"));
            boolean isDocumentStored = fileHandalingDao.storeFile(request_document, DOCUMENT.get("file_path"),
                    DOCUMENT.get("file_name"));

            if (isDocumentStored) {
                feeRefundDetailsRepository.save(fee);
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
            Map<String, String> DOCUMENT = fileHandalingDao.createStorage(request_document, student.getEnrollment(), type,
                    "student/request/");

            Document.setDocument_name(DOCUMENT.get("file_name"));
            Document.setDocument_url(DOCUMENT.get("file_url"));
            Document.setDocument_size(DOCUMENT.get("file_size"));
            Document.setDocument_type(DOCUMENT.get("file_type"));

            boolean isDocumentStored = fileHandalingDao.storeFile(request_document, DOCUMENT.get("file_path"),
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
    public void resetRequest(Request request, MultipartFile request_document, Double cgpa, String username, String type)
            throws IOException {

        request.setStatus1(0);
        request.setStatus2(0);
        request.setStatus3(0);
        Map<String, String> DOCUMENT = fileHandalingDao.createStorage(request_document, username, type, "student/request/");
        request.setDocument_name(DOCUMENT.get("file_name"));
        request.setDocument_url(DOCUMENT.get("file_url"));
        request.setDocument_size(DOCUMENT.get("file_size"));
        request.setDocument_type(DOCUMENT.get("file_type"));

        boolean isDocumetStored = fileHandalingDao.storeFile(request_document, DOCUMENT.get("file_path"), DOCUMENT.get("file_name"));
        if (cgpa != null)
            request.setCgpa(cgpa);
        request.setLive(true);
    }

    private Request getReq() {
        return new Request();
    }




}
