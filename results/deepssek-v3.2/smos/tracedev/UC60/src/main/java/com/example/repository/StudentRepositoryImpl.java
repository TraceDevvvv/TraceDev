package com.example.repository;

import com.example.model.ClassRegister;
import com.example.model.DataSource;
import com.example.model.Student;

/**
 * Implementation of StudentRepository interface.
 */
public class StudentRepositoryImpl implements StudentRepository {
    private DataSource dataSource;
    
    public StudentRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public ClassRegister findClassRegisterData(String studentId) {
        return dataSource.fetchClassRegister(studentId);
    }
    
    @Override
    public Student findStudentData(String studentId) {
        return dataSource.fetchStudentData(studentId);
    }
}