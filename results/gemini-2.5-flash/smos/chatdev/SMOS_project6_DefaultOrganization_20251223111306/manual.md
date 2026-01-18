```
# User Management System: New User Module

## Introduction

This application facilitates the process of adding new user accounts to a system through a graphical user interface (GUI). Designed for system administrators, it provides a simple form to collect essential user details, performs data validation, and securely stores the new user's information.

## Main Functions

The "New User" module primarily provides the following functionalities:

*   **User Entry Form Display**: Presents a clear and intuitive form with fields for `Name`, `Surname`, `E-mail`, `Cell`, `Login`, `Password`, and `Confirm Password`.
*   **Data Input & Collection**: Allows administrators to input all necessary details for a new user account.
*   **Data Validation**: Ensures the integrity and correctness of the entered data by performing checks such as:
    *   Verifying that all mandatory fields are filled.
    *   Validating the format of the E-mail address.
    *   Validating the format of the Cell number.
    *   Ensuring that the `Password` and `Confirm Password` fields match.
    *   Checking for a minimum password length.
    *   Confirming that the chosen `Login` ID is unique and not already in use by another user.
*   **Password Hashing**: Before storing, passwords are processed using a hashing algorithm to enhance security (though for this example, a simplified hashing mechanism is used).
*   **User Creation**: Upon successful validation, the system adds the new user's details to its internal user archive.
*   **Feedback Mechanism**: Provides immediate notifications to the administrator, indicating successful user creation or specific data entry errors.

## Environment Dependencies

To run this application, you will need:

*   **Java Development Kit (JDK)**: Version 8 or newer is required. This includes the Java Runtime Environment (JRE) and development tools like `javac` for compiling the code.

## How to Install and Run

This is a standalone Java application. "Installation" involves ensuring the JDK is set up and then compiling and executing the provided source files.

1.  **Install Java Development Kit (JDK)**
    *   If you don't have JDK installed, download and install it from the official Oracle website or adoptium.net (for OpenJDK builds). Follow the instructions for your specific operating system.
    *   Verify your installation by opening a terminal or command prompt and typing:
        ```bash
        java -version
        javac -version
        ```
        You should see output similar to `openjdk version "11.0.12"` or `java version "1.8.0_292"`.

2.  **Save the Source Code**
    *   Save all the provided Java files (`Main.java`, `User.java`, `UserManager.java`, `UserValidationService.java`, `NewUserForm.java`, `PasswordHasher.java`) into a single directory on your computer. For example, you could create a folder named `NewUserApp`.

3.  **Compile the Code**
    *   Open a terminal or command prompt.
    *   Navigate to the directory where you saved your Java files using the `cd` command:
        ```bash
        cd path/to/your/NewUserApp
        ```
    *   Compile all `.java` files:
        ```bash
        javac *.java
        ```
        If compilation is successful, `.class` files will be generated in the same directory, and you will not see any error messages.

4.  **Run the Application**
    *   From the same terminal or command prompt, run the main application file:
        ```bash
        java Main
        ```
    *   A GUI window titled "New User Entry Form" should appear.

## How to Use/Play

Once the "New User Entry Form" window is displayed, you can proceed to create a new user.

1.  **Fill the Fields of the Form**:
    *   **Name**: Enter the user's first name.
    *   **Surname**: Enter the user's last name.
    *   **E-mail**: Enter the user's email address (e.g., `john.doe@example.com`).
    *   **Cell**: Enter the user's cell phone number (e.g., `1234567890`).
    *   **Login**: Choose a unique login ID for the user (e.g., `johndoe`).
    *   **Password**: Enter a password (e.g., `MySecret123`).
    *   **Confirm Password**: Re-enter the exact same password for confirmation.

2.  **Click on the "Save" Button**:
    *   After filling all the required information, click the "Save" button.

### Expected Outcomes:

*   **Successful User Creation**:
    *   If all data is valid and the login is unique, a message dialog will appear stating: "New user '[login]' created successfully!", where `[login]` is the login ID you entered.
    *   After clicking "OK" on the success message, all form fields will be cleared, allowing you to create another user.
    *   *System Postcondition*: A new user has been created and the administrator is notified.

*   **Data Entry Errors ("Errodati" Use Case)**:
    *   If any validation rule is violated (e.g., a field is left empty, email format is incorrect, passwords don't match, or the login ID is already taken), an "Error" message dialog will appear.
    *   The dialog will clearly describe the specific error (e.g., "Name cannot be empty.", "Invalid email address format.", "Password and Confirm Password do not match.", "Login ID 'admin' is already taken. Please choose another.").
    *   The form fields will retain their current values, allowing you to correct the erroneous input.
    *   *System Postcondition*: The data error is notified to the administrator.

### Example Scenarios:

*   **Scenario 1: Happy Path (Successful Creation)**
    *   Name: `Jane`
    *   Surname: `Doe`
    *   E-mail: `jane.doe@company.com`
    *   Cell: `9876543210`
    *   Login: `janedoe`
    *   Password: `SecureP@ss1`
    *   Confirm Password: `SecureP@ss1`
    *   *Result*: Success message, form cleared.

*   **Scenario 2: Empty Field Error**
    *   Name: `John`
    *   Surname: (left blank)
    *   ... (other fields filled)
    *   *Result*: Error dialog: "Surname cannot be empty."

*   **Scenario 3: Invalid Email Format**
    *   E-mail: `invalid-email`
    *   ... (other fields filled)
    *   *Result*: Error dialog: "Invalid email address format."

*   **Scenario 4: Mismatching Passwords**
    *   Password: `pass123`
    *   Confirm Password: `pass456`
    *   ... (other fields filled)
    *   *Result*: Error dialog: "Password and Confirm Password do not match."

*   **Scenario 5: Login Already Taken**
    *   Login: `admin` (since `UserManager` pre-populates an 'admin' user)
    *   ... (other fields filled correctly)
    *   *Result*: Error dialog: "Login ID 'admin' is already taken. Please choose another."

This module provides a robust and user-friendly interface for managing new user entries within the system.
```