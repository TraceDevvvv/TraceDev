package com.example.usermanagement;

import java.util.Objects;

/**
 * Represents a user in the system with personal and login details.
 * This class acts as a data model for user information.
 */
public class User {
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String login;
    private String password; // In a real-world application, this would be hashed and salted.

    /**
     * Default constructor for User.
     */
    public User() {
    }

    /**
     * Constructs a new User with the specified details.
     *
     * @param name The first name of the user.
     * @param surname The last name of the user.
     * @param email The email address of the user.
     * @param cell The cell phone number of the user.
     * @param login The unique login username for the user.
     * @param password The password for the user's account.
     */
    public User(String name, String surname, String email, String cell, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell;
        this.login = login;
        this.password = password;
    }

    // --- Getters and Setters ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Provides a string representation of the User object.
     * Note: In a production environment, avoid including sensitive information like passwords in toString().
     *
     * @return A string containing the user's details.
     */
    @Override
    public String toString() {
        return "User{" +
               "name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", email='" + email + '\'' +
               ", cell='" + cell + '\'' +
               ", login='" + login + '\'' +
               // ", password='" + password + '\'' + // Omitted for security reasons in real apps
               '}';
    }

    /**
     * Compares this User object with another object for equality.
     * Two User objects are considered equal if all their fields (excluding password for security) are identical.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
               Objects.equals(surname, user.surname) &&
               Objects.equals(email, user.email) &&
               Objects.equals(cell, user.cell) &&
               Objects.equals(login, user.login) &&
               Objects.equals(password, user.password); // Include password for strict equality, but consider implications
    }

    /**
     * Generates a hash code for this User object.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, cell, login, password);
    }
}