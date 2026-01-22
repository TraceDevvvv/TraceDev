package com.tourist.feedback.exception;

import lombok.Getter;

/**
 * API    
 *           ，     、       
 *                ，                
 *   PRD          （P0-005）
 */
@Getter
public class ApiException extends RuntimeException {
    
    /**
     *    ，        
     *           ，          
     */
    private final String errorCode;
    
    /**
     *     ，          
     *               
     */
    private final String details;
    
    /**
     *     ：              
     * 
     * @param errorCode    ，        
     * @param message     ，        
     */
    public ApiException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.details = null;
    }
    
    /**
     *     ：     、           
     * 
     * @param errorCode    ，        
     * @param message     ，        
     * @param details     ，          
     */
    public ApiException(String errorCode, String message, String details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }
    
    /**
     *     ：     、    、         
     *         ，        
     * 
     * @param errorCode    ，        
     * @param message     ，        
     * @param details     ，          
     * @param cause     ，       
     */
    public ApiException(String errorCode, String message, String details, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.details = details;
    }
    
    /**
     *          
     *      、       （   ）
     *          
     * 
     * @return            
     */
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(errorCode).append("] ").append(super.getMessage());
        
        if (details != null && !details.trim().isEmpty()) {
            sb.append(" - ").append(details);
        }
        
        return sb.toString();
    }
    
    /**
     *          （      ）
     *            
     * 
     * @return           
     */
    public String getSimpleMessage() {
        return super.getMessage();
    }
    
    /**
     *          
     *                 
     * 
     * @param expectedErrorCode       
     * @return true       ，false  
     */
    public boolean isErrorCode(String expectedErrorCode) {
        return errorCode != null && errorCode.equals(expectedErrorCode);
    }
    
    /**
     *        
     *      API        
     * 
     * @param errorCode    
     * @param message     
     * @return        
     */
    public static Builder builder(String errorCode, String message) {
        return new Builder(errorCode, message);
    }
    
    /**
     *       
     *      API    ApiException  
     */
    public static class Builder {
        private final String errorCode;
        private final String message;
        private String details;
        private Throwable cause;
        
        private Builder(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }
        
        /**
         *       
         * 
         * @param details     
         * @return      
         */
        public Builder details(String details) {
            this.details = details;
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
         *   ApiException  
         * 
         * @return     ApiException  
         */
        public ApiException build() {
            if (cause != null) {
                return new ApiException(errorCode, message, details, cause);
            } else if (details != null) {
                return new ApiException(errorCode, message, details);
            } else {
                return new ApiException(errorCode, message);
            }
        }
    }
}