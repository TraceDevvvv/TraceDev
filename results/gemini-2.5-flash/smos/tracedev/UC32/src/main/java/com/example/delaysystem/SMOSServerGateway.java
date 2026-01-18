package com.example.delaysystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;

/**
 * Concrete implementation of ISMOSServerGateway.
 * This class simulates interactions with an external SMOS server.
 * It uses simple in-memory data for demonstration purposes.
 */
public class SMOSServerGateway implements ISMOSServerGateway {

    // Simulate in-memory data storage for scheduling information
    private Map<Date, SchedulingInfoDTO> schedulingData = new HashMap<>();

    public SMOSServerGateway() {
        // Initialize with some dummy data for demonstration
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.OCTOBER, 26, 0, 0, 0); // Oct 26, 2023
        Date date1 = cal.getTime();
        cal.set(2023, Calendar.OCTOBER, 27, 0, 0, 0); // Oct 27, 2023
        Date date2 = cal.getTime();
        cal.set(2023, Calendar.OCTOBER, 28, 0, 0, 0); // Oct 28, 2023 - Will simulate connection failure
        Date date3 = cal.getTime();
        cal.set(2023, Calendar.OCTOBER, 29, 0, 0, 0); // Oct 29, 2023 - Will simulate fetch failure
        Date date4 = cal.getTime();
        cal.set(2023, Calendar.OCTOBER, 30, 0, 0, 0); // Oct 30, 2023 - Will simulate save failure


        List<DelayDTO> delays1 = new ArrayList<>();
        delays1.add(new DelayDTO("D001", date1, 60, "Technical Issue"));
        delays1.add(new DelayDTO("D002", date1, 30, "Weather Conditions"));
        schedulingData.put(date1, new SchedulingInfoDTO(date1, delays1));

        List<DelayDTO> delays2 = new ArrayList<>();
        delays2.add(new DelayDTO("D003", date2, 90, "Operational Delay"));
        schedulingData.put(date2, new SchedulingInfoDTO(date2, delays2));
    }

    /**
     * Simulates checking the connection to the SMOS server.
     * Assumes connection fails for a specific date (Oct 28, 2023) for testing purposes.
     *
     * @return true if connection is successful, false otherwise.
     */
    @Override
    public boolean checkConnection() {
        System.out.println("[SMOSServerGateway] Checking connection to SMOS server...");
        // Simulate a delay in connection check
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulate connection failure for a specific date (e.g., Oct 28, 2023)
        // This is a simplification; in a real system, connection status is independent of date.
        // It's here to facilitate sequence diagram testing.
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.OCTOBER, 28, 0, 0, 0);
        Date connectionFailDate = cal.getTime();

        // If we want to simulate failure based on a date requested by a method
        // this method signature would need to change. For now, it's just a general check.
        // Let's assume for the purpose of the sequence diagram test, if the *overall* scenario
        // involves the 'connection fail date', this check will fail.
        // This is a simplification and would be handled differently in a real system.
        // For current simulation, let's just make it randomly fail sometimes.
        if (Math.random() < 0.1) { // 10% chance of connection failure
            System.out.println("[SMOSServerGateway] Connection FAILED (simulated random failure).");
            return false;
        }

        System.out.println("[SMOSServerGateway] Connection successful.");
        return true;
    }

    /**
     * Simulates retrieving scheduling information from the SMOS server.
     * Assumes retrieval fails for a specific date (Oct 29, 2023) for testing purposes.
     *
     * @param date The date for which to get scheduling information.
     * @return A SchedulingInfoDTO, or null if retrieval fails.
     */
    @Override
    public SchedulingInfoDTO getSchedulingInfo(Date date) {
        System.out.println("[SMOSServerGateway] Requesting scheduling info for date: " + date);
        // Simulate a delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulate retrieval failure for a specific date (e.g., Oct 29, 2023)
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.OCTOBER, 29, 0, 0, 0);
        Date fetchFailDate = cal.getTime();

        if (date.equals(fetchFailDate)) {
            System.out.println("[SMOSServerGateway] Simulated FETCH FAILURE for date: " + date);
            return null; // Simulate retrieval failure
        }

        SchedulingInfoDTO info = schedulingData.get(date);
        if (info != null) {
            System.out.println("[SMOSServerGateway] Successfully retrieved scheduling info for " + date + ": " + info.delays.size() + " delays.");
        } else {
            System.out.println("[SMOSServerGateway] No scheduling info found for " + date + ". Returning empty DTO.");
            // Return an empty DTO rather than null if no info, or null to indicate 'not found'
            // For sequence diagram testing, returning null better represents a "failed" scenario
            return new SchedulingInfoDTO(date, new ArrayList<>());
        }
        return info;
    }

    /**
     * Simulates sending an update request for delay data to the SMOS server.
     * Assumes saving fails for a specific date (Oct 30, 2023) or ID ("FAIL_ID") for testing purposes.
     *
     * @param delayDto The DelayDTO containing the updated delay information.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean sendUpdateDelayRequest(DelayDTO delayDto) {
        System.out.println("[SMOSServerGateway] Sending update delay request for: " + delayDto);
        // Simulate a delay
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulate saving failure for a specific date (e.g., Oct 30, 2023) or delay ID
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.OCTOBER, 30, 0, 0, 0);
        Date saveFailDate = cal.getTime();

        if (delayDto == null || delayDto.date == null || delayDto.id == null ||
            delayDto.date.equals(saveFailDate) || "FAIL_ID".equals(delayDto.id)) {
            System.out.println("[SMOSServerGateway] Simulated SAVE FAILURE for " + delayDto);
            return false;
        }

        // Simulate finding the existing scheduling info for the date
        SchedulingInfoDTO existingInfo = schedulingData.get(delayDto.date);
        if (existingInfo == null) {
            // If no scheduling info exists for the date, create a new one
            existingInfo = new SchedulingInfoDTO(delayDto.date, new ArrayList<>());
            schedulingData.put(delayDto.date, existingInfo);
        }

        // Update or add the delay
        boolean updated = false;
        for (int i = 0; i < existingInfo.delays.size(); i++) {
            if (existingInfo.delays.get(i).id.equals(delayDto.id)) {
                existingInfo.delays.set(i, delayDto); // Update existing delay
                updated = true;
                break;
            }
        }
        if (!updated) {
            existingInfo.delays.add(delayDto); // Add new delay
        }

        System.out.println("[SMOSServerGateway] Successfully updated delay data for " + delayDto.date + ".");
        return true;
    }
}