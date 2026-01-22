package com.tourist.feedback.exception;

/**
 *        
 *                ，  ViewVisitedSites       ：       
 *                    ，               ViewVisitedSites  
 * 
 *     ：
 * 1.            （            ）
 * 2.            
 * 3.               
 * 4.             
 * 
 * HTTP   ：401 Unauthorized
 */
public class AuthenticationException extends ApiException {
    
    /**
     *      ：     
     *              
     */
    public static final String ERROR_CODE = "UNAUTHORIZED";
    
    /**
     *       ：        
     */
    public static final String DEFAULT_MESSAGE = "           ";
    
    /**
     *       ：         
     */
    public static final String DEFAULT_DETAILS = "            ";
    
    /**
     *     ：              
     *             
     */
    public AuthenticationException() {
        super(ERROR_CODE, DEFAULT_MESSAGE, DEFAULT_DETAILS);
    }
    
    /**
     *     ：               
     * 
     * @param message        
     */
    public AuthenticationException(String message) {
        super(ERROR_CODE, message, DEFAULT_DETAILS);
    }
    
    /**
     *     ：                  
     * 
     * @param message        
     * @param details        
     */
    public AuthenticationException(String message, String details) {
        super(ERROR_CODE, message, details);
    }
    
    /**
     *     ：         、             
     *           ，        
     * 
     * @param message        
     * @param details        
     * @param cause     ，        （ Spring Security）
     */
    public AuthenticationException(String message, String details, Throwable cause) {
        super(ERROR_CODE, message, details, cause);
    }
    
    /**
     *     ：            
     *               
     * 
     * @param cause     ，        
     */
    public AuthenticationException(Throwable cause) {
        super(ERROR_CODE, DEFAULT_MESSAGE, DEFAULT_DETAILS, cause);
    }
    
    /**
     *              
     *     ，          
     *            
     * 
     * @param message        
     * @return        
     */
    private static String ensureMessage(String message) {
        return message != null && !message.trim().isEmpty() ? message : DEFAULT_MESSAGE;
    }
    
    /**
     *              
     *     ，          
     *            
     * 
     * @param details        
     * @return        
     */
    private static String ensureDetails(String details) {
        return details != null && !details.trim().isEmpty() ? details : DEFAULT_DETAILS;
    }
    
    /**
     *          
     *      API          
     * 
     * @return          
     */
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     *         
     *      API    AuthenticationException  
     */
    public static class Builder {
        private String message;
        private String details;
        private Throwable cause;
        
        private Builder() {
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
            this.message = ensureMessage(message);
            return this;
        }
        
        /**
         *       
         * 
         * @param details     
         * @return      
         */
        public Builder details(String details) {
            this.details = ensureDetails(details);
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
         *   AuthenticationException  
         * 
         * @return     AuthenticationException  
         */
        public AuthenticationException build() {
            if (cause != null) {
                return new AuthenticationException(message, details, cause);
            } else {
                return new AuthenticationException(message, details);
            }
        }
    }
    
    /**
     *          
     *              
     * 
     * @return            
     */
    public String getFriendlyMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(getSimpleMessage());
        
        if (getDetails() != null && !getDetails().trim().isEmpty()) {
            sb.append("：").append(getDetails());
        }
        
        return sb.toString();
    }
    
    /**
     *          
     *                         
     *                     
     * 
     * @return true         ，false  
     */
    public boolean isTokenExpired() {
        String details = getDetails();
        if (details == null) {
            return false;
        }
        
        return details.contains("  ") || 
               details.contains("expired") || 
               details.contains("expire") ||
               getSimpleMessage().contains("  ");
    }
    
    /**
     *          
     *                 /         
     *               
     * 
     * @return true         ，false  
     */
    public boolean isInvalidCredentials() {
        String details = getDetails();
        if (details == null) {
            return false;
        }
        
        return details.contains("  ") || 
               details.contains("   ") || 
               details.contains("  ") ||
               details.contains("invalid") || 
               details.contains("credential");
    }
    
    /**
     *       
     *                
     *           
     * 
     * @return        
     */
    public String getRetrySuggestion() {
        if (isTokenExpired()) {
            return "     ，     ";
        } else if (isInvalidCredentials()) {
            return "        ，      ";
        } else {
            return "            ";
        }
    }
    
    /**
     *   toString  
     *           ，      
     * 
     * @return            
     */
    @Override
    public String toString() {
        return "AuthenticationException{" +
                "errorCode='" + getErrorCode() + '\'' +
                ", message='" + getSimpleMessage() + '\'' +
                ", details='" + getDetails() + '\'' +
                '}';
    }
}