package com.tourist.feedback.exception;

/**
 * 用户认证异常类
 * 当用户未认证或认证已过期时抛出，对应ViewVisitedSites用例的入口条件：游客已成功认证
 * 这个异常用于处理用户认证相关的错误场景，确保只有成功认证的用户才能访问ViewVisitedSites功能
 * 
 * 异常场景：
 * 1. 用户未登录或登录已过期（接口未携带有效的认证令牌）
 * 2. 认证令牌无效或已被撤销
 * 3. 用户认证信息不完整或格式错误
 * 4. 用户认证过程发生系统错误
 * 
 * HTTP状态码：401 Unauthorized
 */
public class AuthenticationException extends ApiException {
    
    /**
     * 默认错误码：用户未认证
     * 用于标识认证相关的异常类型
     */
    public static final String ERROR_CODE = "UNAUTHORIZED";
    
    /**
     * 默认错误消息：提示用户重新登录
     */
    public static final String DEFAULT_MESSAGE = "用户未认证或认证已过期";
    
    /**
     * 默认错误详情：提供具体的操作建议
     */
    public static final String DEFAULT_DETAILS = "请重新登录后再尝试此操作";
    
    /**
     * 构造方法：使用默认错误信息创建认证异常
     * 适用于大多数认证失败场景
     */
    public AuthenticationException() {
        super(ERROR_CODE, DEFAULT_MESSAGE, DEFAULT_DETAILS);
    }
    
    /**
     * 构造方法：使用自定义错误消息创建认证异常
     * 
     * @param message 自定义错误消息
     */
    public AuthenticationException(String message) {
        super(ERROR_CODE, message, DEFAULT_DETAILS);
    }
    
    /**
     * 构造方法：使用自定义错误消息和详情创建认证异常
     * 
     * @param message 自定义错误消息
     * @param details 自定义错误详情
     */
    public AuthenticationException(String message, String details) {
        super(ERROR_CODE, message, details);
    }
    
    /**
     * 构造方法：使用自定义错误消息、详情和原始异常创建认证异常
     * 用于包装底层认证异常，保留原始异常信息
     * 
     * @param message 自定义错误消息
     * @param details 自定义错误详情
     * @param cause 原始异常，通常来自认证框架（如Spring Security）
     */
    public AuthenticationException(String message, String details, Throwable cause) {
        super(ERROR_CODE, message, details, cause);
    }
    
    /**
     * 构造方法：使用原始异常创建认证异常
     * 自动从原始异常中提取错误信息
     * 
     * @param cause 原始异常，通常来自认证框架
     */
    public AuthenticationException(Throwable cause) {
        super(ERROR_CODE, DEFAULT_MESSAGE, DEFAULT_DETAILS, cause);
    }
    
    /**
     * 检查传入的错误消息是否为空
     * 如果为空，则返回默认的错误消息
     * 用于确保异常消息不为空
     * 
     * @param message 传入的错误消息
     * @return 非空的错误消息
     */
    private static String ensureMessage(String message) {
        return message != null && !message.trim().isEmpty() ? message : DEFAULT_MESSAGE;
    }
    
    /**
     * 检查传入的错误详情是否为空
     * 如果为空，则返回默认的错误详情
     * 用于确保异常详情不为空
     * 
     * @param details 传入的错误详情
     * @return 非空的错误详情
     */
    private static String ensureDetails(String details) {
        return details != null && !details.trim().isEmpty() ? details : DEFAULT_DETAILS;
    }
    
    /**
     * 创建认证异常构建器
     * 提供流畅的API用于构建认证异常对象
     * 
     * @return 认证异常构建器实例
     */
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * 认证异常构建器类
     * 提供流畅的API用于构建AuthenticationException对象
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
         * 设置错误消息
         * 
         * @param message 错误消息
         * @return 构建器实例
         */
        public Builder message(String message) {
            this.message = ensureMessage(message);
            return this;
        }
        
        /**
         * 设置错误详情
         * 
         * @param details 错误详情
         * @return 构建器实例
         */
        public Builder details(String details) {
            this.details = ensureDetails(details);
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
         * 构建AuthenticationException对象
         * 
         * @return 构建好的AuthenticationException实例
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
     * 获取简洁的错误描述
     * 用于生成用户友好的错误提示
     * 
     * @return 格式化的错误描述字符串
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
     * 是否为令牌过期异常
     * 根据错误详情判断是否是因为认证令牌过期导致的异常
     * 用于客户端决定是否需要刷新令牌或重新登录
     * 
     * @return true如果是令牌过期异常，false否则
     */
    public boolean isTokenExpired() {
        String details = getDetails();
        if (details == null) {
            return false;
        }
        
        return details.contains("过期") || 
               details.contains("expired") || 
               details.contains("expire") ||
               getSimpleMessage().contains("过期");
    }
    
    /**
     * 是否为凭证无效异常
     * 根据错误详情判断是否是因为用户名/密码错误导致的异常
     * 用于客户端显示特定的错误提示
     * 
     * @return true如果是凭证无效异常，false否则
     */
    public boolean isInvalidCredentials() {
        String details = getDetails();
        if (details == null) {
            return false;
        }
        
        return details.contains("密码") || 
               details.contains("用户名") || 
               details.contains("凭证") ||
               details.contains("invalid") || 
               details.contains("credential");
    }
    
    /**
     * 获取重试建议
     * 根据异常类型返回相应的重试建议
     * 用于前端展示操作指导
     * 
     * @return 重试建议字符串
     */
    public String getRetrySuggestion() {
        if (isTokenExpired()) {
            return "认证已过期，请重新登录";
        } else if (isInvalidCredentials()) {
            return "用户名或密码错误，请检查后重试";
        } else {
            return "请重新登录后再尝试此操作";
        }
    }
    
    /**
     * 重写toString方法
     * 提供更详细的异常信息，包含类型标识
     * 
     * @return 格式化的异常信息字符串
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