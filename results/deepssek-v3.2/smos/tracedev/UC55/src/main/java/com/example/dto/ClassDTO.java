package com.example.dto;

import com.example.dto.StudentDTO;
import java.util.List;

/**
 * Data Transfer Object for Class information.
 * Used to transfer class data between layers.
 */
public class ClassDTO {
    private String classId;
    private String className;
    private List<StudentDTO> students;

    public ClassDTO(String classId, String className, List<StudentDTO> students) {
        this.classId = classId;
        this.className = className;
        this.students = students;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }
}