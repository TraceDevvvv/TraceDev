'''
Enum representing the possible outcomes of a banner insertion operation.
This provides a type-safe way to communicate the result from the service layer
to the UI, replacing brittle string-based messages.
'''
package com.chatdev.bannerapp.service;
public enum BannerInsertionResult {
    /**
     * Indicates that the banner was inserted successfully.
     */
    SUCCESS,
    /**
     * Indicates that the selected image was not valid according to defined rules.
     */
    INVALID_IMAGE,
    /**
     * Indicates that the maximum number of banners for the point of rest has been reached.
     */
    MAX_BANNERS_REACHED,
    /**
     * Indicates that no point of rest was selected, which is an unexpected state during insertion attempt.
     */
    NO_POINT_SELECTED,
    /**
     * Indicates an unexpected or unhandled error during the insertion process.
     */
    UNKNOWN_ERROR
}