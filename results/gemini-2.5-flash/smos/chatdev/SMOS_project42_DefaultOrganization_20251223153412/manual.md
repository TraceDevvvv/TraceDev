# Eliminate Justification System User Manual

## 🚀 Introduction

The Eliminate Justification System is a Java Swing-based application designed to demonstrate a specific use case: an administrator eliminating (deleting) a justification from a simulated system. This application provides a graphical user interface (GUI) to interact with the system and illustrates various scenarios prescribed by the use case, including successful deletion, administrator-initiated interruption, and handling external server connection issues (simulated SMOS server).

**Key Functions:**

*   **Administrator Authentication:** Simulate logging in and out as an administrator to control access to deletion capabilities.
*   **Justification Management:** Provides methods for viewing specific justification details.
*   **Justification Elimination (Deletion):** Allows an authorized and active administrator to delete a viewed justification.
*   **External System Interaction Simulation:** Integrates a simulated SMOS (System Management and Operating System) server connection to demonstrate how external system failures can affect the deletion process.
*   **Robust Error Handling:** Demonstrates user notifications and system reactions to various events, including successful operations, user cancellations, and technical errors.

## 🛠️ Environment Dependencies Installation

To compile and run this Java application, you will need the following installed on your system:

### 1. Java Development Kit (JDK) 8 or higher

The application is written in Java and uses standard Swing components, which are part of the Java SE platform.

