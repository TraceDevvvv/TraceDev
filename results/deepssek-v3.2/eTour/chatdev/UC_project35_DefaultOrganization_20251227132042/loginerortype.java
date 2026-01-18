'''
Enumeration of possible login error types.
This helps categorize different login failure scenarios.
'''
public enum LoginErrorType {
    INVALID_CREDENTIALS,      // Wrong username or password
    EMPTY_FIELDS,            // Username or password fields are empty
    SERVER_CONNECTION_FAILED, // Connection to server interrupted (ETOUR)
    ACCOUNT_LOCKED,          // User account is locked
    INVALID_INPUT_FORMAT     // Invalid format for username or password
}