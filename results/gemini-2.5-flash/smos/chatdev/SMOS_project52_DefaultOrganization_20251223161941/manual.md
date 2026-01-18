```
# Administrator Entity Search Application User Manual

## 🚀 Introduction

The Administrator Entity Search Application is a standalone Java Swing desktop application designed to allow system administrators to efficiently search through various types of entities within a simulated system. This tool provides a user-friendly graphical interface where administrators can input keywords and retrieve a categorized list of matching "Classes," "Teachings," "Addresses," and "Users." It's an illustrative example of an administrative search functionality, showcasing how different data types can be unified under a single search mechanism.

## ✨ Main Functions

*   **Keyword-based Entity Search**: Search through predefined mock system entities using text keywords.
*   **Categorized Results**: Search results are automatically organized and displayed in separate tabs for "Classes," "Teachings," "Addresses," and "Users."
*   **Active List Display**: Entities found are presented in active, navigable lists within each tab.
*   **Administrator Access (Simulated)**: The application is designed for an "Administrator" user, implying elevated search permissions (though login is not explicitly implemented in this standalone version).
*   **Simple User Interface**: Easy-to-use search box and button for quick operations.
*   **Connection Interruption Simulation**: Upon closing the application, it simulates the "Connection to the interrupted SMOS server" postcondition, indicating proper resource release.

## 🛠️ System Requirements & Installation

### Environment Dependencies

To run this application, you need to have a Java Development Kit (JDK) installed on your system.

*   **Java Development Kit (JDK)**: Version 8 or higher.
    *   You can download the latest JDK from Oracle's website or use OpenJDK distributions like Adoptium (Temurin).
    *   Ensure that Java is correctly installed and accessible from your command line (`java -version` and `javac -version` should print version information).

### Installation Guide

Follow these steps to set up and run the application:

1.  **Create Project Directory**: Create a new directory for your project, for example, `AdminEntitySearch`.
    ```bash
    mkdir AdminEntitySearch
    cd AdminEntitySearch
    ```

2.  **Create Source Directory Structure**: The Java files are organized into packages (`com.chatdev.entities`, `com.chatdev.serv`, `com.chatdev.ui`). You need to replicate this structure within a `src` directory.
    ```bash
    mkdir -p src/com/chatdev/entities
    mkdir -p src/com/chatdev/serv
    mkdir -p src/com/chatdev/ui
    ```

3.  **Place Java Files**:
    *   Save `ClassEntity.java`, `TeachingEntity.java`, `AddressEntity.java`, and `UserEntity.java` into the `src/com/chatdev/entities` directory.
    *   Save `SearchResult.java` and `EntitySearchService.java` into the `src/com/chatdev/serv` directory.
    *   Save `AdminEntitySearchApp.java` into the `src/com/chatdev/ui` directory.

    Your directory structure should look like this:
    ```
    AdminEntitySearch/
    ├── src/
    │   └── com/
    │       └── chatdev/
    │           ├── entities/
    │           │   ├── ClassEntity.java
    │           │   ├── TeachingEntity.java
    │           │   ├── AddressEntity.java
    │           │   └── UserEntity.java
    │           ├── serv/
    │           │   ├── SearchResult.java
    │           │   └── EntitySearchService.java
    │           └── ui/
    │               └── AdminEntitySearchApp.java
    ```

4.  **Compile the Code**: Open your terminal or command prompt, navigate to the `AdminEntitySearch` directory, and compile the Java source files.
    ```bash
    javac src/com/chatdev/ui/AdminEntitySearchApp.java
    ```
    *   This command compiles the main application class, and `javac` will automatically find and compile its dependencies (`.entities` and `.serv` classes) if they are correctly placed within the `src` directory.

5.  **Run the Application**: After successful compilation, run the application from the `AdminEntitySearch` directory.
    ```bash
    java -cp src com.chatdev.ui.AdminEntityApp
    ```

## 🎮 How to Use the Application

Once the application is running, a new window titled "Administrator Entity Search" will appear.

1.  **Enter Keywords**: In the "Keywords:" text field located at the top of the window, type in the text you wish to search for. For example, try "CS", "admin", "Smith", "USA", "2023", etc. The search is case-insensitive.

2.  **Initiate Search**:
    *   Click the "Search" button next to the text field, or
    *   Press the `Enter` key while the text field is focused.

3.  **View Results**:
    *   The results will be displayed in the tabbed pane below the search bar. There are four tabs: "Classes," "Teachings," "Addresses," and "Users."
    *   Each tab will show a list of entities of that type that match your entered keywords.
    *   If a tab contains results, the application will automatically switch to the first tab that has matching entities.
    *   If no entities are found for the given keywords, the lists will remain empty.

4.  **Clear Search / No Results**: If you enter an empty search query and press "Search", a warning message will appear, and previous results will be cleared.

5.  **Stop Operation**: To stop the operation and close the application, simply close the application window (e.g., by clicking the 'X' button on the window title bar).
    *   When you close the application, a message will be printed to your console ("Application is closing. Simulating 'Connection to the interrupted SMOS server'.") simulating the disconnections described in the postconditions.

Enjoy searching through the system's entities!
```