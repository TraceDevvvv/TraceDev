package com.example.data;

import com.example.entities.Parent;
import com.example.entities.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulates a data access layer that interacts with a database/server.
 * Corresponds to DataContext in the class diagram.
 */
public class DataContext {
    private ServerConnection serverConnection;

    // In-memory simulation for demonstration.
    private List<Parent> parentStore = new ArrayList<>();
    private List<Student> studentStore = new ArrayList<>();

    public DataContext(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
        // Initialize some dummy data for demonstration.
        initializeDummyData();
    }

    private void initializeDummyData() {
        Parent p1 = new Parent(1, "John Doe", "john@example.com");
        Parent p2 = new Parent(2, "Jane Smith", "jane@example.com");
        parentStore.add(p1);
        parentStore.add(p2);

        Student s1 = new Student(101, "Alice", "Grade 5");
        Student s2 = new Student(102, "Bob", "Grade 6");
        Student s3 = new Student(103, "Charlie", "Grade 7");
        studentStore.add(s1);
        studentStore.add(s2);
        studentStore.add(s3);
    }

    public Parent getParent(int id) {
        // Simulate SELECT parent WHERE id = parentId
        System.out.println("DataContext: Fetching parent with ID " + id);
        for (Parent p : parentStore) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Student> getStudents(List<Integer> ids) {
        // Simulate SELECT students WHERE id IN (list)
        System.out.println("DataContext: Fetching students with IDs " + ids);
        List<Student> result = new ArrayList<>();
        for (Student s : studentStore) {
            if (ids.contains(s.getId())) {
                result.add(s);
            }
        }
        return result;
    }

    public boolean saveChanges(List<Object> operations) {
        // Simulate saving changes to the database.
        // Check server connection first.
        if (!serverConnection.isConnected()) {
            System.out.println("DataContext: Server connection interrupted, save failed.");
            return false;
        }
        System.out.println("DataContext: Saving " + operations.size() + " operations.");
        // In a real implementation, we would persist each operation.
        // For simplicity, we assume success if connection is active.
        return true;
    }

    public void rollback() {
        // Simulate rollback of any pending transaction.
        System.out.println("DataContext: Rollback performed.");
    }

    public void closeConnection() {
        // Close the connection (for interruption handling).
        serverConnection.interrupt();
    }
}