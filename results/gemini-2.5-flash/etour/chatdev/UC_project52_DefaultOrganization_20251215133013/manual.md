```markdown
# ETOUR - Delete Bookmarked Site (ELIMINASITODAIPREFERITI)

## Introduction

The "ETOUR - Delete Bookmarked Site" application provides a user-friendly interface for tourists to manage their list of bookmarked tourist sites. This application allows users to view their saved sites and easily remove any site they no longer wish to keep in their bookmarks. It is designed to be intuitive, ensuring a smooth experience for the end-user, while also handling potential issues like server connection interruptions.

### Main Functions:

*   **View Bookmarked Sites**: Displays a list of all sites currently bookmarked by the tourist.
*   **Select and Remove Site**: Allows the user to select a specific site from their bookmark list and initiate its removal.
*   **Confirmation Prompt**: Requires user confirmation before a site is permanently removed, preventing accidental deletions.
*   **Real-time Status Updates**: Provides immediate feedback on the success or failure of the removal operation and any system-related messages.
*   **Simulated Server Errors**: Includes a feature to simulate server connection problems, allowing users or testers to understand how the application gracefully handles such interruptions.

## Environment Dependencies

This application is developed in Java and relies solely on the standard Java Development Kit (JDK) for compilation and execution. It uses Java Swing for its Graphical User Interface (GUI).

To run this application, you will need:

*   **Java Development Kit (JDK)**: Version 8 or newer is recommended.
    *   You can download the JDK from the official Oracle website or adoptium.net for OpenJDK distributions.
    *   Ensure that `java` and `javac` commands are accessible from your system's command line (usually means setting up the `PATH` environment variable).

No other external libraries or frameworks are required.

## How to Compile and Run

Follow these steps to set up and use the ETOUR - Delete Bookmarked Site application:

### 1. Save the Source Code Files

Create a directory for your project, for example, `ETOUR_Bookmarks`. Inside this directory, create a sub-directory named `com/chatdev/tourism`. Place each of the provided Java files (`Site.java`, `ServerConnectionException.java`, `BookmarkManager.java`, `TourismApp.java`) into this `com/chatdev/tourism` directory.

Your directory structure should look like this:

```
ETOUR_Bookmarks/
└── com/
    └── chatdev/
        └── tourism/
            ├── Site.java
            ├── ServerConnectionException.java
            ├── BookmarkManager.java
            └── TourismApp.java
```

### 2. Compile the Java Code

Open a terminal or command prompt and navigate to the `ETOUR_Bookmarks` directory.

Compile all the Java source files using the `javac` command:

```bash
javac com/chatdev/tourism/*.java
```

If the compilation is successful, you will see `.class` files created in the same directory structure.

### 3. Run the Application

From the `ETOUR_Bookmarks` directory, run the `TourismApp` class which contains the `main` method:

```bash
java com.chatdev.tourism.TourismApp
```

### How to Use the Application:

1.  **Launch**: After executing the command above, a new window titled "ETOUR - Delete Bookmarked Site" will appear.
2.  **View Bookmarks**: The main area of the window will display a list of pre-loaded sample bookmarked sites (e.g., Eiffel Tower, Colosseum).
3.  **Select a Site**: Click on any site in the "My Bookmarked Sites" list to select it. Once selected, the "Remove Selected Site" button will become active.
4.  **Remove a Site**:
    *   After selecting a site, click the "Remove Selected Site" button.
    *   A confirmation dialog will pop up asking if you are sure you want to remove the selected site.
    *   Click "Yes" to confirm the removal.
    *   Click "No" or close the dialog to cancel the operation.
5.  **Status Feedback**: The status bar at the bottom of the window will provide updates on the operation's outcome (e.g., "Site removed successfully!", "Operation cancelled.").
6.  **Simulate Server Error**:
    *   Check the "Simulate Server Connection Error" checkbox located in the top-right corner.
    *   Attempt to remove a site. This will trigger a `ServerConnectionException`, demonstrating how the application handles loss of connection to the ETOUR server. An error message will appear in the status bar and a popup.
    *   Uncheck the box to disable the simulation.

The application adheres to the use case flow and exit conditions, providing clear feedback to the user at each step.
```