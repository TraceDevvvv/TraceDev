package com.example.culturalgoods.exception;

/**
 * Custom exception for indicating invalid or insufficient data in a CulturalGoodDTO.
 * Satisfies requirement: Flow of Events 4.
 */
public class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }
}