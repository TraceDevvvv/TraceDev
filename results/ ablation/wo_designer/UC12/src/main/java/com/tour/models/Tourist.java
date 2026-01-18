package com.tour.models;

/**
 * Represents a Tourist entity with basic account information.
 */
public class Tourist {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String nationality;
    private String passportNumber;

    public Tourist() {}

    public Tourist(String id, String name, String email, String phone, String nationality, String passportNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nationality = nationality;
        this.passportNumber = passportNumber;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }

    @Override
    public String toString() {
        return "Tourist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", nationality='" + nationality + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }
}