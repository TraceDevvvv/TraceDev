package com.tourist.feedback.exception;

/**
 *        
 *          、    、          ，    ViewVisitedSites               
 *                    ，                          
 * 
 *     ：
 * 1.             （  ：             ）
 * 2. SQL           
 * 3.          
 * 4.         
 * 5.            
 * 6.          
 * 
 * HTTP   ：503 Service Unavailable   500 Internal Server Error
 */
public class DataAccessException extends ApiException {
    
    /**
     *      ：      
     *                
     */
    public static final String ERROR_CODE = "DATA_ACCESS_ERROR";
    
    /**
     *       ：        
     */
    public static final String DEFAULT_MESSAGE = "      ";
    
    /**
     *       ：         
     */
    public static final String DEFAULT_DETAILS = "     ，             ";
    
    /**
     *        ，             
     *              
     */
    private final DataOperation operation;
    
    /**
     * SQL       ，         SQL   
     *          
     */
    private final String operationId;
    
    /**
     *        ，               
     *            
     */
    private final boolean retryable;
    
    /**
     *         ，                
     *     ViewVisitedSites          
     */
    private final boolean connectionLost;
    
    /**
     *         
     *            ，         
     */
    public enum DataOperation {
        QUERY("  "),
        INSERT("  "),
        UPDATE("  "),
        DELETE("  "),
        TRANSACTION("  "),
        CONNECTION("  "),
        UNKNOWN("  ");
        
        private final String description;
        
        DataOperation(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     *     ：                     
     *               
     * 
     * @param operation       
     */
    public DataAccessException(DataOperation operation) {
        super(ERROR_CODE, DEFAULT_MESSAGE, DEFAULT_DETAILS);
        this.operation = operation;
        this.operationId = null;
        this.retryable = true; //      
        this.connectionLost = false;
    }
    
    /**
     *     ：         、                 
     * 
     * @param operation       
     * @param message        
     * @param cause     ，          （ JPA、JDBC）
     */
    public DataAccessException(DataOperation operation, String message, Throwable cause) {
        super(ERROR_CODE, message, DEFAULT_DETAILS, cause);
        this.operation = operation;
        this.operationId = null;
        this.retryable = determineRetryable(cause);
        this.connectionLost = determineConnectionLost(cause);
    }
    
    /**
     *     ：       ，          
     * 
     * @param operation       
     * @param message        
     * @param details        
     * @param operationId     
     * @param retryable      
     * @param connectionLost       
     * @param cause     
     */
    public DataAccessException(DataOperation operation, String message, String details, 
                              String operationId, boolean retryable, boolean connectionLost, 
                              Throwable cause) {
        super(ERROR_CODE, message, details, cause);
        this.operation = operation;
        this.operationId = operationId;
        this.retryable = retryable;
        this.connectionLost = connectionLost;
    }
    
    /**
     *         
     * 
     * @return       
     */
    public DataOperation getOperation() {
        return operation;
    }
    
    /**
     *       
     * 
     * @return     ，   null
     */
    public String getOperationId() {
        return operationId;
    }
    
    /**
     *      
     * 
     * @return true            ，false  
     */
    public boolean isRetryable() {
        return retryable;
    }
    
    /**
     *       （  ：  ViewVisitedSites       ）
     *               ，                
     * 
     * @return true            ，false  
     */
    public boolean isConnectionLost() {
        return connectionLost;
    }
    
    /**
     *          
     *      、    、  、         
     *             
     * 
     * @return            
     */
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(getErrorCode()).append("] ");
        
        if (operation != null) {
            sb.append("[").append(operation.getDescription()).append("    ] ");
        }
        
        sb.append(super.getSimpleMessage());
        
        if (getDetails() != null && !getDetails().trim().isEmpty()) {
            sb.append(" - ").append(getDetails());
        }
        
        if (operationId != null && !operationId.trim().isEmpty()) {
            sb.append(" (  ID: ").append(operationId).append(")");
        }
        
        if (connectionLost) {
            sb.append(" [     ]");
        }
        
        return sb.toString();
    }
    
    /**
     *            
     *            ，         
     * 
     * @return             
     */
    public String getOperationFriendlyMessage() {
        StringBuilder sb = new StringBuilder();
        
        if (operation != null) {
            sb.append(operation.getDescription()).append("    ");
        } else {
            sb.append("      ");
        }
        
        if (connectionLost) {
            sb.append("：        ");
        } else if (getCause() != null) {
            String causeMessage = getCause().getMessage();
            if (causeMessage != null && !causeMessage.trim().isEmpty()) {
                sb.append("：").append(causeMessage);
            }
        }
        
        return sb.toString();
    }
    
    /**
     *         
     *                   
     *              
     * 
     * @return         
     */
    public RetrySuggestion getRetrySuggestion() {
        if (!retryable) {
            return new RetrySuggestion(false, 0, 0, "           ");
        }
        
        if (connectionLost) {
            return new RetrySuggestion(true, 3, 5000, 
                "    ，        ，       ");
        }
        
        if (operation == DataOperation.CONNECTION) {
            return new RetrySuggestion(true, 5, 1000, 
                "    ，      ，      ");
        }
        
        return new RetrySuggestion(true, 3, 1000, 
            "      ，               ");
    }
    
    /**
     *        
     *            
     */
    public static class RetrySuggestion {
        private final boolean shouldRetry;
        private final int maxRetries;
        private final long retryDelayMs;
        private final String suggestion;
        
