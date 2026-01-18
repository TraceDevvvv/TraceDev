package com.example.controller;

import com.example.dto.SearchTouristDTO;
import com.example.dto.TouristAccountDTO;
import com.example.repository.TouristAccountRepository;
import com.example.domain.TouristAccount;
import com.example.exception.ConnectionException;
import java.util.ArrayList;
import java.util.List;

/**
 * Use case controller for searching tourist accounts.
 */
public class SearchTouristAccountUseCaseController {
    private TouristAccountRepository repository;

    public SearchTouristAccountUseCaseController(TouristAccountRepository repository) {
        this.repository = repository;
    }

    /**
     * Searches tourist accounts based on criteria.
     * @param searchData Search criteria DTO.
     * @return List of TouristAccountDTOs; empty list on error (as per sequence diagram).
     */
    public List<TouristAccountDTO> searchTouristAccounts(SearchTouristDTO searchData) {
        try {
            List<TouristAccount> accounts = repository.findAllByCriteria(searchData);
            return mapToDTO(accounts);
        } catch (ConnectionException e) {
            // Log the exception (not shown) and return empty list as per sequence diagram
            System.err.println("Connection error: " + e.getMessage());
            // Return empty list as per sequence diagram message "returns error message / empty list"
            return new ArrayList<>();
        }
    }

    /**
     * Maps domain entities to DTOs.
     */
    protected List<TouristAccountDTO> mapToDTO(List<TouristAccount> accounts) {
        List<TouristAccountDTO> dtos = new ArrayList<>();
        for (TouristAccount account : accounts) {
            dtos.add(new TouristAccountDTO(
                    account.getAccountId(),
                    account.getFirstName(),
                    account.getLastName(),
                    account.getEmail(),
                    account.getAgencyReference()
            ));
        }
        return dtos;
    }

    // New method to correspond to sequence diagram message "shows search form"
    public void showSearchForm() {
        // This method would be called by the UI to initiate the search form display.
        // In a real implementation, it might return a view or data for the form.
        System.out.println("Controller: Initiating search form display.");
    }
}