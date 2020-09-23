package com.ldce.security;

import com.ldce.filter.JwtRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ldce.Model.Student.Student;
import com.ldce.Model.Student.StudentRepository;
import com.ldce.Model.Admin.Admin;
import com.ldce.Model.Admin.AdminRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	AdminRepository adminrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		String usertype[] = username.split(",");
		String user = usertype[0];
		String type = usertype[1];

		if (type.equals("STUDENT")) {
			Student student = studentRepo.findByEnrollment(user);
			if (student == null) {
				logger.trace("Student not found from database");
				return null;
			}
			logger.trace("Student found from database");
			return new CustomUserDetails(student);

		} else if (type.equals("ADMIN")) {
			Admin admin = adminrepo.findByEmail(user);

			if (admin == null) {
				logger.trace("Admin not found from database");
				return null;
			}
			logger.trace("Admin not found from database");
			return new CustomUserDetails(admin);
		} else
			return new CustomUserDetails();
	}

}
