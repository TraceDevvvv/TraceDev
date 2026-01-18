'''
Custom TableModel for displaying ClassInfo objects in a JTable.
This provides the data and column definitions for the list of classes.
'''
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
public class ClassListTableModel extends AbstractTableModel {
    private List<ClassInfo> classes; // List of ClassInfo objects to display.
    private final String[] columnNames = {
        "Class ID", "Class Name", "Subject", "Semester"
    };
    /**
     * Constructs a new ClassListTableModel with an initial list of classes.
     * @param classes The list of ClassInfo objects to display.
     */
    public ClassListTableModel(List<ClassInfo> classes) {
        this.classes = new ArrayList<>(classes); // Create a mutable copy.
    }
    /**
     * Returns the ClassInfo object at the specified row index.
     * @param rowIndex The index of the row.
     * @return The ClassInfo object.
     */
    public ClassInfo getClassAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < classes.size()) {
            return classes.get(rowIndex);
        }
        return null;
    }
    /**
     * Returns the number of rows in the table.
     * @return The number of classes.
     */
    @Override
    public int getRowCount() {
        return classes.size();
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
        if (rowIndex < 0 || rowIndex >= classes.size()) {
            return null; // Handle out of bounds access gracefully.
        }
        ClassInfo classInfo = classes.get(rowIndex);
        switch (columnIndex) {
            case 0: return classInfo.getClassId();
            case 1: return classInfo.getClassName();
            case 2: return classInfo.getSubject();
            case 3: return classInfo.getSemester();
            default: return null;
        }
    }
    /**
     * Sets new data for the table model and fires a table data changed event.
     * @param newClasses The new list of ClassInfo objects.
     */
    public void setClasses(List<ClassInfo> newClasses) {
        this.classes = new ArrayList<>(newClasses);
        // Notifies connected JTables that the entire table data has changed,
        // prompting them to repaint.
        fireTableDataChanged(); 
    }
}