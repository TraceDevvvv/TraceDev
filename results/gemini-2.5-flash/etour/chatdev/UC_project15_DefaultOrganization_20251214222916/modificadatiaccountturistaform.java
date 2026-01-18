import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * DOCSTRING:
 * This class implements the graphical user interface (GUI) for the
 * MODIFICADATIACCOUNTTURISTA use case. It allows an 'Agency Operator'
 * to select a tourist, view their current data, modify it, and save the changes.
 * It leverages Swing components for the UI and interacts with TuristaDao
 * for data persistence and ErroredUseCase for error reporting.
 *
 * Flow of events (as per use case):
 * 1. Operator selects a tourist from a list (simulated by JComboBox).
 * 2. System loads and displays the selected tourist's data in editable fields.
 * 3. Operator edits fields and submits the changes.
 * 4. System validates information; if invalid, ErroredUseCase is activated.
 *    If valid, it asks for confirmation.
 * 5. Operator confirms the operation.
 * 6. System stores the modified data.
 *
 * MODIFICATION: Added a JCheckBox to simulate the ETOUR server connection status.
 *               Implemented a try-catch block in handleSaveButtonClick to gracefully
 *               handle connection interruptions as specified in the use case.
 *               Removed the addition of 'null' to the JComboBox when no tourists are found,
 *               providing a cleaner visual representation.
 */
