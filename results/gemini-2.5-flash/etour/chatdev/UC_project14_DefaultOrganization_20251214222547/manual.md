```markdown
# RICERCATURISTA - Tourist Search System

## 🤔 What is this?

The "RICERCATURISTA" (Tourist Researcher) system is a desktop application designed to allow an Agency Operator to efficiently search for tourist accounts within the system. It enables operators to query a database of tourist profiles based on various parameters such as first name, last name, country, and email. The system then displays a list of matching tourist accounts in a clear, tabular format.

This application simulates real-world scenarios by incorporating features like network latency and potential connection interruptions to a backend server, providing a robust testing ground for the user experience under varying conditions.

## ✨ Main Functions

The `RICERCATURISTA` system provides the following core functionalities:

1.  **Search Tourist Accounts**: Operators can input search criteria (First Name, Last Name, Country, Email) into a user-friendly form to find specific tourist profiles.
2.  **Display Search Results**: Matching tourist accounts are presented in a dynamic table, showing details like ID, First Name, Last Name, Email, and Country. The table supports sorting by column headers.
3.  **Connection Handling**: The system simulates potential connection interruptions to the ETOUR server, providing appropriate user feedback in such cases.
4.  **Asynchronous Operations**: Search operations run in the background to ensure the user interface remains responsive, even during simulated network delays.

## 💻 Environment Setup

To run the `RICERCATURISTA` application, you need to have a Java Development Kit (JDK) installed on your system.

### Dependencies

*   **Java Development Kit (JDK)**: Version 11 or higher is recommended.
    *   You can download the JDK from the official Oracle website or use an open-source distribution like OpenJDK.

### Installation Steps

1.  **Save the Code**: Ensure all `.java` files (`Tourist.java`, `TouristService.java`, `TouristSearchPanel.java`, `TouristSearchApp.java`) are saved in the same directory (e.g., `ricercaturista/`).

2.  **Open Terminal/Command Prompt**: Navigate to the directory where you saved the Java files.

3.  **Compile the Code**: Use the Java compiler (`javac`) to compile all `.java` files.

    ```bash
    javac *.java
    ```

    If compilation is successful, several `.class` files will be generated in the same directory.

## ▶️ How to Use/Play It

Once the code is compiled, you can run the application.

1.  **Run the Application**: From the same terminal/command prompt, execute the main class `TouristSearchApp`.

    ```bash
    java TouristSearchApp
    ```

2.  **Access the Search Functionality**:
    *   A graphical user interface (GUI) window titled "RICERCATURISTA - Tourist Search System" will appear.
    *   The top part of the window displays the "Search Parameters" form with input fields for "First Name", "Last Name", "Country", and "Email".
    *   Below the form, an empty table titled "Search Results" will be visible.

3.  **Fill Out the Form and Submit**:
    *   Enter your desired search criteria into one or more of the input fields. You can use partial names or specific values. The search is case-insensitive and performs partial matching.
        *   **Example 1: Search by Country**
            *   Enter `USA` in the "Country" field.
        *   **Example 2: Search by First Name and Email**
            *   Enter `Alice` in the "First Name" field.
            *   Enter `example.com` in the "Email" field.
        *   **Example 3: Empty Search (will return all results)**
            *   Leave all fields empty.
    *   Click the "Search Tourists" button.

4.  **Processing the Request**:
    *   The "Search Tourists" button will temporarily change its text to "Searching..." and become disabled to indicate that a search is in progress.
    *   The system simulates a network delay (between 200ms and 1 second) before returning results, mimicking real-world network conditions.
    *   There is a small (5%) chance of a simulated "Connection Interruption to the server ETOUR". If this occurs, an error dialog box will be displayed.

5.  **View Search Results**:
    *   If the search is successful and matches are found, the "Search Results" table will populate with the details of the tourist accounts that meet your criteria.
    *   If no accounts match your criteria, a message dialog "No tourist accounts found matching your criteria." will appear.
    *   If a simulated connection interruption occurs, an error message "Error: Interruption of the connection to the server ETOUR." will be displayed. Click "OK" to dismiss it.
    *   You can sort the results by clicking on the column headers (e.g., "First Name", "Country").

6.  **Performing New Searches**:
    *   You can modify the search criteria in the input fields and click "Search Tourists" again to perform a new search. The table will update with the new results.

This concludes the user manual for the `RICERCATURISTA` system.
```