'''
Custom TableModel for displaying Convention objects in a JTable.
This class handles providing data and column names to the JTable.
'''
package com.chatdev.viscon; // Using a package for better organization
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
/**
 * A custom table model to display a list of {@link Convention} objects in a {@link javax.swing.JTable}.
 * It defines the columns and how to retrieve data for each row and column.
 */
public class ConventionTableModel extends AbstractTableModel {
    private List<Convention> conventions;
    private final String[] columnNames = {"ID", "Name", "Start Date", "End Date", "Terms"};
    /**
     * Constructs a new ConventionTableModel with an empty list of conventions.
     */
    public ConventionTableModel() {
        this.conventions = new ArrayList<>();
    }
    /**
     * Returns the number of rows in the model.
     *
     * @return The number of conventions in the list.
     */
    @Override
    public int getRowCount() {
        return conventions.size();
    }
    /**
     * Returns the number of columns in the model.
     *
     * @return The constant number of predefined columns.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    /**
     * Returns the name of the column at the specified index.
     *
     * @param columnIndex The index of the column.
     * @return The name of the column.
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    /**
     * Returns the value for the cell at {@code columnIndex} and {@code rowIndex}.
     *
     * @param rowIndex The row whose value is to be queried.
     * @param columnIndex The column whose value is to be queried.
     * @return The value of the cell.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= conventions.size()) {
            return null; // Handle out of bounds
        }
        Convention convention = conventions.get(rowIndex);
        switch (columnIndex) {
            case 0: return convention.getId();
            case 1: return convention.getName();
            case 2: return convention.getStartDate();
            case 3: return convention.getEndDate();
            case 4: return convention.getTerms();
            default: return null;
        }
    }
    /**
     * Sets the list of conventions to be displayed in the table.
     * After setting new data, it notifies the table that the data has changed
     * so it can refresh its display.
     *
     * @param conventions The new list of {@link Convention} objects.
     */
    public void setConventions(List<Convention> conventions) {
        // Create a new list to avoid external modifications and ensure immutability of the internal state.
        this.conventions = new ArrayList<>(conventions); 
        fireTableDataChanged(); // Notify listeners that the table data has changed
    }
    /**
     * Clears all data from the table model.
     * Notifies the table that its data has changed (all rows removed).
     */
    public void clearData() {
        this.conventions.clear();
        fireTableDataChanged(); // Notify listeners that the table data has changed
    }
}