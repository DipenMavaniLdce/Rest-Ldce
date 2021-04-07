package com.ldce.controller;

import com.ldce.Dao.SearchQueryDao;
import com.ldce.Dao.UpdateQueryDao;
import com.ldce.Model.Admin.Admin;
import com.ldce.Model.Admin.AdminRepository;
import com.ldce.Model.Student.Student;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/sshead")
public class SSHeadController {

    @Autowired
    SearchQueryDao searchQueryDao;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UpdateQueryDao updateQueryDao;

    @GetMapping("/searchAdmin")
    public ResponseEntity<?> getAdmin(@RequestParam String email){
        Map<Object,Object> res = new HashMap<>();
        if(email == null){
            res.put("message","Invalid Request");
            return  new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        Admin admin = adminRepository.findByEmail(email);
        if(admin == null){
            res.put("message","Admin with email "+email+" not found");
            return  new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
        }
        else{
            res.put("admin",admin);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }
    }

    @PostMapping("/updateAdmin")
    public ResponseEntity updateGlobalAdmin(@Valid  Admin admin){
         HttpStatus httpStatus = updateQueryDao.updateAdminBySSHead(admin);
         Map<Object,Object> res = new HashMap<>();
         if(httpStatus == HttpStatus.NOT_FOUND){
             res.put("message","Admin not found with email "+admin.getEmail());
             return  new ResponseEntity(res,httpStatus);
         }
         res.put("message","Profile Updated Successfully");
         return new ResponseEntity(res,httpStatus);
    }
    
    
    
    
    
	@CrossOrigin
	@GetMapping("/searchAllAdmin")
	public List<Admin> searchAdmin(
			@RequestParam(defaultValue = "0") Integer branch,
			@RequestParam(defaultValue = "ALL") String course) {
		
	

		return searchQueryDao.findAdmin(branch, course);
	}
	
	
}
