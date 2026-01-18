
package com.example.controller;

import com.example.model.CulturalHeritage;
import com.example.usecase.ModifyCulturalHeritageUseCase;
import java.util.Map;

/**
 * Controller for modifying Cultural Heritage.
 * Maps to the ModifyCulturalHeritageController class in the UML diagram.
 */
public class ModifyCulturalHeritageController {
    private ModifyCulturalHeritageUseCase modifyUseCase;

    public ModifyCulturalHeritageController(ModifyCulturalHeritageUseCase modifyUseCase) {
        this.modifyUseCase = modifyUseCase;
    }

    /**
     * Handles load request as per sequence diagram.
     * Returns the CulturalHeritage object to be displayed.
     */
    public CulturalHeritage handleLoadRequest(String id) {
        return modifyUseCase.loadCulturalHeritage(id);
    }

    /**
     * Handles update request as per sequence diagram.
     * Returns a Result object with success/error information.
     */
    public ModifyCulturalHeritageUseCase.Result handleUpdateRequest(String id, Map<String, Object> updatedData) {
        return modifyUseCase.updateCulturalHeritage(id, updatedData);
    }
}
