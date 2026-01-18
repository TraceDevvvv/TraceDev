'''
This class extends AbstractTableModel to provide a custom table model
for displaying User objects in a JTable. It defines how user data
is mapped to table rows and columns.
'''
package dev.chat.system; // Assuming a package structure
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
public class UserTableModel extends AbstractTableModel {
    private List<User> users;
    private String[] columnNames = {"ID", "Username", "Email", "Role"};
    /**
     * Constructs a new UserTableModel with an empty list of users.
     */
    public UserTableModel() {
        this.users = new ArrayList<>();
    }
    /**
     * Constructs a new UserTableModel with a given list of users.
     * @param users The list of User objects to display.
     */
    public UserTableModel(List<User> users) {
        this.users = users;
    }
    /**
     * Sets the list of users for the table model and notifies the table
     * that the data has changed, triggering a repaint.
     * @param users The new list of User objects.
     */
    public void setUsers(List<User> users) {
        this.users = users;
        fireTableDataChanged(); // Notify listeners (JTable) that the data has changed
    }
    /**
     * Returns the number of rows in the table.
     * @return The size of the users list.
     */
    @Override
    public int getRowCount() {
        return users.size();
    }
    /**
     * Returns the number of columns in the table.
     * @return The length of the columnNames array.
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
     * Returns the value for the cell at `columnIndex` and `rowIndex`.
     * This method extracts the appropriate property from the User object
     * based on the column index.
     * @param rowIndex The row of the cell.
     * @param columnIndex The column of the cell.
     * @return The value at the specified cell.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 0: return user.getId();
            case 1: return user.getUsername();
            case 2: return user.getEmail();
            case 3: return user.getRole();
            default: return null;
        }
    }
    /**
     * Returns the Class for the cell at `columnIndex`.
     * All columns are String type for this model.
     * @param columnIndex The index of the column.
     * @return The Class object representing the column's data type.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}