```
# User Manual: Modifica Dati Account Turista

## 🤔 What is this application?

This application, named "Modifica Dati Account Turista" (Modify Tourist Account Data), is a simple desktop tool developed for an **Agency Operator**. Its primary function is to allow operators to easily view and update the personal information of tourist accounts within the system.

The application simulates the core workflow of modifying user data: selecting a user, loading their details into an editable form, validating changes, confirming updates, and persistently storing the new information. It also includes error handling for invalid data entry and simulated external system connection issues (e.g., to an "ETOUR" server).

## 💡 Main Functions

The application provides the following key functionalities for an Agency Operator:

1.  **Tourist Selection**: Operators can select an existing tourist account from a list. This simulates retrieving a list of tourists from a previous "RicercaTurista" (Search Tourist) use case.
2.  **Data Loading**: Upon selection, the system automatically loads and displays the selected tourist's current data (ID, Name, Surname, Email, Phone) into a user-friendly form, allowing for review.
3.  **Data Modification**: Operators can edit the Name, Surname, Email, and Phone fields directly within the form. The Tourist ID is displayed but remains non-editable to maintain data integrity.
4.  **Data Validation**: Before saving, the system performs validation checks on the entered data. This includes ensuring required fields are not empty and verifying the basic format of email addresses and phone numbers.
5.  **Confirmation of Changes**: After successful validation, the system prompts the operator for a final confirmation before applying the changes.
6.  **Data Persistence**: If confirmed, the modified data for the selected tourist account is stored (simulated in an in-memory database).
7.  **Error Handling**:
    *   **Invalid/Insufficient Data**: If the operator enters invalid or incomplete information, the system activates an "Errored" use case, displaying a clear error message.
    *   **ETOUR Connection Interruption**: The application includes a checkbox to simulate a connection loss to a hypothetical "ETOUR" server. If the connection is "interrupted" during an update attempt, the system reports this error, preventing data inconsistencies.

## 🛠️ Environment Dependencies

To run this Java application, you will need:

*   **Java Development Kit (JDK)**: Version 8 or higher is required. You can download it from Oracle or use an open-source distribution like OpenJDK.
*   **A Java Integrated Development Environment (IDE)**: While not strictly necessary, using an IDE like IntelliJ IDEA, Eclipse, or Visual Studio Code (with Java extensions) is highly recommended for easy compilation and execution.
*   **Standard Java Libraries**: The application uses only standard Java libraries (`java.awt`, `javax.swing`, `java.util.regex`), which are included with the JDK. No additional third-party dependencies (like Maven or Gradle packages) are required.

## 🚀 How to Install and Run

### Step 1: Set up Java Development Kit (JDK)

1.  **Download and Install JDK**: If you don't have Java 8 or newer installed, download and install the appropriate JDK for your operating system from Oracle's website or an OpenJDK provider (e.g., Adoptium).
2.  **Set JAVA_HOME (Optional but Recommended)**: Configure the `JAVA_HOME` environment variable to point to your JDK installation directory and add the JDK's `bin` directory to your system's `PATH` variable. This allows you to run Java commands from any directory.

### Step 2: Obtain the Source Code

1.  Create a dedicated folder for the project (e.g., `ModificaTuristaApp`).
2.  Save all the provided Java source files (`Turista.java`, `TuristaDao.java`, `ErroredUseCase.java`, `ModificaDatiAccountTuristaForm.java`, `Main.java`) into this folder.

### Step 3: Compile and Run the Application

#### Option A: Using a Java IDE (Recommended for convenience)

1.  **Open your IDE**: Launch IntelliJ IDEA, Eclipse, VS Code, or your preferred Java IDE.
2.  **Create a New Project**:
    *   **IntelliJ IDEA**: Select "New Project" -> "Java" -> "From existing sources" or "Empty Project". Point it to the `ModificaTuristaApp` folder. Ensure the correct JDK is selected.
    *   **Eclipse**: Select "File" -> "New" -> "Java Project". Specify a project name and point to the `ModificaTuristaApp` folder as the source directory.
    *   **VS Code**: Open the `ModificaTuristaApp` folder. VS Code will usually detect the Java files and prompt you to install necessary extensions if not already present.
3.  **Build the Project**: Your IDE should automatically compile the `.java` files. If not, look for a "Build" or "Compile" option in your IDE's menu.
4.  **Run the Application**: Locate the `Main.java` file in your IDE's project explorer. Right-click on it and select "Run 'Main.main()'" or a similar option.

#### Option B: Using the Command Line

1.  **Open Terminal/Command Prompt**: Navigate to the `ModificaTuristaApp` directory where you saved the Java files.

    ```bash
    cd path/to/ModificaTuristaApp
    ```

2.  **Compile the Java Files**: Use the `javac` command to compile all `.java` files.

    ```bash
    javac *.java
    ```

    If compilation is successful, `.class` files will be generated in the same directory.

3.  **Run the Application**: Use the `java` command to run the `Main` class.

    ```bash
    java Main
    ```

    The graphical user interface (GUI) of the "Modifica Dati Account Turista" application should now appear.

## 🎮 How to Use the Application

Once the application is running, follow these steps:

1.  **Select a Tourist**:
    *   In the "Seleziona Turista" section at the top, you will see a dropdown menu labeled "Turista:".
    *   This menu is populated with sample tourist accounts (e.g., "Mario Rossi (ID: T001)").
    *   Select the tourist whose data you wish to modify from this list.

2.  **Load Tourist Data**:
    *   After selecting a tourist, click the "Carica Dati" (Load Data) button next to the dropdown menu.
    *   The form fields below ("ID Turista", "Nome", "Cognome", "Email", "Telefono") will be populated with the details of the selected tourist. Note that "ID Turista" is read-only.

3.  **Modify Data**:
    *   Edit the "Nome", "Cognome", "Email", and "Telefono" fields as desired.

4.  **Simulate ETOUR Connection (Optional)**:
    *   On the top right, there's a "ETOUR Connesso" checkbox. By default, it's checked (simulating a connected state).
    *   **Uncheck** this box to simulate an interruption in the connection to the external ETOUR server. This will demonstrate the error handling for connection loss.

5.  **Save Changes**:
    *   Once you have made your modifications, click the "Salva Modifiche" (Save Changes) button at the bottom of the window.

6.  **Validation and Confirmation**:
    *   **If data is invalid/insufficient**: An "Errore di Validazione" (Validation Error) dialog will appear, indicating what corrections are needed (e.g., missing name, invalid email format). You will need to close the error dialog and correct the fields.
    *   **If data is valid**: A "Conferma Modifica" (Confirm Modification) dialog will ask you to confirm if you want to save the changes. Click "Sì" (Yes) to proceed or "No" to cancel.

7.  **Operation Result**:
    *   **Successful Update**: If the data is valid, confirmed, and the ETOUR connection is active, an "Operazione Completata" (Operation Completed) message will appear, confirming the update. The form will then clear.
    *   **ETOUR Connection Error**: If the ETOUR connection was simulated as interrupted (checkbox unchecked), an "Errore di Validazione" dialog will appear with the message "Connessione al server ETOUR interrotta."

Congratulations! You have successfully used the "Modifica Dati Account Turista" application.
```