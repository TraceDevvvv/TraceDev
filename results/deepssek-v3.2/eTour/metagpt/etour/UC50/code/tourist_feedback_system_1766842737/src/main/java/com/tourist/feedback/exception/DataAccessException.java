package com.tourist.feedback.exception;

/**
 * 数据访问异常类
 * 处理数据库查询失败、连接中断、事务异常等数据层错误，特别处理ViewVisitedSites用例中的服务器连接中断边界条件
 * 这个异常用于处理数据访问相关的错误场景，确保系统在面对数据层故障时有合适的降级和错误处理机制
 * 
 * 异常场景：
 * 1. 数据库连接失败或连接超时（重要：处理服务器连接中断边界条件）
 * 2. SQL查询语法错误或执行失败
 * 3. 数据库事务回滚失败
 * 4. 数据库连接池耗尽
 * 5. 数据库服务器崩溃或维护
 * 6. 网络分区或通信故障
 * 
 * HTTP状态码：503 Service Unavailable 或 500 Internal Server Error
 */
public class DataAccessException extends ApiException {
    
    /**
     * 默认错误码：数据访问错误
     * 用于标识数据访问相关的异常类型
     */
    public static final String ERROR_CODE = "DATA_ACCESS_ERROR";
    
    /**
     * 默认错误消息：提示数据访问失败
     */
    public static final String DEFAULT_MESSAGE = "数据访问失败";
    
    /**
     * 默认错误详情：提供具体的重试建议
     */
    public static final String DEFAULT_DETAILS = "请稍后重试，如问题持续存在请联系管理员";
    
    /**
     * 数据库操作类型，记录引发异常的具体操作类型
     * 用于错误分类和恢复策略选择
     */
    private final DataOperation operation;
    
    /**
     * SQL语句或操作标识，记录引发异常的具体SQL或操作
     * 用于调试和性能分析
     */
    private final String operationId;
    
    /**
     * 是否可重试标志，表示此异常是否可以通过重试恢复
     * 用于客户端选择重试策略
     */
    private final boolean retryable;
    
    /**
     * 是否连接中断标志，特别标记服务器连接中断的边界条件
     * 用于处理ViewVisitedSites用例中的服务中断场景
     */
    private final boolean connectionLost;
    
    /**
     * 数据操作类型枚举
     * 定义常见的数据操作类型，便于异常分类和处理
     */
    public enum DataOperation {
        QUERY("查询"),
        INSERT("插入"),
        UPDATE("更新"),
        DELETE("删除"),
        TRANSACTION("事务"),
        CONNECTION("连接"),
        UNKNOWN("未知");
        
        private final String description;
        
