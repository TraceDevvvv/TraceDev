package com.tourist.feedback.exception;

/**
 *         
 *       （ ETOUR   ）      ，    ViewVisitedSites               
 *                   ，                           
 * 
 *     ：
 * 1. ETOUR       （  ：  ViewVisitedSites        ）
 * 2.         
 * 3.            （ 200）
 * 4.        （    、DNS     ）
 * 5.              
 * 6.                
 * 
 * HTTP   ：503 Service Unavailable
 */
public class ServiceUnavailableException extends ApiException {
    
    /**
     *      ：     
     *                 
     */
    public static final String ERROR_CODE = "SERVICE_UNAVAILABLE";
    
    /**
     *       ：       
     */
    public static final String DEFAULT_MESSAGE = "       ";
    
    /**
     *       ：         
     */
    public static final String DEFAULT_DETAILS = "             ，     ";
    
    /**
     *     ，             
     *              
     */
    private final String serviceName;
    
    /**
     *      URL，           
     *            
     */
    private final String endpointUrl;
    
    /**
     *     ，            
     *              
     */
    private final int attemptCount;
    
    /**
     *       （  ），        
     *            
     */
    private final long timeoutMs;
    
    /**
     *       ，           （  ）
     *              
     */
    private final Long estimatedRecoveryTime;
    
    /**
     *     ：                      
     *              ，   ETOUR       
     * 
     * @param serviceName     ， "ETOUR-SERVER"
     */
    public ServiceUnavailableException(String serviceName) {
        super(ERROR_CODE, DEFAULT_MESSAGE, DEFAULT_DETAILS);
        this.serviceName = serviceName;
        this.endpointUrl = null;
        this.attemptCount = 1;
        this.timeoutMs = 0;
        this.estimatedRecoveryTime = null;
    }
    
    /**
     *     ：               
     * 
     * @param serviceName     
     * @param message     
     * @param details     
     * @param endpointUrl     URL
     * @param attemptCount     
     * @param timeoutMs     （  ）
     * @param estimatedRecoveryTime       
     * @param cause     
     */
    public ServiceUnavailableException(String serviceName, String message, String details, 
                                      String endpointUrl, int attemptCount, long timeoutMs, 
                                      Long estimatedRecoveryTime, Throwable cause) {
        super(ERROR_CODE, message, details, cause);
        this.serviceName = serviceName;
        this.endpointUrl = endpointUrl;
        this.attemptCount = attemptCount;
        this.timeoutMs = timeoutMs;
        this.estimatedRecoveryTime = estimatedRecoveryTime;
    }
    
    /**
     *       
     * 
     * @return     
     */
    public String getServiceName() {
        return serviceName;
    }
    
    /**
     *       URL
     * 
     * @return     URL，   null
     */
    public String getEndpointUrl() {
        return endpointUrl;
    }
    
    /**
     *       
     * 
     * @return     
     */
    public int getAttemptCount() {
        return attemptCount;
    }
    
    /**
     *       （  ）
     * 
     * @return     （  ）
     */
    public long getTimeoutMs() {
        return timeoutMs;
    }
    
    /**
     *         
     * 
     * @return       （     ），   null
     */
    public Long getEstimatedRecoveryTime() {
        return estimatedRecoveryTime;
    }
    
    /**
     *          
     * 
     * @return true         ，false  
     */
    public boolean hasEstimatedRecoveryTime() {
        return estimatedRecoveryTime != null && estimatedRecoveryTime > 0;
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
        
        if (serviceName != null && !serviceName.trim().isEmpty()) {
            sb.append("[").append(serviceName).append("     ] ");
        }
        
        sb.append(super.getSimpleMessage());
        
        if (getDetails() != null && !getDetails().trim().isEmpty()) {
            sb.append(" - ").append(getDetails());
        }
        
        if (attemptCount > 1) {
            sb.append(" (    : ").append(attemptCount).append(")");
        }
        
        if (hasEstimatedRecoveryTime()) {
            sb.append(" [      : ").append(formatRecoveryTime(estimatedRecoveryTime)).append("]");
        }
        
        return sb.toString();
    }
    
    /**
     *            
     *            ，         
     * 
     * @return             
     */
    public String getUserFriendlyMessage() {
        StringBuilder sb = new StringBuilder();
        
        if (serviceName != null) {
            sb.append(serviceName).append("  ");
        } else {
            sb.append("    ");
        }
        
        sb.append("     ");
        
        if (hasEstimatedRecoveryTime()) {
            sb.append("，    ").append(formatRecoveryTime(estimatedRecoveryTime)).append("   ");
        } else {
            sb.append("，     ");
        }
        
        return sb.toString();
    }
    
    /**
     *        
     *              
     * 
     * @param timestamp      
     * @return          
     */
    private String formatRecoveryTime(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long diffMillis = timestamp - currentTime;
        
        if (diffMillis <= 0) {
            return "     ";
        }
        
        long diffSeconds = diffMillis / 1000;
        long diffMinutes = diffSeconds / 60;
        long diffHours = diffMinutes / 60;
        
        if (diffHours > 0) {
            return diffHours + "  " + (diffMinutes % 60) + "  ";
        } else if (diffMinutes > 0) {
            return diffMinutes + "  " + (diffSeconds % 60) + " ";
        } else {
            return diffSeconds + " ";
        }
    }
    
