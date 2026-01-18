package presentation.dto;

/**
 * Presentation Layer DTO: Data Transfer Object for registration form input.
 * Carries data from the UI to the controller and service.
 */
public class RegistrationFormDTO {
    public String name;
    public String surname;
    public String mobilePhone;
    public String email;
    public String username;
    public String password;
    public String confirmationPassword;

    /**
     * Constructor for RegistrationFormDTO.
     *
     * @param name The visitor's first name.
     * @param surname The visitor's last name.
     * @param mobilePhone The visitor's mobile phone number.
     * @param email The visitor's email address.
     * @param username The desired username.
     * @param password The desired password.
     * @param confirmationPassword The password confirmation.
     */
    public RegistrationFormDTO(String name, String surname, String mobilePhone, String email, String username, String password, String confirmationPassword) {
        this.name = name;
        this.surname = surname;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
    }

    // Getters for all fields
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

    public String getPassword() {
        return password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }
}