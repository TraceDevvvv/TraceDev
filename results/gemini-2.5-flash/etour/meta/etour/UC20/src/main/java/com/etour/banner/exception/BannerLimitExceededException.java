package com.etour.banner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception indicating that the maximum number of banners allowed
 * for a specific refreshment point has been exceeded.
 * This exception maps to an HTTP 400 Bad Request status.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BannerLimitExceededException extends RuntimeException {

    /**
     * Constructs a new BannerLimitExceededException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public BannerLimitExceededException(String message) {
        super(message);
    }
}