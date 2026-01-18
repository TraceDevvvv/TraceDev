package com.tourist.feedback.exception;

import lombok.Getter;

/**
 * API异常基类
 * 所有自定义异常的父类，包含错误码、错误消息和详情
 * 这个类提供了统一的异常处理机制，确保所有异常都有标准化的错误信息
 * 符合PRD文档中的错误处理需求（P0-005）
 */
@Getter
public class ApiException extends RuntimeException {
    
    /**
     * 错误码，用于标识异常类型
     * 遵循统一的错误码规范，便于前端处理和国际化
     */
    private final String errorCode;
    
    /**
     * 错误详情，提供更具体的错误信息
     * 用于调试和用户友好的错误提示
     */
    private final String details;
    
    /**
     * 构造方法：使用错误码和错误消息创建异常
     * 
     * @param errorCode 错误码，用于标识异常类型
     * @param message 错误消息，简要描述错误原因
     */
    public ApiException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.details = null;
    }
    
    /**
     * 构造方法：使用错误码、错误消息和详情创建异常
     * 
     * @param errorCode 错误码，用于标识异常类型
     * @param message 错误消息，简要描述错误原因
     * @param details 错误详情，提供更具体的错误信息
     */
    public ApiException(String errorCode, String message, String details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }
    
    /**
     * 构造方法：使用错误码、错误消息、详情和原因创建异常
     * 用于包装底层异常，保留原始异常信息
     * 
     * @param errorCode 错误码，用于标识异常类型
     * @param message 错误消息，简要描述错误原因
     * @param details 错误详情，提供更具体的错误信息
     * @param cause 原始异常，用于异常链追踪
     */
    public ApiException(String errorCode, String message, String details, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.details = details;
    }
    
    /**
     * 获取完整的错误信息
     * 包含错误码、错误消息和详情（如果有）
     * 用于日志记录和调试
     * 
     * @return 格式化的错误信息字符串
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
     * 获取简化的错误信息（不包含错误码）
     * 用于用户友好的错误提示
     * 
     * @return 简化的错误信息字符串
     */
    public String getSimpleMessage() {
        return super.getMessage();
    }
    
    /**
     * 检查错误码是否匹配
     * 用于异常处理逻辑中的错误类型判断
     * 
     * @param expectedErrorCode 期望的错误码
     * @return true如果错误码匹配，false否则
     */
    public boolean isErrorCode(String expectedErrorCode) {
        return errorCode != null && errorCode.equals(expectedErrorCode);
    }
    
    /**
     * 创建异常构建器
     * 提供流畅的API用于构建异常对象
     * 
     * @param errorCode 错误码
     * @param message 错误消息
     * @return 异常构建器实例
     */
    public static Builder builder(String errorCode, String message) {
        return new Builder(errorCode, message);
    }
    
    /**
     * 异常构建器类
     * 提供流畅的API用于构建ApiException对象
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
         * 设置错误详情
         * 
         * @param details 错误详情
         * @return 构建器实例
         */
        public Builder details(String details) {
            this.details = details;
            return this;
        }
        
        /**
         * 设置原始异常
         * 
         * @param cause 原始异常
         * @return 构建器实例
         */
        public Builder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }
        
        /**
         * 构建ApiException对象
         * 
         * @return 构建好的ApiException实例
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