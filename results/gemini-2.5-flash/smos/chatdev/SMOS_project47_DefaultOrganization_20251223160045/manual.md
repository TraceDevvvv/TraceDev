```
# Student Report Card Management System

## Introduction

The Student Report Card Management System is a Java-based desktop application designed for administrators to manage student report cards. This tool allows for the viewing of a list of students, and more importantly, provides functionality to edit individual student report cards, including modifying existing subject grades and adding new subjects with their corresponding grades.

The system aims to streamline the process of updating student academic records by offering an intuitive graphical user interface.

**Key Features:**

*   **Student List View:** Displays all registered students along with their grades in various subjects in a tabular format.
*   **Report Card Editing:** Allows administrators to select a student and modify their report card.
*   **Grade Modification:** Update grades for existing subjects.
*   **New Subject Addition:** Dynamically add new subjects and their grades to a student's report card.
*   **Input Validation:** Basic validation for grades (numeric, between 0-100) to ensure data integrity.
*   **Confirmation & Refresh:** Provides feedback on successful updates and automatically refreshes the student list.

## System Requirements

To run this application, you need:

*   **Java Development Kit (JDK) 11 or higher:** The application is written in Java and requires a compatible JDK to compile and run.
*   **A text editor or Integrated Development Environment (IDE):** Such as VS Code, IntelliJ IDEA, Eclipse, or Notepad++ for viewing and compiling the code.

## Installation and Setup

Follow these steps to set up and run the application:

### 1. Download the Code

Ensure you have all the provided Java source files (`.java`) organized into their respective directories reflecting their package structure. Based on the provided code, your project structure should look like this:

```
├── src
│   ├── Main.java
│   ├── gui
│   │   ├── EditReportCardDialog.java
│   │   └── StudentManagementFrame.java
│   ├── model
│   │   ├── ReportCard.java
│   │   ├── Student.java
│   │   └── SubjectGrade.java
│   └── service
│       └── StudentService.java
```

### 2. Compile the Java Source Files

Open your terminal or command prompt and navigate to the root directory where your `src` folder is located (e.g., `your-project-directory`).

Compile all Java files, ensuring the package structure is maintained:

```bash
javac -d bin src/Main.java src/gui/*.java src/model/*.java src/service/*.java
```

This command will compile all `.java` files into `.class` files and place them in a new directory named `bin`.

### 3. Run the Application

After successful compilation, navigate into the `bin` directory from your terminal:

```bash
cd bin
```

Then, run the main application class:

```bash
java Main
```

Alternatively, if you are still in the parent directory of `bin` and `src`, you can run it using:

```bash
java -cp bin Main
```

The main application window, "Student Report Card Management (Admin)", should appear.

## How to Use the Application

This section details how to interact with the Student Report Card Management application.

### 1. Launching the Application

*   Execute the `java Main` command as described in the "Run the Application" section.
*   A window titled "Student Report Card Management (Admin)" will appear. This is the main interface where you can view a list of students.

### 2. Viewing the Student List

*   Upon launching, the main window will display a table populated with sample student data.
*   Each row represents a student, showing their ID, Name, and grades for various subjects like Math, Physics, Chemistry, etc.
*   "N/A" indicates that a student does not have a grade recorded for that particular subject.

### 3. Editing a Student's Report Card

To modify a student's report card:

1.  **Select a Student:** In the main student list table, click on any row to select the student whose report card you wish to edit.
    *   **Important:** If no student is selected and you click "Edit Report Card", a warning message "Please select a student to edit." will be displayed.
2.  **Click "Edit Report Card":** At the bottom of the main window, click the "Edit Report Card" button.
3.  **The "Edit Report Card" Dialog:** A new modal dialog window will appear, titled "Edit Report Card for [Student Name]".
    *   It will display the student's ID and Name at the top.
    *   Below that, "Existing and Added Subject Grades" panel will list all current subjects and their grades for the selected student. Each grade is editable via a text field.

### 4. Modifying Existing Grades

*   For any subject listed in the "Existing and Added Subject Grades" panel, simply click on the text field next to the subject name.
*   Enter the new numeric grade.
*   **Validation:** Grades must be between 0 and 100. Entering non-numeric values or values outside this range will display an error message upon saving.

### 5. Adding a New Subject

You can add new subjects to the student's report card:

1.  **Locate "Add New Subject" panel:** This panel is at the bottom of the "Edit Report Card for..." dialog.
2.  **Enter Subject Name:** Type the name of the new subject into the "Subject Name:" field.
3.  **Enter Grade:** Type the numeric grade for this new subject into the "Grade (0-100):" field.
    *   **Validation:** Grades must be between 0-100. Leaving subject name or grade empty will result in an error.
4.  **Click "Add Subject to Form":** This button adds the subject and its grade to the list of grades displayed in the "Existing and Added Subject Grades" panel *within the dialog*. It does NOT save it to the system yet.
    *   If you add a subject that already exists, its grade will update in the dialog's display.
    *   The "Subject Name:" and "Grade (0-100):" fields will clear, allowing you to add another subject if needed.

### 6. Saving Changes

Once you have made all desired modifications and additions within the "Edit Report Card" dialog:

1.  **Click "Save Changes":** This button attempts to save all grades currently displayed in the "Existing and Added Subject Grades" panel to the student's report card.
2.  **Confirmation:**
    *   If successful, a message "Report card for [Student Name] updated successfully!" will appear, and the dialog will close.
    *   If there are validation errors (e.g., empty grade, non-numeric grade, out-of-range grade), an error message will inform you, and the dialog will remain open for correction.
3.  **Refresh Main Table:** After a successful save, the main "Student Report Card Management (Admin)" window will automatically refresh its table to reflect the updated grades, including any newly added subjects.

### 7. Canceling Changes

*   If you opened the "Edit Report Card" dialog and decide not to save any changes, click the "Cancel" button. The dialog will close without modifying the student's report card, and the main table will remain as it was.

### 8. Exiting the Application

*   Close the main "Student Report Card Management (Admin)" window by clicking the 'X' button in the title bar (Windows/Linux) or the red circle button (macOS).

This manual covers the essential interactions with the Student Report Card Management System. Administrators can use this guide to effectively manage student academic records.
```