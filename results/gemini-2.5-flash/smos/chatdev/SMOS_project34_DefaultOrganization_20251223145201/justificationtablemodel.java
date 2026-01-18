package com.chatdev.smos.ui;
import com.chatdev.smos.model.Absence;
import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
/**
 * Custom {@code AbstractTableModel} implementation for displaying {@link Absence} objects
 * within a {@code JTable}. This model specifically structures the data into "Date", "Reason",
 * and "Status" columns and ensures consistent date formatting.
 */
public class JustificationTableModel extends AbstractTableModel {
    private final List<Absence> absences;
    // Names of the columns to be displayed in the JTable header.
    private final String[] columnNames = {"Date", "Reason", "Status"};
    // Formatter for displaying LocalDate objects consistently in the table.
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * Constructs a new JustificationTableModel.
     *
     * @param absences A list of {@link Absence} objects to be displayed in the table.
     *                 The list is made unmodifiable internally to prevent external modification
     *                 after model creation, ensuring data integrity.
     */
    public JustificationTableModel(List<Absence> absences) {
        // Ensure the list is not null; if so, use an empty list.
        // Wrap the list in Collections.unmodifiableList to make it read-only.
        this.absences = Collections.unmodifiableList(absences != null ? absences : Collections.emptyList());
    }
    /**
     * Returns the number of rows in the model.
     * This corresponds to the total number of absences to display.
     * @return The row count.
     */
    @Override
    public int getRowCount() {
        return absences.size();
    }
    /**
     * Returns the number of columns in the model.
     * This is determined by the size of the {@code columnNames} array.
     * @return The column count.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    /**
     * Returns the name of the column at the specified index.
     * This method provides the text for the table header.
     * @param col The one-based index of the column.
     * @return The descriptive name of the column.
     */
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    /**
     * Returns the value for the cell at {@code columnIndex} and {@code rowIndex}.
     * This method retrieves the specific data point from an {@link Absence} object
     * based on its column and row.
     *
     * @param rowIndex The row whose value is to be queried.
     * @param columnIndex The column whose value is to be queried.
     * @return The Object value at the specified cell.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Retrieve the Absence object corresponding to the current row.
        Absence absence = absences.get(rowIndex);
        switch (columnIndex) {
            case 0: // Date column
                return absence.getDate().format(dateFormatter); // Format date for display
            case 1: // Reason column
                return absence.getReason();
            case 2: // Status column (Justified/To Justify)
                return absence.isJustified() ? "Justified" : "To Justify";
            default:
                return null; // Should not happen with defined columns
        }
    }
    /**
     * Returns the most specific superclass for all the cell values in the column.
     * This method helps the JTable choose the appropriate default renderer/editor.
     * For this model, all displayable values are treated as Strings.
     *
     * @param columnIndex The index of the column.
     * @return The Class of the column's data type.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // All columns are represented as Strings for display purposes.
        return String.class;
    }
    /**
     * Returns the {@link Absence} object associated with a specific row.
     * This getter is crucial for custom renderers (like {@link AbsenceStatusCellRenderer})
     * to access the underlying {@link Absence} data for row-specific styling or logic.
     *
     * @param row The row index.
     * @return The Absence object at the specified row.
     */
    public Absence getAbsenceAt(int row) {
        return absences.get(row);
    }
}