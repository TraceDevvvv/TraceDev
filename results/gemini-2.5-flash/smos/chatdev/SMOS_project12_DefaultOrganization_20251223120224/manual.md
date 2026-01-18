```markdown
# Student-Parent Association Management System

## Introduction

This application is a simple Java Swing-based tool designed for administrators within an educational management system (simulated SMOS server). Its primary function is to manage the association between parents and students. Administrators can view a parent's details and subsequently assign new students to that parent or remove existing student associations. This system simplifies the process of updating parental relationships with their children within the school's records.

## Key Features

*   **Administrator Login Simulation**: The application assumes the user is logged in as an administrator.
*   **Parent Details View**: Allows the administrator to view the details of a specific parent (simulated "viewdetTailsente" use case).
*   **Child Management Interface**: Provides a dedicated form to manage student associations for the selected parent.
*   **Assign Students**: Administrators can select from a list of available students and assign them to the parent.
*   **Remove Students**: Administrators can select from the parent's currently associated students and remove their association.
*   **Save Changes**: All changes (assignments and removals) are applied upon clicking the "Send" button, updating the parent's student roster.
*   **SMOS Server Interaction Simulation**: The application simulates interaction with an "SMOS server" for saving data and interrupting connections.

## Environment Setup

To run this Java application, you will need:

1.  **Java Development Kit (JDK)**: Version 11 or newer is recommended.
    *   **Installation**: Download and install the JDK from Oracle's website or use an open-source distribution like OpenJDK (e.g., Adoptium Temurin).
    *   **Verification**: Open your terminal or command prompt and type `java -version` and `javac -version`. You should see output indicating your installed Java version.
2.  **Integrated Development Environment (IDE)**: An IDE like IntelliJ IDEA, Eclipse, or Apache NetBeans is highly recommended for easy project setup and execution.

## Installation and Project Setup

Follow these steps to set up and run the project in your IDE:

1.  **Create a New Java Project**:
    *   Open your IDE (e.g., IntelliJ IDEA).
    *   Select "File" > "New" > "Project...".
    *   Choose "Java" as the project type. Ensure your JDK is selected.
    *   Click "Next," then "Next" again (for "Create project from template" if offered, choose "Command Line App" or proceed without a template).
    *   Give your project a name (e.g., `StudentParentManager`) and choose a location. Click "Finish" or "Create".

2.  **Add the Source Code Files**:
    *   In your IDE's project explorer, locate the `src` folder (or `src/main/java`).
    *   Create the following Java files inside this directory:
        *   `main.java`
        *   `parent.java`
        *   `student.java`
        *   `parentmanagementservice.java`
        *   `parentdetailswindow.java`
        *   `childmanagementform.java`
    *   Copy the content of each respective code block provided to you and paste it into the corresponding file.

    *Self-correction/Important Note*: Java class names must match their file names. For instance, the content of `parent.java` should define a class named `Parent`.

3.  **Ensure All Classes Are in the Default Package**:
    Since no `package` declarations are used in the provided code, all classes reside in the default package. While not best practice for larger applications, it simplifies setup for this example. Just place all `.java` files directly under `src/` or `src/main/java/` without creating subfolders for packages.

## How to Use/Play

This application simulates the workflow of an administrator managing student-parent associations.

1.  **Run the Application**:
    *   Locate the `Main.java` file in your IDE's project explorer.
    *   Right-click on `Main.java` and select "Run 'Main.main()'" or locate the run button/option in your IDE.

2.  **Initial Screen: Parent Details Window (Simulating "viewdetTailsente")**:
    *   A window titled "Parent Details - John Doe" will appear. This simulates the precondition where an administrator has viewed the details of a parent.
    *   You will see dummy details for "John Doe" (ID: 1).
    *   Click the **"Parentela (Manage Children)"** button.

3.  **Child Management Form**:
    *   A new window, "Manage Children for John Doe," will open. The previous "Parent Details" window will close.
    *   This form presents two lists:
        *   **"Available Students"**: Students not currently associated with John Doe.
        *   **"Assigned Students"**: Students currently associated with John Doe. (Initially, Alice Smith and Bob Johnson should be here, based on the `ParentManagementService`'s dummy data.)

4.  **Assigning Students**:
    *   In the **"Available Students"** list, click on one or more students you wish to assign to John Doe. You can use `Ctrl` (or `Cmd` on Mac) + click to select multiple students.
    *   Click the **"Assign ->"** button.
    *   The selected students will move from the "Available Students" list to the "Assigned Students" list.

5.  **Removing Students**:
    *   In the **"Assigned Students"** list, click on one or more students you wish to remove from John Doe's association.
    *   Click the **"<- Remove"** button.
    *   The selected students will move from the "Assigned Students" list to the "Available Students" list.

6.  **Saving Changes (Clicking "Send")**:
    *   Once you have made all desired changes (assigned or removed students), click the **"Send (Save Changes)"** button.
    *   A message dialog will pop up confirming "Student associations for John Doe updated successfully!".
    *   The console will output a simulated message: "Administrator interrupted connection to SMOS server. (Simulated connection closure/cleanup)".
    *   The "Manage Children" form will then close, indicating the completion of the task.

## Simulated Data

The application uses an in-memory `ParentManagementService` to simulate a database.
*   **Parents**: There are two simulated parents, "John Doe" (ID 1) and "Jane Doe" (ID 2).
*   **Students**: There are six simulated students (Alice, Bob, Charlie, Diana, Eve, Frank).
*   **Initial Associations**:
    *   John Doe is initially associated with Alice Smith and Bob Johnson.
    *   Jane Doe is initially associated with Charlie Brown.

Each time the application is run, the data resets to these initial values, as there's no persistent storage implemented.
```