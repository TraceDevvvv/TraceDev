import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * ResultsWindow: Displays the search results.
 * Exit conditions: The system displays a list of results.
 */
class ResultsWindow extends JFrame {
    public ResultsWindow(List<SearchResult> results) {
        super("Search Results - ETOUR System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Search Results:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        if (results.isEmpty()) {
            JLabel noResultsLabel = new JLabel("No results found.", SwingConstants.CENTER);
            add(noResultsLabel, BorderLayout.CENTER);
        } else {
            // Display results in a JList
            String[] resultStrings = results.stream().map(SearchResult::toString).toArray(String[]::new);
            JList<String> resultList = new JList<>(resultStrings);
            resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            resultList.setFont(new Font("Monospaced", Font.PLAIN, 14));
            JScrollPane scrollPane = new JScrollPane(resultList);
            add(scrollPane, BorderLayout.CENTER);
        }
        JButton backButton = new JButton("New Search");
        backButton.addActionListener(e -> {
            dispose();
            new AdvancedSearchForm().setVisible(true);
        });
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginWindow().setVisible(true);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}