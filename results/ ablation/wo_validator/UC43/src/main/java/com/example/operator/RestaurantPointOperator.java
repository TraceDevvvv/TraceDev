
package com.example.operator;

import com.example.dto.RefreshmentDTO;
import com.example.ui.RefreshmentPointEditForm;
import com.example.service.RefreshmentPointManagementService;
import com.example.repository.RefreshmentRepository;
import com.example.service.ValidationService;
import com.example.service.NotificationService;
import com.example.adapter.ETOURServerAdapter;

/**
 * Restaurant Point Operator actor - main controller class.
 */
public class RestaurantPointOperator {
    private String id;
    private String name;
    private RefreshmentPointEditForm editForm;
    private RefreshmentPointManagementService managementService;

    public RestaurantPointOperator(String id, String name) {
        this.id = id;
        this.name = name;
        // Initialize dependencies
        RefreshmentRepository repository = new RefreshmentRepository();
        ValidationService validationService = new ValidationService();
        NotificationService notificationService = new NotificationService();
        ETOURServerAdapter etourAdapter = new ETOURServerAdapter();
        this.managementService = new RefreshmentPointManagementService(repository, validationService, notificationService, etourAdapter);
        this.editForm = new RefreshmentPointEditForm(managementService, id);
    }

    /**
     * Views refreshment point information by point ID.
     */
    public void viewRefreshmentPointInfo(String pointId) {
        System.out.println("Operator " + name + " viewing refreshment point: " + pointId);
        RefreshmentDTO dto = managementService.getRefreshmentPoint(pointId);
        editForm.displayData(dto);
    }

    /**
     * Modifies refreshment data (called when operator changes form data).
     */
    public void modifyRefreshmentData(RefreshmentDTO formData) {
        System.out.println("Operator " + name + " modifying refreshment data");
        // In real scenario, this would update the form with new data
        // For simulation, we assume formData is the updated DTO
        editForm.displayData(formData);
    }

    /**
     * Confirms the operation (called when operator confirms after validation).
     */
    public void confirmOperation() {
        System.out.println("Operator " + name + " confirming operation");
        editForm.submitForm();
    }

    /**
     * Cancels the operation.
     */
    public void cancelOperation() {
        System.out.println("Operator " + name + " cancelling operation");
        editForm.cancelForm();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
