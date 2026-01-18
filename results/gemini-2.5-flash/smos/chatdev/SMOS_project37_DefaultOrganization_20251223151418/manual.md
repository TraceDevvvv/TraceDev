```
# Disciplinary Notes System - Administrator Manual

## Introduction

Welcome to the Disciplinary Notes System! This application provides a simple, intuitive interface for school administrators to efficiently record disciplinary actions for students. Upon saving a note, the system automatically sends a notification email to the parent or guardian and simulates the interruption of a connection to the SMOS server, fulfilling specific operational requirements.

**Main Functions:**

*   **Disciplinary Note Insertion:** Administrators can easily input details for disciplinary incidents, including student's name, incident date, teacher's name, a detailed description, and the parent's email for notification.
*   **Automated Parent Notification:** The system sends a simulated email notification to the parent/guardian with the disciplinary note's details, ensuring timely communication.
*   **Data Persistence (Simulated):** Notes are "saved" to an in-memory collection, simulating storage for the purpose of this demonstration.
*   **SMOS Server Interaction (Simulated):** After a successful note submission, the system simulates interrupting its connection to a hypothetical "SMOS server" as per operational procedures.
*   **Input Validation:** The application includes client-side and server-side validation to ensure data integrity, such as checking for empty fields, valid email format, and description length.

**Actors:** Administrator

## Environment Setup and Installation

To run this JavaFX application, you need to have a Java Development Kit (JDK) installed and correctly configured with the JavaFX SDK.

### Prerequisites

1.  **Java Development Kit (JDK):**
    *   Ensure you have JDK 11 or newer installed (e.g., OpenJDK). You can download it from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [AdoptOpenJDK (now Adoptium)](https://adoptium.net/).
    *   Verify your JDK installation by running `java -version` and `javac -version` in your terminal.

2.  **JavaFX SDK:**
    *   Since JavaFX is no longer bundled with the standard JDK, you need to download the JavaFX SDK separately.
    *   Go to the [OpenJFX website](https://openjfx.io/openjfx-docs/#install) and download the SDK for your operating system (e.g., `javafx-sdk-17.0.x`).
    *   Extract the downloaded archive to a convenient location on your system (e.g., `C:\javafx-sdk-17.0.x` on Windows, `/opt/javafx-sdk-17.0.x` on Linux/macOS). Let's refer to this path as `PATH_TO_FX`.

### Compilation and Execution

Assuming all the provided Java source files (`MainApplication.java`, `Note.java`, `EmailService.java`, `ConsoleEmailService.java`, `NoteService.java`, `NoteFormController.java`, `SmosService.java`, `ConsoleSmosService.java`) and the FXML file (`note_form.fxml`) are in the same directory (e.g., `DisciplinaryNotesApp`):

1.  **Navigate to the project directory:**
    ```bash
    cd /path/to/your/DisciplinaryNotesApp
    ```

2.  **Compile the Java source files:**
    You need to specify the module path to include JavaFX modules.
    ```bash
    javac --module-path PATH_TO_FX --add-modules javafx.controls,javafx.fxml -d out src/*.java  # Adjust 'src' if sources are directly in current dir
    ```
    *   Replace `PATH_TO_FX` with the actual path to your JavaFX SDK `lib` directory (e.g., `C:\javafx-sdk-17.0.x\lib` or `/opt/javafx-sdk-17.0.x/lib`).
    *   `--add-modules javafx.controls,javafx.fxml` specifies the required JavaFX modules.
    *   `-d out` compiles the classes into an `out` directory. If you place your `.java` files directly in the root without a `src` folder, you might adjust `src/*.java` to `*.java`.

3.  **Copy FXML file:**
    The `note_form.fxml` file needs to be accessible during runtime. Copy it to the `out` directory where the compiled classes are.
    ```bash
    cp note_form.fxml out/
    ```
    *(For Windows, use `copy note_form.fxml out\`)*

4.  **Run the application:**
    Again, include the JavaFX module path and specify the main class.
    ```bash
    java --module-path PATH_TO_FX --add-modules javafx.controls,javafx.fxml -cp out MainApplication
    ```
    *   `PATH_TO_FX` should be the same as used during compilation.
    *   `-cp out` sets the classpath to include your compiled classes.

## How to Use the Application

Once the application is successfully launched, a window titled "Insert Disciplinary Note" will appear.

1.  **Administrator Login (Precondition - Simulated):**
    *   The use case states this as a precondition. For this standalone application, it's assumed the administrator is already "logged in" and has the necessary permissions.

2.  **Accessing the Form (Precondition - Simulated):**
    *   The use case states that the administrator has already performed other actions and clicked a "New Note" button. In this application, the form is presented directly upon launching the application.

3.  **Fill out the Form:**
    The form contains the following fields:
    *   **Student Name:** Enter the full name of the student involved.
    *   **Date:** A date picker is pre-filled with today's date. You can click on it to select a different date.
    *   **Teacher Name:** Enter the full name of the teacher who issued the note.
    *   **Parent Email:** Enter the email address of the parent or guardian for notification. The system performs basic client-side validation for email format.
    *   **Description:** Provide a detailed description of the disciplinary action. This field has a character limit of 255 characters, which is checked both client-side and server-side.

4.  **Click "Save":**
    *   After filling in all the required details, click the "Save" button.

5.  **System Response:**
    *   **Validation:** The system will first perform client-side validation. If any required fields are empty, the email format is incorrect, or the description exceeds the character limit, an error message will appear above the "Save" button in red text.
    *   **Saving and Notification:** If all inputs are valid, the application will attempt to "save" the note and send a simulated email notification to the parent.
        *   **Success:** A green success message ("Note saved and notification sent successfully!") will appear. The form fields will be cleared, ready for a new entry. You will also see console output indicating the simulated email being sent and the SMOS connection being interrupted.
        *   **Failure:** A red error message ("Failed to save note or send notification...") will appear if an issue occurs during the simulated saving or email sending process. Check your console for more detailed error logs.

6.  **Post-Conditions Met:**
    *   Upon successful saving, the data is conceptually entered into the system (in-memory).
    *   A notification email is sent to parents (simulated, output to console).
    *   The system returns to a clear state, mimicking a return to a "registry screen" by clearing the input fields.
    *   The connection to the SMOS server is interrupted (simulated, output to console).

To exit the application, simply close the application window.
```