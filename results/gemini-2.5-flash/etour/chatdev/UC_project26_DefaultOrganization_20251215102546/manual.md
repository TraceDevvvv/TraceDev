```markdown
# ChatDev - Modifica Commento (Modify Feedback Comment)

This application is a part of ChatDev's suite, designed to assist Agency Operators in managing feedback. Specifically, this software implements the "MODIFICACOMMENTO" use case, allowing operators to easily edit comments associated with feedback provided for various sites.

## 🤔 What is this?

The "Modifica Commento" application provides a user-friendly graphical interface (GUI) for Agency Operators to perform the following tasks:

*   **Browse Sites**: View a list of available sites within the system (simulating the `RicercaSito` use case).
*   **Select a Site**: Choose a specific site for which they want to manage feedback.
*   **View Feedback**: Display all feedback entries associated with the selected site.
*   **Select Feedback**: Pick a particular feedback entry whose comment needs editing.
*   **Edit Comment**: Open a dedicated form to modify the text of the selected feedback comment.
*   **Validate Input**: Ensure that the new comment is valid (e.g., not empty).
*   **Confirm Changes**: Request confirmation from the operator before saving any modifications.
*   **Save Changes**: Persistently save the updated comment in the system (simulated in-memory storage for this example).
*   **Cancel Operations**: Allow the operator to cancel the workflow at various stages, preventing unintended changes.
*   **Handle Connection Interruptions**: Simulate and gracefully handle scenarios where the connection to the backend system is lost.

This tool streamlines the process of maintaining up-to-date and accurate feedback records, enhancing the overall quality of information managed by the agency.

## 💻 Environment Dependencies

To compile and run the "Modifica Commento" application, you will need a Java Development Kit (JDK) installed on your system.

*   **Java Development Kit (JDK)**: Version 8 or higher (`java-runtime-environment>=8`).

### Installation Guide for JDK

1.  **Download JDK**:
    *   Visit the official Oracle JDK download page or consider open-source alternatives like Adoptium (Eclipse Temurin) for multi-platform support.
    *   Choose the appropriate JDK version (e.g., JDK 8, JDK 11, JDK 17, etc.) for your operating system (Windows, macOS, Linux).

2.  **Install JDK**:
    *   **Windows**: Run the `.exe` installer and follow the on-screen prompts.
    *   **macOS**: Run the `.dmg` installer.
    *   **Linux**: Use your distribution's package manager (e.g., `sudo apt install openjdk-11-jdk` for Ubuntu/Debian) or extract the downloaded tarball.

3.  **Set Environment Variables (if necessary)**:
    *   **JAVA\_HOME**: Set an environment variable named `JAVA_HOME` pointing to your JDK installation directory (e.g., `C:\Program Files\Java\jdk-11.0.12` on Windows, `/Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home` on macOS, or `/usr/lib/jvm/default-java` on Linux).
    *   **PATH**: Add the `bin` directory of your JDK installation to your system's `PATH` environment variable (e.g., `%JAVA_HOME%\bin` on Windows, or `$JAVA_HOME/bin` on macOS/Linux).

4.  **Verify Installation**:
    *   Open a new command prompt or terminal and run:
        ```bash
        java -version
        javac -version
        ```
    *   You should see output indicating the installed Java and Javac compiler versions.

## ▶️ How to Use/Play

Follow these steps to compile and run the "Modifica Commento" application:

### 1. Project Structure

The provided Java code consists of several files organized into packages. To ensure proper compilation, create the following directory structure:

```
.
└── src
    └── com
        └── chatdev
            ├── gui
            │   ├── components
            │   │   ├── CommentEditForm.java
            │   │   ├── FeedbackSelectionFrame.java
            │   │   └── SiteSelectionFrame.java
            │   ├── utils
            │   │   └── DialogUtils.java
            │   └── ModificaCommentoGUI.java
            ├── models
            │   ├── Feedback.java
            │   └── Site.java
            └── system
                ├── AgencyOperatorSystem.java
                └── ConnectionFailedException.java
