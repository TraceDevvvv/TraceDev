package com.example.validation;

import com.example.dto.EditCommentDTO;
import com.example.dto.FeedbackFormDTO;

/**
 * Validates comment-related data.
 */
public class CommentValidator {
    public boolean validateContent(String content) {
        return content != null && !content.trim().isEmpty() && content.length() <= 1000;
    }

    public boolean validateEditDTO(EditCommentDTO dto) {
        return dto != null && dto.validate() && validateContent(dto.getNewContent());
    }

    public boolean validateFormDTO(FeedbackFormDTO dto) {
        return dto != null && dto.validate();
    }
}