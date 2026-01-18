package com.example.app.dto;

import com.example.app.model.User;

/**
 * Data Transfer Object (DTO) for User details.
 * Used to transfer user information between application layers,
 * typically from application service to presentation layer,
 * without exposing the full domain model.
 */
public class UserDTO {
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String login;
    private String password;

    /**
     * Constructs a UserDTO from a User domain object.
     * This method is assumed to be called by the application service layer.
     * @param user The User domain object from which to create the DTO.
     */
    public UserDTO(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.cell = user.getCell();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    // Getters for all attributes
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getCell() {
        return cell;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User Details:\n" +
               "  Name: " + name + "\n" +
               "  Surname: " + surname + "\n" +
               "  Email: " + email + "\n" +
               "  Cell: " + cell + "\n" +
               "  Login: " + login + "\n" +
               "  Password: " + password; // In a real app, password shouldn't be displayed like this.
    }
}