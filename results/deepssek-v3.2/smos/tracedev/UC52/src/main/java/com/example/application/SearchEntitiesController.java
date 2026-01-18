package com.example.application;

import com.example.domain.SearchableEntity;
import com.example.dto.SearchResultDTO;
import com.example.dto.SearchResultsDTO;
import com.example.infrastructure.ClassRepository;
import com.example.infrastructure.TeachingRepository;
import com.example.infrastructure.AddressRepository;
import com.example.infrastructure.UserRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for searching entities.
 * Coordinates the search across all repositories and calculates relevance.
 */
public class SearchEntitiesController {
    private ClassRepository classRepository;
    private TeachingRepository teachingRepository;
    private AddressRepository addressRepository;
    private UserRepository userRepository;

    /**
     * Constructor with dependency injection for repositories.
     * @param classRepository repository for Class entities
     * @param teachingRepository repository for Teaching entities
     * @param addressRepository repository for Address entities
     * @param userRepository repository for User entities
     */
    public SearchEntitiesController(ClassRepository classRepository, 
                                    TeachingRepository teachingRepository,
                                    AddressRepository addressRepository,
                                    UserRepository userRepository) {
        this.classRepository = classRepository;
        this.teachingRepository = teachingRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    /**
     * Searches across all entity types for the given keywords.
     * Public method called from the UI layer.
     * @param keywords the search keywords.
     * @return SearchResultsDTO containing all results organized by entity type.
     */
    public SearchResultsDTO searchEntities(String keywords) {
        List<SearchableEntity> entities = searchAllRepositories(keywords);
        return convertToDTOs(entities, keywords);
    }

    /**
     * Searches all repositories and aggregates results.
     * Private helper method.
     * @param keywords the search keywords.
     * @return combined list of all matching entities.
     */
    private List<SearchableEntity> searchAllRepositories(String keywords) {
        List<SearchableEntity> allEntities = new ArrayList<>();
        
        // Search each repository and add to combined list
        if (classRepository != null) {
            allEntities.addAll(classRepository.findByKeywords(keywords));
        }
        if (teachingRepository != null) {
            allEntities.addAll(teachingRepository.findByKeywords(keywords));
        }
        if (addressRepository != null) {
            allEntities.addAll(addressRepository.findByKeywords(keywords));
        }
        if (userRepository != null) {
            allEntities.addAll(userRepository.findByKeywords(keywords));
        }
        
        return allEntities;
    }

    /**
     * Converts a list of SearchableEntity objects to SearchResultsDTO.
     * Private helper method.
     * @param entities the list of entities to convert.
     * @param keywords the original search keywords.
     * @return SearchResultsDTO with relevance scores and highlights.
     */
    private SearchResultsDTO convertToDTOs(List<SearchableEntity> entities, String keywords) {
        SearchResultsDTO resultsDTO = new SearchResultsDTO();
        
        for (SearchableEntity entity : entities) {
            SearchResultDTO resultDTO = new SearchResultDTO();
            
            // Determine entity type and set common properties
            if (entity instanceof com.example.domain.Class) {
                com.example.domain.Class classEntity = (com.example.domain.Class) entity;
                resultDTO.setEntityId(classEntity.getId());
                resultDTO.setEntityType("Class");
                resultDTO.setDisplayName(classEntity.getName());
            } else if (entity instanceof com.example.domain.Teaching) {
                com.example.domain.Teaching teachingEntity = (com.example.domain.Teaching) entity;
                resultDTO.setEntityId(teachingEntity.getId());
                resultDTO.setEntityType("Teaching");
                resultDTO.setDisplayName(teachingEntity.getTitle());
            } else if (entity instanceof com.example.domain.Address) {
                com.example.domain.Address addressEntity = (com.example.domain.Address) entity;
                resultDTO.setEntityId(addressEntity.getId());
                resultDTO.setEntityType("Address");
                resultDTO.setDisplayName(addressEntity.getCity() + ", " + addressEntity.getCountry());
            } else if (entity instanceof com.example.domain.User) {
                com.example.domain.User userEntity = (com.example.domain.User) entity;
                resultDTO.setEntityId(userEntity.getId());
                resultDTO.setEntityType("User");
                resultDTO.setDisplayName(userEntity.getFirstName() + " " + userEntity.getLastName());
            }
            
            // Calculate relevance score
            resultDTO.setRelevanceScore(calculateRelevance(entity, keywords));
            
            // Set highlight text (simplified - could be enhanced)
            resultDTO.setHighlightText(extractHighlight(entity.getSearchableContent(), keywords));
            
            resultsDTO.addResult(resultDTO);
        }
        
        return resultsDTO;
    }

    /**
     * Calculates a relevance score for an entity based on keyword matching.
     * Private helper method.
     * @param entity the entity to score.
     * @param keywords the search keywords.
     * @return relevance score between 0.0 and 1.0.
     */
    private Double calculateRelevance(SearchableEntity entity, String keywords) {
        if (entity == null || keywords == null || keywords.trim().isEmpty()) {
            return 0.0;
        }
        
        String content = entity.getSearchableContent().toLowerCase();
        String[] keywordArray = keywords.toLowerCase().split("\\s+");
        
        int matches = 0;
        for (String keyword : keywordArray) {
            if (content.contains(keyword)) {
                matches++;
            }
        }
        
        // Simple scoring: percentage of keywords matched
        if (keywordArray.length == 0) {
            return 0.0;
        }
        
        return (double) matches / keywordArray.length;
    }

    /**
     * Extracts a highlight snippet from content around keywords.
     * @param content the full searchable content.
     * @param keywords the search keywords.
     * @return a highlight snippet.
     */
    private String extractHighlight(String content, String keywords) {
        if (content == null || content.isEmpty()) {
            return "";
        }
        
        if (keywords == null || keywords.trim().isEmpty()) {
            // Return first 50 characters if no keywords
            return content.length() > 50 ? content.substring(0, 50) + "..." : content;
        }
        
        // Simple implementation: find first occurrence of any keyword
        String[] keywordArray = keywords.toLowerCase().split("\\s+");
        String contentLower = content.toLowerCase();
        
        for (String keyword : keywordArray) {
            int index = contentLower.indexOf(keyword);
            if (index >= 0) {
                int start = Math.max(0, index - 20);
                int end = Math.min(content.length(), index + keyword.length() + 20);
                String snippet = content.substring(start, end);
                if (start > 0) snippet = "..." + snippet;
                if (end < content.length()) snippet = snippet + "...";
                return snippet;
            }
        }
        
        // No keyword found, return beginning of content
        return content.length() > 50 ? content.substring(0, 50) + "..." : content;
    }

    // Getters for repositories (optional, for testing)
    public ClassRepository getClassRepository() {
        return classRepository;
    }

    public TeachingRepository getTeachingRepository() {
        return teachingRepository;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}