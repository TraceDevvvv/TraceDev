// File: SearchCriteriaDTO.java
/**
 * Data Transfer Object (DTO) to encapsulate search criteria.
 * Used for passing search input from the UI to the SearchService.
 * // Added to resolve CD-G1 for clarity and usage.
 */
public class SearchCriteriaDTO {
    // + keywords : String
    public String keywords;

    /**
     * Constructs a SearchCriteriaDTO with the given keywords.
     *
     * @param keywords The search string entered by the user.
     */
    public SearchCriteriaDTO(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Provides a string representation of the SearchCriteriaDTO.
     *
     * @return A string containing the keywords.
     */
    @Override
    public String toString() {
        return "SearchCriteriaDTO{keywords='" + keywords + "'}";
    }
}