package com.ldce.Main;

import java.util.List;

import javax.transaction.Transactional;

import com.ldce.Data.FeeRefundData;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ldce.Data.DocumentData;
import com.ldce.Data.RequestDto;
import org.springframework.lang.Nullable;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {


	@Query("from Student where token=(from Token where tokenValue=?1)")
	public Student findBytokenValue(String tokenValue);

	public Student findByEmail(String email);

	@Query("from Student where branch=?1 AND faculty_approve=0")
	public List<Student> findByBranchActive(int branch);

	@Query("from Student")
	public List<Student> findByBranchActivee();

	@Query("from Student")
	public List<Student> findBya();

	@Query(value = "SELECT count(*) from student s inner JOIN request r on r.request_enrollment=s.enrollment AND r.status1=0 AND branch =:branchid", nativeQuery = true)
	public Long countByStatus1(@Param("branchid") int branch);

	@Query(value = "SELECT * from student s inner JOIN request r on r.request_enrollment=s.enrollment AND r.status1=0 AND branch =:branchid", nativeQuery = true)
	public List<DocumentData> findByStatus1(@Param("branchid") int branch);



	@Query(value = "SELECT * from student s inner JOIN request r on r.request_enrollment=s.enrollment AND r.status1=1 AND r.status2=0", nativeQuery = true)
	public List<DocumentData> findByStatus2();

	@Query(value = "SELECT * from student s inner JOIN request r on r.request_enrollment=s.enrollment AND r.status1=1 AND r.status2=1 AND r.status3=0", nativeQuery = true)
	public List<DocumentData> findByStatus3();


	@Query(value = "SELECT * from student s inner JOIN fee_refund_details f on f.fee_refund_enrollment=s.enrollment AND f.status1=0 AND branch =:branchid", nativeQuery = true)
	public List<FeeRefundData> findByfeerefundStatus1(@Param("branchid") int branch);


	@Query(value = "SELECT * from student s inner JOIN fee_refund_details f on  f.fee_refund_enrollment=s.enrollment AND f.status1=1 AND f.status2=0", nativeQuery = true)
	public List<FeeRefundData> findByfeerefundStatus2();


	@Query(value = "SELECT * from student s inner JOIN fee_refund_details f on  f.fee_refund_enrollment=s.enrollment AND f.status1=1 AND f.status2=1 AND r.status3=0", nativeQuery = true)
	public List<FeeRefundData> findByfeerefundStatus3();

//	@Query("from Student where semester=1?")
//	public List<Student> updateBranch(int semester);
	@Transactional
	@Modifying
	@Query("UPDATE Student s SET s.semester = 3 WHERE s.semester=?1")
	public int updateSemester(int semester);

	public Student findByEnrollment(String enrollment);





//	@Query("select new RequestDto(faculty_comment,faculty_approve, List<Request> request) from Student where enrollment =?1")
//	public RequestDto findbyReq(String enrollment);
}
