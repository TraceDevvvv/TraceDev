```markdown
# ChatDev Admin Application: User Details Viewer

## Introduction

The ChatDev Admin Application is a simple Java-based desktop tool designed for administrators to view detailed information about registered users within a simulated system. This application demonstrates a common use case where an administrator navigates a list of users and retrieves specific details for a selected user, including their name, surname, email, cell number, login, and password.

**Main Functions:**

*   **User Listing:** Displays a comprehensive list of all available users in the system.
*   **User Selection:** Allows the administrator to select a specific user from the list.
*   **Detail Viewing:** Presents a separate dialog window showcasing the selected user's detailed profile information.
*   **Mock Data & System Interaction:** Utilizes mock data for user information and simulates system events, such as "connection to the SMOS server interrupted" upon closing the user details view.

This application is built with Java Swing for its graphical user interface (GUI) and serves as a runnable example fulfilling the "ViewUserDetails" use case.

## Installation Environment Dependencies

To compile and run this Java application, you need to have the following installed:

1.  **Java Development Kit (JDK):**
    *   **Version:** JDK 8 or a newer version (e.g., JDK 11, JDK 17).
    *   **Installation:** Download and install the appropriate JDK for your operating system from the official Oracle website or adoptium.net (for OpenJDK).
    *   **Environment Variables:** Ensure that the `JAVA_HOME` environment variable is set to your JDK installation directory and that `java` and `javac` commands are accessible in your system's `PATH`. You can verify this by opening a terminal or command prompt and typing `java -version` and `javac -version`.

2.  **Integrated Development Environment (IDE) (Recommended):**
    *   While not strictly required (you can compile and run from the command line), using an IDE like IntelliJ IDEA, Eclipse, or VS Code with Java extensions greatly simplifies the process of setting up, compiling, and running Java projects.

## How to Use/Play It

Follow these steps to get the ChatDev Admin Application up and running:

### Step 1: Obtain the Source Code

Ensure you have all the provided Java source files (`User.java`, `UserService.java`, `UserDetailView.java`, `UserListView.java`, `AdminApp.java`) structured correctly within a directory, respecting their package declarations.

Here's the expected directory structure:

```
your_project_root/
└── com/
    └── chatdev/
        └── adminapp/
            ├── model/
            │   └── User.java
            ├── service/
            │   └── UserService.java
            └── view/
                ├── UserDetailView.java
                └── UserListView.java
            └── AdminApp.java
```

### Step 2: Compile the Application

#### Using an IDE (Recommended)

1.  **Import Project:** Open your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
2.  **Create New Project:** Create a new Java project and import the source files, ensuring the package structure `com.chatdev.adminapp` is maintained. Most IDEs will automatically recognize the package structure if you place the `com` directory at the root of your source folder.
3.  **Build/Compile:** Your IDE will typically compile the code automatically. If not, look for "Build Project" or "Make Project" options in the IDE's menu.

#### From the Command Line

1.  **Navigate to Project Root:** Open a terminal or command prompt and navigate to the `your_project_root` directory (the one containing the `com` folder).
2.  **Compile:** Use the `javac` command to compile all Java files.

    ```bash
    javac com/chatdev/adminapp/**/*.java
    ```
    This command compiles all `.java` files within the `com/chatdev/adminapp` directory and its subdirectories. Upon successful compilation, `.class` files will be generated alongside their respective `.java` files, preserving the package structure.

### Step 3: Run the Application

#### Using an IDE (Recommended)

1.  **Locate Main Class:** Find the `AdminApp.java` file in your IDE.
2.  **Run:** Right-click on `AdminApp.java` and select "Run 'AdminApp.main()'" or similar option. The IDE will execute the application.

#### From the Command Line

1.  **Navigate to Project Root:** Ensure you are in the `your_project_root` directory.
2.  **Run:** Use the `java` command to execute the `AdminApp` main class.

    ```bash
    java com.chatdev.adminapp.AdminApp
    ```

### Step 4: Interact with the Application

Upon running, a window titled "Administrator: User List" will appear.

1.  **View User List:** The main window will display a list of mock users (e.g., "johnd (John Doe)", "janes (Jane Smith)", etc.). The list will automatically pre-select the first user.
2.  **Select a User:** Click on any user in the list to select them.
3.  **View Details:** Click the "View Details" button located at the bottom of the window.
4.  **User Details Window:** A new, modal dialog window will appear, titled "User Details: [selected_user_login]". This window will display the detailed information of the selected user, including:
    *   Name
    *   Surname
    *   E-mail
    *   Cell
    *   Login
    *   Password (Note: In a real-world application, passwords should never be displayed in plain text for security reasons. This is implemented per the specific requirements of this use case for demonstration purposes.)
5.  **Close Details Window:** Click the "Close" button within the user details dialog, or use the standard window close button (e.g., 'X' in the corner).
    *   As per the use case's postcondition, closing this window will print a message to your console/terminal indicating: `LOG: UserDetailView closed. Connection to the SMOS server interrupted.` This simulates a server interaction being concluded.
6.  **Continue/Exit:** You can select another user to view their details or close the main "Administrator: User List" window to exit the application.