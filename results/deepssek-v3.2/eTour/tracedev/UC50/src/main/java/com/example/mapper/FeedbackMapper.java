package com.example.mapper;

import com.example.entity.Feedback;
import com.example.dto.FeedbackDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for Feedback entities to DTOs.
 * Added to satisfy requirement Description - Feedback History.
 */
public class FeedbackMapper {
    public static FeedbackDTO toDTO(Feedback feedback) {
        if (feedback == null) return null;
        return new FeedbackDTO(
                feedback.getFeedbackId(),
                feedback.getRating(),
                feedback.getComment(),
                feedback.getDate()
        );
    }

    public static List<FeedbackDTO> toDTOList(List<Feedback> feedbackList) {
        if (feedbackList == null) return null;
        return feedbackList.stream()
                .map(FeedbackMapper::toDTO)
                .collect(Collectors.toList());
    }
}