        DataOperation(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 构造方法：使用默认错误信息和操作类型创建数据访问异常
     * 适用于大多数数据访问失败场景
     * 
     * @param operation 数据操作类型
     */
    public DataAccessException(DataOperation operation) {
        super(ERROR_CODE, DEFAULT_MESSAGE, DEFAULT_DETAILS);
        this.operation = operation;
        this.operationId = null;
        this.retryable = true; // 默认可重试
        this.connectionLost = false;
    }
    
    /**
     * 构造方法：使用自定义错误消息、操作类型和原始异常创建数据访问异常
     * 
     * @param operation 数据操作类型
     * @param message 自定义错误消息
     * @param cause 原始异常，通常来自数据访问框架（如JPA、JDBC）
     */
    public DataAccessException(DataOperation operation, String message, Throwable cause) {
        super(ERROR_CODE, message, DEFAULT_DETAILS, cause);
        this.operation = operation;
        this.operationId = null;
        this.retryable = determineRetryable(cause);
        this.connectionLost = determineConnectionLost(cause);
    }
    
    /**
     * 构造方法：完整的构造参数，支持所有字段的自定义
     * 
     * @param operation 数据操作类型
     * @param message 自定义错误消息
     * @param details 自定义错误详情
     * @param operationId 操作标识
     * @param retryable 是否可重试
     * @param connectionLost 是否连接中断
     * @param cause 原始异常
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
     * 获取数据操作类型
     * 
     * @return 数据操作类型
     */
    public DataOperation getOperation() {
        return operation;
    }
    
    /**
     * 获取操作标识
     * 
     * @return 操作标识，可能为null
     */
    public String getOperationId() {
        return operationId;
    }
    
    /**
     * 是否可重试
     * 
     * @return true如果异常可以通过重试恢复，false否则
     */
    public boolean isRetryable() {
        return retryable;
    }
    
    /**
     * 是否连接中断（重要：处理ViewVisitedSites用例的边界条件）
     * 特别标记服务器连接中断的场景，客户端可以根据此标志采取降级策略
     * 
     * @return true如果是连接中断导致的异常，false否则
     */
    public boolean isConnectionLost() {
        return connectionLost;
    }
    
    /**
     * 获取完整的错误信息
     * 包含错误码、错误消息、详情、操作类型和操作标识
     * 用于详细的日志记录和调试
     * 
     * @return 格式化的错误信息字符串
     */
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(getErrorCode()).append("] ");
        
        if (operation != null) {
            sb.append("[").append(operation.getDescription()).append("操作失败] ");
        }
        
        sb.append(super.getSimpleMessage());
        
        if (getDetails() != null && !getDetails().trim().isEmpty()) {
            sb.append(" - ").append(getDetails());
        }
        
        if (operationId != null && !operationId.trim().isEmpty()) {
            sb.append(" (操作ID: ").append(operationId).append(")");
        }
        
        if (connectionLost) {
            sb.append(" [连接已中断]");
        }
        
        return sb.toString();
    }
    
