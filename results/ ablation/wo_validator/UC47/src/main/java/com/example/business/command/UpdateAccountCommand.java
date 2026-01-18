package com.example.business.command;

/**
 * Command object containing data for updating a tourist account.
 */
public class UpdateAccountCommand {
    private String touristId;
    private String username;
    private String email;
    private String phone;

    public UpdateAccountCommand(String touristId, String username, String email, String phone) {
        this.touristId = touristId;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}