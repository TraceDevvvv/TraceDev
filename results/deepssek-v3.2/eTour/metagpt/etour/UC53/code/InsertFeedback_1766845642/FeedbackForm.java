import java.util.Scanner;

/**
 * FeedbackForm类 - 反馈表单
 * 用于收集游客的评分和评论，包含数据验证逻辑
 * 模拟用户界面表单输入过程
 */
public class FeedbackForm {
    private String touristId;
    private String siteId;
    private int rating;
    private String comment;
    private Scanner scanner;
    
    /**
     * 构造函数
     * @param touristId 游客ID
     * @param siteId 站点ID
     */
    public FeedbackForm(String touristId, String siteId) {
        this.touristId = touristId;
        this.siteId = siteId;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * 显示表单并收集用户输入
     * 模拟用例中的步骤3：填写表单，选择一个评分并输入评论
     * @return 如果用户成功填写表单并提交，返回true；如果用户取消操作，返回false
     */
    public boolean showForm() {
        System.out.println("\n=== 反馈表单 ===");
        System.out.println("游客: " + touristId);
        System.out.println("站点: " + siteId);
        System.out.println("请为您的体验提供反馈：");
        
        // 收集评分
        boolean validRating = false;
        while (!validRating) {
            System.out.print("请输入评分 (1-5，1表示非常差，5表示非常好): ");
            
            // 检查用户是否取消操作
            String input = scanner.nextLine();
            if ("cancel".equalsIgnoreCase(input)) {
                System.out.println("操作已取消。");
                return false;
            }
            
            try {
                rating = Integer.parseInt(input);
                if (rating >= 1 && rating <= 5) {
                    validRating = true;
                } else {
                    System.out.println("错误：评分必须在1到5之间。请重新输入或输入'cancel'取消。");
                }
            } catch (NumberFormatException e) {
                System.out.println("错误：请输入有效的数字 (1-5)。请重新输入或输入'cancel'取消。");
            }
        }
        
        // 收集评论
        boolean validComment = false;
        while (!validComment) {
            System.out.print("请输入评论 (至少5个字符): ");
            
            // 检查用户是否取消操作
            comment = scanner.nextLine();
            if ("cancel".equalsIgnoreCase(comment)) {
                System.out.println("操作已取消。");
                return false;
            }
            
            if (comment != null && comment.trim().length() >= 5) {
                validComment = true;
            } else {
                System.out.println("错误：评论至少需要5个字符。请重新输入或输入'cancel'取消。");
            }
        }
        
        // 确认提交
        System.out.print("\n请确认提交反馈 (输入'confirm'确认，或'cancel'取消): ");
        String confirmation = scanner.nextLine();
        
        if ("confirm".equalsIgnoreCase(confirmation)) {
            System.out.println("表单提交成功！");
            return true;
        } else {
            System.out.println("操作已取消。");
            return false;
        }
    }
    
    /**
     * 获取用户输入的评分
     * @return 评分值 (1-5)
     */
    public int getRating() {
        return rating;
    }
    
    /**
     * 获取用户输入的评论
     * @return 评论内容
     */
    public String getComment() {
        return comment;
    }
    
    /**
     * 获取游客ID
     * @return 游客ID
     */
    public String getTouristId() {
        return touristId;
    }
    
    /**
     * 获取站点ID
     * @return 站点ID
     */
    public String getSiteId() {
        return siteId;
    }
    
    /**
     * 验证表单数据是否有效
     * 模拟用例中的步骤4：验证输入的数据
     * @return 如果数据有效，返回true；否则返回false
     */
    public boolean validateFormData() {
        // 检查评分是否在有效范围内
        if (rating < 1 || rating > 5) {
            return false;
        }
        
        // 检查评论是否有效
        if (comment == null || comment.trim().isEmpty()) {
            return false;
        }
        
        // 检查评论长度是否足够
        if (comment.trim().length() < 5) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 创建Feedback对象
     * 注意：这个方法只应在表单数据验证通过后调用
     * @param feedbackId 反馈ID
     * @return 包含表单数据的Feedback对象
     */
    public Feedback createFeedback(String feedbackId) {
        return new Feedback(feedbackId, touristId, siteId, rating, comment);
    }
    
    /**
     * 关闭表单资源
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     * 模拟服务器连接中断的情况
     * 用于测试退出条件中的"连接到ETOUR服务器的连接中断"
     * @return 如果连接正常，返回true；如果连接中断，返回false
     */
    public boolean checkServerConnection() {
        // 在实际应用中，这里会检查与ETOUR服务器的连接
        // 这里我们模拟90%的情况下连接正常，10%的情况下连接中断
        double random = Math.random();
        return random < 0.9; // 90%的概率连接正常
    }
}