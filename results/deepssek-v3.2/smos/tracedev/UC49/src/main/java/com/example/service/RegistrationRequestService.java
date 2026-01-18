package com.example.service;

import com.example.dto.RegistrationRequestDTO;
import java.util.List;

/**
 * Service interface for registration request operations.
 */
public interface RegistrationRequestService {
    List<RegistrationRequestDTO> getAllPendingRequests();
    List<RegistrationRequestDTO> refreshRequestList();  // For quality requirement (accurate display)
}