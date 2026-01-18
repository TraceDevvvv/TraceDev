import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {
    private JTable conventionsTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private JComboBox<String> pointOfRestComboBox;
    private List<Convention> conventions;
    private boolean serverConnected = true;

    public Main() {
        setTitle("Convention History Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        conventions = new ArrayList<>();
        initUI();
        loadInitialData();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Top panel for controls
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Select Point of Rest:"));
        pointOfRestComboBox = new JComboBox<>(new String[]{"Restaurant A", "Restaurant B", "Restaurant C"});
        topPanel.add(pointOfRestComboBox);

        JButton uploadButton = new JButton("Upload Conventions Data");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadConventionsData();
            }
        });
        topPanel.add(uploadButton);

        JButton refreshButton = new JButton("Refresh Connection");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshServerConnection();
            }
        });
        topPanel.add(refreshButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Table for displaying conventions history
        String[] columnNames = {"ID", "Name", "Date", "Status", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0);
        conventionsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(conventionsTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Status label at bottom
        statusLabel = new JLabel("Status: Ready");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void loadInitialData() {
        // Simulate initial connection check
        checkServerConnection();
        if (serverConnected) {
            statusLabel.setText("Status: Connected to server ETOUR");
        } else {
            statusLabel.setText("Status: Connection to server ETOUR IS interrupted");
        }
    }

    private void checkServerConnection() {
        // Simulate server connection check
        serverConnected = Math.random() > 0.2; // 80% chance of being connected
    }

    private void uploadConventionsData() {
        if (!serverConnected) {
            JOptionPane.showMessageDialog(this, "Cannot upload data: Connection to server ETOUR IS interrupted",
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String selectedPointOfRest = (String) pointOfRestComboBox.getSelectedItem();
        statusLabel.setText("Status: Uploading data for " + selectedPointOfRest + "...");

        // Simulate data upload and retrieval with a delay
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Simulate network delay
                TimeUnit.MILLISECONDS.sleep(1500);
                return null;
            }

            @Override
            protected void done() {
                // Clear existing data
                conventions.clear();
                tableModel.setRowCount(0);

                // Simulate fetching conventions data
                fetchConventionsForPointOfRest(selectedPointOfRest);

                // Update table with conventions
                for (Convention conv : conventions) {
                    Object[] row = {conv.getId(), conv.getName(), conv.getDate(), conv.getStatus(), conv.getDescription()};
                    tableModel.addRow(row);
                }

                statusLabel.setText("Status: Conventions history IS displayed for " + selectedPointOfRest);
                // Check quality requirement: display within 2 seconds
                // Our simulated delay is 1.5 seconds, meeting the requirement.
            }
        };
        worker.execute();
    }

    private void fetchConventionsForPointOfRest(String pointOfRest) {
        // Simulate data retrieval based on selected point of rest
        conventions.add(new Convention(1, "Convention Alpha", "2023-10-01", "Active", "Annual meeting for " + pointOfRest));
        conventions.add(new Convention(2, "Convention Beta", "2023-09-15", "Completed", "Quarterly review for " + pointOfRest));
        conventions.add(new Convention(3, "Convention Gamma", "2023-08-20", "Pending", "Upcoming event for " + pointOfRest));
        conventions.add(new Convention(4, "Convention Delta", "2023-07-05", "Active", "Training session at " + pointOfRest));
        conventions.add(new Convention(5, "Convention Epsilon", "2023-06-12", "Cancelled", "Cancelled due to logistic issues at " + pointOfRest));
    }

    private void refreshServerConnection() {
        checkServerConnection();
        if (serverConnected) {
            statusLabel.setText("Status: Connected to server ETOUR");
            JOptionPane.showMessageDialog(this, "Server connection restored.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            statusLabel.setText("Status: Connection to server ETOUR IS interrupted");
            JOptionPane.showMessageDialog(this, "Unable to connect to server ETOUR.", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main app = new Main();
                app.setVisible(true);
            }
        });
    }
}

// Helper class representing a Convention
class Convention {
    private int id;
    private String name;
    private String date;
    private String status;
    private String description;

    public Convention(int id, String name, String date, String status, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.status = status;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}