package com.example.school.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * A mock DatabaseContext for simulating database interactions.
 * In a real application, this would use JDBC or an ORM like Hibernate.
 * For simplicity, data is stored in in-memory lists/maps.
 */
public class DatabaseContext {
    // In-memory data stores
    public final List<Map<String, Object>> classesTable = new ArrayList<>();
    public final List<Map<String, Object>> studentsTable = new ArrayList<>();
    public final List<Map<String, Object>> reportCardsTable = new ArrayList<>();
    public final List<Map<String, Object>> gradesTable = new ArrayList<>();
    public final List<Map<String, Object>> academicYearsTable = new ArrayList<>();
    public final List<Map<String, Object>> subjectsTable = new ArrayList<>();

    public DatabaseContext() {
        // Initialize with some dummy data for testing
        initDummyData();
    }

    private void initDummyData() {
        // Academic Year
        Map<String, Object> currentYear = new HashMap<>();
        currentYear.put("id", "AY2023-2024");
        currentYear.put("year", 2023);
        currentYear.put("startDate", new java.util.Date(System.currentTimeMillis() - 365L * 24 * 60 * 60 * 1000)); // ~1 year ago
        currentYear.put("endDate", new java.util.Date(System.currentTimeMillis() + 180L * 24 * 60 * 60 * 1000)); // ~6 months from now
        academicYearsTable.add(currentYear);

        // Classes for AY2023-2024
        Map<String, Object> class1A = new HashMap<>();
        class1A.put("id", "C001");
        class1A.put("name", "Grade 1A");
        class1A.put("academicYearId", "AY2023-2024");
        classesTable.add(class1A);

        Map<String, Object> class1B = new HashMap<>();
        class1B.put("id", "C002");
        class1B.put("name", "Grade 1B");
        class1B.put("academicYearId", "AY2023-2024");
        classesTable.add(class1B);

        // Students in C001
        Map<String, Object> student1 = new HashMap<>();
        student1.put("id", "S001");
        student1.put("name", "Alice Smith");
        student1.put("classId", "C001");
        studentsTable.add(student1);

        Map<String, Object> student2 = new HashMap<>();
        student2.put("id", "S002");
        student2.put("name", "Bob Johnson");
        student2.put("classId", "C001");
        studentsTable.add(student2);

        // Students in C002
        Map<String, Object> student3 = new HashMap<>();
        student3.put("id", "S003");
        student3.put("name", "Charlie Brown");
        student3.put("classId", "C002");
        studentsTable.add(student3);

        // Subjects
        Map<String, Object> math = new HashMap<>();
        math.put("id", "SUB001");
        math.put("name", "Mathematics");
        subjectsTable.add(math);

        Map<String, Object> science = new HashMap<>();
        science.put("id", "SUB002");
        science.put("name", "Science");
        subjectsTable.add(science);

        Map<String, Object> english = new HashMap<>();
        english.put("id", "SUB003");
        english.put("name", "English");
        subjectsTable.add(english);
    }


    /**
     * Simulates connecting to the database.
     */
    public void connect() {
        System.out.println("DatabaseContext: Connected to in-memory database.");
    }

    /**
     * Simulates disconnecting from the database.
     */
    public void disconnect() {
        System.out.println("DatabaseContext: Disconnected from in-memory database.");
    }

    /**
     * Simulates executing a SQL query and returning a list of maps,
     * where each map represents a row.
     * This is a highly simplified mock.
     * @param sql The SQL query string (not actually parsed, just for logging).
     * @return A list of maps representing the query result.
     */
    public List<Map<String, Object>> executeQuery(String sql) {
        System.out.println("DatabaseContext: Executing query: " + sql);
        // This method will be implemented by specific repositories to filter
        // data from the in-memory tables based on the simulated query.
        return new ArrayList<>(); // Default empty result
    }
}