class ModificaDatiAccountTuristaForm extends JFrame {
    private TuristaDao turistaDao; // Data Access Object for Turista
    private Turista selectedTurista; // The currently selected tourist for modification
    // GUI Components
    private JComboBox<Turista> turistaComboBox; // Dropdown to select a tourist (RicercaTurista simulation)
    private JTextField idField;
    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField emailField;
    private JTextField telefonoField;
    private JButton loadButton;
    private JButton saveButton;
    // New GUI component for simulating connection status to ETOUR server
    private JCheckBox etourConnectionCheckbox;
    /**
     * Constructor for ModificaDatiAccountTuristaForm.
     * Initializes the TuristaDao and sets up the GUI components.
     */
    public ModificaDatiAccountTuristaForm() {
        // Assume Agency Operator is logged in, and connection to ETOUR is established.
        this.turistaDao = new TuristaDao(); // Initialize the DAO
        initComponents(); // Set up all Swing components
        loadTuristiIntoComboBox(); // Populate the selection dropdown
    }
    /**
     * Initializes and arranges all GUI components within the JFrame.
     */
    private void initComponents() {
        setTitle("Modifica Dati Account Turista");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall layout
        // Panel for tourist selection and connection simulation checkbox
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        selectionPanel.setBorder(BorderFactory.createTitledBorder("Seleziona Turista"));
        turistaComboBox = new JComboBox<>();
        loadButton = new JButton("Carica Dati");
        selectionPanel.add(new JLabel("Turista:"));
        selectionPanel.add(turistaComboBox);
        selectionPanel.add(loadButton);
        // Panel for the ETOUR connection simulation checkbox
        JPanel connectionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        etourConnectionCheckbox = new JCheckBox("ETOUR Connesso");
        etourConnectionCheckbox.setSelected(true); // Default to connected
        connectionPanel.add(etourConnectionCheckbox);
        topPanel.add(selectionPanel, BorderLayout.WEST);
        topPanel.add(connectionPanel, BorderLayout.EAST); // Place connection checkbox on the right
        // Panel for data entry/modification form
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // 0 rows, 2 columns, with gaps
        formPanel.setBorder(BorderFactory.createTitledBorder("Dati Account Turista"));
        // Initialize text fields
        idField = new JTextField(20);
        idField.setEditable(false); // ID should not be modifiable once loaded
        nomeField = new JTextField(20);
        cognomeField = new JTextField(20);
        emailField = new JTextField(20);
        telefonoField = new JTextField(20);
        // Add labels and fields to the form panel
        formPanel.add(new JLabel("ID Turista:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Cognome:"));
        formPanel.add(cognomeField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Telefono:"));
        formPanel.add(telefonoField);
        // Panel for action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        saveButton = new JButton("Salva Modifiche");
        saveButton.setEnabled(false); // Initially disabled until a tourist is loaded
        buttonPanel.add(saveButton);
        // Add panels to the frame
        add(topPanel, BorderLayout.NORTH); // Add the top panel which includes selection and connection control
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        // Add Action Listeners
        loadButton.addActionListener(e -> handleLoadButtonClick());
        saveButton.addActionListener(e -> handleSaveButtonClick());
        // Listener for the ETOUR connection checkbox to update the DAO's status
        etourConnectionCheckbox.addActionListener(e -> turistaDao.setEtourConnectionStatus(etourConnectionCheckbox.isSelected()));
        pack(); // Adjusts frame size based on components
        setLocationRelativeTo(null); // Center the frame on the screen
    }
    /**
     * Populates the turistaComboBox with a list of available tourists.
     * This simulates getting a list from the "RicercaTurista" use case.
     */
    private void loadTuristiIntoComboBox() {
        List<Turista> turisti = turistaDao.getTuristi();
        turistaComboBox.removeAllItems(); // Clear existing items
        if (turisti.isEmpty()) {
            // If no tourists are available, the JComboBox will remain empty after removeAllItems().
            // This clearly indicates no selection can be made.
            loadButton.setEnabled(false);
            // Ensure the JComboBox visually updates to be empty
            turistaComboBox.revalidate();
            turistaComboBox.repaint();
        } else {
            for (Turista t : turisti) {
                turistaComboBox.addItem(t);
            }
            loadButton.setEnabled(true);
        }
    }
    /**
     * Handles the 'Carica Dati' (Load Data) button click event.
     * Loads the data of the selected tourist into the form fields.
     * This is step 2 in the use case flow.
     */
    private void handleLoadButtonClick() {
        Object selectedItem = turistaComboBox.getSelectedItem();
        if (selectedItem instanceof Turista) {
            selectedTurista = (Turista) selectedItem;
            loadSelectedTuristaData(selectedTurista);
            saveButton.setEnabled(true); // Enable save button once data is loaded
        } else {
            // No tourist selected or list is empty (should not happen if combobox is empty but button enabled)
            ErroredUseCase.displayError("Nessun turista selezionato per la modifica.");
            clearFormFields();
            saveButton.setEnabled(false);
        }
    }
    /**
     * Displays the data of the given Turista object in the form fields.
     *
     * @param turista The Turista object whose data is to be displayed.
     */
    private void loadSelectedTuristaData(Turista turista) {
        if (turista != null) {
            idField.setText(turista.getId());
            nomeField.setText(turista.getNome());
            cognomeField.setText(turista.getCognome());
            emailField.setText(turista.getEmail());
            telefonoField.setText(turista.getTelefono());
        } else {
            clearFormFields();
        }
    }
    /**
     * Clears all input fields in the form.
     */
    private void clearFormFields() {
        idField.setText("");
        nomeField.setText("");
        cognomeField.setText("");
        emailField.setText("");
        telefonoField.setText("");
        selectedTurista = null;
    }
    /**
     * Handles the 'Salva Modifiche' (Save Changes) button click event.
     * This method orchestrates steps 3, 4, 5, and 6 of the use case flow.
     * It includes validation, user confirmation, and error handling for
     * both invalid data and simulated connection interruptions.
     */
    private void handleSaveButtonClick() {
        if (selectedTurista == null) {
            ErroredUseCase.displayError("Selezionare un turista prima di salvare le modifiche.");
            return;
        }
        // Step 3: Edit the fields in the form and submit. (Data collected now)
        Turista updatedTurista = collectFormData();
        // Step 4: Verify the information and asks for confirmation.
        // Where the data is invalid or insufficient, the system activates the use case Errored.
        if (!validateData(updatedTurista)) {
            // Validation error message already shown by validateData method via ErroredUseCase
            return;
        }
        // Ask for confirmation (Step 4 & 5)
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Sei sicuro di voler salvare le modifiche per " + updatedTurista.getNome() + " " + updatedTurista.getCognome() + "?",
                "Conferma Modifica",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            // Step 5: Confirm the operation.
            // Step 6: Stores the modified data selected account.
            try {
                if (turistaDao.updateTurista(updatedTurista)) {
                    displaySuccessMessage("Dati del turista " + updatedTurista.getId() + " aggiornati con successo.");
                    // After successful update, clear the form and disable save button
                    clearFormFields();
                    saveButton.setEnabled(false);
                    // In a real system, this might trigger an event or a report generation.
                    // For this simulation, confirming the save and clearing the form signifies "reporting the information".
                } else {
                    // This 'else' branch handles the case where the tourist ID was not found in DAO,
                    // but there was no connection issue.
                    ErroredUseCase.displayError("Errore durante il salvataggio dei dati: Turista con ID '" + updatedTurista.getId() + "' non trovato o non esiste.");
                }
            } catch (RuntimeException ex) {
                // Catch the RuntimeException simulated for connection loss (exit condition 2)
                ErroredUseCase.displayError(ex.getMessage()); // Displays "Connessione al server ETOUR interrotta."
            }
        }
    }
    /**
     * Collects data from the form fields and creates a new Turista object
     * with the updated information. The ID is taken from the internally
     * stored selectedTurista to ensure we're updating the correct entry.
     *
     * @return A Turista object with the data from the form fields.
     */
    private Turista collectFormData() {
        // Use the ID of the originally selected tourist to ensure we're updating the correct one.
        // idField is not editable, so its content is always the original ID of selectedTurista
        String id = idField.getText();
        String nome = nomeField.getText().trim();
        String cognome = cognomeField.getText().trim();
        String email = emailField.getText().trim();
        String telefono = telefonoField.getText().trim();
        return new Turista(id, nome, cognome, email, telefono);
    }
    /**
     * Validates the data collected from the form.
     * The system activates the use case Errored where data is invalid or insufficient.
     * Validates presence of required fields and basic format for email and phone.
     *
     * @param turista The Turista object to validate.
     * @return true if all data is valid and sufficient, false otherwise.
     */
    private boolean validateData(Turista turista) {
        StringBuilder errors = new StringBuilder();
        // Basic presence checks
        if (turista.getNome().isEmpty()) {
            errors.append("- Il campo 'Nome' non può essere vuoto.\n");
        }
        if (turista.getCognome().isEmpty()) {
            errors.append("- Il campo 'Cognome' non può essere vuoto.\n");
        }
        if (turista.getEmail().isEmpty()) {
            errors.append("- Il campo 'Email' non può essere vuoto.\n");
        }
        if (turista.getTelefono().isEmpty()) {
            errors.append("- Il campo 'Telefono' non può essere vuoto.\n");
        }
        // Email format validation (basic regex)
        // From: https://owasp.org/www-community/Open_Source_Software_and_Tools/Owasp_Validation_Regex_Repository
        // For a more robust validation, consider using dedicated libraries like Apache Commons Validator.
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(turista.getEmail());
        if (!turista.getEmail().isEmpty() && !emailMatcher.matches()) {
            errors.append("- Il formato dell'email non è valido.\n");
        }
        // Phone number format validation (basic digit check, could be more complex based on regional formats)
        // This regex allows for an optional leading '+', followed by 7 to 25 digits, spaces, hyphens, or parentheses.
        if (!turista.getTelefono().isEmpty() && !turista.getTelefono().matches("^[\\+]?[0-9\\s\\-()]{7,25}$")) {
            errors.append("- Il formato del numero di telefono non è valido. Deve contenere solo numeri, spazi, trattini o parentesi (min 7, max 25 caratteri).\n");
        }
        if (errors.length() > 0) {
            ErroredUseCase.displayError("Dati inseriti non validi o insufficienti:\n" + errors.toString());
            return false;
        }
        return true;
    }
    /**
     * Displays a success message to the user after a successful operation.
     *
     * @param message The success message to be displayed.
     */
    private void displaySuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Operazione Completata", JOptionPane.INFORMATION_MESSAGE);
    }
}