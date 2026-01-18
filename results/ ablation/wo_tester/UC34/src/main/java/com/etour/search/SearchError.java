package com.etour.search;

/**
 * Exception representing a search error.
 * Added to satisfy requirement Exit Conditions: Connection to server interrupted
 */
public class SearchError extends RuntimeException {
    private String message;
    private int errorCode;
    private SearchCriteria criteria;

    public SearchError(String message, int errorCode, SearchCriteria criteria) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.criteria = criteria;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    /**
     * Gets the error message for display.
     * @return Formatted error message.
     */
    public String getErrorMessage() {
        return String.format("[%d] Search failed for criteria: %s - %s", 
                errorCode, criteria.getSearchString(), message);
    }
}