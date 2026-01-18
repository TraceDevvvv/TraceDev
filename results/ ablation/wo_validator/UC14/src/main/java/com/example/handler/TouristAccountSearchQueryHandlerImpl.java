package com.example.handler;

import com.example.dto.TouristAccountSearchCriteriaDTO;
import com.example.dto.TouristAccountDTO;
import com.example.repository.ITouristAccountRepository;
import com.example.entity.TouristAccount;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the query handler.
 * Orchestrates the search process: calls repository and maps entities to DTOs.
 */
public class TouristAccountSearchQueryHandlerImpl implements TouristAccountSearchQueryHandler {
    private ITouristAccountRepository touristAccountRepository;

    public TouristAccountSearchQueryHandlerImpl(ITouristAccountRepository repository) {
        this.touristAccountRepository = repository;
    }

    @Override
    public List<TouristAccountDTO> handle(TouristAccountSearchCriteriaDTO searchCriteria) {
        // Step 1: Call repository to get domain entities.
        List<TouristAccount> accounts = touristAccountRepository.findAll(searchCriteria);
        
        // Step 2: Map each entity to DTO (as per sequence diagram note "4. Map to DTO").
        return accounts.stream()
                .map(TouristAccountDTO::new)
                .collect(Collectors.toList());
    }
}