*   **Download & Install:** Obtain the appropriate **JDK** (not just JRE) for your operating system from the official [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/) or [OpenAdoptium](https://adoptium.net/temurin/releases/) page.
*   **Verify Installation:** After installation, open your terminal or command prompt and ensure that `java` and `javac` commands are recognized:
    ```bash
    java -version
    javac -version
    ```
    You should see output indicating your installed Java version.

### 2. Integrated Development Environment (IDE) - *Recommended*

While you can compile and run the application using command-line tools, an IDE significantly simplifies the process of project setup, compilation, and execution. Popular cho include:

*   **IntelliJ IDEA Community Edition:** Free, powerful, and widely used.
*   **Eclipse IDE for Java Developers:** Another robust open-source option.
*   **Visual Studio Code with Java Extension Pack:** Lightweight and highly customizable.

## 📂 Project Structure

The provided Java code is organized into a package hierarchy. To ensure the application compiles and runs correctly, you must replicate this structure within your project directory.

Create a root directory for your project (e.g., `eliminate-justification-system`). Inside this, create a `src` folder, and then build the following package structure:

```
eliminate-justification-system/
├── src/
│   ├── com/
│   │   └── chatdev/
│   │       └── eliminatejustification/
│   │           ├── app/
│   │           │   └── EliminateJustificationApp.java  (Main GUI application)
│   │           ├── models/
│   │           │   └── Justification.java            (Data model for Justification)
│   │           ├── serv/
│   │           │   ├── AdminAuthService.java         (Admin authentication logic)
│   │           │   ├── JustificationService.java     (Justification business logic)
│   │           │   └── SMOSConnectionService.java    (SMOS server connection simulation)
│   │           └── exceptions/
│   │               └── SMOSConnectionException.java  (Custom exception for SMOS errors)
```

Place each `.java` file into its corresponding directory as shown above.

## 🎮 How to Use / Play the Application

Follow these instructions to set up, compile, run, and interact with the Eliminate Justification System.

### Step 1: Save the Code

1.  Create a directory for your project (e.g., `eliminate-justification-system`).
2.  Inside this directory, create the `src/com/chatdev/eliminatejustification/` package structure as detailed above.
3.  Copy and paste the content of each `.java` file into its correctly named file within the respective package folder.

### Step 2: Compile the Code

**Using an IDE (Recommended):**

1.  Open your chosen IDE (IntelliJ IDEA, Eclipse, VS Code).
2.  Create a new Java Project. Most IDEs will ask for a project SDK (your installed JDK).
3.  Point the IDE to your `eliminate-justification-system` directory and specify the `src` folder as the source root.
4.  The IDE should automatically detect and compile the Java files. If not, look for a "Build Project" or "Rebuild Project" option in your IDE's menu.

**Using Command Line:**

1.  Open your terminal or command prompt.
2.  Navigate to the `eliminate-justification-system` directory (the root of your project).
    ```bash
    cd eliminate-justification-system
    ```
3.  Create an output directory for compiled class files (e.g., `bin`):
    ```bash
    mkdir -p bin
    ```
4.  Compile all Java files. This command assumes you are in the `eliminate-justification-system` directory:
    ```bash
    javac -d bin src/com/chatdev/eliminatejustification/models/*.java \
          src/com/chatdev/eliminatejustification/exceptions/*.java \
          src/com/chatdev/eliminatejustification/serv/*.java \
          src/com/chatdev/eliminatejustification/app/*.java
    ```

### Step 3: Run the Application

**Using an IDE (Recommended):**

1.  In your IDE's project explorer, locate `EliminateJustificationApp.java` within the `com.chatdev.eliminatejustification.app` package.
2.  Right-click on `EliminateJustificationApp.java`.
3.  Select "Run 'EliminateJustificationApp.main()'" (or a similar option, depending on your IDE).

**Using Command Line:**

1.  Open your terminal or command prompt.
2.  Navigate to the `eliminate-justification-system` directory.
3.  Run the application, specifying the `bin` directory (where the compiled classes are) in the classpath:
    ```bash
    java -cp bin com.chatdev.eliminatejustification.app.EliminateJustificationApp
    ```
    A GUI window titled "Eliminate Justification System" will appear.

### Step 4: Interact with the Application (Simulating the Use Case)

The application window consists of a "Status" label at the top, a "Justification Details" text area in the center, and a control panel with buttons on the right.

1.  **Initial State (Precondition: Not Logged In)**
    *   **Status Label:** Will display "Status: Please log in as administrator."
    *   **Buttons:** "View Justification Details" and "Delete Justification" will be disabled.

2.  **Simulate Administrator Login (Meet Precondition 1)**
    *   Click the **"Simulate Admin Login"** button.
    *   **Effect:**
        *   The button text changes to "Simulate Admin Logout".
        *   **Status Label:** Updates to "Status: Administrator logged in successfully."
        *   **Buttons:** "View Justification Details" becomes enabled.

3.  **View Justification Details (Meet Precondition 2)**
    *   Click the enabled **"View Justification Details"** button.
    *   **Effect:**
        *   The "Justification Details" text area will populate with information for a mock justification (ID: `J1001`, Description: "Request for budget increase, project Alpha.", Status: "Pending").
        *   **Status Label:** Updates to reflect that details are being viewed.
        *   **Buttons:** "Delete Justification" becomes enabled.
        *   *Note:* If you ran `deleteJustification` successfully in a previous session without restarting the app, `J1001` might not be found. Restart the application or simulate a logout/login and then view again to refresh the mock data.

4.  **Delete Justification (Main Event Sequence & Postconditions)**

    *   **Scenario A: Successful Justification Elimination**
        1.  Ensure you are logged in and viewing `J1001` (from steps 2 and 3).
        2.  Click the **"Delete Justification"** button.
        3.  A confirmation dialog will appear, asking "Are you sure you want to delete Justification ID: J1001...?".
        4.  Click **"Yes"**.
        5.  **Effect (Postcondition: Justification eliminated, returns to registry screen):**
            *   A success message dialog will appear: "Justification ID: J1001 was eliminated successfully."
            *   The "Justification Details" area will clear (simulating a return to a 'registry screen').
            *   **Status Label:** Updates to "Status: Justification ID: J1001 was successfully eliminated. Returning to registry screen."
            *   **Buttons:** The "Delete Justification" button will be disabled again.
            *   If you try to "View Justification Details" again, it will report "No justification found for ID J1001." (as it was removed from the in-memory store).

    *   **Scenario B: Administrator Interrupts the Operation (Postcondition)**
        1.  Ensure you are logged in and viewing `J1001`. If `J1001` was already deleted, you might need to restart the application or simulate logout/login to reset the mock data model.
        2.  Click the **"Delete Justification"** button.
        3.  In the confirmation dialog, click **"No"**.
        4.  **Effect (Postcondition: Administrator interrupts the operation):**
            *   A message dialog "Operation Cancelled" will appear.
            *   The "Justification Details" will remain displayed.
            *   **Status Label:** Updates to "Status: Administrator interrupted the deletion operation for Justification ID: J1001."

    *   **Scenario C: Connection to the SMOS Server Interrupted (Postcondition)**
        1.  Ensure you are logged in and viewing `J1001` (from steps 2 and 3).
        2.  Click the **"Simulate SMOS Disconnect"** button.
            *   **Effect:** The button text will change to "Simulate SMOS Connect". The status label will indicate "Status: SMOS server connection simulated as DISCONNECTED."
        3.  Now, click the **"Delete Justification"** button.
        4.  In the confirmation dialog, click **"Yes"**.
        5.  **Effect (Postcondition: Connection to the SMOS server interrupted):**
            *   An error dialog will appear: "SMOS Connection Error: Failed to connect to SMOS server. Please check network connection."
            *   **Status Label:** Displays the SMOS connection error.
            *   The "Justification Details" will *not* clear, as the deletion did not complete.
        6.  To re-enable deletion, click `Simulate SMOS Connect` button.

5.  **Simulate Administrator Logout:**
    *   Click the **"Simulate Admin Logout"** button.
    *   **Effect:**
        *   The button text changes back to "Simulate Admin Login".
        *   **Status Label:** Returns to "Status: Please log in as administrator."
        *   **Buttons:** "View Justification Details" and "Delete Justification" become disabled.
        *   The "Justification Details" area is cleared.