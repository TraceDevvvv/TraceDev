'''
Displays list of classes for the current academic year
Each class has a "report cards" button as specified in the use case (Event 1)
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import models.SchoolClass;
public class ClassSelectionFrame extends JFrame {
    public ClassSelectionFrame() {
        setTitle("Select Class - Report Card System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Select Class for Report Cards");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        List<SchoolClass> classes = DatabaseSimulator.getClassesForCurrentYear();
        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        if (classes.isEmpty()) {
            centerPanel.add(new JLabel("No classes found for current academic year"));
        } else {
            for (SchoolClass classObj : classes) {
                JPanel classPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                classPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                classPanel.setBackground(Color.WHITE);
                JLabel classLabel = new JLabel("Class " + classObj.getClassName() +
                    " - Teacher: " + classObj.getTeacherName());
                classLabel.setPreferredSize(new Dimension(400, 30));
                classPanel.add(classLabel);
                JButton reportCardsButton = new JButton("Report Cards");
                reportCardsButton.setBackground(new Color(33, 150, 243));
                reportCardsButton.setForeground(Color.WHITE);
                reportCardsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new StudentListFrame(classObj);
                    }
                });
                classPanel.add(reportCardsButton);
                centerPanel.add(classPanel);
            }
        }
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainAdminFrame();
            }
        });
        bottomPanel.add(backButton);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }
}