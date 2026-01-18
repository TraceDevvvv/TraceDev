package com.example.infrastructure;

import com.example.domain.Note;
import com.example.domain.Student;
import com.example.domain.Teacher;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Concrete implementation of NoteRepository that uses a DataSource.
 * For simplicity, we simulate database operations with in-memory data.
 */
public class NoteRepositoryImpl implements NoteRepository {
    // Simulated data source: in-memory list of notes.
    private static List<Note> inMemoryNotes = new ArrayList<>();

    static {
        // Initialize with some sample data for demonstration.
        Student stu1 = new Student("Alice Johnson");
        Teacher tea1 = new Teacher("Dr. Smith");
        Note n1 = new Note("NOTE001", stu1, "Excellent performance in the final exam.", tea1, new Date());
        inMemoryNotes.add(n1);

        Student stu2 = new Student("Bob Williams");
        Teacher tea2 = new Teacher("Prof. Brown");
        Note n2 = new Note("NOTE002", stu2, "Needs improvement in homework submissions.", tea2, new Date());
        inMemoryNotes.add(n2);
    }

    // In a real implementation, this would be a JDBC DataSource.
    // For simplicity, we omit it.
    // private DataSource dataSource;

    public NoteRepositoryImpl() {
        // In a real implementation, dataSource would be provided (e.g., via DI).
    }

    @Override
    public Note findById(String id) {
        System.out.println("Repository: Searching for note with ID: " + id);
        // Simulate database query.
        executeQuery("SELECT * FROM notes WHERE id = '" + id + "'");
        for (Note note : inMemoryNotes) {
            if (note.getId().equals(id)) {
                return note;
            }
        }
        return null;
    }

    @Override
public List<Note> findAll() {
    System.out.println("Repository: Retrieving all notes.");
    executeQuery("SELECT * FROM notes");
    return new ArrayList<>(inMemoryNotes);
}

/**
 * Simulates executing a SQL query.
 * @param query the SQL query string
 * @return a dummy ResultSet (not used)
 */
private ResultSet executeQuery(String query) {
    System.out.println("Repository: Executing query: " + query);
    // In a real implementation, this would execute via JDBC.
    return null;
}

/**
 * Simulates a database query as in the sequence diagram message "Database query".
 */
public void databaseQuery() {
    executeQuery("SELECT * FROM notes");
}

/**
 * Cancels any pending query (simulated).
 * Called when the administrator disconnects.
 */
public void cancelQuery() {
    System.out.println("Repository: Cancelling current query.");
}
}