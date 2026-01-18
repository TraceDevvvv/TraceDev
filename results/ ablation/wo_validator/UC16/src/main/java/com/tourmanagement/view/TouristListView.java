package com.tourmanagement.view;

import com.tourmanagement.controller.DeleteTouristController;
import com.tourmanagement.dto.TouristDto;
import com.tourmanagement.domain.Tourist;
import com.tourmanagement.repository.TouristRepository;
import com.tourmanagement.repository.TouristRepositoryImpl;
import com.tourmanagement.repository.ETOURDataSource;
import com.tourmanagement.service.TouristService;
import com.tourmanagement.service.TouristServiceImpl;
import com.tourmanagement.service.NotificationService;
import java.util.List;
import java.util.Scanner;

public class TouristListView {
    private DeleteTouristController deleteTouristController;
    private TouristRepository touristRepository;
    private Scanner scanner;

    public TouristListView() {
        // Initialize dependencies
        ETOURDataSource dataSource = new ETOURDataSource();
        this.touristRepository = new TouristRepositoryImpl(dataSource);
        NotificationService notificationService = new NotificationService();
        TouristService touristService = new TouristServiceImpl(touristRepository, notificationService);
        this.deleteTouristController = new DeleteTouristController(touristService);
        this.scanner = new Scanner(System.in);
    }

    public void displayTourists(TouristDto[] tourists) {
        System.out.println("=== List of Tourists ===");
        for (TouristDto tourist : tourists) {
            System.out.println("ID: " + tourist.touristId + ", Name: " + tourist.fullName + ", Email: " + tourist.email);
        }
        System.out.println("========================");
    }

    public String getSelectedTouristId() {
        System.out.print("Enter the Tourist ID to delete: ");
        return scanner.nextLine();
    }

    public boolean showConfirmationDialog(String message) {
        System.out.println(message);
        System.out.print("Confirm? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    public void showSuccessNotification(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    public void showErrorNotification(String message) {
        System.err.println("[ERROR] " + message);
    }

    // Simulate the use case flow
    public void run() {
        // Step 1: Agency Operator views tourists list (via SearchTourist use case)
        List<Tourist> tourists = touristRepository.findAll();
        TouristDto[] touristDtos = tourists.stream()
                .map(Tourist::toDto)
                .toArray(TouristDto[]::new);
        displayTourists(touristDtos);

        // Step 2: AO selects tourist from list
        String selectedTouristId = getSelectedTouristId();

        // Step 3: AO activates disposal feature -> controller is called
        // Step 4: System asks for confirmation
        boolean confirmed = showConfirmationDialog("Confirm deletion of tourist " + selectedTouristId + "?");
        if (confirmed) {
            try {
                deleteTouristController.deleteTourist(selectedTouristId);
                showSuccessNotification("Tourist account deleted successfully");
            } catch (RuntimeException e) {
                // Handle connection errors
                showErrorNotification("Server connection lost");
            }
        } else {
            System.out.println("Deletion cancelled by user.");
        }
    }

    public static void main(String[] args) {
        TouristListView view = new TouristListView();
        view.run();
    }
}