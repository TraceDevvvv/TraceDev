```markdown
# Address Management System

A simple Java Swing application designed for administrators to view a list of addresses. This system demonstrates basic GUI interaction, user authentication, and simulation of external system integration (SMOS server connection).

## 🤔 What is this?

This application provides a user interface for an Administrator to log in and then access an "Address Management" feature. Upon clicking this feature, the system displays a pre-defined list of addresses and simulates an important side-effect: interrupting a connection to an external SMOS server, as specified by the customer's use case. It's built with Java Swing for the graphical user interface.

## 🚀 Main Functions

The Address Management System offers the following key functionalities:

*   **Administrator Login:** Secure login screen requiring specific administrator credentials (`admin`/`admin`).
*   **Administrator Dashboard:** A main dashboard accessible only after successful administrator login, providing navigation options.
*   **View Address List:** Allows the administrator to view a simulated list of addresses from an archive, displayed in a tabular format.
*   **SMOS Server Connection Management:** Simulates the interruption of a connection to an SMOS server immediately after the address list is displayed, fulfilling a specific postcondition of the use case.

## 🛠️ Quick Install & Setup

To set up and run this application, you need to have a Java Development Kit (JDK) installed on your system.

### Prerequisites

*   **Java Development Kit (JDK) 11 or higher:**
    *   You can download and install the latest JDK from Oracle's website or use OpenJDK distributions like AdoptOpenJDK, Amazon Corretto, etc.
    *   Ensure that the `JAVA_HOME` environment variable is set and `java` and `javac` commands are accessible in your system's PATH. You can verify this by opening a terminal/command prompt and typing `java -version` and `javac -version`.

### Source Code

The application consists of the following Java source files:

*   `Main.java`
*   `Address.java`
*   `SMOSServerConnectionManager.java`
*   `AddressService.java`
*   `LoginScreen.java`
*   `AdminDashboard.java`
*   `AddressListPanel.java`

Ensure all these `.java` files are located in the same directory.

### Compilation

Open a terminal or command prompt, navigate to the directory where you saved all the `.java` files, and compile them using the Java compiler:

```bash
javac *.java
```

If compilation is successful, you will see `.class` files generated for each `.java` file in the same directory.

## ▶️ How to Use/Play

Follow these steps to explore the Address Management System:

1.  **Run the Application:**
    From your terminal or command prompt, in the same directory where you compiled the files, execute the `Main` class:

    ```bash
    java Main
    ```

2.  **Login Screen:**
    A "Login - Address Management System" window will appear.
    *   **Username:** Enter `admin` (it might be pre-filled).
    *   **Password:** Enter `admin` (it might be pre-filled).
    *   Click the **"Login"** button.

    If the credentials are correct, the login screen will close, and the "Administrator Dashboard" will open. If not, an error message "Invalid username or password." will be displayed.

3.  **Administrator Dashboard:**
    You will see the main dashboard with a "Welcome" message. On the left-hand side, there's a navigation sidebar.

4.  **View Address List:**
    *   Click the **"Address Management"** button in the sidebar.

    *   The main content area of the dashboard will then display a list of addresses in a table.
    *   During this process, you will see console output indicating "Addresses fetched successfully." and then ">>> SMOS server connection interrupted. <<<". This confirms that the system has fetched the addresses and performed the required action of interrupting the SMOS server connection.
    *   The status label at the bottom of the Address List Panel will update from "Loading addresses..." to "Address list displayed. Interrupting SMOS connection..." and finally to "Address list displayed. SMOS server connection interrupted.".

5.  **Logout:**
    *   To log out, click on the **"File"** menu in the top-left corner of the dashboard.
    *   Select **"Logout"**.
    *   The dashboard will close, and you will be returned to the Login Screen.

6.  **Exit Application:**
    You can close the application at any time by clicking the `X` button on the window title bar.
```