package com.tourist.feedback.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 统一的API响应格式类
 * 包含成功/失败响应、数据、错误码和消息，支持分页数据的封装
 * 这个类提供了标准化的API响应格式，确保所有API接口返回一致的响应结构
 * 符合PRD文档中的API设计规范
 * 
 * 响应格式：
 * 成功响应：
 * {
 *   "success": true,
 *   "timestamp": "2024-01-15T14:30:00",
 *   "data": {...},
 *   "total": 100,
 *   "page": 1,
 *   "size": 10
 * }
 * 
 * 失败响应：
 * {
 *   "success": false,
 *   "timestamp": "2024-01-15T14:30:00",
 *   "error": {
 *     "code": "UNAUTHORIZED",
 *     "message": "用户未认证或认证已过期",
 *     "details": "请重新登录"
 *   }
 * }
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "API响应对象")
public class ApiResponse<T> {
    
    /**
     * 请求是否成功处理
     * true：请求成功，data字段包含有效数据
     * false：请求失败，error字段包含错误信息
     */
    @Schema(description = "请求是否成功", example = "true")
    private boolean success;
    
    /**
     * 响应时间戳
     * 记录服务器处理完成的时间，使用ISO 8601格式
     * 便于客户端计算响应时间和调试
     */
    @Schema(description = "响应时间戳", example = "2024-01-15T14:30:00")
    private LocalDateTime timestamp;
    
    /**
     * 响应数据
     * 当success为true时，此字段包含请求的结果数据
     * 当success为false时，此字段通常为null
     * 泛型T表示返回数据的类型，支持各种数据类型
     */
    @Schema(description = "响应数据")
    private T data;
    
    /**
     * 分页数据的总记录数
     * 仅当响应数据是分页列表时有效
     * 用于前端实现分页控件
     */
    @Schema(description = "总记录数（分页时有效）", example = "100")
    private Long total;
    
    /**
     * 当前页码（从1开始）
     * 仅当响应数据是分页列表时有效
     */
    @Schema(description = "当前页码（分页时有效）", example = "1")
    private Integer page;
    
    /**
     * 每页大小
     * 仅当响应数据是分页列表时有效
     */
    @Schema(description = "每页大小（分页时有效）", example = "10")
    private Integer size;
    
    /**
     * 错误信息对象
     * 当success为false时，此字段包含详细的错误信息
     * 包括错误码、错误消息和错误详情
     */
    @Schema(description = "错误信息（失败时有效）")
    private ErrorInfo error;
    
