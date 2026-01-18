'''
This file defines the User class, which represents a user in the system.
It holds detailed information about a user including their name, surname, email,
cell number, login, and password. This class is a simple Plain Old Java Object (POJO)
with a constructor and getter methods for all its properties.
'''
package com.chatdev.adminapp.model;
public class User {
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String login;
    private String password; // In a real application, passwords should be hashed, not stored plain text.
    /**
     * Constructs a new User object with the specified details.
     *
     * @param name The first name of the user.
     * @param surname The last name of the user.
     * @param email The email address of the user.
     * @param cell The cell phone number of the user.
     * @param login The login username of the user.
     * @param password The password of the user.
     */
    public User(String name, String surname, String email, String cell, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell;
        this.login = login;
        this.password = password;
    }
    /**
     * Returns the name of the user.
     * @return The user's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the surname of the user.
     * @return The user's surname.
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Returns the email address of the user.
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Returns the cell phone number of the user.
     * @return The user's cell number.
     */
    public String getCell() {
        return cell;
    }
    /**
     * Returns the login username of the user.
     * @return The user's login.
     */
    public String getLogin() {
        return login;
    }
    /**
     * Returns the password of the user.
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Overrides toString method for display purposes in JList.
     * @return A string representation of the user, typically their login name.
     */
    @Override
    public String toString() {
        return login + " (" + name + " " + surname + ")";
    }
}