    /**
     * 获取操作友好的错误消息
     * 包含操作类型和恢复建议，便于用户理解和操作
     * 
     * @return 操作友好的错误消息字符串
     */
    public String getOperationFriendlyMessage() {
        StringBuilder sb = new StringBuilder();
        
        if (operation != null) {
            sb.append(operation.getDescription()).append("操作失败");
        } else {
            sb.append("数据操作失败");
        }
        
        if (connectionLost) {
            sb.append("：服务器连接已中断");
        } else if (getCause() != null) {
            String causeMessage = getCause().getMessage();
            if (causeMessage != null && !causeMessage.trim().isEmpty()) {
                sb.append("：").append(causeMessage);
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 获取重试策略建议
     * 根据异常类型和特征返回相应的重试策略
     * 用于客户端实现智能重试逻辑
     * 
     * @return 重试策略建议对象
     */
    public RetrySuggestion getRetrySuggestion() {
        if (!retryable) {
            return new RetrySuggestion(false, 0, 0, "此错误不可通过重试恢复");
        }
        
        if (connectionLost) {
            return new RetrySuggestion(true, 3, 5000, 
                "连接中断，建议指数退避重试，成功后恢复连接");
        }
        
        if (operation == DataOperation.CONNECTION) {
            return new RetrySuggestion(true, 5, 1000, 
                "连接问题，建议线性重试，检查网络连接");
        }
        
        return new RetrySuggestion(true, 3, 1000, 
            "建议立即重试，如果持续失败请等待一段时间再试");
    }
    
    /**
     * 重试策略建议类
     * 包含重试参数和建议描述
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
     * 判断异常是否可以通过重试恢复
     * 分析原始异常类型，确定是否适合重试
     * 
     * @param cause 原始异常
     * @return true如果可以通过重试恢复，false否则
     */
    private boolean determineRetryable(Throwable cause) {
        if (cause == null) {
            return true; // 默认可重试
        }
        
        String message = cause.getMessage();
        if (message != null) {
            // 以下情况不适合重试
            if (message.contains("语法错误") || message.contains("syntax error") ||
                message.contains("违反唯一约束") || message.contains("duplicate key") ||
                message.contains("违反外键约束") || message.contains("foreign key constraint")) {
                return false;
            }
        }
        
        // 连接相关错误通常可以重试
        return true;
    }
    
    /**
     * 判断是否是连接中断导致的异常
     * 特别处理ViewVisitedSites用例中的服务器连接中断边界条件
     * 
     * @param cause 原始异常
     * @return true如果是连接中断导致的异常，false否则
     */
    private boolean determineConnectionLost(Throwable cause) {
        if (cause == null) {
            return false;
        }
        
        String message = cause.getMessage();
        if (message != null) {
            return message.contains("连接") || 
                   message.contains("connect") || 
                   message.contains("通信") ||
                   message.contains("communication") || 
                   message.contains("超时") ||
                   message.contains("timeout") || 
                   message.contains("中断") ||
                   message.contains("interrupt") || 
                   message.contains("断开") ||
                   message.contains("disconnect") ||
                   cause.getClass().getSimpleName().contains("Connect") ||
                   cause.getClass().getSimpleName().contains("Timeout");
        }
        
        return false;
    }
    
    /**
     * 创建数据访问异常构建器
     * 提供流畅的API用于构建DataAccessException对象
     * 
     * @param operation 数据操作类型
     * @return 数据访问异常构建器实例
     */
    public static Builder builder(DataOperation operation) {
        return new Builder(operation);
    }
    
    /**
     * 数据访问异常构建器类
     * 提供流畅的API用于构建DataAccessException对象
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
            this.retryable = null; // 根据cause自动判断
            this.connectionLost = null; // 根据cause自动判断
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
         * 设置操作标识
         * 
         * @param operationId 操作标识
         * @return 构建器实例
         */
        public Builder operationId(String operationId) {
            this.operationId = operationId;
            return this;
        }
        
        /**
         * 设置是否可重试
         * 
         * @param retryable 是否可重试
         * @return 构建器实例
         */
        public Builder retryable(boolean retryable) {
            this.retryable = retryable;
            return this;
        }
        
        /**
         * 设置是否连接中断
         * 
         * @param connectionLost 是否连接中断
         * @return 构建器实例
         */
        public Builder connectionLost(boolean connectionLost) {
            this.connectionLost = connectionLost;
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
         * 构建DataAccessException对象
         * 
         * @return 构建好的DataAccessException实例
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
     * 获取详细的日志信息
     * 包含所有异常字段，用于系统监控和问题追踪
     * 
     * @return 详细的日志信息字符串
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
     * 重写toString方法
     * 提供更详细的异常信息，包含操作类型和连接状态
     * 
     * @return 格式化的异常信息字符串
     */
    @Override
    public String toString() {
        return getLogMessage();
    }
    
    /**
     * 创建连接中断异常（专门处理ViewVisitedSites用例的边界条件）
     * 这是一个便捷方法，专门用于处理服务器连接中断的场景
     * 
     * @param operation 数据操作类型
     * @param operationId 操作标识
     * @param cause 原始异常
     * @return 连接中断异常实例
     */
    public static DataAccessException connectionLost(DataOperation operation, String operationId, Throwable cause) {
        return builder(operation)
                .message("服务器连接中断")
                .details("无法连接到ETOUR服务器，请检查网络连接并稍后重试")
                .operationId(operationId)
                .connectionLost(true)
                .retryable(true) // 连接中断通常可以重试
                .cause(cause)
                .build();
    }
    
    /**
     * 创建查询失败异常（专门处理ViewVisitedSites用例的核心功能）
     * 这是一个便捷方法，专门用于处理用户访问站点查询失败场景
     * 
     * @param userId 用户ID
     * @param cause 原始异常
     * @return 查询失败异常实例
     */
    public static DataAccessException queryFailed(Long userId, Throwable cause) {
        String operationId = "QUERY_VISITED_SITES_" + userId;
        return builder(DataOperation.QUERY)
                .message("查询用户访问站点失败")
                .details("无法获取用户（ID: " + userId + "）的访问站点列表，请稍后重试")
                .operationId(operationId)
                .cause(cause)
                .build();
    }
}