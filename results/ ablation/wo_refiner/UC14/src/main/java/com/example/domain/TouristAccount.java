
package com.example.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Domain entity representing a tourist account.
 */
public class TouristAccount {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String nationality;
    private Date dateOfBirth;
    private String passportNumber;

    // Constructor for manual creation
    public TouristAccount(String id, String name, String email, String phoneNumber,
                          String nationality, Date dateOfBirth, String passportNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getNationality() { return nationality; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public String getPassportNumber() { return passportNumber; }

    /**
     * Determines if this account matches the given search criteria.
     * Simplified: only checks non-null criteria fields.
     */
    public boolean matchesCriteria(String nameCriteria, String emailCriteria, String nationalityCriteria) {
        if (nameCriteria != null && !nameCriteria.isEmpty() && !name.contains(nameCriteria)) {
            return false;
        }
        if (emailCriteria != null && !emailCriteria.isEmpty() && !email.contains(emailCriteria)) {
            return false;
        }
        if (nationalityCriteria != null && !nationalityCriteria.isEmpty() && !nationality.equals(nationalityCriteria)) {
            return false;
        }
        // Date matching omitted for brevity.
        return true;
    }

    /**
     * Creates a TouristAccount from a database ResultSet.
     */
    public static TouristAccount createFromResultSet(ResultSet rs) throws SQLException {
        // Assumes the ResultSet columns match the entity fields.
        String id = rs.getString("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone_number");
        String nationality = rs.getString("nationality");
        Date dob = rs.getDate("date_of_birth");
        String passport = rs.getString("passport_number");
        return new TouristAccount(id, name, email, phone, nationality, dob, passport);
    }
}
