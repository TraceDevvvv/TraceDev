```markdown
# Tourist Feedback System

## Introduce Main Functions

The Tourist Feedback System is a Java Swing application designed to simulate the process of a tourist providing feedback for a specific site they are currently visiting. It implements the "INSERISCIFEEDBACK" (Insert Feedback) use case, guiding the user through the steps of selecting a tourist and a site, activating the feedback feature, submitting feedback (vote and comment), and handling various scenarios such as invalid data, previously submitted feedback, and network interruptions.

**Key Features:**

*   **Tourist Selection:** Allows users to select from a list of pre-configured tourists.
*   **Site Selection:** Enables selection of a tourist attraction or site for which feedback is intended.
*   **Location Simulation:** A dedicated control allows setting or changing the tourist's current physical location, crucial for entry condition checks.
*   **Feedback Activation:** Initiates the feedback process, performing initial validation checks.
*   **Feedback Form:** Provides a user-friendly form for entering a numerical vote (1-5) and a written comment.
*   **Data Validation:** Verifies the validity of the submitted feedback data (e.g., vote range, non-empty comment).
*   **Duplicate Feedback Check:** Prevents tourists from submitting multiple feedbacks for the same site.
*   **Location-Based Feedback:** Ensures that a tourist can only give feedback for a site they are currently "located" at.
*   **Network Interruption Simulation:** Includes a feature to simulate server connection issues (ETOUR server) to test error handling.
*   **Cancellation Functionality:** Allows the tourist to cancel the feedback submission process at any point.
*   **Clear Notifications:** Provides clear success and error messages to the user.

## How to Install Environment Dependencies

This project is developed in Java and utilizes standard Java libraries, primarily Java Swing for its graphical user interface. There are no external third-party libraries or frameworks required beyond the Java Development Kit (JDK).

**Prerequisites:**

1.  **Java Development Kit (JDK) 8 or higher:** You need a JDK installed on your system to compile and run the Java source code.
    *   **Verification:** To check if you have Java installed and verify its version, open your terminal or command prompt and type:
        ```bash
        java -version
        javac -version
        ```
    *   If you don't have a JDK installed, you can download it from Oracle's website or use an open-source distribution like Adoptium (Eclipse Temurin).

## How to Compile and Run the Application

Follow these steps to compile and run the Tourist Feedback System:

1.  **Save the Source Files:**
    Save all the provided Java code snippets into individual `.java` files in a single directory. Make sure the filename matches the class name (e.g., `Feedback.java`, `Site.java`, `Tourist.java`, `InvalidFeedbackDataException.java`, `NetworkInterruptionException.java`, `FeedbackManager.java`, `FeedbackAppGUI.java`).

    Your directory structure should look something like this:
    ```
    /your_project_directory
    ├── Feedback.java
    ├── Site.java
    ├── Tourist.java
    ├── InvalidFeedbackDataException.java
    ├── NetworkInterruptionException.java
    ├── FeedbackManager.java
    └── FeedbackAppGUI.java
    ```

2.  **Open a Terminal/Command Prompt:**
    Navigate to the directory where you saved your Java files using the `cd` command.
    ```bash
    cd /path/to/your_project_directory
    ```

3.  **Compile the Java Code:**
    Use the `javac` command to compile all the Java source files.
    ```bash
    javac *.java
    ```
    This command will compile all `.java` files in the current directory, generating corresponding `.class` files. If there are no errors, you will return to the command prompt without any output.

4.  **Run the Application:**
    Once compiled, run the main application using the `java` command, specifying the class that contains the `main` method (which is `FeedbackAppGUI`).
    ```bash
    java FeedbackAppGUI
    ```
    A graphical user interface (GUI) window titled "Tourist Feedback System" should appear.

## How to Use/Play It

The application will launch with a main window where you can select a tourist and a site, simulate the tourist's current location, and then proceed to give feedback.

### Getting Started

1.  **Main Window:**
    *   You will see three dropdown menus (`JComboBox`):
        *   **Select Tourist:** Pre-populated with "Alice Smith" (T101) and "Bob Johnson" (T102).
        *   **Tourist's Current Location:** Shows where the selected tourist is currently "located". You can change this to simulate the tourist moving to a different site.
        *   **Select Site for Feedback:** Shows the available sites (Eiffel Tower, Colosseum, Statue of Liberty). This is the target site for feedback.
    *   A checkbox labeled "Force ETOUR connection failure (100%)" allows you to simulate network issues.
    *   Two buttons: "Activate Feedback Feature" and "Exit Application".

2.  **Sample Data Overview:**
    The application starts with the following pre-configured state:
    *   **Alice Smith (T101):**
        *   Currently at: Eiffel Tower (S001)
        *   Has *already* visited and given feedback for: Colosseum (S002)
    *   **Bob Johnson (T102):**
        *   Currently at: Colosseum (S002)
        *   Has visited (but *not* given feedback for): Statue of Liberty (S003)

### Basic Flow: Giving Feedback Successfully

Let's guide Bob Johnson to give feedback for the Statue of Liberty:

1.  **Select Tourist:** In the "Select Tourist" dropdown, choose "Bob Johnson (T102)".
2.  **Set Tourist's Current Location:** Bob needs to be physically at the Statue of Liberty to give feedback for it. In the "Tourist's Current Location" dropdown, select "Statue of Liberty (S003)".
    *   _Behind the scenes:_ This updates Bob's `currentSite` to S003.
3.  **Select Site for Feedback:** In the "Select Site for Feedback" dropdown, choose "Statue of Liberty (S003)".
4.  **Activate Feedback Feature:** Click the "Activate Feedback Feature" button.
    *   The system will verify:
        *   Is Bob at the Statue of Liberty? (Yes, you just set his location).
        *   Has Bob already given feedback for the Statue of Liberty? (No, based on sample data).
    *   Since both conditions are met, a new "Provide Feedback" window will open.
5.  **Fill out Feedback Form:**
    *   **Vote (1-5):** Use the spinner to select a vote (e.g., 5).
    *   **Comment:** Type a comment (e.g., "Absolutely breathtaking! A must-see monument.").
6.  **Submit Feedback:** Click the "Submit Feedback" button.
    *   The system will validate the data (vote 1-5, non-empty comment).
    *   A confirmation message "Feedback for Statue of Liberty (S003) successfully submitted!" will appear.
    *   The "Provide Feedback" window will close.

Congratulations! You have successfully submitted feedback.

### Exploring Edge Cases and Alternative Flows

This application is designed to demonstrate several use case scenarios:

#### 1. Feedback Already Issued (`FeedbackGiàRilasciato` use case)

*   **Goal:** Try to make Alice give feedback for the Colosseum.
*   **Steps:**
    1.  Select "Alice Smith (T101)" in "Select Tourist".
    2.  Set "Tourist's Current Location" to "Colosseum (S002)".
    3.  Select "Colosseum (S002)" in "Select Site for Feedback".
    4.  Click "Activate Feedback Feature".
*   **Expected Result:** A message popup will appear: "Feedback Already Issued - You have already submitted feedback for Colosseum (S002). Only one feedback per site is allowed for this use case." The feedback form will not open.

#### 2. Tourist Not at Selected Site (Entry Condition Failure)

*   **Goal:** Try to make Alice give feedback for the Colosseum while she's still at the Eiffel Tower.
*   **Steps:**
    1.  Select "Alice Smith (T101)" in "Select Tourist".
    2.  Ensure "Tourist's Current Location" is "Eiffel Tower (S001)" (her initial location).
    3.  Select "Colosseum (S002)" in "Select Site for Feedback".
    4.  Click "Activate Feedback Feature".
*   **Expected Result:** A message popup will appear: "Action Denied - The tourist card is not in the selected site (Colosseum (S002)). Please ensure the tourist is physically at the site before activating feedback." The feedback form will not open.

#### 3. Invalid or Insufficient Feedback Data (`Errored` use case)

*   **Goal:** Submit feedback with an empty comment.
*   **Steps:**
    1.  Follow the "Basic Flow" steps to open the feedback form (e.g., Bob for Statue of Liberty).
    2.  In the feedback form, select a vote (e.g., 5).
    3.  **Leave the "Comment" text area empty.**
    4.  Click "Submit Feedback".
*   **Expected Result:** A message popup will appear: "Invalid Data - Errored! - Invalid or insufficient feedback data. Vote must be 1-5, comment cannot be empty." The feedback form will remain open, allowing you to correct the data.

#### 4. Connection Interruption to ETOUR Server (Exit Condition)

*   **Goal:** Simulate a network error during feedback submission.
*   **Steps:**
    1.  **Before activating feedback:** Check the "Force ETOUR connection failure (100%)" checkbox in the main window.
    2.  Follow the "Basic Flow" steps to open the feedback form (e.g., Bob for Statue of Liberty).
    3.  Fill out valid feedback (e.g., Vote: 5, Comment: "Great experience!").
    4.  Click "Submit Feedback".
*   **Expected Result:** A message popup will appear: "Connection Error - ETOUR - Connection to ETOUR server interrupted. Please try again later." The feedback form will close, as the operation cannot be completed.

#### 5. Tourist Cancels Operation (Exit Condition)

*   **Goal:** Cancel feedback submission.
*   **Steps:**
    1.  Follow the "Basic Flow" steps to open the feedback form (e.g., Bob for Statue of Liberty).
    2.  In the feedback form, click the "Cancel" button.
*   **Expected Result:** A message popup will appear: "Operation Cancelled - Feedback submission cancelled by tourist." The feedback form will close.

This manual covers the core functionalities and interactive elements of the Tourist Feedback System. Enjoy exploring the various scenarios!

```