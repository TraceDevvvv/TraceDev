'''
Custom TableModel for displaying notes in a JTable
'''
package studentnotessystem;
import javax.swing.table.AbstractTableModel;
import java.util.List;
public class NotesTableModel extends AbstractTableModel {
    private List<Note> notes;
    private final String[] columnNames = {
        "Note ID", "Student", "Date", "Teacher", "Subject", "Type", "Content"
    };
    public NotesTableModel(List<Note> notes) {
        this.notes = notes;
    }
    public void setNotes(List<Note> notes) {
        this.notes = notes;
        fireTableDataChanged(); // Notify table that data has changed
    }
    @Override
    public int getRowCount() {
        return notes.size();
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Note note = notes.get(rowIndex);
        switch (columnIndex) {
            case 0: return note.getNoteId();
            case 1: return note.getStudent().toString();
            case 2: return note.getDate().toString();
            case 3: return note.getTeacher();
            case 4: return note.getSubject();
            case 5: return note.getTypeString();
            case 6: return note.getContent();
            default: return null;
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class; // All columns contain strings
    }
    public Note getNoteAt(int row) {
        return notes.get(row);
    }
}