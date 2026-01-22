import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * EmailService  -         
 *                  
 *   ：           ，                 
 */
public class EmailService {
    
    private String smtpHost;
    private int smtpPort;
    private String senderEmail;
    private String senderPassword;
    private boolean useSSL;
    
    /**
     *        -       
     */
    public EmailService() {
        //     ，         
        this.smtpHost = "smtp.example.com";
        this.smtpPort = 587;
        this.senderEmail = "noreply@schoolsystem.edu";
        this.senderPassword = "password";
        this.useSSL = false;
    }
    
    /**
     *          
     * @param smtpHost SMTP    
     * @param smtpPort SMTP  
     * @param senderEmail      
     * @param senderPassword      
     * @param useSSL     SSL
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
     *        
     * @param parent     
     * @param subject     
     * @param message     
     * @return       
     */
    public boolean sendEmail(Parent parent, String subject, String message) {
        if (parent == null) {
            System.out.println("  ：      ，      。");
            return false;
        }
        
        String recipientEmail = parent.getEmail();
        if (recipientEmail == null || recipientEmail.trim().isEmpty()) {
            System.out.println("  ：        ，      。");
            return false;
        }
        
        System.out.println("    ：        " + parent.getName() + " (" + recipientEmail + ")");
        System.out.println("  ：" + subject);
        System.out.println("  ：" + message);
        
        //       ，             
        //      ，          
        
        try {
            //         
            Thread.sleep(1000);
            
            //            
            boolean success = simulateEmailSending(recipientEmail, subject, message);
            
            if (success) {
                System.out.println("         " + recipientEmail);
                logEmailSent(parent, subject, message);
                return true;
            } else {
                System.out.println("  ：      ，         。");
                logEmailFailure(parent, subject, message, "    ");
                return false;
            }
        } catch (InterruptedException e) {
            System.out.println("  ：         。");
            logEmailFailure(parent, subject, message, "    ");
            return false;
        }
    }
    
    /**
     *         
     * @param recipient      
     * @param subject     
     * @param message     
     * @return       （     ，85%   ）
     */
    private boolean simulateEmailSending(String recipient, String subject, String message) {
        //             
        //       ，     JavaMail API      
        
        System.out.println("     SMTP   ：" + smtpHost + ":" + smtpPort);
        
        //       
        System.out.println("           。");
        
        //       
        System.out.println("         ...");
        
        //   85%    
        if (Math.random() > 0.15) {
            System.out.println("      ，      ...");
            System.out.println("               。");
            
            //         
            System.out.println("         。");
            return true;
        } else {
            System.out.println("  ：           。");
            return false;
        }
    }
    
    /**
     *         （  ）
     * @param parent   
     * @param subject   
     * @param message   
     */
    private void logEmailSent(Parent parent, String subject, String message) {
        String logEntry = String.format("[      ]   : %s |    : %s (%s) |   : %s",
                                       java.time.LocalDateTime.now(), 
                                       parent.getName(), parent.getEmail(), subject);
        System.out.println("    ：" + logEntry);
    }
    
    /**
     *         （  ）
     * @param parent   
     * @param subject   
     * @param message   
     * @param error     
     */
    private void logEmailFailure(Parent parent, String subject, String message, String error) {
        String logEntry = String.format("[      ]   : %s |    : %s (%s) |   : %s",
                                       java.time.LocalDateTime.now(), 
                                       parent.getName(), parent.getEmail(), error);
        System.out.println("      ：" + logEntry);
    }
    
    /**
     *          
     * @return       
     */
    public boolean validateConfiguration() {
        if (smtpHost == null || smtpHost.trim().isEmpty()) {
            System.out.println("  ：SMTP        。");
            return false;
        }
        
        if (smtpPort <= 0 || smtpPort > 65535) {
            System.out.println("  ：SMTP     。");
            return false;
        }
        
        if (senderEmail == null || senderEmail.trim().isEmpty()) {
            System.out.println("  ：         。");
            return false;
        }
        
        if (!senderEmail.contains("@")) {
            System.out.println("  ：            。");
        }
        
        return true;
    }
    
    /**
     *       
     * @param testEmail       
     * @return       
     */
    public boolean sendTestEmail(String testEmail) {
        System.out.println("       ：" + testEmail);
        
        Parent testParent = new Parent("TEST", "    ", testEmail);
        boolean success = sendEmail(testParent, "      ", "        ，              。");
        
        if (success) {
            System.out.println("        。");
        } else {
            System.out.println("        。");
        }
        
        return success;
    }
    
    // Getter Setter  
    
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