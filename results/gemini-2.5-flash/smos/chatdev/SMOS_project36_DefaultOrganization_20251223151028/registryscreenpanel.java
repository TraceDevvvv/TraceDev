/**
 * JPanel that simulates the "registry screen". It displays a list of absences
 * and allows the administrator to select an unjustified absence to initiate
 * the justification process.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
public class RegistryScreenPanel extends JPanel {
    private JList<Absence> absenceList; // List to display absence records
    private DefaultListModel<Absence> listModel; // Model for the JList
    private JButton justifySelectedButton; // Button to trigger justification for selected absence
    // Declared as instance variables to fix scoping issue
    private JLabel titleLabel;
    private JScrollPane scrollPane;
    private List<Absence> absences; // Reference to the main list of absences
    // Callback to notify parent (MainApp) when an absence is selected for justification
    private Consumer<Absence> onAbsenceSelectedForJustification;
    /**
     * Constructor for RegistryScreenPanel.
     * @param absences The list of Absence objects to display.
     * @param onAbsenceSelectedForJustification Callback to invoke when an absence is chosen for justification.
     */
    public RegistryScreenPanel(List<Absence> absences, Consumer<Absence> onAbsenceSelectedForJustification) {
        this.absences = absences;
        this.onAbsenceSelectedForJustification = onAbsenceSelectedForJustification;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        initComponents(); // Initialize GUI components
        setupLayout();    // Arrange components
        addListeners();   // Add event listeners
        refreshAbsenceList(); // Populate the list initially
    }
    /**
     * Initializes the graphical components of the panel.
     */
    private void initComponents() {
        titleLabel = new JLabel("Employee Absence Registry", SwingConstants.CENTER); // Assign to instance variable
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        listModel = new DefaultListModel<>();
        absenceList = new JList<>(listModel);
        absenceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one absence can be selected at once
        absenceList.setCellRenderer(new AbsenceListCellRenderer()); // Custom renderer to color "red" absences
        scrollPane = new JScrollPane(absenceList); // Assign to instance variable
        scrollPane.setPreferredSize(new Dimension(700, 400));
        justifySelectedButton = new JButton("Justify Selected Absence");
    }
    /**
     * Lays out the components on the panel.
     */
    private void setupLayout() {
        add(new JPanel().add(titleLabel), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(new JPanel().add(justifySelectedButton), BorderLayout.SOUTH);
    }
    /**
     * Adds action listeners to components.
     */
    private void addListeners() {
        // Action for double-clicking an item in the list
        absenceList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Double-click detected
                    int index = absenceList.locationToIndex(evt.getPoint());
                    if (index >= 0) {
                        handleAbsenceSelection(absenceList.getModel().getElementAt(index));
                    }
                }
            }
        });
        // Action for the 'Justify Selected Absence' button
        justifySelectedButton.addActionListener(e -> {
            Absence selectedAbsence = absenceList.getSelectedValue();
            if (selectedAbsence != null) {
                handleAbsenceSelection(selectedAbsence);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an absence to justify.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    /**
     * Handles the selection of an absence from the list.
     * This method checks pre-conditions and then triggers the justification form.
     * Preconditions from use case:
     * - The user must be logged in to the system as an administrator (assumed by launching MainApp).
     * - The user has carried out the case of use "SviewTetTingloregister" (simulated by showing this panel).
     * - The user has held the case of use "viewellacogiustifies" (simulated by showing this panel).
     * - The user clicks on one of the absences in red (handled here).
     *
     * @param selectedAbsence The Absence object that was selected.
     */
    private void handleAbsenceSelection(Absence selectedAbsence) {
        if (selectedAbsence.isJustified()) {
            JOptionPane.showMessageDialog(this, "This absence is already justified.", "Action Not Allowed", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // Simulate "clicks on one of the absences in red" (i.e., an unjustified absence)
        System.out.println("Administrator selected unjustified absence: " + selectedAbsence.getId());
        // Event sequence step 1: Show a form in which to insert the fields of justice
        // (This is handled by the MainApp through the callback)
        onAbsenceSelectedForJustification.accept(selectedAbsence);
    }
    /**
     * Refreshes the list of absences displayed in the JList.
     * This is called after a justification is saved or initially.
     */
    public void refreshAbsenceList() {
        listModel.clear();
        for (Absence absence : absences) {
            listModel.addElement(absence);
        }
        // Ensure component repaints to reflect any changes in color/status
        absenceList.repaint();
    }
    /**
     * Custom ListCellRenderer to display unjustified absences in red.
     * This fulfills the use case description "the user clicks on one of the absences in red".
     */
    private static class AbsenceListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Absence) {
                Absence absence = (Absence) value;
                // If the absence is not justified, color it red
                if (!absence.isJustified()) {
                    label.setForeground(Color.RED);
                    // Optionally make it bold or add an indicator
                    Font font = label.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
                    label.setFont(font.deriveFont(attributes));
                } else {
                    label.setForeground(Color.BLACK); // Reset to default for justified absences
                    // Ensure the font is normal if it was bolded before
                    label.setFont(label.getFont().deriveFont(Font.PLAIN));
                }
            }
            return label;
        }
    }
}