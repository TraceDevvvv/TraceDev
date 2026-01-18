'''
Represents a teacher user in the system.
Contains teacher authentication and profile information.
'''
package com.chatdev.reportcardsystem.model;
public class Teacher {
    private String teacherId;
    private String username;
    private String password;
    private String fullName;
    private String department;
    public Teacher(String teacherId, String username, String password,
                   String fullName, String department) {
        this.teacherId = teacherId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.department = department;
    }
    public String getTeacherId() {
        return teacherId;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFullName() {
        return fullName;
    }
    public String getDepartment() {
        return department;
    }
    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}