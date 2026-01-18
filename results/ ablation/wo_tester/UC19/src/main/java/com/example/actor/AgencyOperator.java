package com.example.actor;

import com.example.dto.BannerDTO;
import com.example.dto.OperationResultDTO;
import com.example.dto.RefreshmentPointDTO;
import com.example.controller.DeleteBannerController;
import java.util.List;

/**
 * Represents the Agency Operator actor.
 * Interacts with the DeleteBannerController to perform banner deletion.
 */
public class AgencyOperator {
    private String operatorId;
    private String name;
    private String email;
    private DeleteBannerController controller;

    public AgencyOperator(String operatorId, String name, String email, DeleteBannerController controller) {
        this.operatorId = operatorId;
        this.name = name;
        this.email = email;
        this.controller = controller;
    }

    public boolean login(String username, String password) {
        // Simulated login logic
        return username != null && password != null;
    }

    public void logout() {
        // Simulated logout logic
        System.out.println("Operator logged out");
    }

    public List<RefreshmentPointDTO> searchRefreshmentPoint(String criteria) {
        // Modified to satisfy requirement Flow of Events 1
        // In a real implementation, this would query a service for refreshment points.
        // Returning empty list for simplicity.
        System.out.println("Searching refreshment points with criteria: " + criteria);
        return List.of();
    }

    public RefreshmentPointDTO selectRefreshmentPoint(String pointId) {
        // In a real implementation, this would fetch details of a specific refreshment point.
        // Returning null for simplicity.
        System.out.println("Selected refreshment point with ID: " + pointId);
        return null;
    }

    public List<BannerDTO> viewBannerList(String pointId) {
        // Delegates to controller to view banner list
        return controller.viewBannerList(pointId);
    }

    public BannerDTO selectBanner(String bannerId) {
        // Delegates to controller to select a banner
        return controller.selectBanner(bannerId);
    }

    public boolean confirmDeletion(boolean confirmation) {
        // Returns the confirmation boolean as per the class diagram.
        // The actual deletion is handled by the controller.
        return confirmation;
    }

    // Additional method to directly trigger deletion process (for sequence diagram step 8)
    public OperationResultDTO confirmDeletion(String bannerId, boolean confirmed) {
        // This method corresponds to the sequence diagram step where operator confirms deletion.
        // It calls the controller's processDeletionConfirmation.
        return controller.processDeletionConfirmation(bannerId, confirmed);
    }

    // Sequence diagram message: Connection lost
    public void connectionLost() {
        System.out.println("Connection lost");
        // This would typically throw an exception or handle reconnection
    }

    // Sequence diagram note: Server ETOUR connection interrupted
    public void serverETOURConnectionInterrupted() {
        System.out.println("Server ETOUR connection interrupted");
        // This would be triggered by an external event
    }

    // Getters and setters
    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DeleteBannerController getController() {
        return controller;
    }

    public void setController(DeleteBannerController controller) {
        this.controller = controller;
    }
}