        public RetrySuggestion(boolean shouldRetry, int maxRetries, long retryDelayMs, String suggestion) {
            this.shouldRetry = shouldRetry;
            this.maxRetries = maxRetries;
            this.retryDelayMs = retryDelayMs;
            this.suggestion = suggestion;
        }
        
        public boolean shouldRetry() {
            return shouldRetry;
        }
        
        public int getMaxRetries() {
            return maxRetries;
        }
        
        public long getRetryDelayMs() {
            return retryDelayMs;
        }
        
        public String getSuggestion() {
            return suggestion;
        }
    }
    
    /**
     *               
     *         ，        
     * 
     * @param cause     
     * @return true          ，false  
     */
    private boolean determineRetryable(Throwable cause) {
        if (cause == null) {
            return true; //      
        }
        
        String message = cause.getMessage();
        if (message != null) {
            //          
            if (message.contains("    ") || message.contains("syntax error") ||
                message.contains("      ") || message.contains("duplicate key") ||
                message.contains("      ") || message.contains("foreign key constraint")) {
                return false;
            }
        }
        
        //             
        return true;
    }
    
    /**
     *               
     *     ViewVisitedSites               
     * 
     * @param cause     
     * @return true            ，false  
     */
    private boolean determineConnectionLost(Throwable cause) {
        if (cause == null) {
            return false;
        }
        
        String message = cause.getMessage();
        if (message != null) {
            return message.contains("  ") || 
                   message.contains("connect") || 
                   message.contains("  ") ||
                   message.contains("communication") || 
                   message.contains("  ") ||
                   message.contains("timeout") || 
                   message.contains("  ") ||
                   message.contains("interrupt") || 
                   message.contains("  ") ||
                   message.contains("disconnect") ||
                   cause.getClass().getSimpleName().contains("Connect") ||
                   cause.getClass().getSimpleName().contains("Timeout");
        }
        
        return false;
    }
    
    /**
     *            
     *      API    DataAccessException  
     * 
     * @param operation       
     * @return            
     */
    public static Builder builder(DataOperation operation) {
        return new Builder(operation);
    }
    
    /**
     *           
     *      API    DataAccessException  
     */
    public static class Builder {
        private final DataOperation operation;
        private String message;
        private String details;
        private String operationId;
        private Boolean retryable;
        private Boolean connectionLost;
        private Throwable cause;
        
        private Builder(DataOperation operation) {
            this.operation = operation;
            this.message = DEFAULT_MESSAGE;
            this.details = DEFAULT_DETAILS;
            this.retryable = null; //   cause    
            this.connectionLost = null; //   cause    
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
         * @param operationId     
         * @return      
         */
        public Builder operationId(String operationId) {
            this.operationId = operationId;
            return this;
        }
        
        /**
         *        
         * 
         * @param retryable      
         * @return      
         */
        public Builder retryable(boolean retryable) {
            this.retryable = retryable;
            return this;
        }
        
        /**
         *         
         * 
         * @param connectionLost       
         * @return      
         */
        public Builder connectionLost(boolean connectionLost) {
            this.connectionLost = connectionLost;
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
         *   DataAccessException  
         * 
         * @return     DataAccessException  
         */
        public DataAccessException build() {
            boolean finalRetryable = retryable != null ? retryable : determineRetryable(cause);
            boolean finalConnectionLost = connectionLost != null ? connectionLost : determineConnectionLost(cause);
            
            return new DataAccessException(
                operation, 
                message, 
                details, 
                operationId, 
                finalRetryable, 
                finalConnectionLost, 
                cause
            );
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
        sb.append("DataAccessException{");
        sb.append("errorCode='").append(getErrorCode()).append("', ");
        sb.append("operation=").append(operation != null ? operation.name() : "null").append(", ");
        sb.append("message='").append(getSimpleMessage()).append("', ");
        sb.append("details='").append(getDetails()).append("', ");
        sb.append("operationId='").append(operationId != null ? operationId : "null").append("', ");
        sb.append("retryable=").append(retryable).append(", ");
        sb.append("connectionLost=").append(connectionLost).append(", ");
        
        if (getCause() != null) {
            sb.append("cause=").append(getCause().getClass().getSimpleName());
            sb.append(": '").append(getCause().getMessage()).append("'");
        }
        
        sb.append("}");
        return sb.toString();
    }
    
    /**
     *   toString  
     *           ，           
     * 
     * @return            
     */
    @Override
    public String toString() {
        return getLogMessage();
    }
    
    /**
     *         （    ViewVisitedSites       ）
     *         ，                
     * 
     * @param operation       
     * @param operationId     
     * @param cause     
     * @return         
     */
    public static DataAccessException connectionLost(DataOperation operation, String operationId, Throwable cause) {
        return builder(operation)
                .message("       ")
                .details("     ETOUR   ，            ")
                .operationId(operationId)
                .connectionLost(true)
                .retryable(true) //           
                .cause(cause)
                .build();
    }
    
    /**
     *         （    ViewVisitedSites       ）
     *         ，                  
     * 
     * @param userId   ID
     * @param cause     
     * @return         
     */
    public static DataAccessException queryFailed(Long userId, Throwable cause) {
        String operationId = "QUERY_VISITED_SITES_" + userId;
        return builder(DataOperation.QUERY)
                .message("          ")
                .details("      （ID: " + userId + "）       ，     ")
                .operationId(operationId)
                .cause(cause)
                .build();
    }
}