/**
 * Represents a mock class selection screen.
 * This screen fulfills the precondition that "the user has carried out the case of use ViewLancoclassiata selection a class to enter the data in the system".
 * It allows the ATA staff to select a class, which then proceeds to the AbsenceTrackerApp.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class ClassSelectionScreen extends JFrame {
    private JComboBox<String> classComboBox;
    /**
     * Constructs the ClassSelectionScreen.
     * Sets up the GUI components for class selection and a "Select" button.
     */
    public ClassSelectionScreen() {
        setTitle("Select Class");
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Mock data for classes
        String[] classes = {"1A - Science", "1B - Math", "2A - History", "3C - English"};
        classComboBox = new JComboBox<>(classes);
        classComboBox.setSelectedIndex(0); // Select the first item by default
        JButton selectButton = new JButton("Select Class");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClass = (String) classComboBox.getSelectedItem();
                if (selectedClass != null && !selectedClass.isEmpty()) {
                    JOptionPane.showMessageDialog(ClassSelectionScreen.this,
                            "Selected class: " + selectedClass, "Class Selected", JOptionPane.INFORMATION_MESSAGE);
                    // Generate mock student data for the selected class
                    List<Student> students = generateMockStudentsForClass(selectedClass);
                    new AbsenceTrackerApp(selectedClass, students, ClassSelectionScreen.this);
                    setVisible(false); // Hide this window, but don't dispose yet to allow returning
                } else {
                    JOptionPane.showMessageDialog(ClassSelectionScreen.this,
                            "Please select a class.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Choose a Class:"));
        topPanel.add(classComboBox);
        panel.add(topPanel, BorderLayout.CENTER);
        panel.add(selectButton, BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
    }
    /**
     * Generates a list of mock student data for a given class.
     * In a real application, this would fetch data from a database based on the selected class.
     *
     * @param className The name of the selected class.
     * @return A list of {@link Student} objects.
     */
    private List<Student> generateMockStudentsForClass(String className) {
        List<Student> students = new ArrayList<>();
        // Simulate different students based on class, just for demonstration
        switch (className) {
            case "1A - Science":
                students.add(new Student("S001", "Alice Smith", "alice.parent@example.com"));
                students.add(new Student("S002", "Bob Johnson", "bob.parent@example.com"));
                students.add(new Student("S003", "Charlie Brown", "charlie.parent@example.com"));
                break;
            case "1B - Math":
                students.add(new Student("S004", "Diana Prince", "diana.parent@example.com"));
                students.add(new Student("S005", "Eve Adams", "eve.parent@example.com"));
                break;
            case "2A - History":
                students.add(new Student("S006", "Frank White", "frank.parent@example.com"));
                students.add(new Student("S007", "Grace Kelly", "grace.parent@example.com"));
                students.add(new Student("S008", "Heidi Klum", "heidi.parent@example.com"));
                students.add(new Student("S009", "Ivanhoe Black", "ivanhoe.parent@example.com"));
                break;
            default:
                // Fallback for other classes or if no specific students defined
                students.add(new Student("SXXX", "Generic Student 1", "parent1@example.com"));
                students.add(new Student("SYYY", "Generic Student 2", "parent2@example.com"));
                break;
        }
        return students;
    }
}