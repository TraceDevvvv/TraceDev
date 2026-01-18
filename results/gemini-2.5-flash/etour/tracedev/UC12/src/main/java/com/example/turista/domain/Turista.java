package com.example.turista.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Represents the domain entity for a Turista.
 * This class holds the core data for a tourist.
 */
public class Turista {
    // - id : String
    private String id;
    // - firstName : String
    private String firstName;
    // - lastName : String
    private String lastName;
    // - dob : Date
    private Date dob;
    // - nationality : String
    private String nationality;
    // - contactEmail : String
    private String contactEmail;
    // - contactPhone : String
    private String contactPhone;

    /**
     * Constructor for Turista.
     * @param id The unique identifier for the turista.
     * @param firstName The first name of the turista.
     * @param lastName The last name of the turista.
     * @param dob The date of birth of the turista.
     * @param nationality The nationality of the turista.
     * @param contactEmail The contact email of the turista.
     * @param contactPhone The contact phone number of the turista.
     */
    public Turista(String id, String firstName, String lastName, Date dob, String nationality, String contactEmail, String contactPhone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.nationality = nationality;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    // --- Getters ---\
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }

    public String getNationality() {
        return nationality;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    // --- Setters (omitted for immutability in simple domain objects, but can be added if needed) ---\
    // For the purpose of this example, we'll keep it simple and assume properties are set via constructor.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turista turista = (Turista) o;
        return Objects.equals(id, turista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Turista{" +
               "id='" + id + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", dob=" + dob +
               ", nationality='" + nationality + '\'' +
               ", contactEmail='" + contactEmail + '\'' +
               ", contactPhone='" + contactPhone + '\'' +
               '}';
    }
}