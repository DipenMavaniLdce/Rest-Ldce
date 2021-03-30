package com.ldce.Model.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ldce.Model.Student.Student;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> , JpaSpecificationExecutor<Admin> {
	Admin findByEmail(String user);
}
