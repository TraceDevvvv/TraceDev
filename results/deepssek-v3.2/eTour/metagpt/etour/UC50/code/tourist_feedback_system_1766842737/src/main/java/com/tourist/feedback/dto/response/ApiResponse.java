package com.tourist.feedback.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *    API     
 *     /    、  、      ，         
 *           API    ，    API           
 *   PRD    API    
 * 
 *     ：
 *     ：
 * {
 *   "success": true,
 *   "timestamp": "2024-01-15T14:30:00",
 *   "data": {...},
 *   "total": 100,
 *   "page": 1,
 *   "size": 10
 * }
 * 
 *     ：
 * {
 *   "success": false,
 *   "timestamp": "2024-01-15T14:30:00",
 *   "error": {
 *     "code": "UNAUTHORIZED",
 *     "message": "           ",
 *     "details": "     "
 *   }
 * }
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "API    ")
public class ApiResponse<T> {
    
    /**
     *         
     * true：    ，data        
     * false：    ，error        
     */
    @Schema(description = "      ", example = "true")
    private boolean success;
    
    /**
     *      
     *             ，  ISO 8601  
     *               
     */
    @Schema(description = "     ", example = "2024-01-15T14:30:00")
    private LocalDateTime timestamp;
    
    /**
     *     
     *  success true ，            
     *  success false ，      null
     *   T         ，        
     */
    @Schema(description = "    ")
    private T data;
    
    /**
     *          
     *               
     *           
     */
    @Schema(description = "    （     ）", example = "100")
    private Long total;
    
    /**
     *     （ 1  ）
     *               
     */
    @Schema(description = "    （     ）", example = "1")
    private Integer page;
    
    /**
     *     
     *               
     */
    @Schema(description = "    （     ）", example = "10")
    private Integer size;
    
    /**
     *       
     *  success false ，            
     *      、         
     */
    @Schema(description = "    （     ）")
    private ErrorInfo error;
    
