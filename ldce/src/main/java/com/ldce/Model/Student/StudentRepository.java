package com.ldce.Model.Student;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.ldce.Data.DocumentData;
import com.ldce.Data.FeeRefundData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {


	@Query("from Student where token=(from Token where tokenValue=?1)")
    Student findBytokenValue(String tokenValue);

	@Query("from Student")
    List<DocumentData> findwholedocs();

	Student findByEmail(String email);

	@Query("from Student where branch=?1 AND faculty_approve=0 AND course=?2")
    List<Student> findByBranchActive(int branch, String course);



	@Query("from Student")
    List<Student> findByBranchActivee();

	@Query("from Student")
    List<Student> findBya();

	@Query(value = "SELECT count(*) from student s inner JOIN request r on r.request_enrollment=s.enrollment AND r.status1=0 AND branch =:branchid", nativeQuery = true)
    Long countByStatus1(@Param("branchid") int branch);

	@Query(value = "SELECT * from student s inner JOIN request r on r.request_enrollment=s.enrollment AND r.status1=0 AND branch =:branchid", nativeQuery = true)
    List<DocumentData> findByStatus1(@Param("branchid") int branch);



	@Query(value = "SELECT * from student s inner JOIN request r on r.request_enrollment=s.enrollment AND r.status1=1 AND r.status2=0", nativeQuery = true)
    List<DocumentData> findByStatus2();

	@Query(value = "SELECT * from student s inner JOIN request r on r.request_enrollment=s.enrollment AND r.status1=1 AND r.status2=1 AND r.status3=0", nativeQuery = true)
    List<DocumentData> findByStatus3();
	
	
	@Query(value = "SELECT * from student s inner JOIN request r on r.request_enrollment=s.enrollment where (r.modified_date >= ?1 or ?1 IS null) and (modified_date <= ?2 or ?2 IS null) or (s.enrollment= ?3 or ?3 IS null)", nativeQuery = true)
    List<DocumentData> findDocument(Date start, Date end, String enrollment);
	


	@Query(value = "SELECT * from student s inner JOIN fee_refund_details f on f.fee_refund_enrollment=s.enrollment AND f.status1=0 AND branch =:branchid", nativeQuery = true)
    List<FeeRefundData> findByfeerefundStatus1(@Param("branchid") int branch);


	@Query(value = "SELECT * from student s inner JOIN fee_refund_details f on  f.fee_refund_enrollment=s.enrollment AND f.status1=1 AND f.status2=0", nativeQuery = true)
    List<FeeRefundData> findByfeerefundStatus2();


	@Query(value = "SELECT * from student s inner JOIN fee_refund_details f on  f.fee_refund_enrollment=s.enrollment AND f.status1=1 AND f.status2=1 AND r.status3=0", nativeQuery = true)
    List<FeeRefundData> findByfeerefundStatus3();

	//modified_date<=?
	@Query(value = "SELECT * from student s inner JOIN request r on r.request_enrollment=s.enrollment AND r.modified_date=?1 AND r.last_modified_by=?2", nativeQuery = true)
    List<DocumentData> findByApprovedDate(Date date, String lastModifiedBy);

//	@Query("from Student where semester=1?")
//	public List<Student> updateBranch(int semester);
	@Transactional
	@Modifying
	@Query("UPDATE Student s SET s.semester = 3 WHERE s.semester=?1")
    int updateSemester(int semester);

	Student findByEnrollment(String enrollment);




//	@Query("select new RequestDto(faculty_comment,faculty_approve, List<Request> request) from Student where enrollment =?1")
//	public RequestDto findbyReq(String enrollment);
}
