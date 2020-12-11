package com.ldce.Model.ExcelData;

import com.ldce.Model.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelStudentRepository extends JpaRepository<ExcelStudent, String> {
    public ExcelStudent findAllByEnrollment(String enrollment);
}
