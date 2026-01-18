package com.example.reportcard.application;

import com.example.reportcard.domain.ReportCard;
import com.example.reportcard.domain.ReportCardDTO;
import com.example.reportcard.domain.ReportCardService;
import com.example.reportcard.infrastructure.ReportCardRepository;
import java.util.Optional;

/**
 * Implementation of the edit report card use case.
 */
public class EditReportCardUseCaseImpl implements EditReportCardUseCase {
    private final ReportCardRepository reportCardRepository;
    private final ReportCardService reportCardService;

    public EditReportCardUseCaseImpl(ReportCardRepository reportCardRepository,
                                     ReportCardService reportCardService) {
        this.reportCardRepository = reportCardRepository;
        this.reportCardService = reportCardService;
    }

    @Override
    public ReportCardDTO loadReportCard(String studentId) {
        Optional<ReportCard> optionalReportCard = reportCardRepository.findById(studentId);
        if (optionalReportCard.isPresent()) {
            ReportCard card = optionalReportCard.get();
            // Convert domain entity to DTO
            return ReportCardDTO.convertToDTO(card);
        } else {
            // Return empty DTO to indicate not found
            return new ReportCardDTO(studentId, null);
        }
    }
    
    public ReportCardDTO convertToDTO(ReportCard reportCard) {
        return ReportCardDTO.convertToDTO(reportCard);
    }

    @Override
    public boolean modifyReportCard(ReportCardDTO modifiedData) {
        // Find existing report card
        Optional<ReportCard> optionalReportCard = reportCardRepository.findById(modifiedData.getStudentId());
        if (!optionalReportCard.isPresent()) {
            return false;
        }
        ReportCard existingCard = optionalReportCard.get();
        try {
            // Validate and update via domain service
            ReportCard updatedCard = reportCardService.validateAndUpdate(existingCard, modifiedData);
            // Save the updated entity
            reportCardRepository.save(updatedCard);
            return true;
        } catch (IllegalArgumentException e) {
            // Validation failed
            return false;
        }
    }
}