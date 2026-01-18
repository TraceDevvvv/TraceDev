package com.example.usecase;

import com.example.domain.Teaching;
import com.example.dto.TeachingDetailsDTO;
import com.example.repository.TeachingRepository;

/**
 * Use case for viewing teaching details.
 * Implements UC-ViewTeachingDetails traceability.
 */
public class ViewTeachingDetailsUseCase {
    private TeachingRepository teachingRepository;

    public ViewTeachingDetailsUseCase(TeachingRepository repository) {
        this.teachingRepository = repository;
    }

    public TeachingDetailsDTO execute(int teachingId) {
        Teaching teaching = teachingRepository.findById(teachingId);
        if (teaching == null) {
            // Teaching not found or connection error.
            return null;
        }
        // sequence diagram message: map Teaching to TeachingDetailsDTO
        return mapTeachingToDTO(teaching);
    }

    public TeachingDetailsDTO mapTeachingToDTO(Teaching teaching) {
        return new TeachingDetailsDTO(
                teaching.getCourseName(),
                teaching.getInstructorName(),
                teaching.getSchedule(),
                teaching.getLocation(),
                teaching.getStudentCount()
        );
    }
}