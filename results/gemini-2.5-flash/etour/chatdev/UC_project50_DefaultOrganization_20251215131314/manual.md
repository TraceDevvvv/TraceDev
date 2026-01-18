```markdown
# ETOUR: Visited Sites with Feedback

View the list of sites for which you have issued feedback as a tourist.

## 🤔 What is this?

This software application, named "ETOUR: Visited Sites with Feedback," provides a simple graphical user interface (GUI) for tourists to view a curated list of travel sites. The core functionality is to display all sites for which the authenticated user (tourist) has previously submitted feedback.

It simulates a real-world scenario where a tourist application communicates with a backend server (referred to as the ETOUR server) to retrieve personalized data. The application includes features to handle user input, display dynamic lists, and gracefully manage potential network interruptions or server errors that might occur during data retrieval.

**Main Functions:**
*   **Tourist Authentication (Simulated):** The application assumes a tourist is already successfully authenticated. You can input different "Tourist IDs" (e.g., `user1`, `user2`, `user3`) to simulate different authenticated users and their associated feedback data.
*   **Display Feedback Sites:** Upon selecting the feature, the system attempts to retrieve and display a list of sites for which the specified tourist has provided feedback.
*   **Simulated Backend Communication:** It mimics fetching data from a remote service, including simulated network delays.
*   **Error Handling for Connection Interruptions:** The system is designed to detect and inform the user if the connection to the ETOUR server is interrupted during the data retrieval process (simulated with a 20% chance of failure).
*   **Interactive UI:** A user-friendly interface allows for easy interaction and provides real-time status updates on data loading and error conditions.

## 📖 Prerequisites / Environment Setup

To run this Java application, you need to have a Java Development Kit (JDK) installed on your system.

1.  **Install Java Development Kit (JDK):**
    *   This application requires **Java 8 or a newer version** of the JDK.
    *   You can download the latest JDK from the official Oracle website or adoptium.net (for OpenJDK builds). Follow the installation instructions for your operating system.
    *   After installation, ensure that `java` and `javac` commands are accessible from your terminal or command prompt. You can verify this by opening a terminal and typing:
        ```bash
        java -version
        javac -version
        ```
        You should see output indicating the installed Java version.

2.  **Text Editor or Integrated Development Environment (IDE):**
    *   Any text editor (like VS Code, Sublime Text, Notepad++) or a Java IDE (like IntelliJ IDEA, Eclipse, NetBeans) can be used to work with the source code.

## 🚀 How to Use / Play

Follow these steps to compile and run the "ETOUR: Visited Sites with Feedback" application:

1.  **Create Project Structure:**
    *   First, create a root directory for your project, e.g., `ETourApp`.
    *   Inside `ETourApp`, create the package structure `com/chatdev/etour`. This means creating a folder named `com`, inside it a folder named `chatdev`, and inside that a folder named `etour`.

2.  **Save the Source Code Files:**
    *   Save the provided Java code for `Site.java`, `FeedbackService.java`, and `SiteDisplayApp.java` into the `ETourApp/com/chatdev/etour/` directory.

    Your directory structure should look like this:
    ```
    ETourApp/
    └── com/
        └── chatdev/
            └── etour/
                ├── Site.java
                ├── FeedbackService.java
                └── SiteDisplayApp.java
    ```

3.  **Compile the Java Code:**
    *   Open your terminal or command prompt.
    *   Navigate to the root directory of your project (e.g., `cd ETourApp`).
    *   Compile all Java source files using the `javac` command. Ensure you are in the `ETourApp` directory when running this command.
        ```bash
        javac com/chatdev/etour/*.java
        ```
    *   If compilation is successful, `.class` files will be generated in the respective `com/chatdev/etour` directory for each Java file.

4.  **Run the Application:**
    *   From the `ETourApp` directory in your terminal, run the main application class `SiteDisplayApp` using the `java` command:
        ```bash
        java com.chatdev.etour.SiteDisplayApp
        ```

5.  **Interact with the GUI:**
    *   A new window titled "ETOUR: Visited Sites with Feedback" will appear.
    *   **Tourist ID:** You will see an input field pre-filled with `user1`. You can change this to `user2`, `user3`, or any other string.
        *   **`user1`:** Displays "Eiffel Tower", "Louvre Museum", "Colosseum".
        *   **`user2`:** Displays "Statue of Liberty", "Times Square", "Acropolis of Athens".
        *   **`user3`:** Displays "Big Ben", "Buckingham Palace", "Sagrada Familia".
        *   **Any other ID:** Will likely show "No feedback sites found..."
    *   **Display My Feedback Sites Button:** Click this button to fetch the sites.
    *   **Status Label:** Observe the status label at the bottom. It will show "Loading sites...", then a success message, or an error message if a connection interruption occurs.
    *   **Site List:** The center panel will populate with the names of the sites for which feedback was found for the entered Tourist ID.
    *   **Simulated Errors:** The `FeedbackService` has a 20% chance to simulate a "Connection to ETOUR server interrupted" error. If this happens, try clicking the button again.

Enjoy exploring the simulated ETOUR application!
```