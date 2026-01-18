package com.example.application;

import com.example.domain.Teaching;

/**
 * Use case for displaying teaching details.
 */
public interface DisplayTeachingDetailsUseCase {
    Teaching execute(String teachingId);
}