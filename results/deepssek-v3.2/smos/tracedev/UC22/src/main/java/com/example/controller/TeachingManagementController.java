package com.example.controller;

import com.example.model.Administrator;
import com.example.dto.TeachingListDTO;
import com.example.dto.TeachingDTO;
import com.example.mapper.TeachingMapper;
import com.example.repository.TeachingRepository;
import com.example.service.SMOSService;
import java.util.List;

/**
 * Application layer controller that orchestrates the use case flow.
 * Implements the sequence diagram interactions.
 */
public class TeachingManagementController {
    private TeachingRepository teachingRepository;
    private SMOSService smosService;

    public TeachingManagementController(TeachingRepository teachingRepository, SMOSService smosService) {
        this.teachingRepository = teachingRepository;
        this.smosService = smosService;
    }

    /**
     * Validates that the provided administrator is logged in and has the admin role.
     * @param admin The administrator to validate.
     * @return true if validation passes.
     */
    public boolean validateAdmin(Administrator admin) {
        // Check entry conditions: logged in and has administrator role.
        return admin != null && admin.isLoggedIn() && admin.validateAdminRole();
    }

    /**
     * Retrieves the teaching list, maps to DTOs, disconnects from SMOS, and returns the list.
     * Quality requirement: response time < 2 seconds.
     * @return TeachingListDTO containing the list of teachings.
     */
    public TeachingListDTO getTeachingList() {
        // Step 2-4: Retrieve teachings from repository.
        List<com.example.model.Teaching> teachings = teachingRepository.findAll();

        // Map entities to DTOs using the mapper utility.
        List<TeachingDTO> teachingDTOs = TeachingMapper.toDTOList(teachings);

        // Create the TeachingListDTO.
        TeachingListDTO teachingListDTO = new TeachingListDTO(teachingDTOs);

        // Step 5: Disconnect from SMOS server as part of exit condition.
        smosService.disconnect();

        return teachingListDTO;
    }

    /**
     * Disconnects from the SMOS server.
     * Explicit method to satisfy the class diagram.
     */
    public void disconnectFromSMOS() {
        smosService.disconnect();
    }
}