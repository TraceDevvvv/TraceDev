```
# ChatDev News Insertion System

## Introduction

The ChatDev News Insertion System is a simple, standalone Java desktop application designed to facilitate the process of inserting new news articles into a hypothetical system. Built with Swing for its graphical user interface, this application provides an intuitive form for agency operators to quickly submit news with essential details like title, content, author, and publication date.

The system incorporates client-side and server-side validation to ensure data integrity and provides clear feedback to the operator regarding the success or failure of the news insertion. It also simulates potential real-world scenarios such as connection interruptions to a backend server.

**Key Features:**

*   **User-Friendly Interface**: A clear and interactive form for data entry.
*   **Real-time Validation**: Client-side checks for mandatory fields and date format.
*   **Server-Side Robustness**: Additional validation in the backend service for data correctness and date validity.
*   **Confirmation Mechanism**: Operators must confirm news details before final submission.
*   **Error Handling**: Distinct messages for validation errors, simulated connection interruptions, and general system errors.
*   **Operation Cancellation**: Ability to cancel the insertion process at any point.
*   **Form Management**: Automatic clearing of the form upon successful submission or manual cancellation.
*   **Graceful Exit**: Confirmation prompt when attempting to close the application.

## Installation Guide

This project is a pure Java application and requires a Java Development Kit (JDK) to compile and run.

### Prerequisites

1.  **Java Development Kit (JDK)**: You need JDK 8 or newer installed on your system.
    *   You can download the latest JDK from Oracle's website: [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/)
    *   Alternatively, you can use OpenJDK: [OpenJDK Downloads](https://openjdk.org/install/)

    **Verifying JDK Installation:**
    Open your terminal or command prompt and type:
    ```bash
    java -version
    javac -version
    ```
    You should see output indicating your Java version (e.g., `openjdk version "17.0.8"`).

2.  **Setting up JAVA_HOME (Optional but Recommended)**:
    Ensure your `JAVA_HOME` environment variable is set to your JDK installation directory, and that `bin` directory of your JDK is included in your system's `PATH`. This is typically handled by the JDK installer but might need manual configuration depending on your OS.

### Getting the Code

1.  **Download the Source Code**:
    Obtain all the `.java` files provided:
    *   `News.java`
    *   `ValidationException.java`
    *   `NewsService.java`
    *   `NewsFormPanel.java`
    *   `NewsApp.java`

2.  **Create a Project Directory**:
    Place all these `.java` files into a single directory on your local machine, for example, `C:\ChatDevNewsApp` (Windows) or `~/ChatDevNewsApp` (Linux/macOS).

### Compiling the Application

1.  **Open Terminal/Command Prompt**: Navigate to the directory where you saved the `.java` files.
    ```bash
    cd C:\ChatDevNewsApp
    # or
    cd ~/ChatDevNewsApp
    ```

2.  **Compile**: Use the Java compiler (`javac`) to compile all `.java` files.
    ```bash
    javac *.java
    ```
    If compilation is successful, you will see `.class` files generated in the same directory (e.g., `NewsApp.class`, `News.class`, etc.). If there are any errors, ensure your JDK is correctly installed and configured.

## Usage Guide

This section details how to run the application and interact with its features.

### 1. Starting the Application

After successful compilation, you can run the application from your terminal or command prompt:

```bash
java NewsApp
```

A new window titled "ChatDev News Insertion System" will appear, displaying the news insertion form.

### 2. Form Overview (Activating the Feature & Displaying the Form)

Upon launching, you will see the `NewsFormPanel` which is the central interface for inserting news. It includes:

*   **Header**: "Insert a New News Article"
*   **Input Fields**:
    *   `Title`: Text field for the news headline.
    *   `Author`: Text field for the news author.
    *   `Pub. Date (YYYY-MM-DD)`: Text field for the publication date, pre-filled with the current date in `YYYY-MM-DD` format.
    *   `Content`: A large text area for the main body of the news article, with scroll capability for extensive text.
*   **Action Buttons**:
    *   `Submit News`: To process and attempt to save the entered news.
    *   `Cancel`: To clear the form and abort the current news insertion.

### 3. Filling Out the Form

1.  **Title**: Enter a concise and descriptive title for your news article.
2.  **Author**: Input the name of the author or agency responsible for the news.
3.  **Pub. Date (YYYY-MM-DD)**: The field is pre-filled with today's date. You can change it if needed, but ensure it adheres to the `YYYY-MM-DD` format (e.g., `2023-10-27`).
4.  **Content**: Type or paste the full content of the news article into the large text area. You can type as much as you need; the area is scrollable.

### 4. Submitting the News

Once all fields are filled, click the **`Submit News`** button.

The system will perform the following steps:

1.  **Client-Side Validation**:
    *   Checks if all fields (Title, Content, Author, Publication Date) are filled.
    *   Checks if the `Publication Date` field matches the `YYYY-MM-DD` format using a regular expression.
    *   If any validation fails, an `Input Error` dialog will appear, explaining what needs to be corrected. You will need to address the errors in the form before proceeding.

2.  **Confirmation of Transaction**:
    *   If client-side validation passes, a `Confirm News Insertion` dialog will appear. This dialog displays all the entered news details (Title, Author, Publication Date, Content) and asks for your confirmation to insert.
    *   You have two options:
        *   **`Yes`**: Proceed with the news insertion.
        *   **`No`**: Cancel the current submission, and you will return to the form. A message "News insertion operation cancelled by the operator" will be displayed.

3.  **Attempting to Store News (after 'Yes' confirmation)**:
    If you confirm with `Yes`, the application attempts to save the news via the `NewsService`. This step involves:
    *   **Server-Side Validation**: The `NewsService` performs a secondary validation, similar to the client-side, but this also includes a more robust check for the actual validity of the date (e.g., ensuring `2023-02-30` is identified as invalid).
        *   If this validation fails, a `Validation Error` dialog will be shown (e.g., "Data validation failed during storage: Invalid Publication Date..."). This corresponds to the "Errored" use case.
    *   **Simulated Connection Interruption (ETOUR)**: Approximately 20% of the time, the system will simulate a connection error to an external server. If this occurs, a `Connection Error` dialog will be displayed ("Connection to server ETOUR interrupted. Please try again."). This addresses the "Interruption of the connection to the server ETOUR" exit condition.
    *   **Successful Storage**: If all validations pass and no connection error occurs, a `Success` dialog will appear ("News 'Your News Title' successfully inserted into the system!"). The form fields will then be cleared, allowing you to insert another news article.

### 5. Cancelling the Operation

*   Click the **`Cancel`** button on the form.
*   A `Confirm Cancellation` dialog will pop up, asking if you are sure you want to cancel.
*   Select **`Yes`** to clear all fields in the form and receive an "Operation Cancelled" message.
*   Select **`No`** to dismiss the dialog and keep your entered data.

This allows the "Operator Agency cancels the operation" exit condition to be met cleanly.

### 6. Exiting the Application

To close the entire application window:

*   Click the **`X`** button in the top-right corner (or equivalent for your operating system).
*   A `Exit Application` confirmation dialog will appear.
*   Select **`Yes`** to close the application.
*   Select **`No`** to keep the application running.

This prevents accidental closure and potential loss of unsaved data.

---

This manual should provide all the necessary information for an agency operator to effectively use the ChatDev News Insertion System.
```