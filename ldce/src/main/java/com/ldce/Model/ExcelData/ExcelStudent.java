package com.ldce.Model.ExcelData;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class ExcelStudent {
    @Id
    String enrollment;
    @NotEmpty
    String first_name;
    @NotEmpty
    String middle_name;
    @NotEmpty
    String last_name;
    @NotEmpty
    String gender;

    public ExcelStudent() {
    }

    public ExcelStudent(String enrollment, @NotEmpty String first_name, @NotEmpty String middle_name, @NotEmpty String last_name, @NotEmpty String gender) {
        this.enrollment = enrollment;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.gender = gender;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
