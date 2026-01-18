package domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Domain Layer: Represents a request for visitor registration.
 * Encapsulates registration details and their current status.
 */
public class RegistrationRequest {
    private String id;
    private String name;
    private String surname;
    private String mobilePhone;
    private String email;
    private String username;
    private String hashedPassword;
    private RegistrationStatus status;
    private LocalDateTime creationDate;
    private boolean isEncrypted = false; // Added attribute to reflect broader security for R9 (recommendation 6)

    /**
     * Constructor for RegistrationRequest.
     * Automatically generates an ID and sets creation date.
     *
     * @param name The visitor's first name.
     * @param surname The visitor's last name.
     * @param mobilePhone The visitor's mobile phone number.
     * @param email The visitor's email address.
     * @param username The desired username.
     * @param hashedPassword The securely hashed password.
     */
    public RegistrationRequest(String name, String surname, String mobilePhone, String email, String username, String hashedPassword) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID
        this.name = name;
        this.surname = surname;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.creationDate = LocalDateTime.now(); // Set current creation date
        this.status = RegistrationStatus.PENDING; // Default initial status
        // isEncrypted remains false unless explicitly set, or if password hashing is considered encryption in this context.
        // For password, hashedPassword field stores the outcome of 'encryption' via hashing.
    }

    // Getters for attributes
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    // Setter for status
    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    // Setter for isEncrypted (if needed to mark other fields as encrypted)
    public void setEncrypted(boolean encrypted) {
        isEncrypted = encrypted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", username='" + username + '\'' +
               ", status=" + status +
               ", creationDate=" + creationDate +
               ", isEncrypted=" + isEncrypted +
               '}';
    }
}