/**
 * 自定义异常类，表示与ETOUR服务器的连接中断异常
 * 继承自Exception类，用于处理服务器连接问题
 */
public class ETourConnectionException extends Exception {
    
    /**
     * 构造方法，创建没有详细消息的异常
     */
    public ETourConnectionException() {
        super();
    }
    
    /**
     * 构造方法，创建带有详细消息的异常
     * @param message 异常详细信息
     */
    public ETourConnectionException(String message) {
        super(message);
    }
    
    /**
     * 构造方法，创建带有详细消息和原因的异常
     * @param message 异常详细信息
     * @param cause 异常原因
     */
    public ETourConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 构造方法，创建带有原因的异常
     * @param cause 异常原因
     */
    public ETourConnectionException(Throwable cause) {
        super(cause);
    }
}