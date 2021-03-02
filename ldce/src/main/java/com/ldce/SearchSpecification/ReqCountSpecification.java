package com.ldce.SearchSpecification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.ldce.Model.Request.Request;

public class ReqCountSpecification {

	public static Specification<Request> CountBystatus1(Integer status1) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status1"), status1);

	}

	public static Specification<Request> CountBystatus2(Integer status2) {
		return (root, query, criteriaBuilder) -> {

			Predicate equalPredicate = criteriaBuilder.equal(root.get("status2"), status2);
			System.out.println(equalPredicate);
			return equalPredicate;

		};

	}

	public static Specification<Request> CountBystatus3(Integer status3) {
		return (root, query, criteriaBuilder) -> {

			Predicate equalPredicate = criteriaBuilder.equal(root.get("status3"), status3);
			System.out.println(equalPredicate);
			return equalPredicate;

		};

	}
//
//	public static Specification<Request> getRequestByDate(Date date ) {
//		return (root, query, criteriaBuilder) -> {
//
//			Predicate equalPredicate = criteriaBuilder.equal(root.get("modified_date"), date);
//
//			return equalPredicate;
//
//		};
//
//	}
//	public static Specification<Request> getRequestByenrollment(String enrollment) {
//		return (root, query, criteriaBuilder) -> {
//
//			Predicate equalPredicate = criteriaBuilder.equal(root.get("request_enrollment"), enrollment);
//
//			return equalPredicate;
//
//		};

//	}

}
