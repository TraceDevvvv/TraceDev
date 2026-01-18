'''
Tourist model class representing a tourist account with all required fields.
Implements validation logic and data encapsulation.
'''
package com.etour.agency;
public class Tourist {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String nationality;
    private String passportNumber;
    private String address;
    private String dateOfBirth;
    private String emergencyContact;
    // Constructor
    public Tourist(String id, String firstName, String lastName, String email, 
                   String phoneNumber, String nationality, String passportNumber,
                   String address, String dateOfBirth, String emergencyContact) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.passportNumber = passportNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.emergencyContact = emergencyContact;
    }
    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    /**
     * Validate all tourist data fields
     * @return true if all data is valid, false otherwise
     */
    public boolean validate() {
        return ValidationUtils.isValidEmail(email) &&
               ValidationUtils.isValidPhoneNumber(phoneNumber) &&
               ValidationUtils.isValidPassportNumber(passportNumber) &&
               ValidationUtils.isValidName(firstName) &&
               ValidationUtils.isValidName(lastName) &&
               ValidationUtils.isNotEmpty(nationality) &&
               ValidationUtils.isNotEmpty(address) &&
               ValidationUtils.isValidDate(dateOfBirth) &&
               ValidationUtils.isNotEmpty(emergencyContact);
    }
    /**
     * Get validation error message if any field is invalid
     * @return Error message or null if valid
     */
    public String getValidationErrorMessage() {
        if (!ValidationUtils.isValidEmail(email)) {
            return "Invalid email format";
        }
        if (!ValidationUtils.isValidPhoneNumber(phoneNumber)) {
            return "Invalid phone number format";
        }
        if (!ValidationUtils.isValidPassportNumber(passportNumber)) {
            return "Invalid passport number format";
        }
        if (!ValidationUtils.isValidName(firstName)) {
            return "First name must be 2-50 characters and contain only letters";
        }
        if (!ValidationUtils.isValidName(lastName)) {
            return "Last name must be 2-50 characters and contain only letters";
        }
        if (!ValidationUtils.isNotEmpty(nationality)) {
            return "Nationality cannot be empty";
        }
        if (!ValidationUtils.isNotEmpty(address)) {
            return "Address cannot be empty";
        }
        if (!ValidationUtils.isValidDate(dateOfBirth)) {
            return "Invalid date format (expected: YYYY-MM-DD)";
        }
        if (!ValidationUtils.isNotEmpty(emergencyContact)) {
            return "Emergency contact cannot be empty";
        }
        return null;
    }
    @Override
    public String toString() {
        return id + ": " + firstName + " " + lastName + " (" + email + ")";
    }
    /**
     * Create a copy of this tourist
     * @return A new Tourist instance with the same data
     */
    public Tourist copy() {
        return new Tourist(id, firstName, lastName, email, phoneNumber, 
                          nationality, passportNumber, address, dateOfBirth, emergencyContact);
    }
}