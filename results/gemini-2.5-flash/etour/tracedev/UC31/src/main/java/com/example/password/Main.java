
package com.example.password;

/**
 * Main class to set up the application context and demonstrate the password change flow.
 * This acts as a simple Dependency Injection container.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Password Change Application Demo ---\n");

        // --- 1. Setup Dependencies ---\n
        // Interfaces and their implementations
        // Note: For this Main class to compile, the IAgencyAccountRepository interface needs to declare
        // a 'getAccount(String)' method. As we can only modify this file, we cast to the concrete
        // implementation 'AgencyAccountRepositoryImpl' which is presumed to have this method.
        IAgencyAccountRepository accountRepository = new AgencyAccountRepositoryImpl();
        IPasswordHasher passwordHasher = new BCryptPasswordHasher();

        // Utility/Policy classes
        PasswordPolicy passwordPolicy = new PasswordPolicy();

        // --- 2. Instantiate Core Components ---\n
        // Create the UseCase, injecting its dependencies
        ChangePasswordUseCase useCase = new ChangePasswordUseCase(accountRepository, passwordHasher, passwordPolicy);

        // Create the Form (View)
        ChangePasswordForm form = new ChangePasswordForm();

        // Create the Controller, injecting UseCase and Form
        ChangePasswordController controller = new ChangePasswordController(useCase, form);

        // Handle circular dependency for Form to Controller reference
        form.setController(controller);
        // UseCase no longer directly interacts with Form for error messages, so no need for useCase.setView(form);

        System.out.println("\n--- Initial Account State ---");
        // Compilation Fix: Cast to concrete implementation as IAgencyAccountRepository interface is missing getAccount method.
        System.out.println(((AgencyAccountRepositoryImpl) accountRepository).getAccount("testAccount123"));


        // --- 3. Simulate User Interactions (Success Scenario) ---\n
        System.out.println("\n--- Scenario 1: Successful Password Change ---");
        String accountIdSuccess = "testAccount123";
        String newPasswordSuccess = "NewSecurePassword123"; // Meets policy: >=8 chars, digit, uppercase
        String confirmPasswordSuccess = "NewSecurePassword123";
        form.requestChangePassword(accountIdSuccess, newPasswordSuccess, confirmPasswordSuccess);

        System.out.println("\n--- Account State After Scenario 1 ---");
        // Compilation Fix: Cast to concrete implementation as IAgencyAccountRepository interface is missing getAccount method.
        AgencyAccount updatedAccount1 = ((AgencyAccountRepositoryImpl) accountRepository).getAccount(accountIdSuccess);
        System.out.println(updatedAccount1);
        // Verify if password was hashed and updated
        if (updatedAccount1 != null && updatedAccount1.getHashedPassword().equals("hashed_" + newPasswordSuccess)) {
            System.out.println("Verification: Hashed password matches for " + accountIdSuccess + ".");
        } else {
            System.out.println("Verification: Hashed password does NOT match or account not found for " + accountIdSuccess + ".");
        }


        // --- 4. Simulate User Interactions (Input Mismatch Scenario) ---\n
        System.out.println("\n--- Scenario 2: Password Mismatch Error ---");
        String accountIdMismatch = "testAccount123";
        String newPasswordMismatch = "Password123";
        String confirmPasswordMismatch = "PasswordABC"; // Mismatch
        form.requestChangePassword(accountIdMismatch, newPasswordMismatch, confirmPasswordMismatch);


        // --- 5. Simulate User Interactions (Password Policy Violation Scenario) ---\n
        System.out.println("\n--- Scenario 3: Password Policy Violation Error ---");
        String accountIdPolicy = "testAccount123";
        String newPasswordPolicy = "short"; // Violates policy: too short
        String confirmPasswordPolicy = "short";
        form.requestChangePassword(accountIdPolicy, newPasswordPolicy, confirmPasswordPolicy);


        // --- 6. Simulate User Interactions (Persistence Exception - findById) ---\n
        System.out.println("\n--- Scenario 4: Persistence Exception (findById) ---");
        String accountIdPersistenceFind = "error_account456"; // Triggers PersistenceException in findById
        String newPasswordPersistenceFind = "ValidPassword123";
        String confirmPasswordPersistenceFind = "ValidPassword123";
        form.requestChangePassword(accountIdPersistenceFind, newPasswordPersistenceFind, confirmPasswordPersistenceFind);


        // --- 7. Simulate User Interactions (Persistence Exception - save) ---\n
        System.out.println("\n--- Scenario 5: Persistence Exception (save) ---");
        // Create an account that will cause save to fail
        AgencyAccount accountToErrorSave = new AgencyAccount("error_save789", "hashed_InitialPass");
        try {
            accountRepository.save(accountToErrorSave); // Pre-save to ensure it's in the repo to be updated
        } catch (PersistenceException e) {
            System.err.println("DEBUG: Initial save for 'error_save789' failed (expected). " + e.getMessage());
        }

        String accountIdPersistenceSave = "error_save789"; // Triggers PersistenceException in save
        String newPasswordPersistenceSave = "AnotherValidPass789";
        String confirmPasswordPersistenceSave = "AnotherValidPass789";
        form.requestChangePassword(accountIdPersistenceSave, newPasswordPersistenceSave, confirmPasswordPersistenceSave);

        // --- 8. Simulate User Interactions (Account Not Found Scenario) ---\n
        System.out.println("\n--- Scenario 6: Account Not Found Error ---");
        String accountIdNotFound = "nonExistentAccount";
        String newPasswordNotFound = "ValidPassword123";
        String confirmPasswordNotFound = "ValidPassword123";
        form.requestChangePassword(accountIdNotFound, newPasswordNotFound, confirmPasswordNotFound);


        System.out.println("\n--- Demo Complete ---");
    }
}
