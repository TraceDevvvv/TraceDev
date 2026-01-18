```
# Address Management System - Administrator Console

This document serves as a user manual for the Address Management System, a Java-based application designed for administrators to manage a list of addresses.

## 🤔 What is this?

The Address Management System is a simple application that allows an administrator to view a list of existing addresses and enter new addresses into an archive. It includes basic functionalities like data validation and the simulation of backend service errors to demonstrate error handling. This system is comprised of a basic Address entity, a service layer for business logic and data persistence (in-memory for this example), and a Swing-based graphical user interface for administrator interaction.

## 🚀 Main Functions

The system provides the following key functionalities for administrators:

*   **Viewing Existing Addresses**: Displays a list of addresses currently stored in the system's archive. This corresponds to the "ViewingLenchindirizzi" state mentioned in the use case.
*   **Entering New Addresses**: Allows the administrator to input a new address name via a dedicated form.
*   **Data Validation**: Ensures that the entered address name meets specific criteria (e.g., not empty, minimum length) before saving. Invalid data triggers an "Errodati" (data error) notification.
*   **Saving Addresses**: Upon successful validation, the new address is added to the system's archive.
*   **Server Error Simulation**: Includes a feature to simulate a "connection data error to the SMOS server interrupted," demonstrating how the system handles such failures.
*   **User Notifications**: Provides feedback to the administrator regarding successful operations, data validation errors, or simulated server connection issues.

## 🛠️ Environment Dependencies

This project is developed in Java and relies solely on the standard Java Development Kit (JDK). No external third-party libraries or frameworks are required.

To compile and run this application, you will need:

*   **Java Development Kit (JDK)**: Version 8 or higher is recommended.
    *   You can download the latest JDK from Oracle's official website or use an open-source distribution like OpenJDK.
    *   Ensure that the `JAVA_HOME` environment variable is set to your JDK installation directory and that `java` and `javac` commands are accessible from your system's PATH.

## 🎮 How to Use/Play

Follow these steps to set up, compile, and run the Address Management System application:

### Step 1: Save the Code Files

Save each of the provided Java code blocks into separate `.java` files in the same directory.
*   `Address.java`
*   `AddressServiceException.java`
*   `AddressService.java`
*   `AdministratorConsole.java`
*   `AddressApp.java`

For example, your directory structure should look like this:

```
your_project_folder/
├── Address.java
├── AddressServiceException.java
├── AddressService.java
├── AdministratorConsole.java
└── AddressApp.java
```

### Step 2: Compile the Java Code

Open a terminal or command prompt, navigate to the directory where you saved the Java files, and compile them using the Java compiler (`javac`):

```bash
javac *.java
```

If the compilation is successful, `.class` files will be generated for each `.java` file in the same directory.

### Step 3: Run the Application

After successful compilation, you can run the application by executing the `AddressApp` class:

```bash
java AddressApp
```

### Step 4: Interact with the Administrator Console

Upon running the `AddressApp`, a Swing GUI window titled "Address Management System - Administrator" will appear.

1.  **View Existing Addresses**:
    *   The initial screen displays a list of pre-populated addresses (e.g., "123 Main St, Anytown"). This is the "ViewingLenchindirizzi" state.

2.  **Enter a New Address**:
    *   Click the **"New Address"** button.
    *   The view will switch to the "Enter New Address" form.
    *   **Fill out the form**: Type the desired address name into the "Address Name" text field.
    *   **Save the address**: Click the **"Save"** button.
        *   **Successful Save**: If the address is valid, a success message dialog will appear, and then the system will return to the address list, now including the newly added address.
        *   **Invalid Data**: If the address name is empty or shorter than 5 characters, an error message (e.g., "Errodati: Invalid address name...") will be displayed below the input field, indicating a data validation failure.
        *   **Cancel Operation**: Click the **"Cancel"** button to discard the current input and return to the address list without saving.

3.  **Simulate Server Error**:
    *   On the address list view, click the **"Toggle Server Error Sim (ON/OFF)"** button.
    *   This button toggles an internal flag in the `AddressService` that simulates a connection error to the backend. The button's text will update to show its current state.
    *   When server error simulation is **ON**:
        *   Attempt to save a new address. Instead of saving, an `AddressServiceException` will be caught, and an error message ("Connection data error to the SMOS server interrupted.") will be displayed on the form. This demonstrates the system notifying the administrator of a server connection issue.

4.  **Admin Interrupts Operation**:
    *   If an error occurs (either data validation or simulated server error), the administrator can choose to correct the input and retry, or simply click "Cancel" to interrupt the operation and return to the main address list. This fulfills the "administrator interrupts the operation" postcondition.

This application provides a clear demonstration of the `EnterNewAddress` use case, including preconditions, event sequences, data validation, and postconditions, all within a runnable Java Swing application.
```