'''
ReportCardViewerDialog.java
A dialog window to display the detailed information of a single ReportCard for a specific student.
'''
import javax.swing.*;
import java.awt.*;
import java.util.Map;
/**
 * ReportCardViewerDialog.java
 * A dialog window to display the detailed information of a single ReportCard for a specific student.
 */
public class ReportCardViewerDialog extends JDialog {
    private JLabel studentNameLabel;
    private JLabel reportDateLabel;
    private JTextArea gradesTextArea;
    /**
     * Constructs a new ReportCardViewerDialog.
     *
     * @param parent The parent JFrame of this dialog.
     * @param student The Student object whose report card is being displayed. Includes student name.
     * @param reportCard The ReportCard object whose details are to be displayed.
     */
    public ReportCardViewerDialog(JFrame parent, Student student, ReportCard reportCard) {
        super(parent, "Report Card Details", true); // Modal dialog ensures user interacts with it before parent frame.
        setResizable(false); // Prevents dialog from being resized.
        initUI(student, reportCard); // Initialize UI components and populate with data.
        pack(); // Packs components to their preferred size.
        setLocationRelativeTo(parent); // Centers the dialog relative to its parent frame.
    }
    /**
     * Initializes the user interface components of the dialog and populates them with student and report card data.
     *
     * @param student The Student object to display the name from.
     * @param reportCard The ReportCard object to display.
     */
    private void initUI(Student student, ReportCard reportCard) {
        // Use BorderLayout for the main dialog content with some padding.
        setLayout(new BorderLayout(10, 10));
        JPanel contentPanel = new JPanel(new GridBagLayout()); // GridBagLayout for flexible internal layout.
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around content.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around individual components.
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components will fill their display area horizontally.
        // Title/Header Label for the dialog.
        JLabel titleLabel = new JLabel("Student Report Details", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Make title span two columns.
        contentPanel.add(titleLabel, gbc);
        gbc.gridwidth = 1; // Reset gridwidth for subsequent components.
        // Student Name Label.
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1;
        studentNameLabel = new JLabel(student.getName());
        contentPanel.add(studentNameLabel, gbc);
        // Report Date Label.
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(new JLabel("Report Date:"), gbc);
        gbc.gridx = 1;
        reportDateLabel = new JLabel(reportCard.getDate());
        contentPanel.add(reportDateLabel, gbc);
        // Grades Section Title.
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        contentPanel.add(new JLabel("Grades:"), gbc);
        // Text area to display detailed grades.
        gbc.gridy = 4;
        gbc.weighty = 1.0; // Allows the text area to take up extra vertical space.
        gbc.fill = GridBagConstraints.BOTH; // Fills both horizontally and vertically.
        gradesTextArea = new JTextArea(10, 30); // Set initial size for the text area.
        gradesTextArea.setEditable(false); // Make text area read-only.
        gradesTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Use monospaced font for aligned grades.
        JScrollPane scrollPane = new JScrollPane(gradesTextArea); // Add scroll capability if content overflows.
        contentPanel.add(scrollPane, gbc);
        // Populate the grades text area.
        displayGrades(reportCard);
        add(contentPanel, BorderLayout.CENTER);
        // Close button at the bottom of the dialog.
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose()); // Closes the dialog when button is clicked.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align button to the right.
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Populates the grades text area with the report card's grading information.
     * Formats subject and grade for clear display.
     *
     * @param reportCard The ReportCard object containing the grades.
     */
    private void displayGrades(ReportCard reportCard) {
        StringBuilder sb = new StringBuilder();
        if (reportCard.getGrades().isEmpty()) {
            sb.append("No grades available for this report card.");
        } else {
            // Iterate through the grades map and format each entry.
            for (Map.Entry<String, String> entry : reportCard.getGrades().entrySet()) {
                sb.append(String.format("%-15s: %s%n", entry.getKey(), entry.getValue()));
            }
        }
        gradesTextArea.setText(sb.toString());
        gradesTextArea.setCaretPosition(0); // Scrolls the text area to the top.
    }
}