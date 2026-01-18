```markdown
# User Role Management Application Manual

## 🤔 What is this?

This is a Java Swing application designed to demonstrate the "Assign/Remove Roles To A User" use case. It provides an administrator interface to view a list of users, inspect their details, and modify their assigned roles through an intuitive graphical user interface. The application simulates a backend user management system and an external SMOS server connection.

## ✨ Main Functions

The application allows an administrator to perform the following core functions:

*   **View All Users:** Display a list of all registered users in the system.
*   **View User Details:** Select a specific user from the list to view their current roles and other relevant information. This simulates the "viewdetTailsente" use case precondition.
*   **Access Role Management:** From the user details view, an administrator can click the "User Roles" button to open a dedicated form for managing that user's roles.
*   **Assign/Remove Roles:** In the role management form, the administrator can select or deselect available roles using checkboxes to assign or remove them from the selected user.
*   **Apply Changes:** Upon clicking the "Send" button, the system updates the user's roles.
*   **SMOS Server Disconnection (Simulated):** After roles are modified and applied, the system simulates an interruption of the connection to an SMOS server, as per the specified postcondition.

## 🛠️ How to Install and Run

### Prerequisites

*   **Java Development Kit (JDK):** Ensure you have JDK 11 or a newer version installed on your system. You can download it from the official Oracle website or use an open-source distribution like OpenJDK.

### Get the Code

The application code consists of several Java files organized into packages:
*   `model/User.java`
*   `model/UserManager.java`
*   `service/SMOSServer.java`
*   `gui/AdminDashboardFrame.java`
*   `gui/UserDetailsDialog.java`
*   `gui/RoleManagementDialog.java`
*   `Main.java`

Place these files in a directory structure that matches their package declarations (e.g., `src/model/User.java`, `src/gui/AdminDashboardFrame.java`, `src/Main.java`).

### Compilation

Open your terminal or command prompt, navigate to the root directory where your `src` folder is located, and compile the Java source files.

```bash
# Navigate to the directory containing your 'src' folder
cd /path/to/your/project

# Compile all source files.
# The `src` directory should contain subdirectories for `model`, `service`, `gui` and `Main.java` at the root.
javac src/model/*.java src/service/*.java src/gui/*.java src/Main.java
```

If compilation is successful, `.class` files will be generated in the respective package directories.

### Running the Application

After successful compilation, you can run the application from the same root directory.

```bash
# Run the main class
java -cp src Main
```

This will launch the Administrator Dashboard GUI.

## 🎮 How to Use/Play

### 1. Launch the Application

Upon running the `Main` class, the "Admin Dashboard - User Role Management" window will appear. This window represents the administrator's main interface.

### 2. Administrator Dashboard Overview

*   **User List (Left Panel):** This panel displays a list of pre-configured sample users (e.g., "Admin Alice", "User Bob", "User Carol", "User David").
*   **Instructions (Right Panel):** Provides a quick guide on how to use the application.
*   **"View User Details" Button (Bottom):** This button will be enabled once a user is selected from the list.

### 3. View User Details

1.  **Select a User:** Click on a user's name in the "Users" list on the left panel (e.g., "User Bob (ID: U002)").
2.  **Click "View User Details":** Once a user is selected, the "View User Details" button at the bottom will become active. Click it.

    *   *This action fulfills the precondition: "The user has carried out the case of use 'viewdetTailsente' and the system is Viewing the details of a user".*

### 4. User Details Dialog

A new dialog titled "Details for [Username]" will appear.
*   It displays the selected user's username and ID.
*   It lists the user's current roles in the "Current Roles" text area. For example, "Admin Alice" might have "Administrator" and "Editor" roles.
*   At the bottom, you'll find a "User Roles" button.

### 5. Manage User Roles

1.  **Click "User Roles":** In the "Details for [Username]" dialog, click the "User Roles" button.

    *   *This action fulfills the precondition: "The user click on the 'User Roles' button".
    *   *The system responds by displaying the "role management form".*

2.  **Role Management Dialog:** A new dialog titled "Manage Roles for [Username]" will open.
    *   This dialog lists all available roles in the system as checkboxes.
    *   Roles that the user currently possesses are pre-selected (checked).

3.  **Select/Deselect Roles:**
    *   **To assign a role:** Check the box next to the desired role.
    *   **To remove a role:** Uncheck the box next to the role you wish to remove.
    *   You can assign and remove multiple roles in one operation.

4.  **Click "Send":** After making your selections, click the "Send" button at the bottom of the dialog.

    *   *This initiates the system's action to "Assign or remove the user's roles as indicated by the administrator".*

### 6. Observe Postconditions

*   **Role Modification Confirmation:** A pop-up message will indicate whether "User roles modified successfully" or if "No changes made to user roles" (if you clicked "Send" without altering any selections).
*   **SMOS Server Disconnection:** If roles were successfully modified, you will see a message in your console/terminal (where you ran the `java Main` command) stating: `SMOS Server: Connection interrupted. (Simulation)`.
    *   *This fulfills the postcondition: "The administrator interrupts the connection to the SMOS server interrupted".*
*   **Updated User Details:** The "Details for [Username]" dialog will automatically refresh its "Current Roles" display to reflect the changes you just made.

You can close the "Details for [Username]" dialog and select another user to manage their roles, or close the main "Admin Dashboard" window to exit the application.

Enjoy managing user roles!
```