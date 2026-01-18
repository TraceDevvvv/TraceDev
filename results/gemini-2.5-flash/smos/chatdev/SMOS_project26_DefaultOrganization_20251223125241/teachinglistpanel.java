/**
 * A JPanel that displays a scrollable list of teachings.
 * It allows the user to select a teaching and notifies a listener
 * when a selection is made.
 */
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;
public class TeachingListPanel extends JPanel {
    private JList<Teaching> teachingJList;
    private DefaultListModel<Teaching> listModel;
    // Listener interface for teaching selection events
    public interface TeachingSelectionListener {
        void onTeachingSelected(String teachingId);
    }
    private TeachingSelectionListener selectionListener;
    /**
     * Constructs a new TeachingListPanel.
     */
    public TeachingListPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        JLabel titleLabel = new JLabel("Available Teachings", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        listModel = new DefaultListModel<>();
        teachingJList = new JList<>(listModel);
        teachingJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one item can be selected at a time
        teachingJList.setFont(new Font("Dialog", Font.PLAIN, 14));
        // Add list selection listener
        teachingJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Ensure the event is not adjusting and an item is actually selected
                if (!e.getValueIsAdjusting() && teachingJList.getSelectedValue() != null) {
                    // Notify the listener when a teaching is selected by passing its ID
                    if (selectionListener != null) {
                        selectionListener.onTeachingSelected(teachingJList.getSelectedValue().getId());
                    }
                }
            }
        });
        // Add the JList to a JScrollPane for scrollability
        JScrollPane scrollPane = new JScrollPane(teachingJList);
        add(scrollPane, BorderLayout.CENTER);
    }
    /**
     * Sets the listener for teaching selection events.
     * @param listener The object that will receive teaching selection notifications.
     */
    public void setTeachingSelectionListener(TeachingSelectionListener listener) {
        this.selectionListener = listener;
    }
    /**
     * Refreshes the list of teachings displayed in the panel.
     * @param teachings A list of Teaching objects to display.
     */
    public void refreshTeachings(List<Teaching> teachings) {
        listModel.clear(); // Clear existing items
        if (teachings != null) {
            for (Teaching teaching : teachings) {
                listModel.addElement(teaching); // Add new teachings
            }
        }
        // Ensure no item is selected after refresh unless specifically intended
        // This is important when returning to the list view after an action.
        teachingJList.clearSelection();
    }
}