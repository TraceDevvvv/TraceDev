/**
 * InvalidFeedbackException - 自定义异常类
 * 当反馈数据无效或不充分时抛出此异常
 * 对应用例中的Errored用例
 */
public class InvalidFeedbackException extends Exception {
    
    /**
     * 构造函数，包含错误信息
     * @param message 错误信息
     */
    public InvalidFeedbackException(String message) {
        super(message);
    }
    
    /**
     * 构造函数，包含错误信息和原因
     * @param message 错误信息
     * @param cause 异常原因
     */
    public InvalidFeedbackException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 创建一个标准的异常对象，包含数据验证失败的具体原因
     * @param rating 评分值
     * @param comment 评论内容
     * @return 包含特定错误信息的InvalidFeedbackException对象
     */
    public static InvalidFeedbackException create(int rating, String comment) {
        StringBuilder errorMsg = new StringBuilder("反馈数据无效或不充分: ");
        
        // 检查评分是否有效
        if (rating < 1 || rating > 5) {
            errorMsg.append(String.format("评分 '%d' 不在有效范围内 (1-5)。", rating));
        }
        
        // 检查评论是否有效
        if (comment == null || comment.trim().isEmpty()) {
            if (errorMsg.length() > 0) errorMsg.append(" ");
            errorMsg.append("评论内容不能为空。");
        }
        
        // 如果评分有效但评论长度不足，也给出提示
        if (rating >= 1 && rating <= 5 && comment != null && !comment.trim().isEmpty() 
            && comment.trim().length() < 5) {
            errorMsg.append("评论内容至少需要5个字符。");
        }
        
        return new InvalidFeedbackException(errorMsg.toString());
    }
    
    /**
     * 创建一个简化的异常对象
     * @param reason 错误原因描述
     * @return 包含特定错误信息的InvalidFeedbackException对象
     */
    public static InvalidFeedbackException create(String reason) {
        return new InvalidFeedbackException("反馈数据无效: " + reason);
    }
}