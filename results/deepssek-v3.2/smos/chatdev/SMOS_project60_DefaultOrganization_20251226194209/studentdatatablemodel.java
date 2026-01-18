/**
 * studentdatatablemodel.java
 * Custom table model for displaying student data in a JTable.
 * This model defines the columns and how data is displayed according to the use case requirements.
 * Columns: Date, Absences, Disciplinary Notes, Delays, Justification
 */
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;
public class StudentDataTableModel extends AbstractTableModel {
    private List<Student> studentData;
    private String[] columnNames = {"Date", "Absences", "Disciplinary Notes", "Delays", "Justification"};
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public StudentDataTableModel(List<Student> studentData) {
        this.studentData = studentData;
    }
    @Override
    public int getRowCount() {
        return studentData != null ? studentData.size() : 0;
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public String getColumnName(int column) {
        if (column >= 0 && column < columnNames.length) {
            return columnNames[column];
        }
        return "";
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (studentData == null || rowIndex < 0 || rowIndex >= getRowCount()) {
            return null;
        }
        Student student = studentData.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dateFormat.format(student.getDate());
            case 1:
                return student.getAbsences();
            case 2:
                String notes = student.getDisciplinaryNotes();
                return (notes == null || notes.isEmpty()) ? "None" : notes;
            case 3:
                return student.getDelays();
            case 4:
                String justification = student.getJustification();
                return (justification == null || justification.isEmpty()) ? "N/A" : justification;
            default:
                return null;
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            case 3:
                return Integer.class;
            case 4:
                return String.class;
            default:
                return Object.class;
        }
    }
    public void updateData(List<Student> newData) {
        this.studentData = newData;
        fireTableDataChanged();
    }
    public Student getStudentAt(int rowIndex) {
        if (studentData == null || rowIndex < 0 || rowIndex >= getRowCount()) {
            return null;
        }
        return studentData.get(rowIndex);
    }
}