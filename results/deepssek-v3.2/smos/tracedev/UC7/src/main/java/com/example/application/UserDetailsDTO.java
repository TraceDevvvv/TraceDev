package com.example.application;

/**
 * Data Transfer Object for detailed user information.
 * Password removed for security reasons - see audit report.
 */
public class UserDetailsDTO {
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String login;
    private String password;

    public UserDetailsDTO(String name, String surname, String email, String cell, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell;
        this.login = login;
        this.password = password;
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