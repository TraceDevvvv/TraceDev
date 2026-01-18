/**
 * FeedbackAlreadyReleasedException - 自定义异常类
 * 当游客已经为站点发布反馈时抛出此异常
 * 对应用例中的FeedbackAlreadyReleased用例
 */
public class FeedbackAlreadyReleasedException extends Exception {
    
    /**
     * 构造函数，包含错误信息
     * @param message 错误信息
     */
    public FeedbackAlreadyReleasedException(String message) {
        super(message);
    }
    
    /**
     * 构造函数，包含错误信息和原因
     * @param message 错误信息
     * @param cause 异常原因
     */
    public FeedbackAlreadyReleasedException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 创建一个标准的异常对象，包含游客ID和站点ID信息
     * @param touristId 游客ID
     * @param siteId 站点ID
     * @return 包含特定错误信息的FeedbackAlreadyReleasedException对象
     */
    public static FeedbackAlreadyReleasedException create(String touristId, String siteId) {
        String message = String.format("游客 '%s' 已经为站点 '%s' 发布过反馈。每个游客对每个站点只能发布一次反馈。", 
                touristId, siteId);
        return new FeedbackAlreadyReleasedException(message);
    }
}