```

Place each `.java` file into its corresponding directory as shown above.

### 2. Compile the Source Code

1.  Open your command prompt or terminal.
2.  Navigate to the `src` directory:
    ```bash
    cd path/to/your/project/src
    ```
3.  Compile all Java files. The `-d .` option tells the compiler to place the compiled `.class` files into the current directory, maintaining the package structure.
    ```bash
    javac -d . com/chatdev/gui/ModificaCommentoGUI.java \
            com/chatdev/gui/utils/DialogUtils.java \
            com/chatdev/gui/components/CommentEditForm.java \
            com/chatdev/gui/components/FeedbackSelectionFrame.java \
            com/chatdev/gui/components/SiteSelectionFrame.java \
            com/chatdev/models/Feedback.java \
            com/chatdev/models/Site.java \
            com/chatdev/system/AgencyOperatorSystem.java \
            com/chatdev/system/ConnectionFailedException.java
    ```
    *Alternatively, you can compile all files within the `com` directory by simply running `javac -d . com/chatdev/**/*.java` if your shell supports globbing and you navigate to the parent of `com` (e.g., the directory containing `com`). Or run `javac -d . $(find . -name "*.java")` which is more robust.*

### 3. Run the Application

After successful compilation, you can run the main application class. Make sure your current directory in the terminal is `src` (the one containing the `com` folder, or wherever your output `.class` files are rooted).

```bash
java com.chatdev.gui.ModificaCommentoGUI
```

### 4. Interacting with the Application (Flow of Events)

Once the application starts, you will see a main window titled "ChatDev - Modify Feedback Comment" with a single button.

1.  **Start the Process**:
    *   Click the "Start Modifying Feedback Comment" button.
    *   The main window will temporarily hide, and a "Select a Site for Comment Editing" window will appear.

2.  **Select a Site**:
    *   The window displays a list of simulated sites (e.g., "Eiffel Tower Tour," "Rome Colosseum Visit").
    *   Select one of the sites from the list.
    *   Click the "Select Site" button.
    *   A confirmation message will inform you of the selected site.

3.  **Select Feedback**:
    *   A new window, "Select Feedback for Site: [Selected Site Name]", will appear, listing feedback comments associated with the chosen site.
    *   Select a specific feedback entry from the list.
    *   Click the "Select Feedback to Edit" button.
    *   A confirmation message will inform you of the selected feedback.
    *   **Note**: If a site has no feedback, a message will be displayed, and you will be returned to the main menu.

4.  **Edit the Comment**:
    *   The "Edit Comment for Feedback ID: [Feedback ID]" form will appear.
    *   The original comment is displayed for context.
    *   Locate the "Edit Comment" text area, which is pre-filled with the current comment.
    *   Modify the text as desired.

5.  **Save Changes**:
    *   Click the "Save Comment" button.
    *   **Validation**: If the comment text area is left empty or contains only whitespace, an "Invalid Comment" error dialog will appear, prompting you to correct the input.
    *   **Confirmation**: If the comment is valid, a "Confirm Change" dialog will ask, "Are you sure you want to save this change?".
        *   Click "Yes" to confirm and save.
        *   Click "No" to return to the edit form without saving.
    *   Upon successful save, an "Comment Updated" message will appear, and all windows will close, returning you to the main "ChatDev - Modify Feedback Comment" application window.

6.  **Cancel Operations**:
    *   At any stage (Site Selection, Feedback Selection, Comment Edit Form), you can click the "Cancel" button.
    *   This will close the current sub-window, display an "Operation Cancelled" message, and return you to the main application window.

### 5. Simulate Connection Errors (Optional)

To test the `Interruption of the connection to the server` exit condition:

1.  Before running, open the `AgencyOperatorSystem.java` file.
2.  Locate the line:
    ```java
    // public static boolean simulateConnectionError = true; // Uncomment to test connection error
    ```
3.  Uncomment this line:
    ```java
    public static boolean simulateConnectionError = true; // Uncomment to test connection error
    ```
4.  Save the file, recompile `AgencyOperatorSystem.java` and `ModificaCommentoGUI.java`, and then run `ModificaCommentoGUI`.
5.  Now, when you attempt to interact with the system (e.g., fetching sites, feedback, or saving a comment), you will encounter "Connection Error" messages, demonstrating the application's error handling for network issues.

**Note**: The system operates with in-memory data, meaning any changes made will be lost when the application is closed. This simulation is for demonstrating the use case flow and functionality rather than persistent data storage.
```