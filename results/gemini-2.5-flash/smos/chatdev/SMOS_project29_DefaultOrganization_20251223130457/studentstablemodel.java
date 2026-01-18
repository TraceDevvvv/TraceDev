'''
Custom TableModel for displaying student attendance status in a JTable.
It manages the list of students, and their associated absence and delay status
for a specific date, allowing for editing directly within the table.
'''
package com.chatdev.absencetracker.gui;
import com.chatdev.absencetracker.model.AbsenceEntry;
import com.chatdev.absencetracker.model.Student;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
class StudentsTableModel extends AbstractTableModel {
    // Column headers for the table
    private final String[] columnNames = {"Student ID", "Student Name", "Parent Email", "Absent", "Delayed"};
    private List<Student> students; // List of all students being displayed
    private LocalDate currentDate; // The date for which attendance is being displayed/edited
    // Map to store temporary absence/delay status for each student for the currentDate.
    // Key: Student ID, Value: Map with "absent" and "delayed" boolean status.
    private Map<String, Map<String, Boolean>> attendanceStatus;
    '''
    Constructs a new StudentsTableModel.
    Initializes empty student list, default date to now, and empty attendance status.
    '''
    public StudentsTableModel() {
        this.students = new ArrayList<>();
        this.attendanceStatus = new HashMap<>(); // Holds editable status per student
        this.currentDate = LocalDate.now(); // Default to today
    }
    '''
    Sets the list of students to display and initializes their attendance status
    based on existing absence entries for the given date.
    @param students The main list of all students to populate the table.
    @param entriesForDate A list of existing absence/delay entries for the current date.
    @param date The date for which attendance data is being loaded/set.
    '''
    public void setStudentsAndAbsenceEntries(List<Student> students, List<AbsenceEntry> entriesForDate, LocalDate date) {
        // Sort students by name for consistent display
        this.students = students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
        this.currentDate = date;
        this.attendanceStatus.clear();
        // Initialize all students as present/not delayed by default
        for (Student student : this.students) {
            attendanceStatus.put(student.getId(), new HashMap<>(Map.of("absent", false, "delayed", false)));
        }
        // Overlay existing absence entries onto the default status
        for (AbsenceEntry entry : entriesForDate) {
            // Ensure the entry belongs to the current date and is for a student in our list
            if (entry.getDate().equals(date) && attendanceStatus.containsKey(entry.getStudentId())) {
                attendanceStatus.get(entry.getStudentId()).put("absent", entry.isAbsent());
                attendanceStatus.get(entry.getStudentId()).put("delayed", entry.isDelayed());
            }
        }
        fireTableDataChanged(); // Notify JTable that its entire data has changed
    }
    '''
    Returns the number of rows in the table (which is the number of students).
    @return The row count.
    '''
    @Override
    public int getRowCount() {
        return students.size();
    }
    '''
    Returns the number of columns in the table.
    @return The column count.
    '''
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    '''
    Returns the name of the column at the specified index.
    @param col The zero-based index of the column.
    @return The name of the column.
    '''
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    '''
    Returns the value for the cell at `columnIndex` and `rowIndex`.
    @param row The row index of the cell.
    @param col The column index of the cell.
    @return The value at the specified cell.
    '''
    @Override
    public Object getValueAt(int row, int col) {
        Student student = students.get(row);
        Map<String, Boolean> status = attendanceStatus.get(student.getId());
        switch (col) {
            case 0: return student.getId();
            case 1: return student.getName();
            case 2: return student.getParentEmail();
            case 3: // Absent status
                return (status != null) ? status.get("absent") : false;
            case 4: // Delayed status
                return (status != null) ? status.get("delayed") : false;
            default: return null;
        }
    }
    '''
    Returns the most specific superclass for the cell value at `columnIndex`.
    This is important for JTable to determine appropriate cell renderers (e.g., Boolean for JCheckBox).
    @param col The zero-based index of the column.
    @return The Class object that is the most specific superclass of the cell values in the column.
    '''
    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0: return String.class;   // Student ID
            case 1: return String.class;   // Student Name
            case 2: return String.class;   // Parent Email
            case 3: return Boolean.class;  // "Absent" checkbox
            case 4: return Boolean.class;  // "Delayed" checkbox
            default: return Object.class;
        }
    }
    '''
    Returns true if the cell at `rowIndex` and `columnIndex` is editable.
    Only the "Absent" and "Delayed" columns are editable.
    @param row The row index of the cell.
    @param col The column index of the cell.
    @return true if the cell is editable, false otherwise.
    '''
    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 3 || col == 4; // Only "Absent" and "Delayed" columns are editable
    }
    '''
    Sets the value in the cell at `columnIndex` and `rowIndex`.
    This method is called when an editable cell's value is changed in the JTable.
    @param value The new value for the cell.
    @param row The row index of the cell.
    @param col The column index of the cell.
    '''
    @Override
    public void setValueAt(Object value, int row, int col) {
        if (col == 3 || col == 4) { // Only for Absent or Delayed columns
            Student student = students.get(row);
            Map<String, Boolean> status = attendanceStatus.get(student.getId());
            if (status != null && value instanceof Boolean) {
                if (col == 3) { // Absent column
                    status.put("absent", (Boolean) value);
                } else { // Delayed column
                    status.put("delayed", (Boolean) value);
                }
                fireTableCellUpdated(row, col); // Notify table that a specific cell has changed
            }
        }
    }
    '''
    Returns a list of AbsenceEntry objects based on the current table data
    for students who are marked as absent or delayed.
    This method is typically called when the "Save" button is clicked.
    @return A list of AbsenceEntry objects, containing only students currently
    marked as absent or delayed for the selected date.
    '''
    public List<AbsenceEntry> getCurrentAbsenceEntries() {
        List<AbsenceEntry> currentEntries = new ArrayList<>();
        for (Student student : students) {
            Map<String, Boolean> status = attendanceStatus.get(student.getId());
            if (status != null) {
                boolean isAbsent = status.get("absent");
                boolean isDelayed = status.get("delayed");
                if (isAbsent || isDelayed) { // Only create an entry if student is actually absent or delayed
                    currentEntries.add(new AbsenceEntry(student.getId(), currentDate, isAbsent, isDelayed));
                }
            }
        }
        return currentEntries;
    }
}