    /**
     *        
     *   API    ，     、         
     *             
     */
    @Data
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "      ")
    public static class ErrorInfo {
        
        /**
         *    ，        
         *           ，          
         *   ：UNAUTHORIZED, USER_NOT_FOUND, DATA_ACCESS_ERROR 
         */
        @Schema(description = "   ", example = "UNAUTHORIZED")
        private String code;
        
        /**
         *     ，        
         *            
         */
        @Schema(description = "    ", example = "           ")
        private String message;
        
        /**
         *     ，          
         *            
         */
        @Schema(description = "    ", example = "     ")
        private String details;
        
        /**
         *     ：        
         * 
         * @param code    
         * @param message     
         */
        public ErrorInfo(String code, String message) {
            this.code = code;
            this.message = message;
        }
        
        /**
         *     ：        
         * 
         * @param code    
         * @param message     
         * @param details     
         */
        public ErrorInfo(String code, String message, String details) {
            this.code = code;
            this.message = message;
            this.details = details;
        }
    }
    
    /**
     *       
     *       ApiResponse  ，           
     * 
     * @param success       
     * @param data     
     * @param error     
     */
    private ApiResponse(boolean success, T data, ErrorInfo error) {
        this.success = success;
        this.data = data;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }
    
    /**
     *      API  
     *             
     * 
     * @param <T>       
     * @param data     
     * @return    ApiResponse  
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }
    
    /**
     *       API  
     *                
     * 
     * @param <T>       
     * @return    ApiResponse  
     */
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(true, null, null);
    }
    
    /**
     *            API  
     *             
     * 
     * @param <T>       
     * @param data     （     ）
     * @param total     
     * @param page     
     * @param size     
     * @return          ApiResponse  
     */
    public static <T> ApiResponse<T> success(T data, long total, int page, int size) {
        ApiResponse<T> response = new ApiResponse<>(true, data, null);
        response.setTotal(total);
        response.setPage(page);
        response.setSize(size);
        return response;
    }
    
    /**
     *      API  
     *            
     * 
     * @param <T>       
     * @param errorCode    
     * @param errorMessage     
     * @return    ApiResponse  
     */
    public static <T> ApiResponse<T> error(String errorCode, String errorMessage) {
        ErrorInfo errorInfo = new ErrorInfo(errorCode, errorMessage);
        return new ApiResponse<>(false, null, errorInfo);
    }
    
    /**
     *          API  
     *                   
     * 
     * @param <T>       
     * @param errorCode    
     * @param errorMessage     
     * @param errorDetails     
     * @return        ApiResponse  
     */
    public static <T> ApiResponse<T> error(String errorCode, String errorMessage, String errorDetails) {
        ErrorInfo errorInfo = new ErrorInfo(errorCode, errorMessage, errorDetails);
        return new ApiResponse<>(false, null, errorInfo);
    }
    
    /**
     *          
     *      API          
     * 
     * @return          
     */
    public static ErrorResponseBuilder errorBuilder() {
        return new ErrorResponseBuilder();
    }
    
    /**
     *          
     *      API          
     * 
     * @return          
     */
    public static <T> SuccessResponseBuilder<T> successBuilder() {
        return new SuccessResponseBuilder<>();
    }
    
    /**
     *       
     *                
     * 
     * @param total     
     * @param page     
     * @param size     
     * @return   ApiResponse  （      ）
     */
    public ApiResponse<T> withPagination(long total, int page, int size) {
        this.total = total;
        this.page = page;
        this.size = size;
        return this;
    }
    
    /**
     *             
     * 
     * @return true          ，false  
     */
    public boolean hasPagination() {
        return total != null && page != null && size != null;
    }
    
    /**
     *           
     * 
     * @return true        ，false  
     */
    public boolean hasData() {
        return data != null;
    }
    
    /**
     *         
     *              ，          
     * 
     * @param defaultValue    
     * @return       
     */
    public T getDataOrDefault(T defaultValue) {
        return hasData() ? data : defaultValue;
    }
    
    /**
     *         
     *      API          
     */
    public static class ErrorResponseBuilder {
        private String errorCode;
        private String errorMessage;
        private String errorDetails;
        
        private ErrorResponseBuilder() {
            //       
        }
        
        /**
         *      
         * 
         * @param errorCode    
         * @return      
         */
        public ErrorResponseBuilder code(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }
        
        /**
         *       
         * 
         * @param errorMessage     
         * @return      
         */
        public ErrorResponseBuilder message(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }
        
        /**
         *       
         * 
         * @param errorDetails     
         * @return      
         */
        public ErrorResponseBuilder details(String errorDetails) {
            this.errorDetails = errorDetails;
            return this;
        }
        
        /**
         *         
         * 
         * @param <T>       
         * @return     ApiResponse  
         */
        public <T> ApiResponse<T> build() {
            if (errorDetails != null) {
                return ApiResponse.error(errorCode, errorMessage, errorDetails);
            } else {
                return ApiResponse.error(errorCode, errorMessage);
            }
        }
    }
    
    /**
     *         
     *      API          
     */
    public static class SuccessResponseBuilder<T> {
        private T data;
        private Long total;
        private Integer page;
        private Integer size;
        
        private SuccessResponseBuilder() {
            //       
        }
        
        /**
         *       
         * 
         * @param data     
         * @return      
         */
        public SuccessResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }
        
        /**
         *       
         * 
         * @param total     
         * @param page     
         * @param size     
         * @return      
         */
        public SuccessResponseBuilder<T> pagination(long total, int page, int size) {
            this.total = total;
            this.page = page;
            this.size = size;
            return this;
        }
        
        /**
         *         
         * 
         * @return     ApiResponse  
         */
        public ApiResponse<T> build() {
            if (total != null && page != null && size != null) {
                return ApiResponse.success(data, total, page, size);
            } else {
                return ApiResponse.success(data);
            }
        }
    }
    
    /**
     *         
     *                  
     * 
     * @param <T>       
     * @param message        
     * @param details        
     * @return         
     */
    public static <T> ApiResponse<T> unauthorized(String message, String details) {
        return ApiResponse.error("UNAUTHORIZED", message, details);
    }
    
    /**
     *          
     *                  
     * 
     * @param <T>       
     * @param details        
     * @return          
     */
    public static <T> ApiResponse<T> userNotFound(String details) {
        return ApiResponse.error("USER_NOT_FOUND", "     ", details);
    }
    
    /**
     *           
     *                   
     * 
     * @param <T>      
     * @param details        
     * @return           
     */
    public static <T> ApiResponse<T> dataAccessError(String details) {
        return ApiResponse.error("DATA_ACCESS_ERROR", "      ", details);
    }
    
    /**
     *          
     *                   （         ）
     * 
     * @param <T>      
     * @param details        
     * @return          
     */
    public static <T> ApiResponse<T> serviceUnavailable(String details) {
        return ApiResponse.error("SERVICE_UNAVAILABLE", "       ", details);
    }
    
    /**
     *   toString  
     *           ，         
     * 
     * @return            
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApiResponse{");
        sb.append("success=").append(success);
        sb.append(", timestamp=").append(timestamp);
        
        if (hasData()) {
            sb.append(", data=").append(data.getClass().getSimpleName());
        } else {
            sb.append(", data=null");
        }
        
        if (hasPagination()) {
            sb.append(", total=").append(total);
            sb.append(", page=").append(page);
            sb.append(", size=").append(size);
        }
        
        if (error != null) {
            sb.append(", error=").append(error);
        }
        
        sb.append("}");
        return sb.toString();
    }
}