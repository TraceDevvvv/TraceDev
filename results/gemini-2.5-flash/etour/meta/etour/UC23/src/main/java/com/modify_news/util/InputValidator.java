package com.modify_news.util;

import java.time.LocalDateTime;

/**
 * Utility class for validating various input data, especially for news articles.
 */
public class InputValidator {

    /**
     * Validates if a given string is not null and not empty after trimming whitespace.
     *
     * @param str The string to validate.
     * @return true if the string is valid, false otherwise.
     */
    public static boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * Validates if the provided news data (title, content, author, publicationDate) is sufficient and valid.
     * This method checks for non-null and non-empty strings for title, content, and author,
     * and ensures the publication date is not null.
     *
     * @param title The title of the news.
     * @param content The content of the news.
     * @param author The author of the news.
     * @param publicationDate The publication date of the news.
     * @return true if all news data fields are valid, false otherwise.
     */
    public static boolean isValidNewsData(String title, String content, String author, LocalDateTime publicationDate) {
        return isValidString(title) &&
               isValidString(content) &&
               isValidString(author) &&
               publicationDate != null;
    }

    /**
     * Validates if a given news ID is valid.
     * A news ID is considered valid if it's not null and not empty after trimming.
     *
     * @param newsId The news ID to validate.
     * @return true if the news ID is valid, false otherwise.
     */
    public static boolean isValidNewsId(String newsId) {
        return isValidString(newsId);
    }
}