package com.example.turista.domain;

/**
 * Data Transfer Object (DTO) for Turista information.
 * This class is used to transfer a simplified and formatted view of Turista data
 * from the application layer to the presentation layer.
 */
public class TuristaDTO {
    // + id : String
    public String id;
    // + fullName : String
    public String fullName;
    // + mainContact : String
    public String mainContact;
    // + briefDetails : String
    public String briefDetails;

    /**
     * Constructor for TuristaDTO.
     * @param id The unique identifier of the turista.
     * @param fullName The full name of the turista.
     * @param mainContact The primary contact information (e.g., email or phone).
     * @param briefDetails A brief summary of other relevant details.
     */
    public TuristaDTO(String id, String fullName, String mainContact, String briefDetails) {
        this.id = id;
        this.fullName = fullName;
        this.mainContact = mainContact;
        this.briefDetails = briefDetails;
    }

    @Override
    public String toString() {
        return "Turista Card Details:\n" +
               "  ID: " + id + "\n" +
               "  Full Name: " + fullName + "\n" +
               "  Contact: " + mainContact + "\n" +
               "  Details: " + briefDetails;
    }
}