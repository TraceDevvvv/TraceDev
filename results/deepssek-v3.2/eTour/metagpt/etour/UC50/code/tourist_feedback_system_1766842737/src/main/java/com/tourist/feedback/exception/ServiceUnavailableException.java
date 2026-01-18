package com.tourist.feedback.exception;

/**
 * 服务不可用异常类
 * 处理外部服务（如ETOUR服务器）不可用的情况，特别处理ViewVisitedSites用例中的服务器连接中断边界条件
 * 这个异常用于处理服务不可用的错误场景，确保系统在面对外部服务故障时有合适的降级和错误处理机制
 * 
 * 异常场景：
 * 1. ETOUR服务器连接中断（重要：处理ViewVisitedSites用例中的边界条件）
 * 2. 外部服务响应超时
 * 3. 外部服务返回错误状态码（非200）
 * 4. 外部服务不可达（网络分区、DNS解析失败等）
 * 5. 外部服务正在进行维护或升级
 * 6. 外部服务达到速率限制或配额耗尽
 * 
 * HTTP状态码：503 Service Unavailable
 */
public class ServiceUnavailableException extends ApiException {
    
    /**
     * 默认错误码：服务不可用
     * 用于标识服务不可用相关的异常类型
     */
    public static final String ERROR_CODE = "SERVICE_UNAVAILABLE";
    
    /**
     * 默认错误消息：提示服务不可用
     */
    public static final String DEFAULT_MESSAGE = "服务暂时不可用";
    
    /**
     * 默认错误详情：提供具体的重试建议
     */
    public static final String DEFAULT_DETAILS = "系统正在维护或遇到临时故障，请稍后重试";
    
    /**
     * 服务名称，记录引发异常的具体服务名称
     * 用于错误分类和恢复策略选择
     */
    private final String serviceName;
    
    /**
     * 服务端点的URL，记录引发异常的服务端点
     * 用于调试和服务健康检查
     */
    private final String endpointUrl;
    
    /**
     * 尝试次数，记录对服务的尝试访问次数
     * 用于实现重试逻辑和监控统计
     */
    private final int attemptCount;
    
    /**
     * 服务超时时间（毫秒），记录请求超时时间
     * 用于调整客户端超时配置
     */
    private final long timeoutMs;
    
    /**
     * 预计恢复时间，记录服务的预计恢复时间（可选）
     * 用于客户端展示预计恢复时间
     */
    private final Long estimatedRecoveryTime;
    
    /**
     * 构造方法：使用默认错误信息和服务名称创建服务不可用异常
     * 适用于大多数服务不可用场景，特别是ETOUR服务器连接中断
     * 
     * @param serviceName 服务名称，如"ETOUR-SERVER"
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
     * 构造方法：使用所有参数创建服务不可用异常
     * 
     * @param serviceName 服务名称
     * @param message 错误消息
     * @param details 错误详情
     * @param endpointUrl 服务端点URL
     * @param attemptCount 尝试次数
     * @param timeoutMs 超时时间（毫秒）
     * @param estimatedRecoveryTime 预计恢复时间
     * @param cause 原始异常
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
     * 获取服务名称
     * 
     * @return 服务名称
     */
    public String getServiceName() {
        return serviceName;
    }
    
    /**
     * 获取服务端点URL
     * 
     * @return 服务端点URL，可能为null
     */
    public String getEndpointUrl() {
        return endpointUrl;
    }
    
    /**
     * 获取尝试次数
     * 
     * @return 尝试次数
     */
    public int getAttemptCount() {
        return attemptCount;
    }
    
    /**
     * 获取超时时间（毫秒）
     * 
     * @return 超时时间（毫秒）
     */
    public long getTimeoutMs() {
        return timeoutMs;
    }
    
    /**
     * 获取预计恢复时间
     * 
     * @return 预计恢复时间（毫秒时间戳），可能为null
     */
    public Long getEstimatedRecoveryTime() {
        return estimatedRecoveryTime;
    }
    
    /**
     * 是否预计有恢复时间
     * 
     * @return true如果有预计恢复时间，false否则
     */
    public boolean hasEstimatedRecoveryTime() {
        return estimatedRecoveryTime != null && estimatedRecoveryTime > 0;
    }
    
    /**
     * 获取完整的错误信息
     * 包含错误码、错误消息、详情、服务名称和尝试次数
     * 用于详细的日志记录和调试
     * 
     * @return 格式化的错误信息字符串
     */
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(getErrorCode()).append("] ");
        
        if (serviceName != null && !serviceName.trim().isEmpty()) {
            sb.append("[").append(serviceName).append("服务不可用] ");
        }
        
        sb.append(super.getSimpleMessage());
        
        if (getDetails() != null && !getDetails().trim().isEmpty()) {
            sb.append(" - ").append(getDetails());
        }
        
        if (attemptCount > 1) {
            sb.append(" (尝试次数: ").append(attemptCount).append(")");
        }
        
        if (hasEstimatedRecoveryTime()) {
            sb.append(" [预计恢复时间: ").append(formatRecoveryTime(estimatedRecoveryTime)).append("]");
        }
        
