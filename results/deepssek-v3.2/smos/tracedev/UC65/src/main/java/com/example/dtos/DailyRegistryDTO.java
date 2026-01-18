package com.example.dtos;

import java.util.Date;
import java.util.List;

/**
 * Data Transfer Object for daily registry.
 */
public class DailyRegistryDTO {
    private Date date;
    private List<StudentStatusDTO> students;

    public DailyRegistryDTO(Date date, List<StudentStatusDTO> students) {
        this.date = date;
        this.students = students;
    }

    public Date getDate() {
        return date;
    }

    public List<StudentStatusDTO> getStudents() {
        return students;
    }

    public void addStudent(StudentStatusDTO student) {
        students.add(student);
    }
}