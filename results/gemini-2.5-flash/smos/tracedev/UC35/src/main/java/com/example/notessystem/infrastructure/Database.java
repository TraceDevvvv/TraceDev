package com.example.notessystem.infrastructure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Infrastructure Layer: Represents a simulated underlying data store.
 * This class provides a mock implementation for database interactions.
 */
public class Database {
    // Simulated in-memory database tables
    private List<Map<String, Object>> notesTable;
    private List<Map<String, Object>> studentsTable;

    public Database() {
        initializeData();
    }

    /**
     * Initializes mock data for the database tables.
     */
    private void initializeData() {
        notesTable = new ArrayList<>();
        studentsTable = new ArrayList<>();

        // Populate studentsTable
        Map<String, Object> student1 = new HashMap<>();
        student1.put("studentId", "S001");
        student1.put("name", "Alice Smith");
        studentsTable.add(student1);

        Map<String, Object> student2 = new HashMap<>();
        student2.put("studentId", "S002");
        student2.put("name", "Bob Johnson");
        studentsTable.add(student2);

        Map<String, Object> student3 = new HashMap<>();
        student3.put("studentId", "S003");
        student3.put("name", "Charlie Brown");
        studentsTable.add(student3);

        // Populate notesTable
        Map<String, Object> note1 = new HashMap<>();
        note1.put("id", "N001");
        note1.put("studentId", "S001");
        note1.put("content", "Alice's progress is excellent. Engaging well in class discussions.");
        note1.put("date", LocalDate.of(2023, 10, 26));
        note1.put("author", "Ms. Davis");
        notesTable.add(note1);

        Map<String, Object> note2 = new HashMap<>();
        note2.put("id", "N002");
        note2.put("studentId", "S001");
        note2.put("content", "Follow up on homework submission for chapter 3.");
        note2.put("date", LocalDate.of(2023, 11, 01));
        note2.put("author", "Mr. Green");
        notesTable.add(note2);

        Map<String, Object> note3 = new HashMap<>();
        note3.put("id", "N003");
        note3.put("studentId", "S002");
        note3.put("content", "Bob shows great potential in mathematics, needs encouragement for group projects.");
        note3.put("date", LocalDate.of(2023, 10, 20));
        note3.put("author", "Ms. Davis");
        notesTable.add(note3);

        Map<String, Object> note4 = new HashMap<>();
        note4.put("id", "N004");
        note4.put("studentId", "S003");
        note4.put("content", "Charlie needs extra support in reading comprehension.");
        note4.put("date", LocalDate.of(2023, 11, 05));
        note4.put("author", "Mr. Green");
        notesTable.add(note4);
    }

    /**
     * Simulates executing a database query.
     * In a real system, this would parse SQL, connect to a DB, and return a ResultSet.
     * Here, it parses a very specific SQL format for "SELECT * FROM notes WHERE student_id = '...'".
     *
     * @param sql The SQL query string. Expected format: "SELECT * FROM <table_name> WHERE <column_name> = '<value>'"
     * @return A List of Maps, where each Map represents a row and maps column names to their values.
     *         Returns an empty list if no results or if the SQL is not recognized/supported by this mock.
     */
    public List<Map<String, Object>> executeQuery(String sql) {
        System.out.println("DB: Executing query: " + sql);
        // Simple parsing for "SELECT * FROM notes WHERE student_id = '<studentId>'"
        if (sql.startsWith("SELECT * FROM notes WHERE student_id = '")) {
            String studentId = sql.substring(sql.indexOf("='") + 2, sql.lastIndexOf("'"));
            // Filter the notes table based on studentId
            List<Map<String, Object>> result = notesTable.stream()
                                                    .filter(row -> studentId.equals(row.get("studentId")))
                                                    .collect(Collectors.toList());
            return result;
        } else if (sql.startsWith("SELECT * FROM students WHERE studentId = '")) {
             String studentId = sql.substring(sql.indexOf("='") + 2, sql.lastIndexOf("'"));
             List<Map<String, Object>> result = studentsTable.stream()
                                                     .filter(row -> studentId.equals(row.get("studentId")))
                                                     .collect(Collectors.toList());
            return result;
        }
        System.out.println("DB: Unsupported query format for mock database.");
        return new ArrayList<>(); // Return empty list for unsupported queries
    }
}