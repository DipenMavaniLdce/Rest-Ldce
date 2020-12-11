package com.ldce.controller;

import com.ldce.Model.ExcelData.ExcelStudent;
import com.ldce.Model.ExcelData.ExcelStudentList;
import com.ldce.Model.ExcelData.ExcelStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;


@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController
public class ExcelController {

    @Autowired
    ExcelStudentRepository excelStudentRepository;

    @PostMapping("/importExcel")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> importExcelData(@RequestBody  ExcelStudentList excelStudentList){
        HashMap<String,String> res = new HashMap<>();
        System.out.println(excelStudentList.getExcelStudentList());
        if(excelStudentList != null){
            excelStudentList.getExcelStudentList().forEach(excelStudent -> {
                if(!excelStudentRepository.existsById(excelStudent.getEnrollment())){
                    excelStudentRepository.save(excelStudent);
                }
            });

            res.put("Success","Excel sheet uploaded successfully");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
       else {
            res.put("error", "please upload in proper formate");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }

    @GetMapping("/getStudentByEnrollment")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getStudent(@RequestParam("er") String enrollment){

        ExcelStudent excelStudent = excelStudentRepository.findAllByEnrollment(enrollment);
        if(excelStudent == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with this enrollment "+ enrollment +" is not present in Database");
        }
        return ResponseEntity.ok(excelStudent);
        }

}
