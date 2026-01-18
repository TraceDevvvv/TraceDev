package com.tourist.feedback.exception;

/**
 * 用户不存在异常类
 * 当查询的用户ID对应的用户不存在时抛出，包括用户被删除或状态异常的情况
 * 这个异常用于处理用户查询相关的错误场景，确保只有存在的用户才能访问ViewVisitedSites功能
 * 
 * 异常场景：
 * 1. 用户ID在数据库中不存在（可能是错误的ID或用户已被删除）
 * 2. 用户状态为禁用或非激活状态（isActive = false）
 * 3. 用户信息被逻辑删除但ID仍然被引用
 * 4. 用户数据迁移或同步过程中导致的ID映射错误
 * 
 * HTTP状态码：404 Not Found
 */
public class UserNotFoundException extends ApiException {
    
    /**
     * 默认错误码：用户不存在
     * 用于标识用户查找相关的异常类型
     */
    public static final String ERROR_CODE = "USER_NOT_FOUND";
    
    /**
     * 默认错误消息：提示用户ID不存在
     */
    public static final String DEFAULT_MESSAGE = "指定的用户不存在";
    
    /**
     * 默认错误详情：提供具体的操作建议
     */
    public static final String DEFAULT_DETAILS = "请检查用户ID是否正确，或用户可能已被删除";
    
    /**
     * 用户ID字段，记录引发异常的具体用户ID
     * 用于错误日志记录和调试信息
     */
    private final Long userId;
    
    /**
     * 构造方法：使用默认错误信息和用户ID创建用户不存在异常
     * 
     * @param userId 引发异常的用户ID
     */
    public UserNotFoundException(Long userId) {
        super(ERROR_CODE, DEFAULT_MESSAGE, DEFAULT_DETAILS);
        this.userId = userId;
    }
    
    /**
     * 构造方法：使用自定义错误消息和用户ID创建用户不存在异常
     * 
     * @param userId 引发异常的用户ID
     * @param message 自定义错误消息
     */
    public UserNotFoundException(Long userId, String message) {
        super(ERROR_CODE, message, DEFAULT_DETAILS);
        this.userId = userId;
    }
    
    /**
     * 构造方法：使用自定义错误消息、详情和用户ID创建用户不存在异常
     * 
     * @param userId 引发异常的用户ID
     * @param message 自定义错误消息
     * @param details 自定义错误详情
     */
    public UserNotFoundException(Long userId, String message, String details) {
        super(ERROR_CODE, message, details);
        this.userId = userId;
    }
    
    /**
     * 构造方法：使用自定义错误消息、详情、用户ID和原始异常创建用户不存在异常
     * 用于包装底层异常，保留原始异常信息，同时记录用户ID
     * 
     * @param userId 引发异常的用户ID
     * @param message 自定义错误消息
     * @param details 自定义错误详情
     * @param cause 原始异常，通常来自数据库操作
     */
    public UserNotFoundException(Long userId, String message, String details, Throwable cause) {
        super(ERROR_CODE, message, details, cause);
        this.userId = userId;
    }
    
    /**
     * 获取引发异常的用户ID
     * 
     * @return 用户ID
     */
    public Long getUserId() {
        return userId;
    }
    
    /**
     * 获取完整的错误信息
     * 包含错误码、错误消息、详情和用户ID
     * 用于详细的日志记录和调试
     * 
     * @return 格式化的错误信息字符串
     */
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(getErrorCode()).append("] ").append(super.getSimpleMessage());
        
        if (getDetails() != null && !getDetails().trim().isEmpty()) {
            sb.append(" - ").append(getDetails());
        }
        
        if (userId != null) {
            sb.append(" (用户ID: ").append(userId).append(")");
        }
        
        return sb.toString();
    }
    
    /**
     * 获取用户友好的错误消息
     * 包含明确的用户ID信息，便于用户理解和操作
     * 
     * @return 用户友好的错误消息字符串
     */
    public String getUserFriendlyMessage() {
        if (userId != null) {
            return "用户ID为 \"" + userId + "\" 的用户不存在。请检查用户ID是否正确。";
        } else {
            return "指定的用户不存在。请检查用户信息。";
        }
    }
    
    /**
     * 检查是否是因为用户被删除导致的异常
     * 根据错误详情判断用户是否是被逻辑删除
     * 
     * @return true如果用户是被删除状态，false否则
     */
    public boolean isUserDeleted() {
        String details = getDetails();
        if (details == null) {
            return false;
        }
        
        return details.contains("删除") || 
               details.contains("删除") || 
               details.contains("deleted") ||
               getSimpleMessage().contains("删除");
    }
    
    /**
     * 检查是否是因为用户被禁用导致的异常
     * 根据错误详情判断用户是否是禁用状态
     * 
     * @return true如果用户是被禁用状态，false否则
     */
    public boolean isUserDisabled() {
        String details = getDetails();
        if (details == null) {
            return false;
        }
        
        return details.contains("禁用") || 
               details.contains("停用") || 
               details.contains("disabled") ||
               details.contains("inactive");
    }
    
    /**
     * 获取操作建议
     * 根据异常原因返回相应的操作建议
     * 用于前端展示指导用户下一步操作
     * 
     * @return 操作建议字符串
     */
    public String getOperationSuggestion() {
        if (isUserDeleted()) {
            return "用户已被删除，请联系管理员恢复账户或使用其他账户";
        } else if (isUserDisabled()) {
            return "用户账户已被禁用，请联系管理员激活账户";
        } else if (userId != null) {
            return "请检查用户ID是否正确，或尝试使用其他用户ID";
        } else {
            return "请检查用户信息，确认用户是否存在且状态正常";
        }
    }
    
    /**
     * 获取详细的日志信息
     * 包含所有异常字段，用于系统监控和问题追踪
     * 
     * @return 详细的日志信息字符串
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
     * 创建用户不存在异常构建器
     * 提供流畅的API用于构建UserNotFoundException对象
     * 
     * @param userId 用户ID
     * @return 用户不存在异常构建器实例
     */
    public static Builder builder(Long userId) {
        return new Builder(userId);
    }
    
    /**
     * 用户不存在异常构建器类
     * 提供流畅的API用于构建UserNotFoundException对象
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
         * 设置错误消息
         * 
         * @param message 错误消息
         * @return 构建器实例
         */
        public Builder message(String message) {
            this.message = message != null && !message.trim().isEmpty() ? message : DEFAULT_MESSAGE;
            return this;
        }
        
        /**
         * 设置错误详情
         * 
         * @param details 错误详情
         * @return 构建器实例
         */
        public Builder details(String details) {
            this.details = details != null && !details.trim().isEmpty() ? details : DEFAULT_DETAILS;
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
         * 构建UserNotFoundException对象
         * 
         * @return 构建好的UserNotFoundException实例
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
     * 重写toString方法
     * 提供更详细的异常信息，包含用户ID和类型标识
     * 
     * @return 格式化的异常信息字符串
     */
    @Override
    public String toString() {
        return getLogMessage();
    }
}