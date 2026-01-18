package com.etour;

public class User {
    private String username;
    private String password; // In real application, this would be hashed
    private String role;
    private int accessLevel;
    
    public User(String username, String password, String role, int accessLevel) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.accessLevel = accessLevel;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getRole() {
        return role;
    }
    
    public int getAccessLevel() {
        return accessLevel;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}