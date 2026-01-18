```markdown
# ETourApp - Bookmark Your Adventures

## 🚀 Introduction

The ETourApp is a simple desktop application designed to simulate a tourist's experience of bookmarking visited sites. Developed in Java using Swing for its graphical user interface, this application allows users (simulated tourists) to "visit" various global landmarks and add them to a personal list of favorite sites. It demonstrates core functionality like user interaction, data management, and handling simulated external service failures, specifically focusing on the `INSERISCISITONEIPREFERITI` (Insert Site into Favorites) use case.

## ✨ Main Functions

The ETourApp provides the following key functionalities:

*   **Simulate Site Visit:** Allows the "tourist" to choose from a predefined list of global sites, simulating their current physical location. This sets the context for bookmarking.
*   **Add Site to Favorites:** Enables the user to add their currently visited site to a personal list of bookmarks. This feature includes a confirmation step and handles potential errors such as a site already being bookmarked or a simulated server connection failure.
*   **View Favorite Sites:** Displays a dynamic list of all sites successfully added to the user's favorites.
*   **Error Handling:** Provides clear feedback to the user in case of connection issues to the simulated ETOUR server or attempts to bookmark an already saved site.

## 🛠️ Environment Dependencies

To run the ETourApp, you will need:

*   **Java Development Kit (JDK):** Version 8 or higher is recommended. You can download it from the official Oracle website or adoptium.net (for OpenJDK).
    *   Ensure `JAVA_HOME` is set and `java` and `javac` commands are accessible from your terminal.
*   **A Text Editor or Integrated Development Environment (IDE):**
    *   **Text Editor (e.g., VS Code, Sublime Text):** For writing and saving the `.java` files. You will compile and run from the command line.
    *   **IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans):** Simplifies compilation, running, and project management. This is the recommended approach for development.

## 📦 Installation and Setup

Since this is a simple application provided as raw Java files, the "installation" process involves saving the code and compiling it.

1.  **Save the Code:**
    *   Create a new directory (e.g., `ETourApp`) on your computer.
    *   Inside this directory, save the provided code snippets into three separate `.java` files:
        *   `Site.java`
        *   `BookmarkManager.java`
        *   `ETourApp.java`
    *   Ensure all files are in the same directory, as they rely on each other without explicit package declarations.

2.  **Compile the Code:**

    *   **Using a Command Line:**
        1.  Open your terminal or command prompt.
        2.  Navigate to the directory where you saved the `.java` files (e.g., `cd path/to/ETourApp`).
        3.  Compile the Java files using the Java compiler:
            ```bash
            javac Site.java BookmarkManager.java ETourApp.java
            ```
            If compilation is successful, `.class` files will be generated in the same directory for each `.java` file.

    *   **Using an IDE (Recommended):**
        1.  Open your chosen IDE (e.g., IntelliJ IDEA).
        2.  Create a new Java project.
        3.  Copy the `Site.java`, `BookmarkManager.java`, and `ETourApp.java` files into the `src` folder (or equivalent source root) of your new project.
        4.  The IDE should automatically detect and compile the classes. If not, trigger a "Build Project" action.

## 🎮 How to Use/Play

Once the application is compiled, you can run it.

1.  **Run the Application:**

    *   **Using a Command Line:**
        1.  Ensure you are in the directory containing the compiled `.class` files.
        2.  Run the main application class:
            ```bash
            java ETourApp
            ```
            A new window titled "ETOUR - Tourist Application" should appear.

    *   **Using an IDE:**
        1.  Locate the `ETourApp.java` file in your project explorer.
        2.  Right-click on `ETourApp.java` and select "Run 'ETourApp.main()'" or click the run button in your IDE.
        3.  The application window will open.

2.  **Interacting with the Application:**

    *   **Initial State:**
        *   Upon launching, the "Current Tourist Location" will show "No site currently selected."
        *   The "Add Current Site to Favorites" button will be disabled.
        *   The "Your Favorite Sites" list will initially show "No favorites yet."

    *   **Simulate Visiting a New Site:**
        1.  Click the "Simulate Visiting a New Site" button.
        2.  A dialog box will appear, allowing you to select a site from a predefined list (e.g., "Colosseum," "Eiffel Tower").
        3.  Choose a site and click "OK."
        4.  The "Current Tourist Location" label will update to show your selected site (e.g., "Currently at: Colosseum (Rome, Italy)"). The "Add Current Site to Favorites" button will now be enabled.

    *   **Add Current Site to Favorites:**
        1.  After selecting a current site, click the "Add Current Site to Favorites" button.
        2.  A confirmation dialog will appear, asking if you want to add the site to your favorites.
        3.  Click "Yes" to confirm.
            *   **Success:** If successful, a "Success" message will indicate that the site has been added, and it will appear in the "Your Favorite Sites" list. The "Add Current Site to Favorites" button will become disabled if the current site has just been bookmarked.
            *   **Server Connection Error:** Due to a simulated 20% chance of server failure, you might receive a "Connection Error" message ("Interruption of connection to the server ETOUR."). If this happens, simply try adding the site again.
            *   **Already Bookmarked:** If you try to add a site that is already in your favorites, a "Operation Failed" message will appear, stating that the site is already in favorites.

    *   **Viewing Bookmarks:**
        *   The "Your Favorite Sites" list at the bottom of the window will continuously update to show all the sites you have successfully added to your bookmarks.

Experiment by selecting different sites and adding them. Observe how the application handles successes, duplicates, and simulated server errors.

Enjoy your virtual tour and bookmarking experience!
```