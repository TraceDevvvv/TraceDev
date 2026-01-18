package com.example.domain;

import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Domain entity representing a Tourist.
 */
public class Tourist {
    public String id;
    public String name;
    public String email;
    public String phoneNumber;
    public Map<String, Object> otherData;

    public Tourist(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.otherData = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Map<String, Object> getOtherData() {
        return otherData;
    }

    public void setOtherData(Map<String, Object> otherData) {
        this.otherData = otherData;
    }

    /**
     * Updates the tourist's profile with provided data.
     */
    public void updateProfile(Map<String, Object> data) {
        if (data.containsKey("name")) {
            this.name = (String) data.get("name");
        }
        if (data.containsKey("email")) {
            this.email = (String) data.get("email");
        }
        if (data.containsKey("phoneNumber")) {
            this.phoneNumber = (String) data.get("phoneNumber");
        }
        if (data.containsKey("otherData")) {
            this.otherData.putAll((Map<String, Object>) data.get("otherData"));
        } else {
            // Merge other fields into otherData
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                if (!entry.getKey().equals("name") && !entry.getKey().equals("email") 
                    && !entry.getKey().equals("phoneNumber")) {
                    this.otherData.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    /**
     * Validates the tourist data integrity.
     */
    public boolean validate() {
        return validateEmail() && validatePhone();
    }

    /**
     * Validates email format.
     */
    public boolean validateEmail() {
        if (email == null) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validates phone number format (simplified).
     */
    public boolean validatePhone() {
        if (phoneNumber == null) return false;
        String phoneRegex = "^[0-9\\-\\+\\s]+$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}