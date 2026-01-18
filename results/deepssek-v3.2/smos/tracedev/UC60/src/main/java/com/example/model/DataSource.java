package com.example.model;

/**
 * Entity representing a data source or archive.
 * Satisfies requirement REQ-GEN-001 (Archive/DataSource representation).
 */
public class DataSource {
    /**
     * Fetches class register data for a student.
     */
    public ClassRegister fetchClassRegister(String studentId) {
        // In a real implementation, this would query a database or other data source
        // For this example, we return mock data
        ClassRegister register = new ClassRegister();
        register.setClassId("CS101");
        register.addStudentId(studentId);
        return register;
    }
    
    /**
     * Fetches student data.
     */
    public Student fetchStudentData(String studentId) {
        // In a real implementation, this would query a database
        // For this example, we return mock data
        Student student = new Student();
        student.setStudentId(studentId);
        student.setName("John Doe");
        return student;
    }
}