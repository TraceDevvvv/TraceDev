package com.example.usecase;

import com.example.domain.Tourist;
import com.example.dto.TouristDTO;
import com.example.repository.TouristRepository;
import com.example.service.NotificationService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use case for deleting tourists.
 */
public class DeleteTouristUseCase {
    private TouristRepository touristRepository;
    private NotificationService notificationService;

    public DeleteTouristUseCase(TouristRepository repository) {
        this.touristRepository = repository;
        // Assumption: NotificationService is instantiated here for simplicity
        this.notificationService = new NotificationService();
    }

    // Added constructor with NotificationService for better flexibility
    public DeleteTouristUseCase(TouristRepository repository, NotificationService notificationService) {
        this.touristRepository = repository;
        this.notificationService = notificationService;
    }

    public List<TouristDTO> getTouristList() {
        List<Tourist> tourists = touristRepository.findAll();
        // Convert each Tourist to TouristDTO using the constructor
        return tourists.stream()
                       .map(TouristDTO::new)
                       .collect(Collectors.toList());
    }

    public void deleteTourist(String id) {
        // Step 7: Delete tourist from repository
        touristRepository.deleteById(id);
        // Quality Requirement: Send deletion notification
        notificationService.sendDeletionNotification(id);
    }

    // Conversion method as specified in the class diagram
    public TouristDTO convertToDTO(Tourist tourist) {
        return new TouristDTO(tourist);
    }
}