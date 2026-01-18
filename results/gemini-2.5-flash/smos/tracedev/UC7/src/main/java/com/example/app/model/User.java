package com.example.app.model;

/**
 * Represents a User entity in the domain layer.
 * Contains core user information.
 */
public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String login;
    private String password;

    /**
     * Constructs a new User object.
     * @param id The unique identifier for the user.
     * @param name The first name of the user.
     * @param surname The last name of the user.
     * @param email The email address of the user.
     * @param cell The cell number of the user.
     * @param login The login username of the user.
     * @param password The password of the user.
     */
    public User(String id, String name, String surname, String email, String cell, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell;
        this.login = login;
        this.password = password;
    }

    // Getters for all attributes
    public String getId() {
        return id;
    }

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
}