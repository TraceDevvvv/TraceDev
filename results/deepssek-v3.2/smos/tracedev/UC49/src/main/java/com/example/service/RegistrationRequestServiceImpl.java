package com.example.service;

import com.example.domain.RegistrationRequest;
import com.example.dto.RegistrationRequestDTO;
import com.example.repository.IRegistrationRequestRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for registration request operations.
 */
public class RegistrationRequestServiceImpl implements RegistrationRequestService {
    private IRegistrationRequestRepository registrationRequestRepository;

    public RegistrationRequestServiceImpl(IRegistrationRequestRepository registrationRequestRepository) {
        this.registrationRequestRepository = registrationRequestRepository;
    }

    @Override
    public List<RegistrationRequestDTO> getAllPendingRequests() {
        List<RegistrationRequest> requests = registrationRequestRepository.findAllPending();
        List<RegistrationRequestDTO> dtos = new ArrayList<>();
        
        for (RegistrationRequest request : requests) {
            dtos.add(createRegistrationRequestDTO(request));
        }
        
        return dtos;
    }

    @Override
    public List<RegistrationRequestDTO> refreshRequestList() {
        return getAllPendingRequests();
    }

    private RegistrationRequestDTO createRegistrationRequestDTO(RegistrationRequest request) {
        return new RegistrationRequestDTO(
            request.getStudentId(),
            request.getStudentName(),
            request.getRequestDate(),
            request.getStatus()
        );
    }

    public void queryDatabase() {
        // Simulates querying the database
        System.out.println("Querying database...");
    }

    public RegistrationRequestDTO createRegistrationRequestDTO() {
        return new RegistrationRequestDTO();
    }

    public List<RegistrationRequestDTO> getDTOList() {
        return new ArrayList<>();
    }

    public void iterateThroughDTOs(List<RegistrationRequestDTO> dtos) {
        for (RegistrationRequestDTO dto : dtos) {
            formatForDisplay(dto);
        }
    }

    private void formatForDisplay(RegistrationRequestDTO dto) {
        System.out.println("Formatted: " + dto.getStudentName());
    }
}