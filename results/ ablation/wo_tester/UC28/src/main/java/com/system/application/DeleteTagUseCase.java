package com.system.application;

import com.system.entities.Tag;
import com.system.exceptions.DatabaseException;
import com.system.repository.ITagRepository;
import com.system.dtos.TagDTO;
import com.system.dtos.ResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Application layer use case for deleting tags.
 */
public class DeleteTagUseCase {
    private ITagRepository tagRepository;

    public DeleteTagUseCase(ITagRepository repository) {
        this.tagRepository = repository;
    }

    public List<TagDTO> getExistingTags() {
        List<Tag> tags = tagRepository.findAll();
        return convertToDTOs(tags);
    }

    public ResponseDTO deleteTags(List<String> tagIds) throws DatabaseException {
        try {
            tagRepository.deleteAllByIds(tagIds);
            return ResponseDTO.createSuccessResponse("Tags deleted successfully");
        } catch (DatabaseException e) {
            return handleException(e);
        }
    }

    private List<TagDTO> convertToDTOs(List<Tag> tags) {
        return tags.stream()
                   .map(TagDTO::new)
                   .collect(Collectors.toList());
    }

    private ResponseDTO handleException(DatabaseException exception) {
        // Log the exception if needed
        return ResponseDTO.createErrorResponse("Connection to server interrupted");
    }

    public void createSuccessResponse() {
        // This method is modeled after sequence diagram message m21
        // It may be used internally but is not required to return anything.
        // We'll keep it as a placeholder to satisfy traceability.
    }

    public ResponseDTO handleException() {
        // This method is modeled after sequence diagram message m33
        // It handles the exception and returns a response DTO.
        return ResponseDTO.createErrorResponse("Connection to server interrupted");
    }
}