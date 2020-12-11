package com.ldce.SearchSpecification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import com.ldce.Model.Request.Request;
import org.springframework.data.jpa.domain.Specification;

import com.ldce.Model.Student.Student;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class StudentSpecification {
	public static Specification<Student> getStudentByBranch(Integer branch) {
		return (root, query, criteriaBuilder) -> {

			if (branch != null && branch != 0) {
				Predicate equalPredicate = criteriaBuilder.equal(root.get("branch"), branch);

				return equalPredicate;
			} else
				return null;
		};
	}

	public static Specification<Student> getStudentByCaste(String caste) {
		return (root, query, criteriaBuilder) -> {

			if (caste != null && !(caste.equals("ALL"))) {
				Predicate equalPredicate = criteriaBuilder.equal(root.get("caste"), caste);
				return equalPredicate;
			} else
				return null;
		};
	}

	public static Specification<Student> getStudentByGender(String gender) {
		return (root, query, criteriaBuilder) -> {

			if (gender != null && !(gender.equals("ALL"))) {
				Predicate equalPredicate = criteriaBuilder.equal(root.get("gender"), gender);
				return equalPredicate;
			} else
				return null;
		};
	}

	public static Specification<Student> getStudentByCourse(String course) {

		return (root, query, criteriaBuilder) -> {

			if (course != null && !(course.equals("ALL"))) {
				Predicate equalPredicate = criteriaBuilder.equal(root.get("course"), course);
				return equalPredicate;
			} else
				return null;
		};
	}

	public static Specification<Student> getStudentByAddmissionYear(Integer year) {
		return (root, query, criteriaBuilder) -> {

			if (year != null && year != 0) {
				Predicate equalPredicate = criteriaBuilder.equal(root.get("addmission_year"), year);

				return equalPredicate;
			} else
				return null;
		};
	}

	public static Specification<Student> getStudentBySem(Integer sem) {

		return (root, query, criteriaBuilder) -> {

			if (sem != null && sem != 0) {
				Predicate equalPredicate = criteriaBuilder.equal(root.get("semester"), sem);
				return equalPredicate;
			} else
				return null;
		};
	}
	public static Specification<Student> getRequestData(Date start,Date end,String role) {
		return (root, query, criteriaBuilder) -> {

			if(start==null || end ==null) return null;
		
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			String s = simpleDateFormat.format(start);
		//	String e = simpleDateFormat.format(end);
			
//		LocalDateTime st = 	LocalDateTime.parse);
//		LocalDateTime ed= 	LocalDateTime.parse(e);
//			
	System.out.println(s+"...............................................");
//		System.out.println(ed+"..................................................");
			Join<Student, Request> requestJoin = root.join("request");
			
			System.out.println(requestJoin.get("modified_date"));
			
			Predicate equalPredicate = //criteriaBuilder.and(
										criteriaBuilder.equal(requestJoin.get("modified_date"), new Date(start.getYear(),start.getMonth(),start.getDate()));//(requestJoin.get("modified_date"),start)
										//,criteriaBuilder.lessThanOrEqualTo(requestJoin.get("modified_date"),end)
										//,criteriaBuilder.equal(requestJoin.get("last_modified_by"),role)
										//);
					System.out.println(equalPredicate.toString());
					//equal(requestJoin.get("modified_date"),date);
		query.distinct(true);
			return equalPredicate;
		};

	}

//	public static Specification<Student> getStudentByfirstlevel(String role) {
//		return (root, query, criteriaBuilder) -> {
//			Join<Student, Request> requestJoin = root.join("request");
//			Predicate equalPredicate = criteriaBuilder.equal(requestJoin.get("last_modified_by"),role);
//
//			return equalPredicate;
//		};
//
//	}
//
	public static Specification<Student> getStudentByEnrollment(String enrollment) {

		return (root, query, criteriaBuilder) -> {
			if(enrollment==null) return null;
			Predicate equalPredicate = criteriaBuilder.equal(root.get("enrollment"), enrollment);

			return equalPredicate;
		};

	}


}
