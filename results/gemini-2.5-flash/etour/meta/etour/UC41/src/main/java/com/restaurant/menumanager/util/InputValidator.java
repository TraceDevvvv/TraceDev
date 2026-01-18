package com.restaurant.menumanager.util;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class for validating user input.
 * Provides methods to get validated input for various data types.
 */
public class InputValidator {

    private final Scanner scanner;
    private static final List<String> DAYS_OF_WEEK = Arrays.asList(
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    );

    /**
     * Constructs an InputValidator with a given Scanner.
     *
     * @param scanner The Scanner object to use for reading input.
     */
    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Checks if a given string is a valid day of the week.
     *
     * @param day The string to validate.
     * @return true if the string is a valid day of the week (case-insensitive), false otherwise.
     */
    public boolean isValidDayOfWeek(String day) {
        if (day == null || day.trim().isEmpty()) {
            return false;
        }
        return DAYS_OF_WEEK.stream().anyMatch(d -> d.equalsIgnoreCase(day.trim()));
    }

    /**
     * Checks if a given string is a valid menu item name.
     * A valid name is not null, not empty, and consists of at least 2 characters.
     *
     * @param name The string to validate.
     * @return true if the string is a valid menu item name, false otherwise.
     */
    public boolean isValidMenuItemName(String name) {
        return name != null && name.trim().length() >= 2;
    }

    /**
     * Checks if a given string can be parsed as a valid positive double for a menu item price.
     *
     * @param price The string to validate.
     * @return true if the string represents a positive double, false otherwise.
     */
    public boolean isValidMenuItemPrice(String price) {
        try {
            double p = Double.parseDouble(price);
            return p > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Prompts the user for integer input and validates it.
     * Repeats the prompt until a valid integer is entered.
     *
     * @param prompt The message to display to the user.
     * @return The validated integer input.
     */
    public int getValidIntegerInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    /**
     * Prompts the user for double input and validates it.
     * Repeats the prompt until a valid positive double is entered.
     *
     * @param prompt The message to display to the user.
     * @return The validated double input.
     */
    public double getValidDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Price must be a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Prompts the user for string input and ensures it's not empty.
     * Repeats the prompt until a non-empty string is entered.
     *
     * @param prompt The message to display to the user.
     * @return The validated non-empty string input.
     */
    public String getValidStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input.trim();
            } else {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
    }

    /**
     * Prompts the user for a day of the week and validates it.
     * Repeats the prompt until a valid day of the week is entered.
     *
     * @param prompt The message to display to the user.
     * @return The validated day of the week (capitalized first letter).
     */
    public String getValidDayOfWeekInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (isValidDayOfWeek(input)) {
                // Capitalize the first letter for consistent storage/display
                return input.trim().substring(0, 1).toUpperCase() + input.trim().substring(1).toLowerCase();
            } else {
                System.out.println("Invalid day of the week. Please enter one of: " + String.join(", ", DAYS_OF_WEEK));
            }
        }
    }
}