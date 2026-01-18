import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * EmailService类 - 处理邮件发送服务
 * 模拟向学生家长发送缺勤变更通知邮件
 * 注意：这是一个简化的模拟实现，实际系统中需要完整的邮件服务器配置
 */
public class EmailService {
    
    private String smtpHost;
    private int smtpPort;
    private String senderEmail;
    private String senderPassword;
    private boolean useSSL;
    
    /**
     * 默认构造函数 - 使用默认配置
     */
    public EmailService() {
        // 默认配置，模拟邮件服务器设置
        this.smtpHost = "smtp.example.com";
        this.smtpPort = 587;
        this.senderEmail = "noreply@schoolsystem.edu";
        this.senderPassword = "password";
        this.useSSL = false;
    }
    
    /**
     * 自定义配置构造函数
     * @param smtpHost SMTP主机地址
     * @param smtpPort SMTP端口
     * @param senderEmail 发件人邮箱
     * @param senderPassword 发件人密码
     * @param useSSL 是否使用SSL
     */
    public EmailService(String smtpHost, int smtpPort, String senderEmail, 
                       String senderPassword, boolean useSSL) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
        this.useSSL = useSSL;
    }
    
    /**
     * 发送邮件给家长
     * @param parent 家长对象
     * @param subject 邮件主题
     * @param message 邮件内容
     * @return 发送是否成功
     */
    public boolean sendEmail(Parent parent, String subject, String message) {
        if (parent == null) {
            System.out.println("错误：家长对象为空，无法发送邮件。");
            return false;
        }
        
        String recipientEmail = parent.getEmail();
        if (recipientEmail == null || recipientEmail.trim().isEmpty()) {
            System.out.println("错误：家长邮箱地址为空，无法发送邮件。");
            return false;
        }
        
        System.out.println("邮件服务：准备发送邮件给 " + parent.getName() + " (" + recipientEmail + ")");
        System.out.println("主题：" + subject);
        System.out.println("内容：" + message);
        
        // 在实际系统中，这里会有真实的邮件发送逻辑
        // 为简化演示，我们模拟邮件发送过程
        
        try {
            // 模拟邮件发送延迟
            Thread.sleep(1000);
            
            // 模拟邮件发送成功或失败
            boolean success = simulateEmailSending(recipientEmail, subject, message);
            
            if (success) {
                System.out.println("邮件已成功发送到 " + recipientEmail);
                logEmailSent(parent, subject, message);
                return true;
            } else {
                System.out.println("警告：邮件发送失败，但将重试或记录错误。");
                logEmailFailure(parent, subject, message, "发送失败");
                return false;
            }
        } catch (InterruptedException e) {
            System.out.println("警告：邮件发送过程被中断。");
            logEmailFailure(parent, subject, message, "发送中断");
            return false;
        }
    }
    
    /**
     * 模拟邮件发送过程
     * @param recipient 收件人邮箱
     * @param subject 邮件主题
     * @param message 邮件内容
     * @return 模拟发送结果（为演示目的，85%成功率）
     */
    private boolean simulateEmailSending(String recipient, String subject, String message) {
        // 模拟网络延迟和可能的失败
        // 在实际系统中，这里会使用JavaMail API发送真实邮件
        
        System.out.println("正在连接到SMTP服务器：" + smtpHost + ":" + smtpPort);
        
        // 模拟连接成功
        System.out.println("已成功连接到邮件服务器。");
        
        // 模拟身份验证
        System.out.println("正在验证发件人身份...");
        
        // 模拟85%的成功率
        if (Math.random() > 0.15) {
            System.out.println("身份验证成功，正在发送邮件...");
            System.out.println("邮件内容已成功传输到邮件服务器。");
            
            // 模拟邮件队列处理
            System.out.println("邮件已进入发送队列。");
            return true;
        } else {
            System.out.println("错误：邮件服务器身份验证失败。");
            return false;
        }
    }
    
    /**
     * 记录已发送的邮件（模拟）
     * @param parent 家长
     * @param subject 主题
     * @param message 内容
     */
    private void logEmailSent(Parent parent, String subject, String message) {
        String logEntry = String.format("[邮件发送成功] 时间: %s | 收件人: %s (%s) | 主题: %s",
                                       java.time.LocalDateTime.now(), 
                                       parent.getName(), parent.getEmail(), subject);
        System.out.println("邮件日志：" + logEntry);
    }
    
    /**
     * 记录邮件发送失败（模拟）
     * @param parent 家长
     * @param subject 主题
     * @param message 内容
     * @param error 错误信息
     */
    private void logEmailFailure(Parent parent, String subject, String message, String error) {
        String logEntry = String.format("[邮件发送失败] 时间: %s | 收件人: %s (%s) | 错误: %s",
                                       java.time.LocalDateTime.now(), 
                                       parent.getName(), parent.getEmail(), error);
        System.out.println("邮件错误日志：" + logEntry);
    }
    
    /**
     * 验证邮件服务器配置
     * @return 配置是否有效
     */
    public boolean validateConfiguration() {
        if (smtpHost == null || smtpHost.trim().isEmpty()) {
            System.out.println("错误：SMTP主机地址不能为空。");
            return false;
        }
        
        if (smtpPort <= 0 || smtpPort > 65535) {
            System.out.println("错误：SMTP端口号无效。");
            return false;
        }
        
        if (senderEmail == null || senderEmail.trim().isEmpty()) {
            System.out.println("错误：发件人邮箱不能为空。");
            return false;
        }
        
        if (!senderEmail.contains("@")) {
            System.out.println("警告：发件人邮箱格式可能不正确。");
        }
        
        return true;
    }
    
    /**
     * 发送测试邮件
     * @param testEmail 测试邮箱地址
     * @return 测试是否成功
     */
    public boolean sendTestEmail(String testEmail) {
        System.out.println("发送测试邮件到：" + testEmail);
        
        Parent testParent = new Parent("TEST", "测试家长", testEmail);
        boolean success = sendEmail(testParent, "系统测试邮件", "这是一封测试邮件，用于验证邮件服务是否正常工作。");
        
        if (success) {
            System.out.println("邮件服务测试通过。");
        } else {
            System.out.println("邮件服务测试失败。");
        }
        
        return success;
    }
    
    // Getter和Setter方法
    
    public String getSmtpHost() {
        return smtpHost;
    }
    
    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }
    
    public int getSmtpPort() {
        return smtpPort;
    }
    
    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }
    
    public String getSenderEmail() {
        return senderEmail;
    }
    
    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }
    
    public String getSenderPassword() {
        return senderPassword;
    }
    
    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }
    
    public boolean isUseSSL() {
        return useSSL;
    }
    
    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }
    
    @Override
    public String toString() {
        return String.format("EmailService{smtpHost='%s', smtpPort=%d, senderEmail='%s', useSSL=%s}",
                           smtpHost, smtpPort, senderEmail, useSSL);
    }
}