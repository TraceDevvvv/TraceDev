package com.example.app.infrastructure;

import com.example.app.domain.Justification;
import com.example.app.domain.JustificationStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Mock implementation of IJustificationRepository.
 * Simulates a database by storing Justification objects.
 */
public class JustificationRepositoryImpl implements IJustificationRepository {
    private final List<Justification> justifications = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public JustificationRepositoryImpl() {
        // Populate with some mock data
        try {
            // For REG001 on 2023-10-27, for S002
            String justId1 = UUID.randomUUID().toString();
            justifications.add(new Justification(justId1, "S002", "REG001", dateFormat.parse("2023-10-27"), "Fever", JustificationStatus.PENDING));

        } catch (ParseException e) {
            System.err.println("Error parsing mock date in JustificationRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public List<Justification> findByRegisterIdAndDate(String registerId, String date) {
        // Simulates fetching from a database
        System.out.println(String.format("DB: SELECT * FROM Justifications WHERE registerId = %s AND date = %s", registerId, date));
        Date targetDate;
        try {
            targetDate = dateFormat.parse(date);
        } catch (ParseException e) {
            System.err.println("Invalid date format: " + date);
            return new ArrayList<>();
        }

        return justifications.stream()
                             .filter(j -> j.getRegisterId().equals(registerId) && dateFormat.format(j.getDate()).equals(date))
                             .collect(Collectors.toList());
    }

    @Override
    public Justification save(Justification justification) {
        System.out.println("DB: INSERT INTO Justifications values(...)");
        if (justification.getId() == null) {
            justification = new Justification(UUID.randomUUID().toString(), justification.getStudentId(), justification.getRegisterId(),
                                            justification.getDate(), justification.getReason(), justification.getStatus());
        }
        justifications.add(justification);
        System.out.println("Justification saved: " + justification.getId());
        return justification;
    }

    @Override
    public void updateStatus(String justificationId, JustificationStatus status) {
        System.out.println(String.format("DB: UPDATE Justifications SET status = %s WHERE id = %s", status, justificationId));
        for (Justification j : justifications) {
            if (j.getId().equals(justificationId)) {
                j.setStatus(status);
                System.out.println("Justification " + justificationId + " status updated to " + status);
                return;
            }
        }
        System.out.println("Justification with ID " + justificationId + " not found for update.");
    }
}