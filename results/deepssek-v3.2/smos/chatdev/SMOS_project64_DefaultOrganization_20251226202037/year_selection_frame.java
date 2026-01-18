/**
 * Frame for selecting the academic year of interest.
 * This corresponds to step 1 in the event sequence: "Show a screen for the selection of the academic year of interest."
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class YearSelectionFrame extends JFrame {
    private JComboBox<Integer> yearComboBox;
    public YearSelectionFrame() {
        setTitle("Digital Register - Select Academic Year");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Instruction label
        JLabel instructionLabel = new JLabel("Select the academic year:", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(instructionLabel, BorderLayout.NORTH);
        // Year selection panel
        JPanel selectionPanel = new JPanel(new FlowLayout());
        yearComboBox = new JComboBox<>(getAcademicYears());
        yearComboBox.setPreferredSize(new Dimension(150, 30));
        selectionPanel.add(yearComboBox);
        // "Digital Register" button (as per precondition)
        JButton digitalRegisterButton = new JButton("Digital Register");
        digitalRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectYear();
            }
        });
        selectionPanel.add(digitalRegisterButton);
        mainPanel.add(selectionPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    /**
     * Generates a list of recent academic years for the combo box.
     * In a real application, this could be fetched from a database.
     */
    private Integer[] getAcademicYears() {
        List<Integer> years = new ArrayList<>();
        int currentYear = java.time.Year.now().getValue();
        for (int i = currentYear - 5; i <= currentYear + 1; i++) {
            years.add(i);
        }
        return years.toArray(new Integer[0]);
    }
    /**
     * Handles the year selection event (step 2 in the sequence).
     * This simulates the user selecting the year and clicking "Digital Register".
     */
    private void selectYear() {
        Integer selectedYear = (Integer) yearComboBox.getSelectedItem();
        if (selectedYear == null) {
            JOptionPane.showMessageDialog(this, "Please select an academic year.",
                    "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // In a real system, this would trigger a server request to fetch registers.
        // For this example, we simulate fetching and displaying.
        dispose(); // close this window
        new RegisterListFrame(selectedYear).setVisible(true);
    }
}