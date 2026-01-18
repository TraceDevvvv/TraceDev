# Product Requirement Document: Insert Absences System

## 1. Language & Project Info

*   **Language**: English
*   **Programming Language**: Java
*   **Project Name**: `insert_absences_system`

### Original Requirements:

**Use Case:**
*   **Name**: InsertAbsences
*   **Actors**: ATA staff
*   **Description**: Register digitization, data collection from ATA class personnel per class
*   **Preconditions**:
    *   The user must be logged in to the system as the ATA staff.
    *   The user has carried out the use case `ViewLancoclassiata` (selection a class to enter the data in the system).

**Events Sequence**:
1.  **System**: Shows the user a screen with pupils and radio button to select if a student is absent or present. By default, the student is present.
2.  **User**: The user selects absent students.
3.  **User**: Clicks on Save.
4.  **System**: The system sends data to the server. The server for each absence sends a notification email to the parent of the student.

**Postconditions**:
*   Absence data has been entered in the system, and the system sent notifications to parents. The initial screen is shown again.
*   Connection to the interrupted SMOS server.
*   The user interrupts the operation.

## 2. Product Definition

### 2.1 Product Goals

1.  **Streamline Absence Recording**: To provide ATA staff with an efficient and intuitive interface for accurately recording student absences for selected classes, reducing manual effort and potential errors.
2.  **Automate Parent Notifications**: To ensure timely and accurate communication with parents regarding their child's absence by automatically sending email notifications upon absence registration.
3.  **Maintain Data Integrity**: To securely store and manage student absence data within the system, ensuring consistency and reliability for reporting and administrative purposes.

### 2.2 User Stories

*   **As an ATA staff member**, I want to easily view a list of students in a selected class with default 'present' status, so I can quickly mark absent students.
*   **As an ATA staff member**, I want to be able to select multiple absent students and save the attendance data with a single action, so I can efficiently complete the absence registration process.
*   **As an ATA staff member**, I want the system to automatically notify parents via email when their child is marked absent, so I don't have to manually send individual notifications.
*   **As an ATA staff member**, I want the system to confirm that absence data has been successfully saved and notifications sent, and then return to the initial class selection screen, so I can proceed with other tasks or register absences for another class.

### 2.3 Competitive Analysis

To be completed after market research. This section will analyze 5-7 existing student information systems or attendance tracking solutions, detailing their pros and cons relevant to absence management and parent notifications.

### 2.4 Competitive Quadrant Chart

To be completed after competitive analysis. This chart will visually represent the positioning of our `Insert Absences System` against competitors based on key metrics like ease of use, notification capabilities, integration, and data management.

## 3. Technical Specifications

### 3.1 Requirements Analysis

This system will be developed in Java and will integrate with an existing student management system (SMOS) for student data and potentially for sending notifications. The core functionality involves a user interface for ATA staff to mark student attendance, a backend service to process this data, and an email service for parent notifications. Error handling for server communication and user interruptions will be critical.

### 3.2 Requirements Pool

*   **P0 (Must-have)**:
    *   The system MUST display a list of students for a selected class.
    *   The system MUST provide a mechanism (e.g., radio buttons) to mark each student as 'present' (default) or 'absent'.
    *   The system MUST allow ATA staff to save the attendance data.
    *   The system MUST send absence data to the server (SMOS).
    *   The system MUST trigger an email notification to the parent of each marked absent student.
    *   The system MUST confirm successful data entry and notification sending.
    *   The system MUST return to the initial class selection screen after successful operation.
    *   The system MUST handle user login and class selection as preconditions.

*   **P1 (Should-have)**:
    *   The system SHOULD provide clear visual feedback during data submission (e.g., loading spinner).
    *   The system SHOULD validate input before sending data to the server.
    *   The system SHOULD log all absence entries and notification attempts for auditing purposes.
    *   The system SHOULD provide an option to review selected absences before saving.

*   **P2 (Nice-to-have)**:
    *   The system MAY allow customization of the parent notification email template.
    *   The system MAY provide a bulk selection option for marking multiple students as absent (e.g., 'mark all absent').
    *   The system MAY offer a search/filter functionality for students within a class.

### 3.3 UI Design Draft

**Screen: Insert Absences**

*   **Header**: "Register Absences for [Class Name]"
*   **Student List Area**:
    *   A table or list displaying student names.
    *   Each row will have:
        *   Student Name (e.g., "John Doe")
        *   Radio Button Group: [ ] Present (default selected) [ ] Absent
*   **Action Buttons**:
    *   `[Save]` button: Submits the attendance data.
    *   `[Cancel]` button: Interrupts the operation and returns to the previous screen.
*   **Status/Feedback Area**: Displays messages like "Saving data...", "Absences recorded successfully!", "Error: Connection to server lost."

**Flow**:
1.  User logs in as ATA staff.
2.  User selects a class (precondition).
3.  System displays the "Insert Absences" screen with students listed, all defaulted to 'Present'.
4.  ATA staff reviews the list and changes the radio button to 'Absent' for relevant students.
5.  ATA staff clicks 'Save'.
6.  System shows a loading indicator, sends data, and sends emails.
7.  Upon completion, a success message is shown, and the screen reverts to the class selection view.
8.  If connection is interrupted or user cancels, appropriate feedback is given, and the system handles the state.

### 3.4 Open Questions

*   What is the exact API endpoint and data format for sending absence data to the SMOS server?
*   What are the specific requirements for the content and sender of the parent notification emails?
*   How should connection interruptions to the SMOS server be handled from a user experience perspective (e.g., retry mechanism, error message, data caching)?
*   Are there any specific security or privacy requirements for handling student absence data and parent contact information?
*   What is the expected volume of students per class and classes per ATA staff member, to ensure performance and scalability?
