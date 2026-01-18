package com.example.application;

import com.example.domain.Teaching;
import com.example.interfaceadapters.gateways.TeachingRepository;

/**
 * Simple implementation of DisplayTeachingDetailsUseCase.
 * This is not in the class diagram but is needed for the sequence diagram.
 */
public class DisplayTeachingDetailsInteractor implements DisplayTeachingDetailsUseCase {
    private final TeachingRepository teachingRepository;

    public DisplayTeachingDetailsInteractor(TeachingRepository teachingRepository) {
        this.teachingRepository = teachingRepository;
    }

    @Override
    public Teaching execute(String teachingId) {
        return teachingRepository.findById(teachingId);
    }
}