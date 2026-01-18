```
# Edit User Application Manual

This manual provides a comprehensive guide to understanding, setting up, and using the "Edit User" application developed by ChatDev. This application demonstrates a core administrative functionality: safely modifying existing user data within a system, complete with validation and access control.

## 🤔 What is this?

The "Edit User" application is a simple, runnable Java desktop (Swing) application designed to simulate the process of an administrator editing a user's details. It showcases key software engineering principles such as modular design (User, UserManager, GUI) and adherence to a defined use case, including preconditions, event sequences, and postconditions. While the backend data storage is mocked in-memory, it illustrates the typical flow of such an operation in a real-world system.

## 🚀 Main Functions

The application provides the following core functionalities:

*   **User Data Management (Mocked):** Manages a set of predefined user accounts storing their ID, name, email, and role. In a real system, this would interact with a database.
*   **Administrator Access Control:** Enforces a critical precondition: only an administrator can perform user editing. This is simulated via a toggle in the UI.
*   **User Details Display:** Allows an administrator to retrieve and display the details of a specific user by their ID, simulating the "viewdetTailsente" precondition.
*   **Edit User Information:** Enables the administrator to change a user's name, email, and role through an interactive form.
*   **Data Validation:** Automatically checks the validity of the entered user data (e.g., non-empty fields, correct email format, allowed roles) before applying changes.
*   **Error Handling ("Errodati" Use Case):** Notifies the administrator visually if the entered data is invalid and simulates an interruption of connection to an external system (SMOS server) as per the use case's postconditions.
*   **Success Notification:** Confirms when a user has been successfully modified.

## 🛠️ Environment Dependencies

To compile and run this Java application, you will need:

*   **Java Development Kit (JDK):** Version 8 or higher. You can download it from Oracle's website or use OpenJDK.
*   **A Text Editor or Integrated Development Environment (IDE):**
    *   **Text Editor:** (e.g., VS Code, Sublime Text, Notepad++) for writing and saving the `.java` files. You will use the command line for compilation and execution.
    *   **IDE:** (e.g., Apache NetBeans, Eclipse, IntelliJ IDEA) provides a more integrated environment for coding, compiling, and running Java applications.

## ⬇️ How to Install/Run

Follow these steps to get the application up and running:

### Step 1: Save the Code Files

1.  Create a new directory (folder) on your computer for the project, for example, `EditUserApp`.
2.  Inside this directory, create four new files and copy the respective code into them:
    *   `User.java`
    *   `UserManager.java`
    *   `EditUserGUI.java`
    *   `MainApp.java`

Ensure the file names match exactly, including capitalization.

### Step 2: Compile the Java Code

#### Using Command Line:

1.  Open your command prompt or terminal.
2.  Navigate to the directory where you saved your `.java` files:
    ```bash
    cd path/to/your/EditUserApp
    ```
3.  Compile all the Java source files:
    ```bash
    javac User.java UserManager.java EditUserGUI.java MainApp.java
    ```
    If successful, this command will create `.class` files for each `.java` file in the same directory. If there are compilation errors, double-check that you've copied the code correctly.

#### Using an IDE (e.g., IntelliJ IDEA, Eclipse):

1.  Open your IDE.
2.  Create a new Java project.
3.  Copy the four `.java` files into the `src` (source) folder of your new project.
4.  The IDE should automatically compile the files. If not, trigger a "Build Project" action.

### Step 3: Run the Application

#### Using Command Line:

1.  After successful compilation, run the main application:
    ```bash
    java MainApp
    ```
    This will launch the graphical user interface (GUI) window.

#### Using an IDE:

1.  Right-click on `MainApp.java` (or directly within the `MainApp` class code) and select "Run MainApp.main()".
2.  The IDE will execute the application, and the GUI window will appear.

## ▶️ How to Use (Play with the Application)

Once the `Edit User Details` window appears, you can interact with it to simulate the use case:

1.  **Launch State:**
    *   The application starts with a mock user (`U001 - John Doe`) pre-loaded into the form fields as a demonstration of the "viewdetTailsente" precondition.
    *   The "Simulate Admin Login" checkbox will be checked by default, satisfying the initial admin login precondition.

2.  **Simulate Admin Login/Logout:**
    *   **Checked:** (Default) The administrator is logged in. You can proceed to edit users.
    *   **Unchecked:** The administrator is logged out. If you try to click "Edit User", you will receive an "Authentication Error" message, demonstrating the first precondition.

3.  **Load a User for Editing (Precondition: "viewdetTailsente"):**
    *   In the "User ID:" field, you can enter a user ID.
    *   Click the **"Load User"** button.
    *   **Valid IDs:** `U001`, `U002`, `U003`. If a valid ID is entered, the corresponding user's details will populate the "Name", "Email", and "Role" fields.
    *   **Invalid ID:** Enter an ID like `U004` and click "Load User". You will see an "User with ID 'U004' not found." error message. The fields will clear, and no user will be selected for editing.

4.  **Edit User Details:**
    *   Ensure a user is loaded (e.g., `U001`).
    *   Modify the text in the "Name", "Email", or "Role" fields.
    *   **Example Edit:** Change John Doe's email from `john.doe@example.com` to `john.doe.new@example.com`.

5.  **Triggering Validation and Errors ("Errodati" Use Case):**
    *   While a user is loaded and "Simulate Admin Login" is checked:
        *   **Empty Name:** Clear the "Name" field and click "Edit User". You will get an "Errodati: Invalid data entered..." error indicating the name cannot be empty.
        *   **Empty Email:** Clear the "Email" field and click "Edit User". Similar error for empty email.
        *   **Invalid Email Format:** Type "invalid-email" into the "Email" field and click "Edit User". You will get an "Errodati: Invalid data entered..." error regarding the email format.
        *   **Empty Role:** Clear the "Role" field and click "Edit User". You will get an "Errodati: Invalid data entered..." error indicating the role cannot be empty.
        *   **Invalid Role:** Change the role to something like "Guest" (which is not in the allowed roles list: "Administrator", "Standard User", "Moderator") and click "Edit User". You will receive an "Errodati: Invalid data entered..." error.
    *   In all "Errodati" scenarios, notice the console output: `DEBUG: Administrator interrupts connection operation to the SMOS server.` This simulates the specified postcondition for invalid data.

6.  **Successful Edit:**
    *   Load a user (e.g., `U002`).
    *   Ensure "Simulate Admin Login" is checked.
    *   Enter valid, new details (e.g., change "Jane Smith" to "Janet Smith", email to "janet.smith@company.com", role to "Moderator").
    *   Click the **"Edit User"** button.
    *   You should now see a "Success" dialog box stating the user was updated, for example: `"User 'Janet Smith' (ID: U002) updated successfully!"`.
    *   You can then try loading `U002` again to confirm the changes are reflected in the form.

This application provides a hands-on way to understand how a basic "Edit User" functionality works, including its preconditions, event flow, and error handling mechanisms within a Java Swing environment.