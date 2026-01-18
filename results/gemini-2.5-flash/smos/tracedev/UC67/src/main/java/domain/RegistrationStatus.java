package domain;

/**
 * Domain Layer Enum: Defines the possible statuses for a registration request.
 */
public enum RegistrationStatus {
    PENDING,    // The request has been received but not yet fully processed/registered.
    REGISTERED, // The request has been successfully processed and the visitor is registered.
    FAILED      // The request failed due to validation errors or system issues.
}