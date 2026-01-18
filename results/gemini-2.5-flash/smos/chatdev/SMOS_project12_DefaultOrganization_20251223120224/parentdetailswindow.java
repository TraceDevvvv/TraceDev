'''
This class represents a GUI window that displays details of a selected parent.
It simulates the precondition "viewdetTailsente" and provides a "Parentela" button
to proceed to the child management form.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ParentDetailsWindow extends JFrame {
    private Parent selectedParent; // The parent object whose details are being viewed
    private JButton parentelaButton;
    private JLabel parentDetailsLabel;
    private ParentManagementService service;
    /**
     * Constructs a ParentDetailsWindow for a given parent.
     * @param parent The parent whose details are to be displayed.
     */
    public ParentDetailsWindow(Parent parent) {
        this.selectedParent = parent;
        this.service = new ParentManagementService(); // Initialize the service
        setTitle("Parent Details - " + parent.getName());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close this window closes the app
        setLocationRelativeTo(null); // Center the window
        initComponents();
        layoutComponents();
    }
    /**
     * Initializes the GUI components.
     */
    private void initComponents() {
        parentDetailsLabel = new JLabel("<html><b>Parent ID:</b> " + selectedParent.getId() + "<br>" +
                                         "<b>Parent Name:</b> " + selectedParent.getName() + "<br>" +
                                         "<i>(Simulating full parent details view)</i></html>");
        parentDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        parentelaButton = new JButton("Parentela (Manage Children)");
        parentelaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Precondition: user clicks "Parentela" button
                // System displays the child management form for the parent in question
                ChildManagementForm childForm = new ChildManagementForm(selectedParent);
                childForm.setVisible(true);
                // Instruct ParentDetailsWindow to dispose itself after launching the child management form.
                // This cleans up the GUI flow by removing the precursor window once the main task window is open.
                dispose(); 
            }
        });
    }
    /**
     * Lays out the components within the frame.
     */
    private void layoutComponents() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(parentDetailsLabel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(parentelaButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }
}