    /**
     *          
     *                  
     *   ViewVisitedSites        
     * 
     * @return       
     */
    public BackoffStrategy getRecommendedBackoffStrategy() {
        if (attemptCount <= 0) {
            attemptCount = 1;
        }
        
        //       ：baseDelay * 2^(attempt-1)
        long baseDelay = 1000L; // 1     
        long maxDelay = 30000L; // 30     
        
        long delay = Math.min(baseDelay * (1L << (attemptCount - 1)), maxDelay);
        
        //              
        long jitter = (long)(Math.random() * 0.2 * delay); // 20%     
        delay += jitter;
        
        return new BackoffStrategy(delay, Math.min(attemptCount * 2, 10), 
            "          ，       " + delay + "  ");
    }
    
    /**
     *      
     *            
     */
    public static class BackoffStrategy {
        private final long delayMs;
        private final int maxRetries;
        private final String suggestion;
        
        public BackoffStrategy(long delayMs, int maxRetries, String suggestion) {
            this.delayMs = delayMs;
            this.maxRetries = maxRetries;
            this.suggestion = suggestion;
        }
        
        public long getDelayMs() {
            return delayMs;
        }
        
        public int getMaxRetries() {
            return maxRetries;
        }
        
        public String getSuggestion() {
            return suggestion;
        }
    }
    
    /**
     *   ETOUR         （    ViewVisitedSites       ）
     *         ，      ViewVisitedSites             
     * 
     * @param endpointUrl ETOUR     URL
     * @param attemptCount     
     * @param timeoutMs     
     * @param cause     
     * @return ETOUR           
     */
    public static ServiceUnavailableException etourConnectionLost(
            String endpointUrl, int attemptCount, long timeoutMs, Throwable cause) {
        return builder("ETOUR-SERVER")
                .message("ETOUR       ")
                .details("     ETOUR   ，          ")
                .endpointUrl(endpointUrl)
                .attemptCount(attemptCount)
                .timeoutMs(timeoutMs)
                .cause(cause)
                .build();
    }
    
    /**
     *             
     *      API    ServiceUnavailableException  
     * 
     * @param serviceName     
     * @return             
     */
    public static Builder builder(String serviceName) {
        return new Builder(serviceName);
    }
    
    /**
     *            
     *      API    ServiceUnavailableException  
     */
    public static class Builder {
        private final String serviceName;
        private String message;
        private String details;
        private String endpointUrl;
        private int attemptCount;
        private long timeoutMs;
        private Long estimatedRecoveryTime;
        private Throwable cause;
        
        private Builder(String serviceName) {
            this.serviceName = serviceName;
            this.message = DEFAULT_MESSAGE;
            this.details = DEFAULT_DETAILS;
            this.attemptCount = 1;
            this.timeoutMs = 5000; //   5   
            this.estimatedRecoveryTime = null;
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
         *       URL
         * 
         * @param endpointUrl     URL
         * @return      
         */
        public Builder endpointUrl(String endpointUrl) {
            this.endpointUrl = endpointUrl;
            return this;
        }
        
        /**
         *       
         * 
         * @param attemptCount     
         * @return      
         */
        public Builder attemptCount(int attemptCount) {
            this.attemptCount = Math.max(attemptCount, 1);
            return this;
        }
        
        /**
         *       （  ）
         * 
         * @param timeoutMs     （  ）
         * @return      
         */
        public Builder timeoutMs(long timeoutMs) {
            this.timeoutMs = Math.max(timeoutMs, 0);
            return this;
        }
        
        /**
         *         （     ）
         * 
         * @param estimatedRecoveryTime       （     ）
         * @return      
         */
        public Builder estimatedRecoveryTime(Long estimatedRecoveryTime) {
            this.estimatedRecoveryTime = estimatedRecoveryTime;
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
         *   ServiceUnavailableException  
         * 
         * @return     ServiceUnavailableException  
         */
        public ServiceUnavailableException build() {
            return new ServiceUnavailableException(
                serviceName,
                message,
                details,
                endpointUrl,
                attemptCount,
                timeoutMs,
                estimatedRecoveryTime,
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
        sb.append("ServiceUnavailableException{");
        sb.append("errorCode='").append(getErrorCode()).append("', ");
        sb.append("serviceName='").append(serviceName != null ? serviceName : "null").append("', ");
        sb.append("message='").append(getSimpleMessage()).append("', ");
        sb.append("details='").append(getDetails()).append("', ");
        sb.append("endpointUrl='").append(endpointUrl != null ? endpointUrl : "null").append("', ");
        sb.append("attemptCount=").append(attemptCount).append(", ");
        sb.append("timeoutMs=").append(timeoutMs).append(", ");
        sb.append("estimatedRecoveryTime=").append(estimatedRecoveryTime != null ? estimatedRecoveryTime : "null").append(", ");
        
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
}