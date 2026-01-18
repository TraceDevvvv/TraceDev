
package com.etour.controller;

import com.etour.dto.ErrorMessageDTO;
import com.etour.dto.TouristDTO;
import com.etour.dto.TouristCardDTO;
import com.etour.service.TouristService;
import com.etour.model.Tourist;
import java.util.List;
import com.etour.dto.Response;

/**
 * Controller class responsible for handling requests from the UI and
 * coordinating with the service layer to search and retrieve tourist information.
 */
public class AgencyOperatorController {

    private TouristService touristService;

    /**
     * Constructor for dependency injection.
     * @param touristService the service to be used.
     */
    public AgencyOperatorController(TouristService touristService) {
        this.touristService = touristService;
    }

    /**
     * Checks if the controller is in a logged-in state.
     * In a real application, this would check session or security context.
     * @return always true for this simplified implementation.
     */
    public boolean isLoggedIn() {
        // Placeholder: Assume the operator is logged in as per the entry condition.
        return true;
    }

    /**
     * Initiates the tourist search process. This method coordinates the retrieval
     * of all tourists and returns a list of DTOs for display.
     */
    public void searchTourist() {
        // This method is triggered by the UI. The actual list retrieval is done in getTouristList().
        // In this implementation, searchTourist() simply delegates to getTouristList().
        // The sequence diagram shows the controller calling getTouristList() internally.
        // Therefore, we keep this method as an entry point but the logic is in getTouristList().
    }

    /**
     * Retrieves a list of all tourists as DTOs.
     * @return list of TouristDTO objects.
     */
    public List<TouristDTO> getTouristList() {
        return touristService.getAllTourists();
    }

    /**
     * Selects a tourist by ID. In this implementation, it simply delegates to
     * displayTouristCard to retrieve the detailed tourist card.
     * @param touristId the ID of the tourist to select.
     */
    public void selectTourist(String touristId) {
        // This method is called by the UI when a tourist is selected.
        // According to the sequence diagram, it internally calls displayTouristCard.
        displayTouristCard(touristId);
    }

    /**
     * Retrieves detailed tourist information and returns it as a Response containing
     * a TouristCardDTO or an error.
     * @param touristId the ID of the tourist.
     * @return Response containing either a TouristCardDTO or an ErrorMessageDTO.
     */
    public Response<TouristCardDTO> displayTouristCard(String touristId) {
        // Delegate to the service to get a Response<Tourist>
        Response<Tourist> touristResponse = touristService.getTouristById(touristId);

        if (touristResponse.isSuccess()) {
            // Convert the Tourist to TouristCardDTO
            TouristCardDTO card = touristService.convertToCardDTO(touristResponse.getData());
            return new Response<>(card, null);
        } else {
            // Propagate the error
            return new Response<>(null, touristResponse.getError());
        }
    }
}
