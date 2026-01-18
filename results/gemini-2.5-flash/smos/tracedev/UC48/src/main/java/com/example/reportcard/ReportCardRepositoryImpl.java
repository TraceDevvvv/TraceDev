package com.example.reportcard;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Concrete implementation of ReportCardRepository.
 * Uses an in-memory HashMap for data storage to simulate a database.
 * Manages transactions with a TransactionManager.
 */
public class ReportCardRepositoryImpl implements ReportCardRepository {

    // In-memory "database"
    private final Map<String, ReportCard> reportCards = new HashMap<>();
    private final TransactionManager transactionManager;

    /**
     * Constructs a ReportCardRepositoryImpl with a TransactionManager.
     *
     * @param transactionManager The transaction manager to use for database operations.
     */
    public ReportCardRepositoryImpl(TransactionManager transactionManager) {
        this.transactionManager = Objects.requireNonNull(transactionManager, "TransactionManager cannot be null");
        // Initialize with some dummy data for testing
        reportCards.put("RC001", new ReportCard("RC001", "S001", "Math", "A"));
        reportCards.put("RC002", new ReportCard("RC002", "S001", "Science", "B+"));
        reportCards.put("RC003", new ReportCard("RC003", "S002", "History", "C"));
    }

    @Override
    public ReportCard findById(String reportCardId) {
        System.out.println("[ReportCardRepositoryImpl] Finding report card with ID: " + reportCardId);
        // Return a deep copy to prevent external modification, or ensure the returned object is immutable
        // For simplicity, returning the direct reference for this example.
        ReportCard foundCard = reportCards.get(reportCardId);
        if (foundCard != null) {
            System.out.println("[ReportCardRepositoryImpl] Found: " + foundCard.toString());
        } else {
            System.out.println("[ReportCardRepositoryImpl] Report card with ID " + reportCardId + " not found.");
        }
        return foundCard;
    }

    @Override
    public boolean delete(ReportCard reportCard) {
        Objects.requireNonNull(reportCard, "ReportCard cannot be null for deletion.");
        System.out.println("[ReportCardRepositoryImpl] Deleting report card: " + reportCard.getId());

        transactionManager.beginTransaction();
        try {
            // Simulate 'executeDeleteStatement' by removing from map if it's marked as deleted
            if (reportCard.isDeleted()) {
                ReportCard removed = reportCards.remove(reportCard.getId());
                if (removed != null) {
                    System.out.println("[ReportCardRepositoryImpl] Database: executeDeleteStatement(" + reportCard.getId() + ") - Success");
                    transactionManager.commitTransaction();
                    return true;
                } else {
                    System.out.println("[ReportCardRepositoryImpl] Report card with ID " + reportCard.getId() + " not found in repository for deletion.");
                    transactionManager.rollbackTransaction();
                    return false;
                }
            } else {
                System.out.println("[ReportCardRepositoryImpl] Report card with ID " + reportCard.getId() + " is not marked as deleted. Cannot proceed with physical deletion.");
                transactionManager.rollbackTransaction(); // Not an error, but no action taken
                return false;
            }
        } catch (Exception e) {
            System.err.println("[ReportCardRepositoryImpl] Error during deletion for report card " + reportCard.getId() + ": " + e.getMessage());
            transactionManager.rollbackTransaction();
            return false;
        }
    }

    /**
     * Adds a report card to the repository for testing purposes.
     * @param reportCard The report card to add.
     */
    public void addReportCard(ReportCard reportCard) {
        if (reportCard != null) {
            reportCards.put(reportCard.getId(), reportCard);
            System.out.println("[ReportCardRepositoryImpl] Added report card: " + reportCard.getId());
        }
    }

    /**
     * Prints all current report cards in the repository.
     */
    public void printAllReportCards() {
        System.out.println("\n--- Current Report Cards in Repository ---");
        if (reportCards.isEmpty()) {
            System.out.println("No report cards available.");
            return;
        }
        reportCards.values().forEach(System.out::println);
        System.out.println("------------------------------------------");
    }
}