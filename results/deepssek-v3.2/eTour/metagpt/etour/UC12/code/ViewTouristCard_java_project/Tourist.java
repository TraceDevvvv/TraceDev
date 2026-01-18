/**
 * Tourist.java
 * This class represents a Tourist entity with personal information and account details.
 * It's used in the ViewTouristCard use case to display tourist information.
 */
package com.etour;

import java.time.LocalDate;
import java.util.Objects;

public class Tourist {
    // Tourist identification fields
    private String touristId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    
    // Personal information
    private String address;
    private String city;
    private String country;
    private String nationality;
    private LocalDate dateOfBirth;
    private String passportNumber;
    
    // Account and system fields
    private LocalDate registrationDate;
    private LocalDate lastLoginDate;
    private boolean isActive;
    private String agencyId; // Reference to the agency that registered this tourist
    
    /**
     * Default constructor
     */
    public Tourist() {
        this.registrationDate = LocalDate.now();
        this.isActive = true;
    }
    
    /**
     * Parameterized constructor with essential fields
     */
    public Tourist(String touristId, String firstName, String lastName, String email, 
                  String phoneNumber, String address, String city, String country) {
        this.touristId = touristId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.registrationDate = LocalDate.now();
        this.isActive = true;
    }
    
    /**
     * Full parameterized constructor
     */
    public Tourist(String touristId, String firstName, String lastName, String email,
                  String phoneNumber, String address, String city, String country,
                  String nationality, LocalDate dateOfBirth, String passportNumber,
                  LocalDate registrationDate, LocalDate lastLoginDate, 
                  boolean isActive, String agencyId) {
        this.touristId = touristId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.registrationDate = registrationDate;
        this.lastLoginDate = lastLoginDate;
        this.isActive = isActive;
        this.agencyId = agencyId;
    }
    
    // Getter and setter methods for all fields
    
    public String getTouristId() {
        return touristId;
    }
    
    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getPassportNumber() {
        return passportNumber;
    }
    
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }
    
    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public String getAgencyId() {
        return agencyId;
    }
    
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    
    /**
     * Calculates the age of the tourist based on date of birth
     * @return age in years, or -1 if date of birth is not set
     */
    public int calculateAge() {
        if (dateOfBirth == null) {
            return -1;
        }
        LocalDate today = LocalDate.now();
        int age = today.getYear() - dateOfBirth.getYear();
        // Adjust if birthday hasn't occurred yet this year
        if (today.getMonthValue() < dateOfBirth.getMonthValue() ||
            (today.getMonthValue() == dateOfBirth.getMonthValue() && 
             today.getDayOfMonth() < dateOfBirth.getDayOfMonth())) {
            age--;
        }
        return age;
    }
    
    /**
     * Formats the tourist data as a string suitable for display
     * @return formatted string with tourist information
     */
    public String toDisplayString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TOURIST CARD\n");
        sb.append("============\n");
        sb.append(String.format("ID: %s\n", touristId));
        sb.append(String.format("Name: %s %s\n", firstName, lastName));
        sb.append(String.format("Email: %s\n", email));
        sb.append(String.format("Phone: %s\n", phoneNumber));
        
        if (address != null) {
            sb.append(String.format("Address: %s\n", address));
        }
        if (city != null && country != null) {
            sb.append(String.format("Location: %s, %s\n", city, country));
        }
        if (nationality != null) {
            sb.append(String.format("Nationality: %s\n", nationality));
        }
        if (dateOfBirth != null) {
            int age = calculateAge();
            sb.append(String.format("Date of Birth: %s (Age: %d)\n", dateOfBirth.toString(), 
                    age != -1 ? age : "Unknown"));
        }
        if (passportNumber != null) {
            sb.append(String.format("Passport: %s\n", passportNumber));
        }
        if (registrationDate != null) {
            sb.append(String.format("Registered: %s\n", registrationDate.toString()));
        }
        if (lastLoginDate != null) {
            sb.append(String.format("Last Login: %s\n", lastLoginDate.toString()));
        }
        sb.append(String.format("Status: %s\n", isActive ? "Active" : "Inactive"));
        if (agencyId != null) {
            sb.append(String.format("Agency ID: %s\n", agencyId));
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "Tourist{" +
                "touristId='" + touristId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", isActive=" + isActive +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return Objects.equals(touristId, tourist.touristId) &&
                Objects.equals(email, tourist.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(touristId, email);
    }
    
    /**
     * Validates the tourist data for required fields
     * @return true if tourist has all required fields, false otherwise
     */
    public boolean isValid() {
        return touristId != null && !touristId.trim().isEmpty() &&
               firstName != null && !firstName.trim().isEmpty() &&
               lastName != null && !lastName.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               phoneNumber != null && !phoneNumber.trim().isEmpty();
    }
}