```markdown
# User Registration System

This document serves as a user manual for the Java Swing-based User Registration System. It provides an overview of the system's core functionality, details on setting up the necessary environment, and step-by-step instructions on how to compile, run, and interact with the application.

## 💡 About the Software

The User Registration System is a simple application designed to demonstrate a basic user registration flow using Java Swing for the graphical user interface. Its primary function is to allow a "Guest User" to create a new account within the system.

**Key Features:**

*   **User-Friendly Interface**: An intuitive form for entering registration details.
*   **Data Validation**: Ensures that provided data (username, password, email) meets specific criteria before account creation. This includes checks for:
    *   Missing or empty fields.
    *   Username length.
    *   Password length and basic complexity.
    *   Valid email format.
    *   Uniqueness of the username.
*   **Confirmation Step**: Requires user confirmation before finalizing the account creation.
*   **Secure Password Handling**: Passwords are handled as character arrays (`char[]`) and wiped from memory after use, along with a simulated hashing process for storage, enhancing security.
*   **Logging**: All significant system events and user actions are logged to the console and a file (`registration_system.log`) for monitoring and debugging.
*   **Error Handling**: Catches invalid data, handles user cancellations, and simulates potential connection interruptions (ETOUR) to demonstrate robust error management.

## 🛠️ Environment Dependencies

This application is developed purely in Java and relies solely on the standard Java Development Kit (JDK) libraries. No external third-party dependencies are required.

### Required Software

*   **Java Development Kit (JDK) 8 or higher**: A Long-Term Support (LTS) version like OpenJDK 11 or 17 is recommended for stability and ongoing support.

### Installation Steps

1.  **Download JDK**:
    *   Visit a reputable JDK provider (e.g., Oracle, Adoptium, Azul Zulu, Amazon Corretto) and download the appropriate JDK installer for your operating system.
2.  **Install JDK**:
    *   Follow the installation instructions provided by the JDK installer.
3.  **Set Environment Variables (Optional but Recommended)**:
    *   **`JAVA_HOME`**: Set this environment variable to the root directory of your JDK installation (e.g., `C:\Program Files\Java\jdk-17` on Windows or `/usr/lib/jvm/jdk-17` on Linux/macOS).
    *   **`PATH`**: Add the `bin` directory of your JDK installation to your system's `PATH` variable (e.g., `%JAVA_HOME%\bin` on Windows or `$JAVA_HOME/bin` on Linux/macOS). This allows you to run `java` and `javac` commands from any directory in your terminal.
    *   *Consult your operating system's documentation for specific instructions on setting environment variables.*
4.  **Verify Installation**:
    *   Open a new terminal or command prompt and run the following commands:
        ```bash
        java -version
        javac -version
        ```
    *   You should see output indicating the installed Java version, confirming a successful installation.

## ▶️ How to Use the Application

### 1. Obtain the Source Code

Ensure you have all the provided Java source files (`.java` files) in a single directory. The files are:

*   `RegistrationSystemApp.java`
*   `RegistrationFormPanel.java`
*   `User.java`
*   `UserService.java`
*   `RegistrationStatus.java`
*   `RegistrationLogger.java`

### 2. Compile the Application

Navigate to the directory containing the source files using your terminal or command prompt, then compile them using the Java compiler (`javac`):

```bash
cd /path/to/your/source/code
javac *.java
```

If the compilation is successful, you will see no output, and new `.class` files will be generated in the same directory.

### 3. Run the Application

After successful compilation, you can run the application:

```bash
java RegistrationSystemApp
```

A graphical window titled "User Registration System" will appear, presenting the registration form.

### 4. Interact with the Registration System

Upon launching, the system's logging feature is immediately enabled (Step 1). You will see log messages in your console and written to a file named `registration_system.log` in the application's directory.

**Flow of Events:**

1.  **View the registration form (GUI)**:
    *   The application will display a window with fields for Username, Password, and Email, along with "Register" and "Cancel" buttons.
2.  **Fill out the form and submit**:
    *   Enter your desired registration details into the respective fields.
    *   Click the "Register" button to submit the form.
3.  **Data Verification and Confirmation**:
    *   The system will immediately validate the data you entered.
    *   **If data is invalid or insufficient**: A dialog box will appear with an error message (e.g., "Invalid or insufficient data provided", "Username already exists", "Password too short", "Invalid email format"). The system will revert to the form, allowing you to correct the errors.
        *   **Example Invalid Inputs**:
            *   **Username**: `ad` (too short), `admin` (already exists), `username with spaces` (may not be explicitly validated in this version, but generally bad practice).
            *   **Password**: `123` (too short).
            *   **Email**: `invalid-email`, `user@.` (invalid format).
    *   **If data is valid**: A confirmation dialog will appear, asking "Are you sure you want to create an account with these details?". It will display the Username and Email you entered.
        *   Click "Yes" to confirm the operation.
        *   Click "No" to cancel the operation before account creation.
4.  **Account Creation**:
    *   If you confirmed the operation by clicking "Yes":
        *   The system will attempt to create a new account.
        *   **Simulated Connection Error (ETOUR)**: Approximately 10% of registration attempts might encounter a simulated connection interruption. If this occurs, an error message ("Connection to server interrupted (ETOUR)") will be displayed, and the account will not be created.
        *   **Successful Creation**: If no errors occur, a "Registration Successful" dialog will appear, stating "Your account has been created successfully!". The form fields will then be cleared for a new registration.
5.  **Cancellation by Guest User**:
    *   If you click the "Cancel" button on the form, a confirmation ("Are you sure you want to cancel the registration?") will appear. Confirming this will clear the form fields and log the cancellation.
    *   If you click "No" during the confirmation step (after valid data entry), the registration will also be cancelled, and the form fields will be cleared.

**Example Successful Registration:**

1.  **Username**: `myusername`
2.  **Password**: `MyStrongPassword123!`
3.  **Email**: `myusername@example.com`
4.  Click "Register".
5.  Confirm in the dialog.
6.  If no simulated connection error occurs, you will see a success message.

Observe the console output and the `registration_system.log` file for detailed information about each step, including validation messages, user actions, and system outcomes.
```