    /**
     * 错误信息内部类
     * 封装API错误信息，包含错误码、错误消息和错误详情
     * 用于标准化的错误响应格式
     */
    @Data
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "错误信息对象")
    public static class ErrorInfo {
        
        /**
         * 错误码，用于标识错误类型
         * 遵循统一的错误码规范，便于前端处理和国际化
         * 示例：UNAUTHORIZED, USER_NOT_FOUND, DATA_ACCESS_ERROR等
         */
        @Schema(description = "错误码", example = "UNAUTHORIZED")
        private String code;
        
        /**
         * 错误消息，简要描述错误原因
         * 用于用户友好的错误提示
         */
        @Schema(description = "错误消息", example = "用户未认证或认证已过期")
        private String message;
        
        /**
         * 错误详情，提供更具体的错误信息
         * 用于调试和提供操作建议
         */
        @Schema(description = "错误详情", example = "请重新登录")
        private String details;
        
        /**
         * 构造方法：创建错误信息对象
         * 
         * @param code 错误码
         * @param message 错误消息
         */
        public ErrorInfo(String code, String message) {
            this.code = code;
            this.message = message;
        }
        
        /**
         * 构造方法：创建错误信息对象
         * 
         * @param code 错误码
         * @param message 错误消息
         * @param details 错误详情
         */
        public ErrorInfo(String code, String message, String details) {
            this.code = code;
            this.message = message;
            this.details = details;
        }
    }
    
    /**
     * 私有构造方法
     * 用于内部创建ApiResponse对象，外部应使用静态工厂方法
     * 
     * @param success 请求是否成功
     * @param data 响应数据
     * @param error 错误信息
     */
    private ApiResponse(boolean success, T data, ErrorInfo error) {
        this.success = success;
        this.data = data;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }
    
    /**
     * 创建成功的API响应
     * 适用于大多数成功请求场景
     * 
     * @param <T> 响应数据类型
     * @param data 响应数据
     * @return 成功的ApiResponse对象
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }
    
    /**
     * 创建空的成功API响应
     * 适用于不需要返回数据的成功请求
     * 
     * @param <T> 响应数据类型
     * @return 成功的ApiResponse对象
     */
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(true, null, null);
    }
    
    /**
     * 创建带有分页信息的成功API响应
     * 适用于分页查询的成功请求
     * 
     * @param <T> 响应数据类型
     * @param data 响应数据（通常是列表）
     * @param total 总记录数
     * @param page 当前页码
     * @param size 每页大小
     * @return 带有分页信息的成功ApiResponse对象
     */
    public static <T> ApiResponse<T> success(T data, long total, int page, int size) {
        ApiResponse<T> response = new ApiResponse<>(true, data, null);
        response.setTotal(total);
        response.setPage(page);
        response.setSize(size);
        return response;
    }
    
    /**
     * 创建失败的API响应
     * 适用于所有失败请求场景
     * 
     * @param <T> 响应数据类型
     * @param errorCode 错误码
     * @param errorMessage 错误消息
     * @return 失败的ApiResponse对象
     */
    public static <T> ApiResponse<T> error(String errorCode, String errorMessage) {
        ErrorInfo errorInfo = new ErrorInfo(errorCode, errorMessage);
        return new ApiResponse<>(false, null, errorInfo);
    }
    
    /**
     * 创建带有详情的失败API响应
     * 适用于需要提供详细错误信息的失败请求
     * 
     * @param <T> 响应数据类型
     * @param errorCode 错误码
     * @param errorMessage 错误消息
     * @param errorDetails 错误详情
     * @return 带有详情的失败ApiResponse对象
     */
    public static <T> ApiResponse<T> error(String errorCode, String errorMessage, String errorDetails) {
        ErrorInfo errorInfo = new ErrorInfo(errorCode, errorMessage, errorDetails);
        return new ApiResponse<>(false, null, errorInfo);
    }
    
    /**
     * 创建错误响应构建器
     * 提供流畅的API用于构建错误响应对象
     * 
     * @return 错误响应构建器实例
     */
    public static ErrorResponseBuilder errorBuilder() {
        return new ErrorResponseBuilder();
    }
    
    /**
     * 创建成功响应构建器
     * 提供流畅的API用于构建成功响应对象
     * 
     * @return 成功响应构建器实例
     */
    public static <T> SuccessResponseBuilder<T> successBuilder() {
        return new SuccessResponseBuilder<>();
    }
    
    /**
     * 设置分页信息
     * 用于添加分页信息到现有响应对象
     * 
     * @param total 总记录数
     * @param page 当前页码
     * @param size 每页大小
     * @return 当前ApiResponse对象（支持链式调用）
     */
    public ApiResponse<T> withPagination(long total, int page, int size) {
        this.total = total;
        this.page = page;
        this.size = size;
        return this;
    }
    
    /**
     * 检查响应是否包含分页信息
     * 
     * @return true如果响应包含分页信息，false否则
     */
    public boolean hasPagination() {
        return total != null && page != null && size != null;
    }
    
    /**
     * 检查响应是否包含数据
     * 
     * @return true如果响应包含数据，false否则
     */
    public boolean hasData() {
        return data != null;
    }
    
    /**
     * 获取数据或默认值
     * 如果响应包含数据则返回数据，否则返回指定的默认值
     * 
     * @param defaultValue 默认值
     * @return 数据或默认值
     */
    public T getDataOrDefault(T defaultValue) {
        return hasData() ? data : defaultValue;
    }
    
    /**
     * 错误响应构建器类
     * 提供流畅的API用于构建错误响应对象
     */
    public static class ErrorResponseBuilder {
        private String errorCode;
        private String errorMessage;
        private String errorDetails;
        
        private ErrorResponseBuilder() {
            // 私有构造方法
        }
        
        /**
         * 设置错误码
         * 
         * @param errorCode 错误码
         * @return 构建器实例
         */
        public ErrorResponseBuilder code(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }
        
        /**
         * 设置错误消息
         * 
         * @param errorMessage 错误消息
         * @return 构建器实例
         */
        public ErrorResponseBuilder message(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }
        
        /**
         * 设置错误详情
         * 
         * @param errorDetails 错误详情
         * @return 构建器实例
         */
        public ErrorResponseBuilder details(String errorDetails) {
            this.errorDetails = errorDetails;
            return this;
        }
        
        /**
         * 构建错误响应对象
         * 
         * @param <T> 响应数据类型
         * @return 构建好的ApiResponse对象
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
     * 成功响应构建器类
     * 提供流畅的API用于构建成功响应对象
     */
    public static class SuccessResponseBuilder<T> {
        private T data;
        private Long total;
        private Integer page;
        private Integer size;
        
        private SuccessResponseBuilder() {
            // 私有构造方法
        }
        
        /**
         * 设置响应数据
         * 
         * @param data 响应数据
         * @return 构建器实例
         */
        public SuccessResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }
        
        /**
         * 设置分页信息
         * 
         * @param total 总记录数
         * @param page 当前页码
         * @param size 每页大小
         * @return 构建器实例
         */
        public SuccessResponseBuilder<T> pagination(long total, int page, int size) {
            this.total = total;
            this.page = page;
            this.size = size;
            return this;
        }
        
        /**
         * 构建成功响应对象
         * 
         * @return 构建好的ApiResponse对象
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
     * 创建认证失败响应
     * 专门用于处理用户认证相关的失败响应
     * 
     * @param <T> 响应数据类型
     * @param message 自定义错误消息
     * @param details 自定义错误详情
     * @return 认证失败响应对象
     */
    public static <T> ApiResponse<T> unauthorized(String message, String details) {
        return ApiResponse.error("UNAUTHORIZED", message, details);
    }
    
    /**
     * 创建用户不存在响应
     * 专门用于处理用户查找相关的失败响应
     * 
     * @param <T> 响应数据类型
     * @param details 自定义错误详情
     * @return 用户不存在响应对象
     */
    public static <T> ApiResponse<T> userNotFound(String details) {
        return ApiResponse.error("USER_NOT_FOUND", "用户不存在", details);
    }
    
    /**
     * 创建数据访问错误响应
     * 专门用于处理数据库访问相关的失败响应
     * 
     * @param <T>响应数据类型
     * @param details 自定义错误详情
     * @return 数据访问错误响应对象
     */
    public static <T> ApiResponse<T> dataAccessError(String details) {
        return ApiResponse.error("DATA_ACCESS_ERROR", "数据访问失败", details);
    }
    
    /**
     * 创建服务不可用响应
     * 专门用于处理服务不可用相关的失败响应（包括服务器连接中断）
     * 
     * @param <T>响应数据类型
     * @param details 自定义错误详情
     * @return 服务不可用响应对象
     */
    public static <T> ApiResponse<T> serviceUnavailable(String details) {
        return ApiResponse.error("SERVICE_UNAVAILABLE", "服务暂时不可用", details);
    }
    
    /**
     * 重写toString方法
     * 提供格式化的响应信息，便于日志记录和调试
     * 
     * @return 格式化的响应信息字符串
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