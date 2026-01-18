package com.example.service;

import com.example.dto.DelayEntryRequest;
import com.example.dto.DelayEntryResponse;
import com.example.repository.ClassRepository;
import com.example.repository.DelayRepository;
import com.example.repository.StudentRepository;
import com.example.model.Delay;
import java.util.Date;

/**
 * Service class for processing delay entries.
 * Handles validation, persistence, and communication with external systems.
 */
public class DelayService {
    private ClassRepository classRepository;
    private StudentRepository studentRepository;
    private DelayRepository delayRepository;
    
    // External server communication
    private SMOSServer smosServer;

    public DelayService(ClassRepository classRepository, StudentRepository studentRepository, 
                       DelayRepository delayRepository, SMOSServer smosServer) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.delayRepository = delayRepository;
        this.smosServer = smosServer;
    }

    public boolean validateDelayData(DelayEntryRequest delayData) {
        // Implement validation logic
        if (delayData == null) return false;
        if (delayData.getClassId() == null || delayData.getClassId().isEmpty()) return false;
        if (delayData.getStaffId() == null || delayData.getStaffId().isEmpty()) return false;
        if (delayData.getStudentDelays() == null || delayData.getStudentDelays().isEmpty()) return false;
        
        return true;
    }

    public DelayEntryResponse processDelayEntry(DelayEntryRequest delayData) {
        // Step 1: Validate data
        if (!validateDelayData(delayData)) {
            return new DelayEntryResponse(false, "Invalid delay data");
        }

        try {
            // Step 2: Save to local repository
            // Convert DTO to entity and save (simplified for demonstration)
            for (var studentDelay : delayData.getStudentDelays()) {
                Delay delay = new Delay(
                    generateDelayId(),
                    delayData.getClassId(),
                    studentDelay.getStudentId(),
                    new Date(), // current date
                    studentDelay.getDelayTime()
                );
                delayRepository.saveDelay(delay);
            }

            // Step 3: Send to external server
            String delayDataString = convertToServerFormat(delayData);
            boolean serverSuccess = smosServer.receiveDelayData(delayDataString);
            
            if (!serverSuccess) {
                return new DelayEntryResponse(false, "Failed to send data to server");
            }

            return new DelayEntryResponse(true, "Delay entry processed successfully");
            
        } catch (Exception e) {
            return new DelayEntryResponse(false, "Error processing delay entry: " + e.getMessage());
        }
    }

    private String generateDelayId() {
        return "DELAY-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }

    private String convertToServerFormat(DelayEntryRequest delayData) {
        // Convert to JSON or other format expected by server
        // Simplified for demonstration
        return delayData.toString();
    }
}