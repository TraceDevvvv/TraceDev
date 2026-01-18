'''
Custom TableModel for displaying StudentRegisterEntry objects in a JTable.
This provides the data and column definitions for the register details table.
'''
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
public class ClassRegisterTableModel extends AbstractTableModel {
    private List<StudentRegisterEntry> registerEntries; // List of register entries to display.
    private final String[] columnNames = {
        "Date", "Student Name", "Absence", "Disciplinary Notes", "Delay", "Justification"
    };
    /**
     * Constructs a new ClassRegisterTableModel with an initial list of entries.
     * @param registerEntries The list of StudentRegisterEntry objects to display.
     */
    public ClassRegisterTableModel(List<StudentRegisterEntry> registerEntries) {
        this.registerEntries = new ArrayList<>(registerEntries); // Create a mutable copy.
    }
    /**
     * Returns the number of rows in the table.
     * @return The number of register entries.
     */
    @Override
    public int getRowCount() {
        return registerEntries.size();
    }
    /**
     * Returns the number of columns in the table.
     * @return The number of defined columns.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    /**
     * Returns the name of the column at the specified index.
     * @param column The index of the column.
     * @return The name of the column.
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    /**
     * Returns the value at the specified row and column.
     * This method maps object properties to table cells.
     * @param rowIndex The row index.
     * @param columnIndex The column index.
     * @return The value at the specified cell.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= registerEntries.size()) {
            return null; // Handle out of bounds access gracefully.
        }
        StudentRegisterEntry entry = registerEntries.get(rowIndex);
        switch (columnIndex) {
            case 0: return entry.getDate();
            case 1: return entry.getStudentName();
            case 2: return Boolean.valueOf(entry.isAbsence()); // Return Boolean object for checkbox rendering
            case 3: return entry.getDisciplinaryNote() != null ? entry.getDisciplinaryNote() : "";
            case 4: return Boolean.valueOf(entry.isDelay()); // Return Boolean object for checkbox rendering
            case 5: return entry.getJustification() != null ? entry.getJustification() : "";
            default: return null;
        }
    }
    /**
     * Returns the most specific superclass for the cell values in the column.
     * Used by JTable to apply appropriate renderers (e.g., CheckBox for Boolean).
     * @param columnIndex The index of the column.
     * @return The Class object representing the column's default renderer/editor.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Provides correct class types for rendering (e.g., Boolean for checkboxes).
        switch (columnIndex) {
            case 0: // Date
            case 1: // Student Name
            case 3: // Disciplinary Notes
            case 5: // Justification
                return String.class;
            case 2: // Absence
            case 4: // Delay
                return Boolean.class; // JTable will render a checkbox for Boolean.class.
            default:
                return Object.class;
        }
    }
    /**
     * Sets new data for the table model and fires a table data changed event.
     * @param newEntries The new list of StudentRegisterEntry objects.
     */
    public void setRegisterEntries(List<StudentRegisterEntry> newEntries) {
        this.registerEntries = new ArrayList<>(newEntries);
        // Notifies connected JTables that the entire table data has changed,
        // prompting them to repaint.
        fireTableDataChanged(); 
    }
}