package com.example.interactor;

import com.example.dto.RegisterDelayRequest;
import com.example.dto.RegisterDelayResponse;
import com.example.dto.LogDataDTO;
import com.example.model.StudentDelay;
import com.example.repository.IDelayRepository;
import com.example.service.INotificationService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Interactor for registering a delay.
 * Implements core business logic including validation and notification.
 */
public class RegisterDelayInteractor {
    private IDelayRepository delayRepository;
    private INotificationService notificationService;
    
    public RegisterDelayInteractor(IDelayRepository delayRepository, INotificationService notificationService) {
        this.delayRepository = delayRepository;
        this.notificationService = notificationService;
    }
    
    /**
     * Executes the delay registration request.
     * @param request the registration request
     * @return the response indicating success or failure
     */
    public RegisterDelayResponse execute(RegisterDelayRequest request) {
        // Data validation happens here (as per note m17 in sequence diagram)
        if (request.getStudents() == null || request.getStudents().isEmpty()) {
            return new RegisterDelayResponse(false, "No students provided", null);
        }
        
        // Save each student delay
        for (com.example.dto.StudentDelayData data : request.getStudents()) {
            StudentDelay delay = new StudentDelay();
            delay.setStudentId(data.getStudentId());
            delay.setStudentName(data.getStudentName());
            delay.setDelayDuration(data.getDelayDuration());
            delay.setDate(request.getEntryDate());
            delay.setParentEmail(data.getParentEmail());
            delay.setParentPhone(data.getParentPhone());
            
            delayRepository.save(delay);
            
            // Send notification asynchronously (background) - note m19
            new Thread(() -> notificationService.sendParentNotification(delay)).start();
        }
        
        // Generate updated log data
        List<StudentDelay> delays = delayRepository.findByDate(request.getEntryDate());
        LogDataDTO updatedLogData = new LogDataDTO();
        updatedLogData.setDate(request.getEntryDate());
        updatedLogData.setDelays(delays);
        updatedLogData.setTotalDelays(delays.size());
        
        return new RegisterDelayResponse(true, "Delay registered successfully", updatedLogData);
    }
}