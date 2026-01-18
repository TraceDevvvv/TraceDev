package com.example.application;

/**
 * Enumeration representing possible results of the insert feedback use case.
 */
public enum InsertFeedbackResult {
    SUCCESS,
    ERROR_INVALID_DATA,
    FEEDBACK_ALREADY_EXISTS,
    SERVER_INTERRUPTION,
    OPERATION_CANCELLED
}