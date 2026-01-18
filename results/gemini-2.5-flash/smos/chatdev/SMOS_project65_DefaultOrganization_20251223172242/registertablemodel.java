import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/*
 * Custom TableModel for displaying RegisterEntry data in a JTable.
 * It provides methods to get column names, row count, and cell values.
 */
public class RegisterTableModel extends AbstractTableModel {
    private List<RegisterEntry> data;
    private String[] columnNames = {"Student ID", "Student Name", "Status", "Justification", "Disciplinary Notes"};
    private LocalDate selectedDate; // The date for which this model displays data
    /**
     * Constructs a new RegisterTableModel.
     * @param data The list of RegisterEntry objects to display.
     * @param selectedDate The date currently being viewed in the register.
     */
    public RegisterTableModel(List<RegisterEntry> data, LocalDate selectedDate) {
        this.data = new ArrayList<>(data); // Make a mutable copy
        this.selectedDate = selectedDate;
    }
    /**
     * Updates the data in the table model.
     * This method should be called when the underlying data changes,
     * e.g., when a new date is selected or an entry is modified.
     * @param newData The new list of RegisterEntry objects.
     * @param newDate The new date being viewed.
     */
    public void updateData(List<RegisterEntry> newData, LocalDate newDate) {
        this.data = new ArrayList<>(newData);
        this.selectedDate = newDate;
        fireTableDataChanged(); // Notifies the JTable to redraw
    }
    /**
     * Retrieves the RegisterEntry object at a specific row.
     * @param rowIndex The index of the row.
     * @return The RegisterEntry object at the specified row.
     */
    public RegisterEntry getRegisterEntryAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            return data.get(rowIndex);
        }
        return null;
    }
    @Override
    public int getRowCount() {
        return data.size();
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class; // Student ID
            case 1: return String.class; // Student Name
            case 2: return AttendanceStatus.class; // Status
            case 3: return String.class; // Justification Summary
            case 4: return String.class; // Disciplinary Notes Summary
            default: return Object.class;
        }
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= data.size()) {
            return null; // Handle out of bounds
        }
        RegisterEntry entry = data.get(rowIndex);
        if (entry == null) {
            return null;
        }
        switch (columnIndex) {
            case 0: return entry.getStudent().getId();
            case 1: return entry.getStudent().getName();
            case 2: return entry.getStatus();
            case 3: return entry.getJustification() != null ? entry.getJustification().toString() : "N/A";
            case 4:
                if (entry.getDisciplinaryNotes().isEmpty()) {
                    return "N/A";
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < entry.getDisciplinaryNotes().size(); i++) {
                        sb.append(entry.getDisciplinaryNotes().get(i).getDescription());
                        if (i < entry.getDisciplinaryNotes().size() - 1) {
                            sb.append("; ");
                        }
                    }
                    return sb.toString();
                }
            default: return null;
        }
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Cells are not directly editable in the table; management happens via dialogs
        return false;
    }
    // As per the requirement, 'managing justifications and disciplinary notes'
    // is to be via a 'form'. This implies separate dialogs rather than in-table editing.
    // If we wanted in-table editing for simple status, we'd override setValueAt
    // and potentially make column 2 editable. But the current spec implies dialogs.
}