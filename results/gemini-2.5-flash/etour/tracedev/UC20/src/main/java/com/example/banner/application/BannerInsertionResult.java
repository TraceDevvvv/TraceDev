package com.example.banner.application;

/**
 * Enum representing the result of a banner insertion attempt.
 * This enum helps to categorize the outcomes of the `insertBanner` operation,
 * guiding the UI on how to proceed or display messages to the user.
 */
public enum BannerInsertionResult {
    /** Indicates that the banner was successfully processed. */
    SUCCESS,
    /** Indicates that the provided image data was invalid according to validation rules. */
    INVALID_IMAGE,
    /** Indicates that the point of rest has reached its maximum banner capacity. */
    MAX_BANNERS_EXCEEDED,
    /** Indicates that the operation was canceled by the user during a confirmation step. */
    CANCELED,
    /** Indicates a connection error with an external system (ETOUR) during the operation.
     *  Added to satisfy requirement REQ-ExitCondition-002. */
    ETOUR_CONNECTION_ERROR,
    /** Indicates that the banner insertion is awaiting user confirmation.
     *  Added to satisfy requirement REQ-FlowOfEvents-009. */
    AWAITING_CONFIRMATION
}