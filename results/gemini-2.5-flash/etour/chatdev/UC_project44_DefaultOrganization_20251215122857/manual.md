```
# RICHIESTACONVENZIONE - Convention Request System User Manual

## Introduction

The "RICHIESTACONVENZIONE - Convention Request System" is a Java application designed for Point Of Restaurant Operators to formalize and send requests for conventions to an involved agency. This system streamlines the process of data entry, validation, and submission, ensuring a structured approach to proposing agreements. It provides a user-friendly graphical interface (GUI) to guide the operator through each step, from initiating a request to receiving confirmation of its dispatch.

## Main Features

This application facilitates the following key interactions as per the RICHIESTACONVENZIONE use case:

1.  **Initiate Convention Request**: Operators can signal their intent to request a new convention by clicking a prominent button on the initial screen.
2.  **Data Entry Form Display**: Upon initiation, the system presents a comprehensive form where operators can input all necessary details for the convention.
3.  **Convention Data Input**: Users can enter specific details such as:
    *   **Convention Name**: Unique identifier or title for the agreement.
    *   **Agency Name**: The name of the agency with which the convention is being sought.
    *   **Start Date (YYYY-MM-DD)**: The proposed start date for the convention.
    *   **End Date (YYYY-MM-DD)**: The proposed end date for the convention.
    *   **Terms & Conditions**: A detailed description of the agreement's terms.
4.  **Data Validation**: Before submission, the system performs an internal validation of the entered data, checking for completeness and correct date formats (YYYY-MM-DD) and logical order (start date not after end date).
5.  **Confirmation & Review**: After successful validation, the system displays a summary of the entered data and requests explicit confirmation from the operator before proceeding.
6.  **Send Request to Agency**: Upon confirmation, the application simulates sending the convention request to the agency. This process includes a simulated network interaction that might experience interruptions.
7.  **Operation Cancellation**: At any point during the data entry stage, operators have the option to cancel the operation, returning to the initial state without submitting the request.
8.  **Status and Error Feedback**: The system provides clear pop-up messages for successful submissions, data validation errors, and simulated connection issues.

## Environment Dependencies Installation

To compile and run this Java application, you need to have a Java Development Kit (JDK) installed on your system.

### Recommended System Requirements:
*   **Java Development Kit (JDK) 8 or higher**: This application uses modern Java features and Swing for the GUI.

### Installation Steps:

1.  **Check for existing JDK**:
    Open your terminal or command prompt and type:
    ```bash
    java -version
    javac -version
    ```
    If these commands return version information, you likely have Java installed. Ensure the version is 8 or higher.

2.  **Download and Install JDK**:
    If you don't have JDK, or need a newer version, download it from one of the following sources:
    *   **Oracle JDK**: Visit [Oracle's Java website](https://www.oracle.com/java/technologies/downloads/).
    *   **OpenJDK (recommended for general use)**: Adoptium (formerly AdoptOpenJDK) is a popular choice. Visit [Adoptium's website](https://adoptium.net/).
    *   Follow the installation instructions provided by the respective download site for your operating system (Windows, macOS, Linux).

3.  **Set JAVA_HOME (Optional but Recommended)**:
    Setting the `JAVA_HOME` environment variable is good practice, especially for larger projects or IDEs.
    *   **Windows**:
        *   Search for "Environment Variables" and select "Edit the system environment variables".
        *   Click "Environment Variables..." button.
        *   Under "System variables", click "New...".
        *   Variable name: `JAVA_HOME`
        *   Variable value: The path to your JDK installation directory (e.g., `C:\Program Files\Java\jdk-17`).
        *   Find the `Path` variable under "System variables", select it, and click "Edit...".
        *   Add `%JAVA_HOME%\bin` to the Path.
        *   Click OK on all windows.
    *   **macOS / Linux**:
        *   Open your terminal and edit your shell's configuration file (e.g., `~/.bash_profile`, `~/.zshrc`, `~/.profile`).
        *   Add the following lines (adjust the path to your JDK):
            ```bash
            export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home # Example path for macOS
            export PATH=$JAVA_HOME/bin:$PATH
            ```
        *   Save the file and then run `source ~/.bash_profile` (or your respective file) to apply changes, or restart your terminal.

## How to Use/Play It

Follow these steps to compile and run the RICHIESTACONVENZIONE application:

### 1. Save the Source Code

Create three `.java` files in the same directory: `ConventionData.java`, `ConventionService.java`, and `ConventionRequestApp.java`.

*   **`ConventionData.java`**:
    ```java
    /*
     * DOCSTRING:
     * This class represents the data model for a Convention Request.
     * It holds details like convention name, agency, start and end dates, and terms.
     * It also includes a basic validation method to check if the data is sufficient.
     */
    import java.time.LocalDate;
    import java.time.format.DateTimeParseException;
    class ConventionData {
        private String conventionName;
        private String agencyName;
        private String startDate;
        private String endDate;
        private String terms;
        /**
         * Constructor for ConventionData.
         * @param conventionName The name or title of the convention.
         * @param agencyName The name of the agency involved.
         * @param startDate The proposed start date of the convention (e.g., "YYYY-MM-DD").
         * @param endDate The proposed end date of the convention (e.g., "YYYY-MM-DD").
         * @param terms The terms and conditions of the convention.
         */
        public ConventionData(String conventionName, String agencyName, String startDate, String endDate, String terms) {
            this.conventionName = conventionName;
            this.agencyName = agencyName;
            this.startDate = startDate;
            this.endDate = endDate;
            this.terms = terms;
        }
        // --- Getters for the fields ---
        public String getConventionName() {
            return conventionName;
        }
        public String getAgencyName() {
            return agencyName;
        }
        public String getStartDate() {
            return startDate;
        }
        public String getEndDate() {
            return endDate;
        }
        public String getTerms() {
            return terms;
        }
        /**
         * Validates the convention data.
         * Checks if all required fields are non-empty, and additionally validates
         * the format and logical order of start and end dates.
         *
         * In a real-world scenario, this would include date format validation,
         * logical date order checks (start before end), etc.
         *
         * @return true if all essential fields are filled and dates are valid, false otherwise.
         */
        public boolean isValid() {
            // Basic check for all fields being non-null and non-empty after trimming.
            if (conventionName == null || conventionName.trim().isEmpty() ||
                agencyName == null || agencyName.trim().isEmpty() ||
                startDate == null || startDate.trim().isEmpty() ||
                endDate == null || endDate.trim().isEmpty() ||
                terms == null || terms.trim().isEmpty()) {
                return false;
            }
            // Robust date validation: Check format and logical order (start_date <= end_date).
            try {
                LocalDate start = LocalDate.parse(startDate);
                LocalDate end = LocalDate.parse(endDate);
                // Ensure start date is not after end date.
                if (start.isAfter(end)) {
                    return false;
                }
            } catch (DateTimeParseException e) {
                // If date strings are not in YYYY-MM-DD format, parsing will fail.
                // This is considered invalid data as per the use case's need for correct input.
                return false;
            }
            return true;
        }
        @Override
        public String toString() {
            return "Convention Details:\n" +
                   "  Name: " + conventionName + "\n" +
                   "  Agency: " + agencyName + "\n" +
                   "  Start Date: " + startDate + "\n" +
                   "  End Date: " + endDate + "\n" +
                   "  Terms: " + terms;
        }
    }
    ```

*   **`ConventionService.java`**:
    ```java
    /*
     * DOCSTRING:
     * This class simulates the service layer responsible for handling convention requests.
     * In a real application, this would interact with a backend system, database, or external APIs.
     * For this example, it simply prints the request details and simulates success/failure.
     */
    class ConventionService {
        /**
         * Simulates sending a convention request to the agency.
         * This method represents the "Send the request to the Convention" step.
         * It randomly simulates success or failure, mimicking potential network issues or
         * backend processing errors for the "Interruption of the connection to the server ETOUR"
         * quality requirement.
         * @param data The ConventionData object containing the request details.
         * @return true if the request was "sent" successfully, false otherwise.
         */
        public boolean sendConventionRequest(ConventionData data) {
            System.out.println("Processing convention request for:");
            System.out.println(data.toString());
            // Simulate network delay or processing time
            try {
                Thread.sleep(1500); // Simulate 1.5 seconds of processing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Convention sending interrupted unexpectedly.");
                return false;
            }
            // Simulate success/failure randomly to cover "Interruption of the connection to the server ETOUR"
            // and other potential backend issues.
            // Approximately 80% chance of success, 20% chance of failure (simulated error).
            if (Math.random() < 0.8) {
                System.out.println("Convention request successfully sent to the Agency!");
                // Exit condition: "The notification about the call for the Convention to the Agency."
                return true;
            } else {
                System.err.println("Failed to send convention request. Server ETOUR connection interrupted or processing error.");
                return false; // Simulates an error case (e.g., server ETOUR interruption)
            }
        }
    }
    ```

*   **`ConventionRequestApp.java`**:
    ```java
    /*
     * DOCSTRING:
     * This is the main application class for the RICHIESTACONVENZIONE use case.
     * It provides a graphical user interface (GUI) for the Point Of Restaurant Operator
     * to request a convention with an agency.
     * It handles the flow of events: enabling functionality, displaying a form,
     * collecting data, validating, confirming, and simulating sending the request.
     *
     * This application uses Java Swing for the GUI.
     */
    import javax.swing.*;           // For GUI components
    import java.awt.*;              // For layout managers and basic GUI classes
    import java.awt.event.ActionEvent; // For handling action events (button clicks)
    import java.awt.event.ActionListener; // For listening to action events
    import javax.swing.SwingWorker; // For performing background tasks
    public class ConventionRequestApp extends JFrame implements ActionListener {
        // GUI Components
        private JPanel mainPanel; // Main panel holding all other components
        private JButton requestConventionButton; // Button to initiate the convention request
        private JPanel conventionFormPanel; // Panel for the convention data entry form
        // Form fields
        private JTextField conventionNameField;
        private JTextField agencyNameField;
        private JTextField startDateField;
        private JTextField endDateField;
        private JTextArea termsArea;
        private JButton submitButton; // Button to submit the form data
        private JButton cancelButton; // Button to cancel the form entry
        private JLabel statusLabel; // Label to show processing status
        // Serv
        private ConventionService conventionService;
        /**
         * Constructor for the ConventionRequestApp.
         * Initializes the JFrame and sets up the user interface.
         */
        public ConventionRequestApp() {
            super("RICHIESTACONVENZIONE - Convention Request System"); // Set window title
            this.conventionService = new ConventionService(); // Initialize the service layer
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
            setSize(800, 600); // Set initial window size
            setLocationRelativeTo(null); // Center the window on the screen
            createUI(); // Call method to set up all GUI components
        }
        /**
         * Initializes and lays out all GUI components.
         * This method covers steps 1 and 2 of the "Flow of events" regarding initial display.
         */
        private void createUI() {
            mainPanel = new JPanel(new CardLayout()); // Use CardLayout to switch between views
            // --- Initial View: Request Convention Button ---
            JPanel initialPanel = new JPanel(new GridBagLayout()); // Using GridBagLayout for centering
            requestConventionButton = new JButton("Request New Convention");
            requestConventionButton.setFont(new Font("Arial", Font.BOLD, 24)); // Make button prominent
            requestConventionButton.addActionListener(this); // Register action listener
            // Entry condition: "The Point Of Restaurant Operator has successfully authenticated to the system."
            // For this example, we assume authentication is successful, so the button is immediately enabled.
            requestConventionButton.setEnabled(true); // Step 1: Enable the functionality
            initialPanel.add(requestConventionButton); // Add button to the initial panel
            mainPanel.add(initialPanel, "INITIAL_VIEW"); // Add initial panel to main panel
            // --- Convention Form View ---
            conventionFormPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for form layout
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8); // Padding
            // Set up labels and text fields for the form
            // Convention Name
            gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
            conventionFormPanel.add(new JLabel("Convention Name:"), gbc);
            gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTALLY;
            conventionNameField = new JTextField(30);
            conventionFormPanel.add(conventionNameField, gbc);
            // Agency Name
            gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
            conventionFormPanel.add(new JLabel("Agency Name:"), gbc);
            gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTALLY;
            agencyNameField = new JTextField(30);
            conventionFormPanel.add(agencyNameField, gbc);
            // Start Date
            gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
            conventionFormPanel.add(new JLabel("Start Date (YYYY-MM-DD):"), gbc);
            gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTALLY;
            startDateField = new JTextField(15);
            conventionFormPanel.add(startDateField, gbc);
            // End Date
            gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
            conventionFormPanel.add(new JLabel("End Date (YYYY-MM-DD):"), gbc);
            gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTALLY;
            endDateField = new JTextField(15);
            conventionFormPanel.add(endDateField, gbc);
            // Terms and Conditions
            gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
            conventionFormPanel.add(new JLabel("Terms & Conditions:"), gbc);
            gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 2; gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 1.0;
            termsArea = new JTextArea(8, 30); // 8 rows, 30 columns
            termsArea.setLineWrap(true); // Wraps lines
            termsArea.setWrapStyleWord(true); // Wraps at word boundaries
            JScrollPane scrollPane = new JScrollPane(termsArea);
            conventionFormPanel.add(scrollPane, gbc);
            // Add a status label for showing progress
            statusLabel = new JLabel(" "); // Initialize with empty text
            statusLabel.setForeground(Color.BLUE); // Optional: style the label
            statusLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            statusLabel.setVisible(false); // Initially hidden
            gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 3; gbc.anchor = GridBagConstraints.CENTER;
            conventionFormPanel.add(statusLabel, gbc);
            // Buttons for form submission/cancellation
            submitButton = new JButton("Submit Convention Request");
            submitButton.addActionListener(this);
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(submitButton);
            buttonPanel.add(cancelButton);
            gbc.gridx = 1; gbc.gridy = 7; gbc.gridwidth = 2; gbc.gridheight = 1; // Adjusted gridy to be after statusLabel
            gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.EAST; gbc.weighty = 0;
            conventionFormPanel.add(buttonPanel, gbc);
            mainPanel.add(conventionFormPanel, "FORM_VIEW"); // Add form panel to main panel
            add(mainPanel); // Add the main panel to the JFrame
        }
        /**
         * Handles action events from GUI components (button clicks).
         * This method orchestrates the different steps of the use case.
         * @param e The ActionEvent generated by a component.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == requestConventionButton) {
                // Step 2: Displays a form for entering data of the Convention.
                displayConventionForm();
            } else if (e.getSource() == submitButton) {
                // Step 3: Inserts the data in the form of the agreement and submit.
                // This is primarily handled by the user interacting with the text fields.
                // When submit is clicked, we move to verification and sending.
                submitConventionRequest();
            } else if (e.getSource() == cancelButton) {
                // Exit condition: "Restaurant Point Of Operator cancels the operation."
                // Clear form and return to initial view.
                clearForm();
                showInitialView();
                JOptionPane.showMessageDialog(this, "Convention request cancelled.", "Cancellation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        /**
         * Switches the view to display the convention request form.
         * Corresponds to "2 Displays a form for entering data of the Convention."
         */
        private void displayConventionForm() {
            CardLayout cl = (CardLayout)(mainPanel.getLayout());
            cl.show(mainPanel, "FORM_VIEW"); // Show the form panel
            setTitle("RICHIESTACONVENZIONE - Enter Convention Details");
        }
        /**
         * Processes the submitted convention data.
         * This method encapsulates Step 4 (Verify data and confirm) and Step 6 (Send request).
         * It uses a SwingWorker to send the request in a background thread to keep the UI responsive.
         */
        private void submitConventionRequest() {
            // Step 3: Data is inserted via JTextFields/JTextAreas.
            String conventionName = conventionNameField.getText();
            String agencyName = agencyNameField.getText();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            String terms = termsArea.getText();
            ConventionData data = new ConventionData(conventionName, agencyName, startDate, endDate, terms);
            // Step 4: Verify the data entered.
            if (!data.isValid()) {
                // Where the data is invalid or insufficient, the system activates the use case Errored.
                // For this example, "Errored" is represented by an error message dialog.
                handleError("Invalid or insufficient data provided.\n" +
                            "Please ensure all fields are filled, and dates are in YYYY-MM-DD format with a valid range (start date not after end date).");
                return;
            }
            // Ask for confirmation of the request (Step 4 continued, Step 5).
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Please confirm the convention details:\n\n" + data.toString(),
                    "Confirm Convention Request",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirmation == JOptionPane.YES_OPTION) {
                // Confirmation given (Step 5 completed).
                // Disable buttons to prevent further actions while the request is being sent.
                // This provides visual feedback that an operation is in progress.
                submitButton.setEnabled(false);
                cancelButton.setEnabled(false);
                // Show status message to the user
                statusLabel.setText("Sending convention request... Please wait.");
                statusLabel.setVisible(true); // Make the label visible
                // Ensure UI updates immediately
                conventionFormPanel.revalidate();
                conventionFormPanel.repaint();
                // Use SwingWorker to perform the potentially long-running operation
                // in a background thread, keeping the UI responsive.
                new SwingWorker<Boolean, Void>() {
                    @Override
                    protected Boolean doInBackground() throws Exception {
                        // This code runs in a separate background thread, not the EDT.
                        // Step 6: Send the request to the Convention.
                        return conventionService.sendConventionRequest(data);
                    }
                    @Override
                    protected void done() {
                        // This code runs back on the Event Dispatch Thread (EDT) after doInBackground completes.
                        // Hide loading indicator regardless of outcome
                        statusLabel.setVisible(false);
                        statusLabel.setText(" "); // Clear text
                        try {
                            boolean success = get(); // Retrieve the result from doInBackground()
                            if (success) {
                                // Exit condition: "The notification about the call for the Convention to the Agency."
                                JOptionPane.showMessageDialog(ConventionRequestApp.this,
                                        "Convention request successfully sent to the agency!",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                                clearForm();        // Clear the form fields
                                showInitialView();  // Return to the initial view
                            } else {
                                // This branch handles simulated "Interruption of the connection to the server ETOUR"
                                // or other service-level errors.
                                handleError("Failed to send convention request. Please try again later.");
                            }
                        } catch (Exception ex) {
                            // Handle any exceptions that occurred during the background task.
                            handleError("An unexpected error occurred: " + ex.getMessage());
                        } finally {
                            // Re-enable the buttons after the operation is complete,
                            // allowing the user to interact with the application again.
                            submitButton.setEnabled(true);
                            cancelButton.setEnabled(true);
                            // If showInitialView() was called, the requestConventionButton for the initial
                            // view is implicitly available/enabled.
                        }
                    }
                }.execute(); // Start the SwingWorker, which begins execution of doInBackground()
            } else {
                // User cancelled confirmation (effectively "Restaurant Point Of Operator cancels the operation.")
                JOptionPane.showMessageDialog(this,
                        "Convention request not sent. Operation cancelled by user.",
                        "Cancelled",
                        JOptionPane.INFORMATION_MESSAGE);
                // No need for a status message here as the operation wasn't initiated
            }
        }
        /**
         * Simulates the "Errored" use case by displaying an error message.
         * @param message The error message to display.
         */
        private void handleError(String message) {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
        /**
         * Clears all input fields in the form.
         */
        private void clearForm() {
            conventionNameField.setText("");
            agencyNameField.setText("");
            startDateField.setText("");
            endDateField.setText("");
            termsArea.setText("");
        }
        /**
         * Switches the view back to the initial panel with the "Request New Convention" button.
         */
        private void showInitialView() {
            CardLayout cl = (CardLayout)(mainPanel.getLayout());
            cl.show(mainPanel, "INITIAL_VIEW");
            setTitle("RICHIESTACONVENZIONE - Convention Request System");
        }
        /**
         * Main method to run the application.
         * @param args Command line arguments (not used).
         */
        public static void main(String[] args) {
            // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
            SwingUtilities.invokeLater(() -> {
                ConventionRequestApp app = new ConventionRequestApp();
                app.setVisible(true); // Make the JFrame visible
            });
        }
    }
    ```

### 2. Compile the Java Source Code

1.  Open your terminal or command prompt.
2.  Navigate to the directory where you saved the `.java` files.
3.  Compile the files using the Java compiler (`javac`):
    ```bash
    javac ConventionData.java ConventionService.java ConventionRequestApp.java
    ```
    If compilation is successful, you will see new `.class` files generated in the same directory (e.g., `ConventionData.class`, `ConventionService.class`, `ConventionRequestApp.class`). If there are errors, ensure your JDK is correctly installed and configured, and that the code was copied exactly.

### 3. Run the Application

Once compiled, you can run the main application class:

```bash
java ConventionRequestApp
```

A GUI window titled "RICHIESTACONVENZIONE - Convention Request System" will appear.

### 4. Interacting with the Application

1.  **Initial Screen**:
    *   You will see a large button labeled "**Request New Convention**". This simulates the functionality being enabled after the operator has successfully authenticated (an assumed precondition).

2.  **Request a New Convention**:
    *   Click the "**Request New Convention**" button.
    *   The window will transition to a form titled "RICHIESTACONVENZIONE - Enter Convention Details".

3.  **Fill Out the Form**:
    *   Enter details into the respective fields:
        *   **Convention Name**: e.g., `Summer Marketing Campaign 2024`
        *   **Agency Name**: e.g., `Global Ad Ventures`
        *   **Start Date (YYYY-MM-DD)**: e.g., `2024-06-15`
        *   **End Date (YYYY-MM-DD)**: e.g., `2024-08-31`
        *   **Terms & Conditions**: e.g., `This convention outlines the terms for advertising serv, including budget allocation, deliverables, and payment schedule. All intellectual property remains with the restaurant.`
    *   **Try invalid data**: Experiment by leaving fields empty or entering incorrect date formats (e.g., `June 15, 2024`) or a start date after the end date (e.g., `Start: 2024-08-01`, `End: 2024-07-31`). This will trigger the validation error message.

4.  **Submit or Cancel**:
    *   **Submit**: Click the "**Submit Convention Request**" button.
        *   If data is invalid, an "Error" message will pop up, detailing the issue.
        *   If data is valid, a "Confirm Convention Request" dialog will appear, showing your entered details.
    *   **Cancel**: Click the "**Cancel**" button. This will clear the form, display a "Convention request cancelled" message, and return you to the initial screen. This fulfills the exit condition "Restaurant Point Of Operator cancels the operation."

5.  **Confirming Submission**:
    *   In the confirmation dialog, click "**Yes**" to proceed with sending the request.
    *   The form will show a "Sending convention request... Please wait." message as the `ConventionService` simulates processing.
    *   **Success Scenario**: Most of the time (80% chance), after a short delay (1.5 seconds), a "Success" message will pop up: "Convention request successfully sent to the agency!". The form will then clear, and the application will revert to the initial screen with the "Request New Convention" button. This fulfills the exit condition "The notification about the call for the Convention to the Agency."
    *   **Failure Scenario**: Occasionally (20% chance), an "Error" message will pop up: "Failed to send convention request. Please try again later.". This simulates potential network issues or server interruptions ("Interruption of the connection to the server ETOUR"). You can try submitting again.

6.  **Continuous Operation**:
    *   You can initiate and submit multiple convention requests by repeating the process from step 2.

This manual should provide you with all the necessary information to set up and interact with the RICHIESTACONVENZIONE application effectively.