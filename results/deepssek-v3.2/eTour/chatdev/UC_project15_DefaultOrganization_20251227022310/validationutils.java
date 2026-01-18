'''
Utility class for form validation.
Provides static methods to validate different types of data.
'''
package com.etour.agency;
import java.util.regex.Pattern;
public class ValidationUtils {
    // Regular expression patterns for validation
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^\\+?[0-9\\s\\-\\(\\)]{10,}$");
    private static final Pattern PASSPORT_PATTERN = 
        Pattern.compile("^[A-Z0-9]{6,12}$");
    private static final Pattern NAME_PATTERN = 
        Pattern.compile("^[A-Za-z\\s]{2,50}$");
    private static final Pattern DATE_PATTERN = 
        Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    /**
     * Validate email format
     * @param email Email to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
    /**
     * Validate phone number format (international format)
     * @param phone Phone number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }
    /**
     * Validate passport number format
     * @param passport Passport number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidPassportNumber(String passport) {
        return passport != null && PASSPORT_PATTERN.matcher(passport).matches();
    }
    /**
     * Validate name (first name or last name)
     * @param name Name to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }
    /**
     * Validate date format (YYYY-MM-DD)
     * @param date Date string to validate
     * @return true if valid format, false otherwise
     */
    public static boolean isValidDate(String date) {
        return date != null && DATE_PATTERN.matcher(date).matches();
    }
    /**
     * Check if string is not empty (not null and not blank)
     * @param str String to check
     * @return true if not empty, false otherwise
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}