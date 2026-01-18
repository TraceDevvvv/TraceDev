/**
 * This class represents the main application window for viewing student report cards.
 * It guides the user through selecting an academic year, class, student, and quadrimestre
 * to display the relevant report.
 */
package gui;
import data.DataManager;
import models.AcademicYear;
import models.ReportCard;
import models.Student;
import models.StudentClass;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
public class ReportCardViewFrame extends JFrame {
    private DataManager dataManager;
    // UI Components
    private JComboBox<AcademicYear> academicYearComboBox;
    private JList<StudentClass> classList;
    private JList<Student> studentList;
    private JComboBox<String> quadrimestreComboBox;
    private JTextArea reportCardTextArea;
    // List models for JLists to easily update their contents
    private DefaultListModel<StudentClass> classListModel;
    private DefaultListModel<Student> studentListModel;
    public ReportCardViewFrame() {
        super("SMOS - View Student Report Card");
        this.dataManager = new DataManager();
        initializeUI();
        loadInitialData();
    }
    /**
     * Sets up the main window's layout and components.
     */
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        setLocationRelativeTo(null); // Center the frame
        // Main panel with a BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding
        // -------------------- Top Panel: Academic Year Selection --------------------
        JPanel yearSelectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        yearSelectionPanel.add(new JLabel("1. Select Academic Year:"));
        academicYearComboBox = new JComboBox<>();
        academicYearComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof AcademicYear) {
                    setText(((AcademicYear) value).getName());
                }
                return this;
            }
        });
        academicYearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AcademicYear selectedYear = (AcademicYear) academicYearComboBox.getSelectedItem();
                if (selectedYear != null) {
                    populateClasses(selectedYear);
                    clearStudentAndReport(); // Clear subsequent selections when year changes
                } else {
                    classListModel.clear();
                    clearStudentAndReport();
                }
            }
        });
        yearSelectionPanel.add(academicYearComboBox);
        mainPanel.add(yearSelectionPanel, BorderLayout.NORTH);
        // -------------------- Center Panel: Classes, Students, Report Card --------------------
        JPanel centerContentPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        // Left section: Class List
        JPanel classPanel = new JPanel(new BorderLayout());
        classPanel.setBorder(BorderFactory.createTitledBorder("2. Choose Pupil Class"));
        classListModel = new DefaultListModel<>();
        classList = new JList<>(classListModel);
        classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        classList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                // Check if the value is an instance of StudentClass before casting
                if (value instanceof StudentClass) {
                    setText(((StudentClass) value).getName());
                } else if (value == null) {
                    // Handle null values, which might occur during model clearing or initial states
                    setText("");
                }
                return this;
            }
        });
        classList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Ensure event fires once
                    StudentClass selectedClass = classList.getSelectedValue();
                    if (selectedClass != null) {
                        populateStudents(selectedClass);
                        clearReportCardDisplay(); // Clear report when class changes
                    } else {
                        studentListModel.clear();
                        clearReportCardDisplay();
                    }
                }
            }
        });
        classPanel.add(new JScrollPane(classList), BorderLayout.CENTER);
        centerContentPanel.add(classPanel);
        // Middle section: Student List
        JPanel studentPanel = new JPanel(new BorderLayout());
        studentPanel.setBorder(BorderFactory.createTitledBorder("3. Select Student"));
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                // Check if the value is an instance of Student before casting
                if (value instanceof Student) {
                    setText(((Student) value).getLastName() + ", " + ((Student) value).getFirstName());
                } else if (value == null) {
                    // Handle null values
                    setText("");
                }
                return this;
            }
        });
        studentList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Ensure event fires once
                    // Only display report if a student IS selected, otherwise clear
                    Student selectedStudent = studentList.getSelectedValue();
                    String selectedQuad = (String) quadrimestreComboBox.getSelectedItem();
                    if (selectedStudent != null && selectedQuad != null) {
                        displayReportCard(selectedStudent, selectedQuad);
                    } else {
                        // Clear report if student selection is cleared, but keep quadrimestre if it's there
                        if (selectedStudent == null) {
                             clearReportCardDisplay();
                        }
                    }
                }
            }
        });
        studentPanel.add(new JScrollPane(studentList), BorderLayout.CENTER);
        centerContentPanel.add(studentPanel);
        // Right section: Quadrimestre and Report Card Display
        JPanel reportPanel = new JPanel(new BorderLayout(5, 5));
        reportPanel.setBorder(BorderFactory.createTitledBorder("4. Select Quadrimestre & View Report"));
        JPanel quadSelectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quadSelectionPanel.add(new JLabel("Quadrimestre:"));
        quadrimestreComboBox = new JComboBox<>(new String[]{"Q1", "Q2"}); // Example quadrimestres
        quadrimestreComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student selectedStudent = studentList.getSelectedValue();
                String selectedQuad = (String) quadrimestreComboBox.getSelectedItem();
                if (selectedStudent != null && selectedQuad != null) {
                    displayReportCard(selectedStudent, selectedQuad);
                } else {
                    clearReportCardDisplay();
                }
            }
        });
        quadSelectionPanel.add(quadrimestreComboBox);
        reportPanel.add(quadSelectionPanel, BorderLayout.NORTH);
        reportCardTextArea = new JTextArea();
        reportCardTextArea.setEditable(false);
        reportCardTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        reportCardTextArea.setLineWrap(true);
        reportCardTextArea.setWrapStyleWord(true); // Wraps at word boundaries
        reportPanel.add(new JScrollPane(reportCardTextArea), BorderLayout.CENTER);
        centerContentPanel.add(reportPanel);
        mainPanel.add(centerContentPanel, BorderLayout.CENTER);
        // Add main panel to frame
        add(mainPanel);
        pack();
    }
    /**
     * Loads the initial academic years into the combo box.
     */
    private void loadInitialData() {
        List<AcademicYear> years = dataManager.getAcademicYears();
        for (AcademicYear year : years) {
            academicYearComboBox.addItem(year);
        }
        // Select the first item if available
        if (!years.isEmpty()) {
            academicYearComboBox.setSelectedIndex(0);
        }
    }
    /**
     * Populates the class list based on the selected academic year.
     *
     * @param selectedYear The academic year chosen by the user.
     */
    private void populateClasses(AcademicYear selectedYear) {
        classListModel.clear(); // Clear existing classes
        if (selectedYear == null) {
            return;
        }
        List<StudentClass> classes = dataManager.getClassesByAcademicYear(selectedYear.getId());
        for (StudentClass studentClass : classes) {
            classListModel.addElement(studentClass);
        }
        // Ensure nothing is selected in the class list initially after population
        classList.clearSelection();
    }
    /**
     * Populates the student list based on the selected class.
     *
     * @param selectedClass The student class chosen by the user.
     */
    private void populateStudents(StudentClass selectedClass) {
        studentListModel.clear(); // Clear existing students
        if (selectedClass == null) {
            return;
        }
        List<Student> students = dataManager.getStudentsByClass(selectedClass.getId());
        for (Student student : students) {
            studentListModel.addElement(student);
        }
        // Ensure nothing is selected in the student list initially after population
        studentList.clearSelection();
    }
    /**
     * Displays the report card for the selected student and quadrimestre.
     * If no report card is found, displays an appropriate message.
     *
     * @param selectedStudent The student chosen by the user.
     * @param selectedQuadrimestre The quadrimestre chosen by the user.
     */
    private void displayReportCard(Student selectedStudent, String selectedQuadrimestre) {
        if (selectedStudent == null || selectedQuadrimestre == null) {
            clearReportCardDisplay();
            return;
        }
        AcademicYear currentAcademicYear = (AcademicYear) academicYearComboBox.getSelectedItem();
        if (currentAcademicYear == null) {
            clearReportCardDisplay();
            reportCardTextArea.setText("Please select an academic year first.");
            return;
        }
        ReportCard report = dataManager.getReportCard(
                selectedStudent.getId(),
                currentAcademicYear.getId(), // The AcademicYear object selected in the combobox represents the context
                selectedQuadrimestre
        );
        if (report != null) {
            reportCardTextArea.setText(report.formatReportCard());
            reportCardTextArea.setCaretPosition(0); // Scroll to top
        } else {
            reportCardTextArea.setText(String.format(
                    "No report card available for %s %s for %s in %s.",
                    selectedStudent.getFirstName(), selectedStudent.getLastName(),
                    selectedQuadrimestre, currentAcademicYear.getName()
            ));
        }
    }
    /**
     * Clears the report card display area.
     */
    private void clearReportCardDisplay() {
        reportCardTextArea.setText("");
    }
    /**
     * Clears student list and report card display area.
     */
    private void clearStudentAndReport() {
        studentListModel.clear();
        clearReportCardDisplay();
    }
}