package com.ldce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ldce.Main.Student;
import com.ldce.Main.StudentRepository;
import com.ldce.admin.Admin;
import com.ldce.admin.AdminRepository;

@Service
public class userdetailservice implements UserDetailsService {
    @Autowired
    StudentRepository studentRepo;
    @Autowired
    AdminRepository adminrepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println("userdetailservice");

        String usertype[] = username.split(",");
        String user = usertype[0];
        String type = usertype[1];
        System.out.println(user);
        System.out.println(type);
        if (type.equals("STUDENT")) {
            Student student = studentRepo.findByEnrollment(user);
            if (student == null) {
                System.out.println("ni malojbhbhjb");
                return null;
            }
            return new userdetails(student);

        } else if (type.equals("ADMIN")) {
            Admin admin = adminrepo.findByEmail(user);
            System.out.println(admin);
            if (admin == null) {
                System.out.println("ni malo");
                return null;
            }
            return new userdetails(admin);
        } else
            return new userdetails();
    }

}
