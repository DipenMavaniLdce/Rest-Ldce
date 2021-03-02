package com.ldce.Dao;

import com.ldce.Data.DocumentData;
import com.ldce.Data.FeeRefundData;
import com.ldce.Data.RequestDto;
import com.ldce.Model.Admin.Admin;
import com.ldce.Model.Admin.AdminRepository;
import com.ldce.Model.FeeRefund.FeeRefundDetails;
import com.ldce.Model.FeeRefund.FeeRefundDetailsRepository;
import com.ldce.Model.Student.Student;
import com.ldce.Model.Student.StudentRepository;
import com.ldce.SearchSpecification.ObjectMapperUtils;
import com.ldce.SearchSpecification.StudentSpecification;
import com.ldce.security.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SearchQueryDao {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    FeeRefundDetailsRepository feeRefundDetailsRepository;

    public List<Student> searchList(int branch, String course) {

        return studentRepository.findByBranchActive(branch, course);
    }

    public List<Student> pendingRegistration(int branch, String course) {
        List<Student> students;
        students = searchList(branch, course);
        return students;
    }

    public Admin adminCrenditials(String email) {
        Admin admin;
        Logger logger = LoggerFactory.getLogger(SearchQueryDao.class);
        admin = adminRepository.findByEmail(email);
        logger.trace("admin found");
        return admin;
    }

    public List<FeeRefundData> penddingFeeRefund(CustomUserDetails userDetails) {
        String role = userDetails.getRole();
        int branch = userDetails.getBranch();
        switch (role) {
            case "ROLE_DEPARTMENT":
                return studentRepository.findByfeerefundStatus1(branch);
            case "ROLE_SSMENTOR":
                return studentRepository.findByfeerefundStatus2();
            case "ROLE_SSHEAD":
                return studentRepository.findByfeerefundStatus3();
            default:
                return null;
        }
    }

    public List<DocumentData> penndingDocument(CustomUserDetails userDetails) {
        String role = userDetails.getRole();
        int branch = userDetails.getBranch();
        String course = userDetails.getCourse();
        switch (role) {
            case "ROLE_DEPARTMENT":
                return studentRepository.findByStatus1(branch,course);
            case "ROLE_SSMENTOR":
                return studentRepository.findByStatus2();
            case "ROLE_SSHEAD":
                return studentRepository.findByStatus3();
            default:
                return null;
        }
    }

    public List<DocumentData> findRequest(Date start, Date end, String role, String enrollment) {

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

        List<DocumentData> students = studentRepository.findDocument(start, end, enrollment);

        System.out.println("..............................................................");
        // List<RequestDto> data = ObjectMapperUtils.mapAll(students, RequestDto.class);

        return students;

    }

    public List<Student> findAllStudent(String caste, Integer addmission_year, String gender, Integer semester,
                                        Integer branch, String course, String admission_category) {

        return studentRepository.findAll(Specification.where(StudentSpecification.getStudentByBranch(branch)
                .and(StudentSpecification.getStudentByCaste(caste))
                .and(StudentSpecification.getStudentByCourse(course))
                .and(StudentSpecification.getStudentByGender(gender))
                .and(StudentSpecification.getStudentByAddmissionYear(addmission_year))
                .and(StudentSpecification.getStudentByAdmissionCategory(admission_category))
                .and(StudentSpecification.getStudentBySem(semester))));
    }



    public RequestDto getStudentDashbord(String enrollment) {
        return ObjectMapperUtils.map(studentRepository.findByEnrollment(enrollment), RequestDto.class);
    }

    public FeeRefundDetails feerefund(String enrollment) {
        FeeRefundDetails temp = feeRefundDetailsRepository.findByEnrollment(enrollment);
        System.out.println(temp);
        return temp;
    }

    public Student search(String email) {
        Logger logger = LoggerFactory.getLogger(SearchQueryDao.class);
        Student student;
        student = studentRepository.findByEnrollment(email);
        if (student != null)
            logger.trace("student data found");
        else
            logger.warn("student data not found");
        return student;
    }

}
