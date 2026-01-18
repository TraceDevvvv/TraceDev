package com.example.controllers;

import com.example.dto.PositionResult;
import com.example.enums.SearchType;
import com.example.serv.IPositionService;

/**
 * Controller that handles position requests from tourists.
 * Orchestrates the flow according to the sequence diagram.
 */
public class PositionController {
    private IPositionService positionService;

    public PositionController(IPositionService service) {
        this.positionService = service;
    }

    /**
     * Gets current position based on search type.
     * @param searchType Type of search (BASIC or ADVANCED)
     * @return PositionResult containing the position data
     */
    public PositionResult getCurrentPosition(SearchType searchType) {
        // Note: SearchType parameter is currently not used in this implementation
        // but kept for API consistency with the UML diagram.
        // In a real implementation, it could influence the strategy selection.
        
        // According to sequence diagram: Controller -> Service: getPosition()
        return positionService.getPosition();
    }
}