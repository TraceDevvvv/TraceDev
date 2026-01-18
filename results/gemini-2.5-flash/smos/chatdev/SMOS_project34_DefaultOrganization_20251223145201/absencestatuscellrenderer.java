package com.chatdev.smos.ui;
import com.chatdev.smos.model.Absence;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
/**
 * A custom {@code DefaultTableCellRenderer} that modifies the background color of table cells
 * based on the {@code isJustified} status of the corresponding {@link Absence} object.
 * Absences marked as justified are displayed with a light green background, while those
 * awaiting justification are shown with a light red background.
 */
public class AbsenceStatusCellRenderer extends DefaultTableCellRenderer {
    // Define custom colors for justified and unjustified absences.
    private static final Color JUSTIFIED_COLOR = new Color(204, 255, 204); // Light Green
    private static final Color TO_JUSTIFY_COLOR = new Color(255, 204, 204); // Light Red
    /**
     * Overrides the default method to provide custom rendering for table cells.
     * This method is called for each cell to determine its appearance.
     *
     * @param table The {@code JTable} being rendered.
     * @param value The value of the cell to be rendered.
     * @param isSelected True if the cell is currently selected.
     * @param hasFocus True if the cell currently has focus.
     * @param row The row index of the cell.
     * @param column The column index of the cell.
     * @return The {@code Component} used for rendering the cell, with custom background color.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        // Call the superclass method to get the default cell renderer component
        // This handles standard properties like text, font, alignment, and selection colors.
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // Ensure the table model is an instance of our custom JustificationTableModel
        // to retrieve the Absence object associated with the current row.
        if (table.getModel() instanceof JustificationTableModel) {
            JustificationTableModel model = (JustificationTableModel) table.getModel();
            // Get the Absence object for the current row to check its justification status.
            Absence absence = model.getAbsenceAt(row);
            // Apply custom background color based on the justification status.
            if (absence.isJustified()) {
                c.setBackground(JUSTIFIED_COLOR); // Set background to light green for justified absences.
            } else {
                c.setBackground(TO_JUSTIFY_COLOR); // Set background to light red for absences to justify.
            }
        } else {
            // If the model is not our custom type, apply a default background.
            // This acts as a fallback and prevents unexpected styling.
            c.setBackground(table.getBackground());
        }
        // Handle selection state: if the cell is selected, override custom colors with selection colors.
        // This ensures usability as selected rows remain visually distinct.
        if (isSelected) {
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
        } else {
            // If not selected, ensure foreground color is reset to the default table foreground
            // (or specific foreground if desired, but default is usually good).
            c.setForeground(table.getForeground());
        }
        return c; // Return the configured component for rendering.
    }
}