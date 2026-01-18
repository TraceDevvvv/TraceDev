package com.example.infrastructure;

/**
 * Simulated database class.
 */
public class Database {
    public void queryExistingFeedback(String touristId, String siteId) {
        System.out.println("Querying database for existing feedback for tourist: " + touristId + ", site: " + siteId);
    }

    public void insertFeedback(Object feedback) {
        System.out.println("Inserting feedback into database: " + feedback.toString());
    }

    public void insertVisitedRecord(Object visitedRecord) {
        System.out.println("Inserting visited record into database: " + visitedRecord.toString());
    }
}