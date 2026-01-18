package com.news.management.system.util;

import com.news.management.system.exception.InvalidNewsDataException;

import java.util.Date;
import java.util.List;

/**
 * Utility class for validating various data types and formats.
 * Provides static methods to perform common validation checks for strings, dates, and lists.
 */
public class ValidationUtil {

    /**
     * Validates a string value based on specified criteria.
     *
     * @param value The string value to validate.
     * @param fieldName The name of the field being validated (for error messages).
     * @param minLength The minimum allowed length for the string.
     * @param maxLength The maximum allowed length for the string.
     * @param required True if the string is mandatory, false otherwise.
     * @throws InvalidNewsDataException if the string fails any validation rule.
     */
    public static void validateString(String value, String fieldName, int minLength, int maxLength, boolean required) throws InvalidNewsDataException {
        if (required) {
            if (value == null || value.trim().isEmpty()) {
                throw new InvalidNewsDataException(fieldName + " cannot be empty.");
            }
        } else {
            // If not required and null/empty, no further validation needed
            if (value == null || value.trim().isEmpty()) {
                return;
            }
        }

        if (value.length() < minLength) {
            throw new InvalidNewsDataException(fieldName + " must be at least " + minLength + " characters long.");
        }
        if (value.length() > maxLength) {
            throw new InvalidNewsDataException(fieldName + " cannot exceed " + maxLength + " characters.");
        }
    }

    /**
     * Validates a Date object.
     *
     * @param date The Date object to validate.
     * @param fieldName The name of the field being validated (for error messages).
     * @param required True if the date is mandatory, false otherwise.
     * @throws InvalidNewsDataException if the date fails any validation rule.
     */
    public static void validateDate(Date date, String fieldName, boolean required) throws InvalidNewsDataException {
        if (required) {
            if (date == null) {
                throw new InvalidNewsDataException(fieldName + " cannot be null.");
            }
            // Optionally, add future date checks, past date checks, etc.
            // For now, just checking for null if required.
        }
    }

    /**
     * Validates a List of strings.
     *
     * @param list The List of strings to validate.
     * @param fieldName The name of the field being validated (for error messages).
     * @param required True if the list is mandatory (cannot be null or empty), false otherwise.
     * @throws InvalidNewsDataException if the list fails any validation rule.
     */
    public static void validateList(List<String> list, String fieldName, boolean required) throws InvalidNewsDataException {
        if (required) {
            if (list == null || list.isEmpty()) {
                throw new InvalidNewsDataException(fieldName + " cannot be empty.");
            }
        } else {
            // If not required and null/empty, no further validation needed
            if (list == null || list.isEmpty()) {
                return;
            }
        }

        // Optionally, validate individual elements within the list
        for (String item : list) {
            if (item == null || item.trim().isEmpty()) {
                throw new InvalidNewsDataException(fieldName + " cannot contain empty or null items.");
            }
            // Further validation for item length, format, etc., can be added here
        }
    }
}