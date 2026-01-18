```markdown
# Administrator Dashboard - View Registration Requests

A simple Java Swing application for administrators to view pending student registration requests.

## 🤔 What is this?

This application provides a graphical user interface (GUI) for an administrator to view a list of student registration requests that are awaiting activation in a simulated system. It's designed to demonstrate how a front-end application might interact with a backend service (referred to as the "SMOS server" in the use case) to fetch data, including handling potential network latency and connection interruptions.

## 🌟 Main Functions

The core functionality of this software is:

*   **View Pending Registration Requests**: Administrators can click a button to retrieve and display a list of all student registration requests that have not yet been activated.
*   **Simulated Backend Interaction**: The application simulates fetching data from a backend service, including:
    *   Artificial network delays to mimic real-world latency.
    *   A chance of connection interruption errors, demonstrating how such issues might be handled and reported to the user.
*   **User-Friendly Interface**: Presents the information in a clear, readable format within a Swing window.

## 🛠️ How to Install Environment Dependencies

To run this Java application, you need to have a Java Development Kit (JDK) installed on your system.

1.  **Install Java Development Kit (JDK)**:
    *   Ensure you have JDK 8 or a newer version installed. You can download it from Oracle's website or use OpenJDK distributions like Adoptium (formerly AdoptOpenJD K).
    *   Follow the installation instructions for your operating system.
    *   Verify your installation by opening a terminal or command prompt and running:
        ```bash
        java -version
        javac -version
        ```
        You should see output indicating your Java version.

2.  **Integrated Development Environment (IDE) (Optional but Recommended)**:
    *   While not strictly necessary, using an IDE like IntelliJ IDEA, Eclipse, or Visual Studio Code with Java extensions can greatly simplify compiling and running Java applications.

## ▶️ How to Use/Play it

Follow these steps to compile and run the application:

1.  **Save the Code**:
    *   Create a new directory (e.g., `AdminDashboardApp`) on your computer.
    *   Save all the provided Java code files (`MainApplication.java`, `AdminDashboardFrame.java`, `RegistrationService.java`, `RegistrationRequest.java`, `ConnectionInterruptedException.java`) into this directory. Ensure each `.java` file corresponds to the class name as provided.

2.  **Compile the Application**:
    *   **Using a Terminal/Command Prompt**:
        *   Navigate to the directory where you saved the files using the `cd` command:
            ```bash
            cd path/to/AdminDashboardApp
            ```
        *   Compile all Java source files:
            ```bash
            javac *.java
            ```
            If compilation is successful, `.class` files will be generated for each Java file in the same directory.
    *   **Using an IDE**:
        *   Create a new Java project in your IDE.
        *   Add all the Java files to the project's source folder.
        *   The IDE will typically compile the code automatically.

3.  **Run the Application**:
    *   **Using a Terminal/Command Prompt**:
        *   From the same directory (where `MainApplication.class` is located), run the main application:
            ```bash
            java MainApplication
            ```
    *   **Using an IDE**:
        *   Locate the `MainApplication.java` file in your project.
        *   Right-click on it and choose "Run 'MainApplication.main()'" or similar option.

4.  **Interact with the Application**:
    *   Once launched, an "Administrator Dashboard - View Registration Requests" window will appear.
    *   Click the **"View list request list"** button.
    *   The text area in the middle of the window will update:
        *   It will first show "Fetching pending registration requests...".
        *   After a short simulated delay (typically 0.5 to 1.5 seconds), it will display either:
            *   A list of simulated pending student registration requests.
            *   The message "No pending requests found." (this happens occasionally due to simulation logic).
            *   An error message like "Error: Connection to SMOS server interrupted." (this happens about 30% of the time due to simulation logic).
    *   You can click the button multiple times to see different results from the simulated service.
    *   To close the application, simply close the window using the standard window close button (e.g., 'X' icon). This simulates the administrator interrupting the connection to the SMOS server.

This covers the full cycle of interacting with the "ViewRequestsSubscription" use case within the simulated environment.
```