package com.ldce.SearchSpecification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.ldce.Main.Student;

public class StudentSpecification {
	 public static Specification<Student> getStudentByBranch(Integer branch){
		 return (root, query, criteriaBuilder) -> {
			 if(branch!=0) {
	          Predicate equalPredicate = criteriaBuilder.equal(root.get("branch"), branch);
	          System.out.println(equalPredicate);
	          return equalPredicate;}
			 else return null;
	      }; 
		 
	 }
	 
	 public static Specification<Student> getStudentByCaste(String caste){
		 return (root, query, criteriaBuilder) -> {
			 if(!(caste.equals("ALL"))) {
	          Predicate equalPredicate = criteriaBuilder.equal(root.get("caste"), caste);
	          return equalPredicate;}
			 else return null;
	      }; 
		 
	 }
	 
	 public static Specification<Student> getStudentByGender(String gender){
		 return (root, query, criteriaBuilder) -> {
			 if(!(gender.equals("ALL"))) {
	          Predicate equalPredicate = criteriaBuilder.equal(root.get("gender"), gender);
	          return equalPredicate;
	          }
			 else return null;
	      }; 
		 
	 }
	 public static Specification<Student> getStudentByCourse(String course){
		 return (root, query, criteriaBuilder) -> {
			 if(!(course.equals("ALL"))) {
	          Predicate equalPredicate = criteriaBuilder.equal(root.get("course"), course);
	          return equalPredicate;}
			 else return null;
	      }; 
		 
	 }

}