        return sb.toString();
    }
    
    /**
     * 获取用户友好的错误消息
     * 包含服务名称和恢复建议，便于用户理解和操作
     * 
     * @return 用户友好的错误消息字符串
     */
    public String getUserFriendlyMessage() {
        StringBuilder sb = new StringBuilder();
        
        if (serviceName != null) {
            sb.append(serviceName).append("服务");
        } else {
            sb.append("系统服务");
        }
        
        sb.append("暂时不可用");
        
        if (hasEstimatedRecoveryTime()) {
            sb.append("，预计将在").append(formatRecoveryTime(estimatedRecoveryTime)).append("后恢复");
        } else {
            sb.append("，请稍后重试");
        }
        
        return sb.toString();
    }
    
    /**
     * 格式化恢复时间
     * 将毫秒时间戳转换为友好格式
     * 
     * @param timestamp 毫秒时间戳
     * @return 格式化的时间字符串
     */
    private String formatRecoveryTime(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long diffMillis = timestamp - currentTime;
        
        if (diffMillis <= 0) {
            return "很快就恢复";
        }
        
        long diffSeconds = diffMillis / 1000;
        long diffMinutes = diffSeconds / 60;
        long diffHours = diffMinutes / 60;
        
        if (diffHours > 0) {
            return diffHours + "小时" + (diffMinutes % 60) + "分钟";
        } else if (diffMinutes > 0) {
            return diffMinutes + "分钟" + (diffSeconds % 60) + "秒";
        } else {
            return diffSeconds + "秒";
        }
    }
    
    /**
     * 获取推荐的退避策略
     * 根据尝试次数和超时时间推荐退避策略
     * 处理ViewVisitedSites用例中的重试逻辑
     * 
     * @return 退避策略建议
     */
    public BackoffStrategy getRecommendedBackoffStrategy() {
        if (attemptCount <= 0) {
            attemptCount = 1;
        }
        
        // 指数退避算法：baseDelay * 2^(attempt-1)
        long baseDelay = 1000L; // 1秒基础延迟
        long maxDelay = 30000L; // 30秒最大延迟
        
        long delay = Math.min(baseDelay * (1L << (attemptCount - 1)), maxDelay);
        
        // 增加随机抖动以避免惊群效应
        long jitter = (long)(Math.random() * 0.2 * delay); // 20%的随机抖动
        delay += jitter;
        
        return new BackoffStrategy(delay, Math.min(attemptCount * 2, 10), 
            "建议使用指数退避重试，每次重试前等待" + delay + "毫秒");
    }
    
    /**
     * 退避策略类
     * 包含退避参数和建议描述
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
     * 创建ETOUR服务器连接中断异常（专门处理ViewVisitedSites用例的边界条件）
     * 这是一个便捷方法，专门用于处理ViewVisitedSites用例中的服务器连接中断场景
     * 
     * @param endpointUrl ETOUR服务器端点URL
     * @param attemptCount 尝试次数
     * @param timeoutMs 超时时间
     * @param cause 原始异常
     * @return ETOUR服务器连接中断异常实例
     */
    public static ServiceUnavailableException etourConnectionLost(
            String endpointUrl, int attemptCount, long timeoutMs, Throwable cause) {
        return builder("ETOUR-SERVER")
                .message("ETOUR服务器连接中断")
                .details("无法连接到ETOUR服务器，无法获取站点列表信息")
                .endpointUrl(endpointUrl)
                .attemptCount(attemptCount)
                .timeoutMs(timeoutMs)
                .cause(cause)
                .build();
    }
    
    /**
     * 创建服务不可用异常构建器
     * 提供流畅的API用于构建ServiceUnavailableException对象
     * 
     * @param serviceName 服务名称
     * @return 服务不可用异常构建器实例
     */
    public static Builder builder(String serviceName) {
        return new Builder(serviceName);
    }
    
    /**
     * 服务不可用异常构建器类
     * 提供流畅的API用于构建ServiceUnavailableException对象
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
            this.timeoutMs = 5000; // 默认5秒超时
            this.estimatedRecoveryTime = null;
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
         * 设置服务端点URL
         * 
         * @param endpointUrl 服务端点URL
         * @return 构建器实例
         */
        public Builder endpointUrl(String endpointUrl) {
            this.endpointUrl = endpointUrl;
            return this;
        }
        
        /**
         * 设置尝试次数
         * 
         * @param attemptCount 尝试次数
         * @return 构建器实例
         */
        public Builder attemptCount(int attemptCount) {
            this.attemptCount = Math.max(attemptCount, 1);
            return this;
        }
        
        /**
         * 设置超时时间（毫秒）
         * 
         * @param timeoutMs 超时时间（毫秒）
         * @return 构建器实例
         */
        public Builder timeoutMs(long timeoutMs) {
            this.timeoutMs = Math.max(timeoutMs, 0);
            return this;
        }
        
        /**
         * 设置预计恢复时间（毫秒时间戳）
         * 
         * @param estimatedRecoveryTime 预计恢复时间（毫秒时间戳）
         * @return 构建器实例
         */
        public Builder estimatedRecoveryTime(Long estimatedRecoveryTime) {
            this.estimatedRecoveryTime = estimatedRecoveryTime;
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
         * 构建ServiceUnavailableException对象
         * 
         * @return 构建好的ServiceUnavailableException实例
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
     * 获取详细的日志信息
     * 包含所有异常字段，用于系统监控和问题追踪
     * 
     * @return 详细的日志信息字符串
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
     * 重写toString方法
     * 提供更详细的异常信息，包含服务名称和连接状态
     * 
     * @return 格式化的异常信息字符串
     */
    @Override
    public String toString() {
        return getLogMessage();
    }
}