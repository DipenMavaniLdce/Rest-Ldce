package com.ldce.SearchSpecification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.ldce.Model.Student.Student;

public class CountSpecification {

	public static Specification<Student> CountByBranch(Integer branch) {
		return (root, query, criteriaBuilder) -> {
			if (branch != 0) {
				Predicate equalPredicate = criteriaBuilder.equal(root.get("branch"), branch);
				System.out.println(equalPredicate);
				return equalPredicate;
			} else
				return null;
		};

	}

	public static Specification<Student> CountByFaculty_approve(Integer status) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("faculty_approve"), status);

	}
	public static Specification<Student> CountByCourse(String course) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("course"), course);

	}

}
