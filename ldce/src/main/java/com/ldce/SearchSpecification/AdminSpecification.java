package com.ldce.SearchSpecification;

import org.springframework.data.jpa.domain.Specification;

import com.ldce.Model.Admin.Admin;

public class AdminSpecification {

	public static Specification<Admin> getAdminByCourse(String course) {

		return (root, query, criteriaBuilder) -> {

			if (course != null && !(course.equals("ALL"))) {
				return criteriaBuilder.equal(root.get("course"), course);
			} else
				return null;
		};
	}
	
	public static Specification<Admin> getAdminByBranch(Integer branch) {
		return (root, query, criteriaBuilder) -> {

			if (branch != null && branch != 0) {

				return criteriaBuilder.equal(root.get("branch"), branch);
			} else
				return null;
		};
	}
	
	
}
