```
# RICERCASITO - Site Search Application User Manual

## 👋 Introduction

Welcome to `RICERCASITO`, a desktop application designed to simulate searching for specific sites on a computer. This application serves as a practical example of a user interface interacting with a backend service that includes simulated network latency and potential connection interruptions, echoing real-world scenarios. It allows users to search for websites based on keywords and view the results in a clear, organized manner.

## 🚀 Main Functions

The `RICERCASITO` application provides the following key functionalities:

1.  **Site Search**: Users can input a query to search for websites. The search matches against site names, URLs, and descriptions.
2.  **Interactive User Interface**: A simple and intuitive graphical user interface (GUI) built with Java Swing for ease of use.
3.  **Simulated Network Delays**: The application mimics real-world network conditions by introducing artificial delays in search requests, demonstrating how responsive UI can be maintained using background processing.
4.  **Simulated Connection Interruptions**: It also simulates potential 'ETOUR server' connection failures, providing a mechanism to handle and display such error conditions gracefully.
5.  **Results Display**: Successful searches return a list of matching sites, each with its name, URL, and description.
6.  **Error Handling**: Clear error messages are displayed for empty queries or simulated connection failures.

## 🛠️ System Requirements and Dependencies

To run the `RICERCASITO` application, you need to have the following installed on your system:

*   **Java Development Kit (JDK) 8 or higher**: The application is written in Java and requires a compatible JDK to compile and run. You can download the latest JDK from the [Oracle website](https://www.oracle.com/java/technologies/downloads/).

No external libraries (beyond standard Java Swing and `java.util` classes) are required.

## 📦 Installation

To get the `RICERCASITO` application up and running:

1.  **Save Source Files**: Ensure all provided `.java` files (`Site.java`, `SiteSearchService.java`, `SearchPanel.java`, `ResultsPanel.java`, `SiteSearchApp.java`) are saved in the same directory on your computer.

## 🚀 How to Build and Run

Follow these steps to compile and execute the application:

### Step 1: Compile the Java Source Files

Open your command prompt or terminal, navigate to the directory where you saved the Java files, and run the Java compiler:

```bash
javac Site.java SiteSearchService.java SearchPanel.java ResultsPanel.java SiteSearchApp.java
```

Alternatively, if all `.java` files are in the same directory and you are in that directory:

```bash
javac *.java
```

This command will compile the `.java` files into `.class` bytecode files. If there are no errors, you will see no output.

### Step 2: Run the Application

Once compiled, you can run the main application using the `java` command:

```bash
java SiteSearchApp
```

A graphical window titled "RICERCASITO - Site Search Application" should appear on your screen.

## 🎮 How to Use the Application

Once the application window is open, you can interact with it as follows:

1.  **Locate the Search Input**: At the top of the window, you will see a text field initially displaying "Type your search query here...".
2.  **Enter Your Search Query**: Click on the text field and type the keywords you want to search for (e.g., "google", "java", "stack").
    *   *Tip*: The placeholder text will disappear when you start typing. If you clear the field, it will reappear.
3.  **Initiate Search**: Click the "Search Site" button located next to the search input field.
4.  **View Results**:
    *   The "Search Results" panel in the center of the window will display either a list of found sites or an error message.
    *   **Simulated Delays**: Note that there will be a short delay (1-3 seconds) before results appear, simulating network latency. This is intentional to fulfill the "time limit set" quality requirement and demonstrate UI responsiveness.
    *   **Simulated Errors**: Occasionally, a "Connection Interruption" error message might appear. This is a simulated failure of the 'ETOUR server' connection. If this happens, simply try your search again.
    *   **No Results**: If no sites match your query, a "No sites found matching your query." message will be displayed.
    *   **Empty Query**: If you attempt to search with an empty query (after clearing the placeholder text), an error will be shown.

## ⚠️ Troubleshooting

*   **"ERROR: Interruption of the connection to the server ETOUR."**: This indicates a simulated network connection failure. This is part of the application's intended behavior. Please try your search again.
*   **"ERROR: Search query cannot be empty."**: You need to type something into the search field before clicking the "Search Site" button.
*   **Application window does not appear**:
    *   Ensure you have JDK 8 or higher installed correctly.
    *   Verify that all `.java` files are in the same directory.
    *   Check for any compilation errors during the `javac` step.
    *   Ensure you are running `java SiteSearchApp` from the correct directory.
*   **UI freezes during search**: The application uses `SwingWorker` to prevent the UI from freezing during the simulated network delay. If you experience freezing, ensure your Java environment is correctly set up.
*   **Character encoding issues**: If you see strange characters, ensure your system's default character encoding is compatible (usually UTF-8 is recommended).

## 💡 Conclusion

`RICERCASITO` provides a basic yet functional simulation of a site search application, demonstrating fundamental Java GUI programming (Swing), service interaction, and handling asynchronous operations and potential errors. It's a foundational example that can be extended for more complex real-world search functionalities.
```