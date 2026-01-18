# Address Management Application

This application allows administrators to manage and view details of addresses within a system. It provides a simple graphical user interface (GUI) to browse a list of addresses and then drill down into the specific details of any chosen address.

## 🤔 What is this?

The Address Management Application is a Java-based desktop application designed to demonstrate the "ViewAddressDetails" use case. It simulates a system where an administrator can see a list of addresses and, upon selecting one, view its comprehensive details. This application utilizes a service layer to mock data retrieval and simulate backend interactions, such as connecting to an "SMOS server".

## 🚀 Key Features

*   **View Address List**: Displays a scrollable list of all available addresses, showing their ID, Name, City, and Country for quick identification.
*   **View Address Details**: Upon selecting an address from the list and clicking "Show Address Details", a new window appears displaying all pertinent information for that specific address, including ID, Name, Street, City, Zip Code, and Country.
*   **User-Friendly Interface**: Built with Java Swing, offering an intuitive and interactive experience.
*   **Simulated Backend**: Includes a mock `AddressService` that provides sample address data and simulates server interactions for fetching details.

## ⚙️ Getting Started

To run this application, you will need a Java Development Kit (JDK) installed on your system.

### Prerequisites

*   **Java Development Kit (JDK)**: Version 8 or higher. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/downloads/) or use an open-source distribution like OpenJDK.

### Building and Running the Project

This project is structured as a standard Java application with multiple classes across different packages.

#### Option 1: Using an Integrated Development Environment (IDE) - Recommended

1.  **Download the Code**: Save all the provided `.java` files (`Address.java`, `AddressService.java`, `AddressListFrame.java`, `AddressDetailFrame.java`, `Main.java`) into a designated project directory.
2.  **Create a New Project**: Open your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse, VS Code with Java extensions).
    *   Create a new Java project.
    *   Configure the project's source folders to match the package structure:
        *   `src/com/chatdev/model` for `Address.java`
        *   `src/com/chatdev/service` for `AddressService.java`
        *   `src/com/chatdev/gui` for `AddressListFrame.java` and `AddressDetailFrame.java`
        *   `src/com/chatdev` for `Main.java`
        (Or simply place all files under a `src/main/java` directory, and the IDE will recognize the package structure automatically).
3.  **Build and Run**:
    *   Locate the `Main.java` file (in `com.chatdev` package).
    *   Right-click on `Main.java` and select "Run 'Main.main()'" or similar option.

#### Option 2: Using the Command Line

1.  **Download the Code**: Save all the provided `.java` files.
2.  **Create Directory Structure**: Manually create the package directory structure in your working directory. For example, if your working directory is `AddressApp`:
    ```
    AddressApp/
    ├── com/
    │   └── chatdev/
    │       ├── model/
    │       │   └── Address.java
    │       ├── service/
    │       │   └── AddressService.java
    │       ├── gui/
    │       │   ├── AddressListFrame.java
    │       │   └── AddressDetailFrame.java
    │       └── Main.java
    ```
3.  **Compile**: Open a terminal or command prompt, navigate to the `AddressApp` directory, and compile the Java source files:
    ```bash
    javac com/chatdev/**/*.java
    ```
    (Ensure you are in the directory *above* `com`).
4.  **Run**: After successful compilation, run the application using the `Main` class:
    ```bash
    java com.chatdev.Main
    ```

## 🎮 How to Use the Application

Once the application is launched, you will see the main window.

1.  **Address List Frame**: The first window that appears is the "Address Management - Address List". This frame displays a list of predefined mock addresses. Each entry shows the Address ID, Name, City, and Country.
2.  **Select an Address**: Click on any address in the list to select it.
3.  **View Address Details**: Click the "Show Address Details" button located at the bottom of the "Address Management - Address List" window.
4.  **Address Details Frame**: A new window titled "Address Details" will open, displaying all the detailed information (ID, Name, Street, City, Zip Code, Country) for the address you selected.
    *   The "Address Name" is highlighted (bold) as specifically requested by the use case.
    *   During the detail retrieval, you might observe console output indicating "Simulating connection to SMOS server..." and "Connection to SMOS server interrupted...", which are part of the simulated backend interaction.
5.  **Close Details**: To close the "Address Details" window, click the "Close" button within that window, or use the standard window close button (e.g., 'X' icon).
6.  **Exit Application**: To close the entire application, close the "Address Management - Address List" window.

### Edge Cases Handled

*   **No Address Selected**: If you click "Show Address Details" without selecting an address, a warning message will pop up.
*   **Address Not Found (Simulated)**: Although the current mock data ensures addresses are found, the `AddressDetailFrame` includes logic to display an error message and close itself if `addressService.getAddressById()` were to return `null`.