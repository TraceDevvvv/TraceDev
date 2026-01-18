package com.example.domain;

/**
 * Domain entity representing a User.
 * Contains core user attributes.
 * Note: No getPassword() method for security reasons.
 */
public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String login;
    private String password;
    private String role;

    public User(String id, String name, String surname, String email,
                String cell, String login, String password, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell;
        this.login = login;
        this.password = password;
        this.role = role;
    }

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

    public String getRole() {
        return role;
    }

    // No getPassword() for security reasons.
}