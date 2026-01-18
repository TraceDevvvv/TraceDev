```
# ATTIVACONVENZIONE - Convention Activation System

## Introduction

The **ATTIVACONVENZIONE - Convention Activation System** is a Java-based desktop application designed to streamline the process of activating conventions requested by designated refreshment points. This system serves as a graphical user interface (GUI) for an Agency Operator to manage and process these activation requests efficiently.

**Main Functions:**

*   **Load Convention Requests:** Operators can input a Refreshment Point ID to load details of a convention request associated with that point.
*   **Display Convention Data:** Once loaded, the system presents comprehensive information about the convention, including its ID, name, associated refreshment point, current activation status (active/inactive), and overall status (e.g., PENDING_ACTIVATION, ACTIVE, INACTIVE).
*   **Operator Decision Support:** The system allows the operator to review the displayed convention data to decide whether to proceed with activation.
*   **Confirmation Mechanism:** Before final activation, the system prompts the operator for explicit confirmation to prevent accidental activations.
*   **Processing Activation:** Upon confirmation, the system simulates the processing of the activation request, including potential interactions with external systems like ETOUR.
*   **Status Notification:** The system provides clear notifications regarding the success or failure of the activation process. It also informs the operator if an interruption to the ETOUR server connection occurs during processing.
*   **Error Handling:** It alerts the user to issues such as invalid Refreshment Point IDs, already active conventions, or connection problems during activation.

## Environment Dependencies

This project is a standard Java application utilizing the Swing framework for its graphical user interface. To compile and run this software, you will need a Java Development Kit (JDK) installed on your system.

*   **Java Development Kit (JDK):**
    *   **Minimum Version:** JDK 8 or later is required.
    *   **Installation:** You can download the latest JDK from Oracle's official website or use open-source alternatives like OpenJDK from adoptium.net (Eclipse Adoptium/Temurin). Follow the installation instructions for your specific operating system.
    *   **Verification:** After installation, you can verify your JDK setup by opening a terminal or command prompt and running:
        ```bash
        java -version
        javac -version
        ```
        These commands should display the installed Java and Java compiler versions, respectively.

**Note:** This project uses only standard Java libraries bundled with the JDK. Therefore, no additional external dependencies (like those found in a `requirements.txt` for Python) are needed.

## How to Compile and Run

Follow these steps to compile and run the ATTIVACONVENZIONE application:

### Step 1: Save the Source Files

Ensure all provided Java source files (`Convention.java`, `ETOURConnectionException.java`, `ConventionService.java`, `ConventionActivationGUI.java`, `Main.java`) are saved in the same directory (e.g., `src`).

### Step 2: Compile the Java Files

Open a terminal or command prompt, navigate to the directory where you saved the `.java` files, and compile them using the Java compiler (`javac`).

```bash
# Navigate to your source directory (if you've saved them inside a 'src' folder)
cd path/to/your/src/folder

# Compile all Java files
javac *.java
```

If the compilation is successful, `.class` files will be generated for each Java source file in the same directory.

### Step 3: Run the Application

Execute the main application class (`Main.java`) using the Java Virtual Machine (`java`).

```bash
# From the same directory where class files are located
java Main
```

### Step 4: Interact with the GUI

Once the application starts, a window titled "ATTIVACONVENZIONE - Convention Activation" will appear.

Here's how to use it:

1.  **Enter Refreshment Point ID:**
    *   Locate the "Refreshment Point ID:" text field at the top of the window.
    *   Enter one of the sample Refreshment Point IDs:
        *   `RP001`: Represents a convention **pending activation**.
        *   `RP002`: Represents a convention that is **already active**.
        *   `RP003`: Represents an **inactive** convention.
        *   Any other ID (e.g., `RP004`): Will show that no pending request is found.

2.  **Load Convention Data (Flow of events - Step 2):**
    *   Click the "Load Convention Data" button.
    *   The "Convention Details" section will populate with the information of the loaded convention (or show "N/A" if nothing is found).
    *   The "Messages" area at the bottom will display status updates.

3.  **Review and Decide (Flow of events - Step 3):**
    *   Carefully review the displayed details, especially the "Status" label.
    *   If `RP001` was loaded, you will see "Status: PENDING_ACTIVATION", and the "Activate Convention" button will become enabled.
    *   If `RP002` or `RP003` were loaded, the "Activate Convention" button will remain disabled, as these conventions are not eligible for activation.

4.  **Confirm Activation (Flow of events - Step 4 & 5):**
    *   If the "Activate Convention" button is enabled (i.e., you loaded `RP001`), click it.
    *   A confirmation dialog will pop up asking: "Do you really want to activate convention '...'?".
    *   Click "Yes" to proceed with activation (Flow of events - Step 5). Click "No" to cancel.

5.  **Process Request and Observe Results (Flow of events - Step 6 & Exit Conditions):**
    *   If you confirmed activation, the system will start "Processing the request...". This simulates backend work.
    *   **Successful Activation:** If successful, the "Messages" area will show "Convention '...' successfully activated!" and then "Notification: Convention '...' has been officially activated." The "Is Active" status will change to `true`, and "Status" to `ACTIVE`.
    *   **ETOUR Connection Interruption:** There is a **~20% chance** that the simulated ETOUR server connection will fail during activation. If this happens, an "Error" pop-up will appear, and the "Messages" area will display an error message like "Activation failed: Interruption of the connection to the server ETOUR.". This simulates the "Interruption of the connection to the server ETOUR" exit condition.
    *   The system will notify the activation if successful (Exit condition: "The system shall notify the activation of the convention.").

6.  **Reset:**
    *   Click the "Reset" button to clear all fields and labels, allowing you to start a fresh operation.

Feel free to experiment with different Refreshment Point IDs and observe how the system responds to various scenarios, including retrying activation after a simulated ETOUR error (you would need to reload the convention after resetting if it failed).