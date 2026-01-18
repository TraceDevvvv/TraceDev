```
# Cultural Heritage Insertion System

## Introducing the Cultural Heritage Insertion System

The "Cultural Heritage Insertion System" is a user-friendly application designed to allow Agency Operators to efficiently add new cultural heritage objects to a digital system. Built with Java Swing for its graphical user interface, this system prioritizes data integrity by preventing duplicate entries and ensuring proper validation of input data. It also incorporates mechanisms to simulate real-world scenarios, such as network interruptions, providing robust feedback to the operator.

## Main Functions

1.  **Cultural Object Insertion**: Provides a dedicated form for entering details of a new cultural good, including its name, description, and location.
2.  **Data Validation**: Automatically checks if all required fields (Name, Description, Location) are filled before attempting to save the data. If data is invalid or insufficient, it prompts the user.
3.  **Transaction Confirmation**: Before finalizing the insertion, the system requests explicit confirmation from the operator, allowing for a review of the entered data and an option to cancel the operation.
4.  **Duplicate Prevention**: Ensures that no two cultural heritage objects with the same name can be added to the system, upholding data uniqueness as a core quality requirement.
5.  **Error Handling**:
    *   Notifies the operator upon successful insertion.
    *   Informs the operator if an entered cultural good is a duplicate.
    *   Simulates and handles network connection interruptions (ETOUR condition), providing appropriate error messages.
    *   Allows operators to cancel the insertion process at certain stages.

## Environment Setup

To run the Cultural Heritage Insertion System, you will need a Java Development Kit (JDK) installed on your system.

*   **Java Development Kit (JDK)**: Ensure you have JDK 8 or a newer version installed.
    *   You can download the latest JDK from the [Oracle website](https://www.oracle.com/java/technologies/downloads/).
    *   Verify your installation by opening a terminal or command prompt and typing:
        ```bash
        java -version
        javac -version
        ```
        This should display the installed Java version.

No other external libraries or dependencies are required, as the application uses standard Java features (Swing for GUI).

## How to Use/Play

Follow these steps to compile and run the Cultural Heritage Insertion System:

### 1. Save the Code Files

Save all the provided Java code snippets into separate `.java` files within a single directory. Make sure the filenames match the class names exactly (e.g., `CulturalHeritageApp.java`, `CulturalHeritage.java`, `NetworkErrorException.java`, `CulturalHeritageService.java`, `CulturalHeritageFormPanel.java`).

For example, create a folder named `CulturalHeritageSystem` and place all `.java` files inside it.

```
/CulturalHeritageSystem
├── CulturalHeritageApp.java
├── CulturalHeritage.java
├── NetworkErrorException.java
├── CulturalHeritageService.java
└── CulturalHeritageFormPanel.java
```

### 2. Compile the Application

Open your terminal or command prompt, navigate to the directory where you saved the files (`CulturalHeritageSystem` in our example), and compile the Java source files:

```bash
cd /path/to/CulturalHeritageSystem
javac *.java
```
If compilation is successful, you will see no output, and `.class` files will be generated in the same directory.

### 3. Run the Application

Execute the compiled application from the same terminal:

```bash
java CulturalHeritageApp
```

### 4. Operator Login Simulation (Entry Condition)

Upon launching, the system will first present a dialog box:

*   **"Simulate agency operator login?"**: This dialog simulates the "Operator conditions: The agency has logged" entry condition of the use case.
*   Click **"Yes"** to proceed and open the main application window.
*   Clicking **"No"** or closing the dialog will exit the application, as an operator login is a prerequisite.

### 5. Using the Insertion Form (Flow of Events)

Once logged in, the "Cultural Heritage Insertion System" window will appear, displaying a form with the following fields:

*   **Name**: Text field for the cultural object's name.
*   **Description**: Text area for a detailed description.
*   **Location**: Text field for the cultural object's location.
*   **Submit Cultural Good**: Button to submit the form.

#### **Scenario 1: Successful Insertion**

1.  **Fill out the form**:
    *   **Name**: `Taj Mahal`
    *   **Description**: `An ivory-white marble mausoleum on the right bank of the river Yamuna in Agra, Uttar Pradesh, India.`
    *   **Location**: `Agra, India`
2.  Click **"Submit Cultural Good"**.
3.  **Confirmation Dialog**: A confirmation dialog will appear, displaying the data you entered.
4.  Click **"Yes"** to confirm.
5.  **Success Notification**: You will receive a message: `"Cultural good 'Taj Mahal' successfully inserted!"`. The form fields will then clear, ready for the next entry.

#### **Scenario 2: Invalid or Insufficient Data (Use Case Errored)**

1.  **Attempt to submit with empty fields**:
    *   Leave one or more fields (e.g., Name) empty.
2.  Click **"Submit Cultural Good"**.
3.  **Error Message**: The system will display an error message like `"Name cannot be empty."`, indicating that the data is insufficient according to the `Errored` use case.
4.  The form will remain populated, allowing you to correct the input.

#### **Scenario 3: Duplicate Cultural Heritage (Quality Requirement)**

The system pre-loads some cultural heritage objects (e.g., "Eiffel Tower", "Great Wall of China").

1.  **Attempt to enter a duplicate**:
    *   **Name**: `Eiffel Tower`
    *   **Description**: `_ (Any valid description)`
    *   **Location**: `_ (Any valid location)`
2.  Click **"Submit Cultural Good"**.
3.  **Confirmation Dialog**: Confirm the operation with "Yes".
4.  **Duplicate Entry Notification**: You will receive a warning message: `"Error: Cultural good with name 'Eiffel Tower' already exists."`. The entry will not be added to the system.

#### **Scenario 4: Operator Cancels Operation (Exit Condition)**

1.  **Fill out the form** with any valid data.
2.  Click **"Submit Cultural Good"**.
3.  **Confirmation Dialog**: When the confirmation dialog appears, click **"No"**.
4.  **Cancellation Notification**: A message will appear: `"Operation cancelled by Agency Operator."`. The data will not be saved.

#### **Scenario 5: Interruption of Connection to Server ETOUR (Exit Condition)**

The system simulates a network error if the cultural good's name contains "ETOUR".

1.  **Trigger a network error**:
    *   **Name**: `My ETOUR Monument` (or `etour site`, etc., case-insensitive)
    *   **Description**: `_ (Any valid description)`
    *   **Location**: `_ (Any valid location)`
2.  Click **"Submit Cultural Good"**.
3.  **Confirmation Dialog**: Confirm the operation with "Yes".
4.  **Network Error Notification**: You will receive an error message: `"Connection Error (ETOUR): Connection to server ETOUR interrupted. Please try again later."`. The data will not be saved.

By interacting with these scenarios, you can fully experience the planned functionality and error handling of the Cultural Heritage Insertion System.

```