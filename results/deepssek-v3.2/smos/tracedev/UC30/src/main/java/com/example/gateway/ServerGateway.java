package com.example.gateway;

import com.example.dto.LogDataDTO;
import com.example.dto.RegisterDelayRequest;
import java.util.Date;

/**
 * Gateway for server communication.
 * Implements the methods as per class diagram.
 */
public class ServerGateway {
    
    /**
     * Sends data to the server.
     * @param data the registration request
     * @return boolean indicating success
     */
    public boolean sendData(RegisterDelayRequest data) {
        // In a real implementation, this would make an HTTP call.
        System.out.println("Sending data to server: " + data.getEntryDate());
        return true; // Assume success
    }
    
    /**
     * Fetches log data for a given date.
     * @param date the date to fetch data for
     * @return log data DTO
     */
    public LogDataDTO fetchLogData(Date date) {
        // In a real implementation, this would fetch from server.
        System.out.println("Fetching log data for date: " + date);
        LogDataDTO dto = new LogDataDTO();
        dto.setDate(date);
        return dto;
    }
}