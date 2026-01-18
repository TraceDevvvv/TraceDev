```markdown
# Student Registration System

A simple Java Swing application that allows students (visitors) to register to a system by filling out a form with various personal and account details. The application includes client-side input validation and simulates secure password handling and system integration.

## 🤔 What is this?

This application is built to demonstrate a basic user registration flow, focusing on immediate input validation and the separation of concerns between the user interface (GUI), business logic, and data models. It simulates the process of a user filling out a registration form and the system validating, processing, and "inserting" that request.

**Key Features:**

*   **User Interface (GUI)**: A graphical form created using Java Swing for easy interaction.
*   **Input Validation**: Real-time checks for:
    *   All fields being filled.
    *   Password and confirmation password matching.
    *   Valid email format.
    *   Valid mobile phone number format.
    *   Strong password policy (length, uppercase, lowercase, digit, special character).
    *   Unique username check (simulated).
*   **Secure Password Handling**: Passwords are *hashed* using SHA-256 with a unique salt before being conceptually "stored". This demonstrates a fundamental security practice.
*   **Feedback Mechanism**: Provides clear success or error messages to the user directly on the form.
*   **Modular Design**: Separates the data model (`User`), business logic (`RegistrationService`), and presentation layer (`RegistrationForm`, `RegistrationApp`).

## 📖 Main Functions

The application focuses on a single core function: **Student Registration**.

1.  **Display Registration Form**: Upon launching, the application presents a form with fields for:
    *   Name
    *   Surname
    *   Mobile Phone
    *   E-mail
    *   Username
    *   Password
    *   Confirmation Password
2.  **Collect User Input**: The user fills in the details in the respective fields.
3.  **Submit Registration**: The user clicks the "Register" button to submit their information.
4.  **Validate Input**: The system performs comprehensive validation on all entered data:
    *   Checks if all fields are non-empty.
    *   Ensures the 'Password' and 'Confirm Password' fields match.
    *   Verifies the `E-mail` adheres to a standard email format.
    *   Validates the `Mobile Phone` number (10-15 digits).
    *   Enforces a strong password policy (minimum 8 characters, with at least one digit, one lowercase, one uppercase, and one special character).
    *   Checks if the chosen `Username` is unique (simulated against a small list of existing usernames).
5.  **Process and "Store" Data**: If all validations pass:
    *   The plain-text password is securely hashed using SHA-256 with a unique salt.
    *   A `User` object is created with all the user's details, including the *hashed* password.
    *   The system simulates "inserting the registration request into the system" by printing the registrant's details (with the password hidden for security) to the console.
    *   A success message is displayed on the GUI.
    *   The form fields are cleared.
6.  **Handle Errors**: If any validation fails, an appropriate error message is displayed on the GUI, guiding the user on how to correct the input.

## 🛠️ Environment Setup

To run this Java application, you need:

1.  **Java Development Kit (JDK)**: Version 8 or newer is recommended.
    *   **Download**: You can download the JDK from Oracle's website or use an open-source distribution like OpenJDK (e.g., from Adoptium's Temurin or Amazon Corretto).
    *   **Installation**: Follow the installation instructions for your operating system.
    *   **JAVA_HOME**: Ensure your `JAVA_HOME` environment variable is set and points to your JDK installation directory, and that the `bin` directory of your JDK is included in your system's `PATH`.

2.  **Text Editor or Integrated Development Environment (IDE)**:
    *   **Recommended**: IntelliJ IDEA (Community Edition), Eclipse, or VS Code with Java extensions. These provide excellent features for writing, compiling, and running Java code.
    *   **Alternative**: Any basic text editor (like Notepad++, Sublime Text, Atom) can be used, but you'll compile and run from the command line.

## ▶️ How to Use/Play

### Step 1: Save the Code Files

Save the provided Java code into three separate files in a directory of your choice (e.g., `registration_app`):

*   `User.java`
*   `RegistrationService.java`
*   `RegistrationForm.java`
*   `RegistrationApp.java`

Make sure all files are in the same directory.

### Step 2: Compile the Java Code

Open your terminal or command prompt, navigate to the directory where you saved the files, and compile them using the `javac` command:

```bash
cd path/to/your/registration_app_directory
javac User.java RegistrationService.java RegistrationForm.java RegistrationApp.java
```

If successful, this will create `.class` files for each `.java` file in the same directory. If there are any compilation errors, ensure your Java code is exactly as provided and your JDK is correctly installed.

### Step 3: Run the Application

Execute the main application class using the `java` command:

```bash
java RegistrationApp
```

### Step 4: Interact with the Registration Form

1.  A window titled "Student Registration" will appear.
2.  **Fill the fields**: Enter information into the "Name", "Surname", "Mobile Phone", "E-mail", "Username", "Password", and "Confirm Password" fields.
    *   **Experiment with validations**: Try leaving fields empty, entering invalid email/mobile formats, using non-matching passwords, or a weak password to see the error messages.
    *   **Strong Password Example**: A strong password might be `P@ssword123`.
3.  **Click "Register"**: After filling in the details, click the "Register" button.
4.  **Observe Feedback**:
    *   **Success**: If all validations pass, a green "Registration successful! Welcome, [Name]." message will appear below the button, and the console (where you ran the `java` command) will display a simulated "insertion" message showing the details of the registered user (with a hidden password). The form fields will then clear.
    *   **Error**: If any validation fails, a red error message will appear below the button, indicating what went wrong (e.g., "Password and confirmation password do not match.", "Invalid email format.", etc.). The password fields will be cleared for security.

You can register multiple users. Note that the username uniqueness check is simulated, so once a username is successfully registered, trying to register with the same username again will result in an "Username already exists" error.

To close the application, simply click the standard "X" close button on the window.
```