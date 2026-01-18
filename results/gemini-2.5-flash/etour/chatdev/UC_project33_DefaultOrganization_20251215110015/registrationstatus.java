/*
Enum to represent various statuses during the registration process.
Used to communicate outcomes from UserService back to the GUI.
*/
public enum RegistrationStatus {
    SUCCESS,          // Registration was successful.
    INVALID_DATA,     // Input data was invalid or insufficient (e.g., empty fields, bad email format).
    USERNAME_TAKEN,   // The chosen username already exists.
    CONNECTION_ERROR  // An interruption of the connection to the server (ETOUR).
}