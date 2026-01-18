package com.example.usermanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides methods to validate user input fields such as name, surname, email, cell, login, and password.
 * This class is responsible for checking the format and basic validity of user data.
 */
public class UserValidator {

    // Regex patterns for validation
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final String CELL_REGEX = "^[0-9]{7,15}$"; // Simple digit-only check for cell number
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9]{3,20}$"; // Alphanumeric login, 3-20 chars
    // Password regex: at least 8 characters, at least one uppercase, one lowercase, one digit, one special character
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$";
    private static final String NAME_SURNAME_REGEX = "^[a-zA-Z\\s'-]{2,50}$"; // Letters, spaces, hyphens, apostrophes

    /**
     * Validates all fields of a User object along with a confirm password.
     * Collects all validation errors into a list of strings.
     *
     * @param user The User object containing the data to validate.
     * @param confirmPassword The confirmation password string, which must match the user's password.
     * @return A list of error messages. If the list is empty, the user data is valid.
     */
    public static List<String> validateUser(User user, String confirmPassword) {
        List<String> errors = new ArrayList<>();

        // Validate Name
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            errors.add("Name cannot be empty.");
        } else if (!Pattern.matches(NAME_SURNAME_REGEX, user.getName())) {
            errors.add("Name must contain only letters, spaces, hyphens, or apostrophes, and be between 2 and 50 characters.");
        }

        // Validate Surname
        if (user.getSurname() == null || user.getSurname().trim().isEmpty()) {
            errors.add("Surname cannot be empty.");
        } else if (!Pattern.matches(NAME_SURNAME_REGEX, user.getSurname())) {
            errors.add("Surname must contain only letters, spaces, hyphens, or apostrophes, and be between 2 and 50 characters.");
        }

        // Validate Email
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            errors.add("Email cannot be empty.");
        } else if (!Pattern.matches(EMAIL_REGEX, user.getEmail())) {
            errors.add("Invalid email format.");
        }

        // Validate Cell
        if (user.getCell() == null || user.getCell().trim().isEmpty()) {
            errors.add("Cell number cannot be empty.");
        } else if (!Pattern.matches(CELL_REGEX, user.getCell())) {
            errors.add("Cell number must contain 7 to 15 digits only.");
        }

        // Validate Login
        if (user.getLogin() == null || user.getLogin().trim().isEmpty()) {
            errors.add("Login cannot be empty.");
        } else if (!Pattern.matches(LOGIN_REGEX, user.getLogin())) {
            errors.add("Login must be alphanumeric and 3 to 20 characters long.");
        }

        // Validate Password
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            errors.add("Password cannot be empty.");
        } else if (!Pattern.matches(PASSWORD_REGEX, user.getPassword())) {
            errors.add("Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }

        // Validate Confirm Password
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            errors.add("Confirm password cannot be empty.");
        } else if (!user.getPassword().equals(confirmPassword)) {
            errors.add("Password and Confirm Password do not match.");
        }

        return errors;
    }
}