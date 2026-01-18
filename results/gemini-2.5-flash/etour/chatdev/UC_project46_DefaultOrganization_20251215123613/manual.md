```
# Edit Personal Search Preferences Application

This application allows tourists to view and modify their personal search preferences within a travel system. It provides a user-friendly interface to update details such as desired destination, price range, and categories of interest, ensuring a personalized search experience.

## 🤔 What is this?

The "Edit Personal Search Preferences" application is a standalone JavaFX desktop application that simulates a common feature found in online travel platforms. It enables an authenticated user (referred to as a "Tourist") to manage their search criteria. The application handles fetching existing preferences, allowing modifications, validating inputs, confirming changes, and saving the updated preferences. It also incorporates robust error handling for connection interruptions to an imagined backend server.

## 🚀 Main Functions

*   **View Current Preferences:** Upon accessing the feature, the system loads and displays the tourist's current search preferences in an editable form.
*   **Edit Preferences:** Users can modify fields such as:
    *   **Destination:** A text field for specifying a preferred travel destination.
    *   **Price Range:** Numerical input fields for minimum and maximum pr.
    *   **Categories:** Checkboxes for selecting various categories of interest (e.g., Culture, Adventure, Food).
*   **Input Validation:** The application validates user input, ensuring correct data types for pr and logical constraints (e.g., minimum price not exceeding maximum price).
*   **Confirmation:** Before saving, the system asks for user confirmation to prevent accidental updates.
*   **Save Preferences:** Once confirmed, the changes are processed and saved, with appropriate feedback to the user.
*   **Cancel Operation:** Users can cancel the modification process at any point, discarding any unsaved changes.
*   **Connection Interruption Handling:** The system gracefully handles simulated connection failures during both loading and saving preferences, providing users with options to retry or proceed.
*   **User Feedback:** Provides real-time status messages and alerts for successful operations, validation errors, and system issues.

## 🛠️ Environment Setup

To run this application, you need a Java Development Kit (JDK) and the JavaFX SDK.

### 1. Install Java Development Kit (JDK)

The application is built with Java 17. It is recommended to use JDK 17 or a later version.

*   Download and install a JDK from Oracle, AdoptOpenJDK, or other providers:
    *   [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/)
    *   [Adoptium (Eclipse Temurin) Downloads](https://adoptium.net/temurin/releases/)
*   Ensure your `JAVA_HOME` environment variable is set to your JDK installation directory and that `java` and `javac` commands are accessible from your terminal. You can verify your Java version by running:
    ```bash
    java -version
    ```
    You should see output indicating Java 17 or higher.

### 2. Obtain JavaFX SDK

The `requirements.txt` specifies `javafx-controls==21.0.1` and `javafx-fxml==21.0.1`. You should download the JavaFX SDK compatible with your JDK version (at least 17, preferably 21+ for consistency).

*   Download the JavaFX SDK from the official website:
    *   [OpenJFX Downloads](https://gluonhq.com/products/javafx/)
*   Choose the SDK for your operating system and compatible with your JDK.
*   Extract the downloaded zip file to a convenient location (e.g., `C:\javafx-sdk-21` on Windows, or `/opt/javafx-sdk-21` on Linux/macOS). Let's refer to this path as `PATH_TO_FX`.

## 📦 Project Structure and Dependencies

The application consists of several Java source files and an FXML layout file.

```
.
├── mainapp.java
├── tourist.java
├── searchpreferences.java
├── preferenceservice.java
├── preferencescontroller.java
├── preferences_view.fxml
└── requirements.txt
```

The `requirements.txt` indicates the need for `javafx-controls` and `javafx-fxml` modules. These are provided by the JavaFX SDK you downloaded.

## ▶️ How to Build and Run

Follow these steps to compile and run the application from your terminal.

### 1. Create Project Directory

Create a directory for your project and place all the provided `.java` and `.fxml` files inside it. For example:

```bash
mkdir SearchPreferencesApp
cd SearchPreferencesApp
# Copy all .java and .fxml files here
```

### 2. Compile Java Files

You need to compile the Java source files, specifying the JavaFX module path. Replace `PATH_TO_FX` with the actual path where you extracted the JavaFX SDK (e.g., `/opt/javafx-sdk-21/lib` or `C:\javafx-sdk-21\lib`).

```bash
javac --module-path PATH_TO_FX --add-modules javafx.controls,javafx.fxml *.java
```

This command compiles all `.java` files in the current directory, linking them against the necessary JavaFX modules.

### 3. Run the Application

After successful compilation, you can run the application. Again, you need to provide the JavaFX module path and specify the `MainApp` class as the entry point.

```bash
java --module-path PATH_TO_FX --add-modules javafx.controls,javafx.fxml MainApp
```

### 4. Interact with the Application

Once the application launches, you will see a window titled "Edit Search Preferences".

*   **Initial Load:** The application will attempt to load preferences for a mock user "tourist123". If a simulated connection interruption occurs, an alert will prompt you to retry or continue with an empty form.
*   **Edit Fields:**
    *   Type a `Destination` (e.g., "Tokyo").
    *   Enter `Min Price` and `Max Price` (e.g., "50.0" and "1000.0").
    *   Check or uncheck the `Categories` (e.g., "Culture", "Food").
*   **Save Changes:** Click the "Save Preferences" button.
    *   The application will ask for confirmation.
    *   If saved successfully, an "Information" alert will appear, and the status message will update.
    *   If a simulated connection interruption occurs during save, an alert will offer options to retry, revert, or continue editing.
*   **Cancel Operation:** Click the "Cancel" button.
    *   A confirmation dialog will appear.
    *   If you confirm, the window will close. If you cancel the dialog, the window remains open.
*   **Error Messages:** Watch the `statusLabel` at the bottom for feedback on validation errors or system issues.

This user manual should guide you through getting the "Edit Personal Search Preferences" application up and running, and how to use its core functionalities.