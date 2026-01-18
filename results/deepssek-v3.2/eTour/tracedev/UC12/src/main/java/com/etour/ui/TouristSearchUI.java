package com.etour.ui;

import com.etour.controller.AgencyOperatorController;
import com.etour.dto.TouristDTO;
import com.etour.dto.TouristCardDTO;
import com.etour.dto.ErrorMessageDTO;
import com.etour.dto.Response;
import java.util.List;

/**
 * Boundary class representing the user interface for tourist search.
 * It interacts with the controller to perform operations and display results.
 */
public class TouristSearchUI {

    private AgencyOperatorController controller;

    /**
     * Constructor for dependency injection.
     * @param controller the controller to be used.
     */
    public TouristSearchUI(AgencyOperatorController controller) {
        this.controller = controller;
    }

    /**
     * Checks if the UI is in a logged-in state.
     * This would typically check the user's session.
     * @return true if logged in.
     */
    public boolean isLoggedIn() {
        // Placeholder: Assume the operator is logged in as per the entry condition.
        return true;
    }

    /**
     * Activates the "Search Tourist" use case. This method is called by the actor
     * and triggers the flow to retrieve and display the tourist list.
     */
    public void activateSearchTourist() {
        // Check login status (as per sequence diagram)
        if (isLoggedIn() && controller.isLoggedIn()) {
            // Request the controller to search tourists
            controller.searchTourist();
            // Retrieve the list of tourists
            List<TouristDTO> list = controller.getTouristList();
            displayTouristList(list);
        } else {
            // Should not happen given the entry condition, but handle gracefully.
            displayError(new ErrorMessageDTO(401, "Not logged in.", new java.util.Date()));
        }
    }

    /**
     * Displays the list of tourists.
     * @param list the list of TouristDTO objects.
     */
    public void displayTouristList(List<TouristDTO> list) {
        // In a real UI, this would update a table or list component.
        // For this implementation, we simply print to console.
        System.out.println("=== Tourist List ===");
        for (TouristDTO dto : list) {
            System.out.println(dto);
        }
    }

    /**
     * Displays detailed tourist card.
     * @param card the TouristCardDTO to display.
     */
    public void displayTouristCard(TouristCardDTO card) {
        // In a real UI, this would show a detailed view.
        System.out.println("=== Tourist Card ===");
        System.out.println(card);
    }

    /**
     * Displays an error message.
     * @param error the ErrorMessageDTO containing error details.
     */
    public void displayError(ErrorMessageDTO error) {
        // In a real UI, this would show an error dialog.
        System.out.println("=== Error ===");
        System.out.println(error);
    }

    /**
     * Simulates the actor selecting a tourist from the list.
     * This method is called by the actor (or a test driver) to trigger the
     * display of a specific tourist's card.
     * @param touristId the ID of the selected tourist.
     */
    public void selectTourist(String touristId) {
        // Pass the selection to the controller
        controller.selectTourist(touristId);
        // The controller will call displayTouristCard via the response.
        // In the sequence diagram, the controller returns a Response<TouristCardDTO>
        // which the UI uses to either display the card or an error.
        // Therefore, we need to get the response from the controller.
        // However, the controller's selectTourist method already internally calls displayTouristCard.
        // To align with the sequence diagram, we'll have the controller return the response.
        // But the diagram shows the UI calling displayTouristCard after receiving the response.
        // Let's adjust: the controller's selectTourist will return the response.
        // However, in the given sequence diagram, the UI calls selectTourist(touristId) and then
        // the controller internally calls displayTouristCard which returns a response to the UI.
        // To keep the flow, we modify: after calling selectTourist, the UI should retrieve the response.
        // But the current controller signature does not return a response from selectTourist.
        // We'll change the controller's selectTourist to return the response for simplicity.
        // However, to avoid changing the class diagram signature, we'll keep the controller as is
        // and have the UI call displayTouristCard directly? That would deviate from the sequence.
        // Let's assume the sequence diagram's "selectTourist" triggers the controller to call
        // displayTouristCard and then the UI receives the response and displays it.
        // We'll implement a separate method in the controller to get the card response.
        // But the controller already has displayTouristCard that returns a response.
        // So in the UI's selectTourist, we call controller.displayTouristCard and handle the response.
        Response<TouristCardDTO> response = controller.displayTouristCard(touristId);
        if (response.isSuccess()) {
            displayTouristCard(response.getData());
        } else {
            displayError(response.getError());
        }
    }
}