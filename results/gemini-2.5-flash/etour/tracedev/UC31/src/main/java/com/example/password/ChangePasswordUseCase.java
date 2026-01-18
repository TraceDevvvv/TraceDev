package com.example.password;

/**
 * Encapsulates the business logic for changing a user's password.
 * This class orchestrates interactions between the repository, password hasher, and password policy.
 */
public class ChangePasswordUseCase {
    private IAgencyAccountRepository accountRepository;
    private IPasswordHasher passwordHasher;
    private PasswordPolicy passwordPolicy;

    /**
     * Constructs a ChangePasswordUseCase with its dependencies.
     * @param accountRepository The repository for managing AgencyAccount entities.
     * @param passwordHasher The service for hashing passwords.
     * @param passwordPolicy The policy for validating passwords.
     */
    public ChangePasswordUseCase(IAgencyAccountRepository accountRepository, IPasswordHasher passwordHasher, PasswordPolicy passwordPolicy) {
        this.accountRepository = accountRepository;
        this.passwordHasher = passwordHasher;
        this.passwordPolicy = passwordPolicy;
        System.out.println("DEBUG: ChangePasswordUseCase initialized.");
    }

    /**
     * Executes the password change operation for a given account.
     * This method handles input validation, password policy checking, hashing, and persistence.
     *
     * @param accountId The ID of the account whose password is to be changed.
     * @param newPassword The new password provided by the user.
     * @param confirmPassword The confirmed new password provided by the user.
     * @return A {@link PasswordChangeResult} indicating the outcome of the operation.
     * @throws PersistenceException If a connection or database error occurs during repository operations.
     */
    public PasswordChangeResult execute(String accountId, String newPassword, String confirmPassword) throws PersistenceException {
        System.out.println("DEBUG: UseCase executing for account: " + accountId);

        // 1. Validate password input (match and not empty)
        if (!validatePasswordInput(newPassword, confirmPassword)) {
            System.out.println("DEBUG: UseCase: Password input validation failed.");
            return PasswordChangeResult.INPUT_MISMATCH;
        }

        // 2. Validate password against policy
        if (!passwordPolicy.validate(newPassword)) {
            System.out.println("DEBUG: UseCase: Password policy validation failed.");
            return PasswordChangeResult.POLICY_VIOLATION;
        }

        // 3. Retrieve account from repository
        AgencyAccount agencyAccount = accountRepository.findById(accountId); // May throw PersistenceException
        if (agencyAccount == null) {
            System.err.println("DEBUG: UseCase: Account not found for ID: " + accountId);
            return PasswordChangeResult.ACCOUNT_NOT_FOUND;
        }
        System.out.println("DEBUG: UseCase: Account retrieved: " + agencyAccount.getAccountId());

        // 4. Hash the new password
        String hashedNewPassword = passwordHasher.hashPassword(newPassword);
        System.out.println("DEBUG: UseCase: New password hashed.");

        // 5. Update the account with the new hashed password
        agencyAccount.setHashedPassword(hashedNewPassword); // Updates the internal state of the AgencyAccount object
        System.out.println("DEBUG: UseCase: AgencyAccount instance updated with new hashed password.");

        // 6. Save the updated account back to the repository
        accountRepository.save(agencyAccount); // May throw PersistenceException
        System.out.println("DEBUG: UseCase: AgencyAccount saved successfully.");

        return PasswordChangeResult.SUCCESS; // Password changed successfully
    }

    /**
     * Validates if the new password and its confirmation match and are not empty.
     * @param newPassword The new password string.
     * @param confirmPassword The confirmed password string.
     * @return true if both passwords match and are not empty, false otherwise.
     */
    private boolean validatePasswordInput(String newPassword, String confirmPassword) {
        System.out.println("DEBUG: UseCase: Validating password input...\n");
        if (newPassword == null || newPassword.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty()) {
            System.out.println("DEBUG: UseCase: Input validation failed: Passwords are empty.");
            return false;
        }
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("DEBUG: UseCase: Input validation failed: Passwords do not match.");
            return false;
        }
        System.out.println("DEBUG: UseCase: Input validation passed.");
        return true;
    }
}