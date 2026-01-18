/**
 * Encapsulates the search criteria for Tourist accounts.
 */
package com.example.touristagency;

public class TouristSearchCriteria {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String nationality;
    private String passportNumber;

    // Default constructor
    public TouristSearchCriteria() {}

    // Parameterized constructor
    public TouristSearchCriteria(String firstName, String lastName, String email,
                                 String phone, String nationality, String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.nationality = nationality;
        this.passportNumber = passportNumber;
    }

    // Getters and setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
}