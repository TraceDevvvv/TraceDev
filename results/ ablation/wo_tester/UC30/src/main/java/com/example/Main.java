package com.example;

import com.example.adapters.ConsoleErrorRepository;
import com.example.adapters.EtourServerAdapterImpl;
import com.example.adapters.InMemoryTagRepository;
import com.example.application.controllers.ErroredController;
import com.example.application.controllers.ExistingErrorTagController;
import com.example.application.controllers.InsertTagController;
import com.example.application.dto.InsertTagRequest;
import com.example.application.interfaces.ErrorRepository;
import com.example.application.interfaces.TagRepository;
import com.example.application.serv.TagValidationService;
import com.example.domain.AgencyOperator;

/**
 * Main class to demonstrate the flow as per sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        TagRepository tagRepository = new InMemoryTagRepository();
        ErrorRepository errorRepository = new ConsoleErrorRepository();
        TagValidationService validationService = new TagValidationService(errorRepository);
        EtourServerAdapterImpl etourAdapter = new EtourServerAdapterImpl(errorRepository);
        ExistingErrorTagController existingErrorTagController = new ExistingErrorTagController();
        ErroredController erroredController = new ErroredController();

        InsertTagController controller = new InsertTagController(
                tagRepository,
                validationService,
                etourAdapter,
                existingErrorTagController,
                erroredController
        );

        // Simulate Agency Operator
        AgencyOperator operator = new AgencyOperator("op1", "John Doe");
        operator.login(); // Entry condition: logged in

        // Simulate UI interaction: operator fills form and submits
        InsertTagRequest request = new InsertTagRequest("Adventure", "Exciting adventure tours");
        operator.submitTagForm(request);

        // UI calls controller
        var response = controller.insertTag(request);

        // Display result
        System.out.println("Response: " + (response.isSuccess() ? "SUCCESS" : "FAILURE"));
        System.out.println("Message: " + response.getMessage());
    }
}