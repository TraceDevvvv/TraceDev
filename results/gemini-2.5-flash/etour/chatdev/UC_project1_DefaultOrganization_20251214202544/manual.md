# User Manual: Elimina Bene Culturale

This manual provides instructions on how to set up, run, and interact with the "Elimina Bene Culturale" application. This software simulates a system for agency operators to manage and delete cultural objects.

## 🤔 What is this?

The "Elimina Bene Culturale" application is a simple Java-based desktop application (using Swing) that demonstrates a use case for deleting cultural objects from a system. It provides a graphical user interface (GUI) for an "Agency Operator" to:

1.  **Log in** to the system.
2.  **View a list** of available cultural objects (`Beni Culturali`).
3.  **Select** a specific cultural object.
4.  **Confirm** the deletion of the selected object.
5.  **Delete** the chosen cultural object from the system.
6.  Receive **notifications** regarding the success or failure of the operation, including simulated connection interruptions.

The system incorporates basic error handling, user confirmation prompts, and UI blocking during operations to prevent multiple submissions.

## 📖 Main Functions

The application comprises the following key functionalities:

*   **Login (LoginGUI.java)**: Simulates an operator login. Users must enter predefined credentials (`agency_operator`/`password`) to access the main application. This fulfills the "Operator conditions: The agency has logged" entry condition.
*   **Cultural Object Management (BeniCulturaleService.java)**: A mock service layer that manages a list of `BeniCulturale` objects. It simulates data retrieval and deletion operations, including artificial network delays and potential connection interruptions.
*   **Cultural Object Display and Deletion (EliminaBeneCulturaleGUI.java)**: The main application window where the operator views the list of cultural objects. This GUI allows the user to select an item and trigger its deletion with a confirmation step. It displays status messages to the user.
*   **Data Model (BeniCulturale.java)**: Represents a cultural object with an ID, name, and description.
*   **Custom Exception (ConnectionInterruptionException.java)**: Used to simulate network connectivity issues, adhering to the "Interruption of connection to the server ETOUR" exit condition.

## 💻 Environment Dependencies

To compile and run this Java application, you will need:

*   **Java Development Kit (JDK)**: Version 8 or higher is recommended. You can download it from Oracle or use an OpenJDK distribution like Adoptium (Eclipse Temurin).

    *   **Verification**: Open your terminal or command prompt and type:
        ```bash
        java -version
        javac -version
        ```
        You should see output indicating your installed Java version.

*   **No external libraries are required.** All necessary components are part of the Java Standard Edition (SE).

## 🚀 How to Install and Run

Follow these steps to get the application running on your system.

### 1. Save the Code Files

Create a directory for the project (e.g., `EliminaBeneCulturale`). Save each provided Java code snippet into its respective `.java` file within this directory:

*   `BeniCulturale.java`
*   `ConnectionInterruptionException.java`
*   `BeniCulturaleService.java`
*   `EliminaBeneCulturaleGUI.java`
*   `Main.java`
*   `LoginGUI.java`

Ensure all files are in the same directory.

### 2. Compile the Java Files

Open your terminal or command prompt, navigate to the directory where you saved the files, and compile them using the Java compiler (`javac`):

```bash
cd path/to/EliminaBeneCulturale
javac *.java
```

If the compilation is successful, you will see `.class` files generated for each `.java` file in the same directory. If there are errors, ensure your JDK is correctly installed and configured, and that there are no typos in the saved code.

### 3. Run the Application

After successful compilation, run the application by executing the `Main` class:

```bash
java Main
```

## ▶️ How to Use/Play

Once the application starts, you will first encounter the **Login Window**.

### Step 1: Login

1.  **Enter Credentials**: In the "Login Operatore Agenzia" window, you will see fields for "Username" and "Password".
    *   **Username**: Type `agency_operator`
    *   **Password**: Type `password`
    *(These fields are pre-filled for convenience in this demonstration).*
2.  **Click "Login"**: Click the "Login" button.
3.  **Successful Login**: If the credentials are correct, the login window will close, and the main "Elimina Bene Culturale" application window will appear. The status label will briefly show "Login riuscito!".
4.  **Failed Login**: If incorrect credentials are entered, the status label will turn red and display "Credenziali non valide. Riprova." The password field will be cleared.

### Step 2: View Cultural Objects

1.  Upon successful login, the main `EliminaBeneCulturaleGUI` window will open.
2.  The application will automatically attempt to load the list of cultural objects. You will see "Caricamento beni culturali..." in the status bar at the top.
3.  After a short simulated delay (1 second), the `Beni Culturali Disponibili` list will populate with sample items like "Colosseo", "Fontana di Trevi", etc.
4.  The status bar will update to "Beni culturali caricati. Selezionare un elemento per eliminare."

### Step 3: Select a Cultural Object for Deletion

1.  **Click on an item** in the `Beni Culturali Disponibili` list. For example, click on "ID: BC001 - Name: Colosseo".
2.  Once an item is selected, the "Elimina Bene Culturale Selezionato" button at the bottom will become active.

### Step 4: Delete the Selected Object

1.  **Click the "Elimina Bene Culturale Selezionato" button.**
2.  A **confirmation dialog** will appear, asking "Sei sicuro di voler eliminare 'Colosseo' (ID: BC001)?". This serves as Step 2 & 3 (Asks/Confirms confirmation) from the use case.
3.  **Confirm Deletion**:
    *   Click **"Sì"**: The deletion process will begin. The status bar will show "Eliminazione di 'Colosseo' in corso..." and input controls will be temporarily blocked.
        *   If deletion is successful, the item will be removed from the list, and the status bar will display "Bene culturale 'Colosseo' eliminato con successo." (Exit condition: successful elimination).
        *   If a simulated connection interruption occurs during deletion, an error message like "Fallito l'eliminazione: Failed to delete cultural object BC001: Connection to ETOUR server interrupted." will appear in red. (Exit condition: Interruption of connection).
    *   Click **"No"**: The deletion operation will be cancelled. The status bar will update to "Operazione di eliminazione annullata dall'operatore." (Exit condition: Operator cancels operation).

### Step 5: Continue or Exit

*   You can continue to select and delete other cultural objects.
*   If no items remain in the list, the delete button will be disabled.
*   To exit the application, simply close the main "Elimina Bene Culturale" window.

**Note on Quality Requirements:** The system temporarily blocks input controls (disables the delete button and list selection) on receipt of confirmation of cancellation (after clicking "Sì" in the confirmation dialog) until the deletion operation ends, to avoid multiple submissions.