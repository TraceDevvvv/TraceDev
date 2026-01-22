package com.tourist.feedback.exception;

/**
 *         
 *       ID           ，               
 *                    ，             ViewVisitedSites  
 * 
 *     ：
 * 1.   ID        （      ID       ）
 * 2.              （isActive = false）
 * 3.           ID     
 * 4.                ID    
 * 
 * HTTP   ：404 Not Found
 */
public class UserNotFoundException extends ApiException {
    
    /**
     *      ：     
     *                
     */
    public static final String ERROR_CODE = "USER_NOT_FOUND";
    
    /**
     *       ：    ID   
     */
    public static final String DEFAULT_MESSAGE = "        ";
    
    /**
     *       ：         
     */
    public static final String DEFAULT_DETAILS = "     ID    ，         ";
    
    /**
     *   ID  ，           ID
     *              
     */
    private final Long userId;
    
    /**
     *     ：           ID         
     * 
     * @param userId        ID
     */
    public UserNotFoundException(Long userId) {
        super(ERROR_CODE, DEFAULT_MESSAGE, DEFAULT_DETAILS);
        this.userId = userId;
    }
    
    /**
     *     ：            ID         
     * 
     * @param userId        ID
     * @param message        
     */
    public UserNotFoundException(Long userId, String message) {
        super(ERROR_CODE, message, DEFAULT_DETAILS);
        this.userId = userId;
    }
    
    /**
     *     ：         、     ID         
     * 
     * @param userId        ID
     * @param message        
     * @param details        
     */
    public UserNotFoundException(Long userId, String message, String details) {
        super(ERROR_CODE, message, details);
        this.userId = userId;
    }
    
    /**
     *     ：         、  、  ID              
     *         ，        ，      ID
     * 
     * @param userId        ID
     * @param message        
     * @param details        
     * @param cause     ，         
     */
    public UserNotFoundException(Long userId, String message, String details, Throwable cause) {
        super(ERROR_CODE, message, details, cause);
        this.userId = userId;
    }
    
    /**
     *          ID
     * 
     * @return   ID
     */
    public Long getUserId() {
        return userId;
    }
    
    /**
     *          
     *      、    、     ID
     *             
     * 
     * @return            
     */
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(getErrorCode()).append("] ").append(super.getSimpleMessage());
        
        if (getDetails() != null && !getDetails().trim().isEmpty()) {
            sb.append(" - ").append(getDetails());
        }
        
        if (userId != null) {
            sb.append(" (  ID: ").append(userId).append(")");
        }
        
        return sb.toString();
    }
    
    /**
     *            
     *        ID  ，         
     * 
     * @return             
     */
    public String getUserFriendlyMessage() {
        if (userId != null) {
            return "  ID  \"" + userId + "\"       。     ID    。";
        } else {
            return "        。       。";
        }
    }
    
    /**
     *                  
     *                   
     * 
     * @return true          ，false  
     */
    public boolean isUserDeleted() {
        String details = getDetails();
        if (details == null) {
            return false;
        }
        
        return details.contains("  ") || 
               details.contains("  ") || 
               details.contains("deleted") ||
               getSimpleMessage().contains("  ");
    }
    
    /**
     *                  
     *                  
     * 
     * @return true          ，false  
     */
    public boolean isUserDisabled() {
        String details = getDetails();
        if (details == null) {
            return false;
        }
        
        return details.contains("  ") || 
               details.contains("  ") || 
               details.contains("disabled") ||
               details.contains("inactive");
    }
    
    /**
     *       
     *                
     *                
     * 
     * @return        
     */
    public String getOperationSuggestion() {
        if (isUserDeleted()) {
            return "      ，                 ";
        } else if (isUserDisabled()) {
            return "        ，          ";
        } else if (userId != null) {
            return "     ID    ，         ID";
        } else {
            return "       ，             ";
        }
    }
    
    /**
     *          
     *         ，           
     * 
     * @return           
     */
    public String getLogMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserNotFoundException{");
        sb.append("errorCode='").append(getErrorCode()).append("', ");
        sb.append("message='").append(getSimpleMessage()).append("', ");
        sb.append("details='").append(getDetails()).append("', ");
        sb.append("userId=").append(userId);
        
        if (getCause() != null) {
            sb.append(", cause=").append(getCause().getClass().getSimpleName());
            sb.append(": ").append(getCause().getMessage());
        }
        
        sb.append("}");
        return sb.toString();
    }
    
    /**
     *             
     *      API    UserNotFoundException  
     * 
     * @param userId   ID
     * @return             
     */
    public static Builder builder(Long userId) {
        return new Builder(userId);
    }
    
    /**
     *            
     *      API    UserNotFoundException  
     */
    public static class Builder {
        private final Long userId;
        private String message;
        private String details;
        private Throwable cause;
        
        private Builder(Long userId) {
            this.userId = userId;
            this.message = DEFAULT_MESSAGE;
            this.details = DEFAULT_DETAILS;
        }
        
        /**
         *       
         * 
         * @param message     
         * @return      
         */
        public Builder message(String message) {
            this.message = message != null && !message.trim().isEmpty() ? message : DEFAULT_MESSAGE;
            return this;
        }
        
        /**
         *       
         * 
         * @param details     
         * @return      
         */
        public Builder details(String details) {
            this.details = details != null && !details.trim().isEmpty() ? details : DEFAULT_DETAILS;
            return this;
        }
        
        /**
         *       
         * 
         * @param cause     
         * @return      
         */
        public Builder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }
        
        /**
         *   UserNotFoundException  
         * 
         * @return     UserNotFoundException  
         */
        public UserNotFoundException build() {
            if (cause != null) {
                return new UserNotFoundException(userId, message, details, cause);
            } else {
                return new UserNotFoundException(userId, message, details);
            }
        }
    }
    
    /**
     *   toString  
     *           ，    ID     
     * 
     * @return            
     */
    @Override
    public String toString() {
        return getLogMessage();
    }
}