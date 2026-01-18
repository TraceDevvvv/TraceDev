package com.example;

/**
 * Simulates a database connection.
 */
public class Database {
    public void query(String sql) {
        System.out.println("Executing query: " + sql);
    }

    public void update(String sql) {
        System.out.println("Executing update: " + sql);
    }
}