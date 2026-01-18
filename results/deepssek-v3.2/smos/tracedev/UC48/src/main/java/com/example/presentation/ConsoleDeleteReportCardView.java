package com.example.presentation;

import com.example.domain.ReportCard;
import java.util.List;
import java.util.Scanner;

/**
 * Console implementation of DeleteReportCardView for demonstration.
 */
public class ConsoleDeleteReportCardView implements DeleteReportCardView {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayReportCardList(List<ReportCard> reportCards) {
        System.out.println("\n=== Report Cards ===");
        for (ReportCard rc : reportCards) {
            System.out.println("ID: " + rc.getId() + ", Student: " + rc.getStudentId() + 
                               ", Class: " + rc.getClassName());
        }
        System.out.println("===================\n");
    }

    @Override
    public boolean showConfirmationDialog(String reportCardId) {
        System.out.print("Delete report card " + reportCardId + "? (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    @Override
    public void showSuccessMessage() {
        System.out.println("Report card deleted successfully!");
    }

    @Override
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    @Override
    public void updateListView() {
        System.out.println("Updating list view...");
    }
}