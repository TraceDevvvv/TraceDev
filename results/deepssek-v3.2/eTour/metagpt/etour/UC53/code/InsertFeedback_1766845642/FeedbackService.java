import java.util.UUID;

/**
 * FeedbackService类 - 核心服务类
 * 实现InsertFeedback用例的业务逻辑，协调表单验证、数据存储和异常处理
 * 执行用例中的主要步骤：
 * 1. 激活反馈功能
 * 2. 验证游客是否已发布反馈
 * 3. 处理表单数据
 * 4. 验证数据有效性
 * 5. 保存反馈
 * 6. 处理各种退出条件
 */
public class FeedbackService {
    private DataStorage dataStorage;
    private FeedbackForm feedbackForm;
    
    /**
     * 构造函数
     * @param dataStorage 数据存储实例
     */
    public FeedbackService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }
    
    /**
     * 执行InsertFeedback用例的主要方法
     * 入口条件：游客卡片在特定站点
     * @param touristId 游客ID
     * @param siteId 站点ID
     * @return 执行结果，包含成功/失败信息和反馈ID
     * @throws FeedbackAlreadyReleasedException 当游客已为站点发布反馈时抛出
     * @throws InvalidFeedbackException 当反馈数据无效或不充分时抛出
     * @throws IllegalStateException 当出现未预期的状态错误时抛出
     */
    public InsertFeedbackResult executeInsertFeedback(String touristId, String siteId) 
            throws FeedbackAlreadyReleasedException, InvalidFeedbackException, IllegalStateException {
        
        // 步骤1：激活反馈功能 - 记录日志
        System.out.println("=== 激活反馈功能 ===");
        System.out.println("游客ID: " + touristId + ", 站点ID: " + siteId);
        
        // 验证入口条件：检查游客和站点是否存在
        if (!dataStorage.touristExists(touristId)) {
            throw new IllegalStateException("游客ID '" + touristId + "' 不存在。");
        }
        
        if (!dataStorage.siteExists(siteId)) {
            throw new IllegalStateException("站点ID '" + siteId + "' 不存在。");
        }
        
        // 步骤2：验证游客尚未为站点发布反馈
        if (dataStorage.hasTouristGivenFeedback(touristId, siteId)) {
            System.out.println("错误：游客已经为该站点发布过反馈。");
            throw FeedbackAlreadyReleasedException.create(touristId, siteId);
        }
        
        // 显示验证通过信息
        System.out.println("验证通过：游客尚未为该站点发布反馈。");
        
        // 创建反馈表单，对应步骤2的显示表单部分
        feedbackForm = new FeedbackForm(touristId, siteId);
        
        // 检查服务器连接 - 处理连接中断的退出条件
        if (!feedbackForm.checkServerConnection()) {
            System.out.println("错误：连接到ETOUR服务器的连接中断。操作已取消。");
            feedbackForm.close();
            throw new IllegalStateException("服务器连接中断。无法继续操作。");
        }
        
        // 步骤3：显示表单并收集输入，处理用户取消操作
        System.out.println("\n显示反馈表单...");
        boolean formSubmitted = feedbackForm.showForm();
        
        // 处理用户取消操作（退出条件之一）
        if (!formSubmitted) {
            System.out.println("游客取消了反馈操作。");
            feedbackForm.close();
            return new InsertFeedbackResult(false, null, "操作被游客取消。");
        }
        
        try {
            // 步骤4：验证输入的数据
            int rating = feedbackForm.getRating();
            String comment = feedbackForm.getComment();
            
            // 创建临时反馈对象进行验证
            Feedback tempFeedback = new Feedback("TEMP", touristId, siteId, rating, comment);
            
            if (!tempFeedback.isValid()) {
                System.out.println("错误：反馈数据无效或不充分。");
                // 对应用例中的Errored用例
                throw InvalidFeedbackException.create(rating, comment);
            }
            
            // 步骤5：确认发布反馈
            System.out.println("\n正在确认发布反馈...");
            
            // 在真实系统中，这里可能会有额外的确认步骤
            // 例如：发送确认邮件或显示确认对话框
            
            // 步骤6：记录反馈并将选定站点插入已访问站点列表
            System.out.println("正在保存反馈...");
            
            // 生成唯一的反馈ID
            String feedbackId = generateFeedbackId();
            
            // 创建最终的反馈对象
            Feedback finalFeedback = feedbackForm.createFeedback(feedbackId);
            
            // 保存到数据存储
            String savedFeedbackId = dataStorage.saveFeedback(finalFeedback);
            
            // 显示成功信息
            System.out.println("\n=== 反馈保存成功 ===");
            System.out.println("反馈ID: " + savedFeedbackId);
            System.out.println("站点已添加到游客的已访问站点列表。");
            System.out.println("游客已添加到站点的访问游客列表。");
            
            // 返回成功结果
            return new InsertFeedbackResult(true, savedFeedbackId, "反馈成功提交到站点。");
            
        } finally {
            // 确保表单资源被关闭
            if (feedbackForm != null) {
                feedbackForm.close();
            }
        }
    }
    
    /**
     * 处理InsertFeedback操作的便捷方法
     * 这个方法包装了executeInsertFeedback方法，提供了更好的异常处理
     * @param touristId 游客ID
     * @param siteId 站点ID
     * @return 执行结果，包含处理结果和详细信息
     */
    public InsertFeedbackResult processFeedback(String touristId, String siteId) {
        try {
            return executeInsertFeedback(touristId, siteId);
        } catch (FeedbackAlreadyReleasedException e) {
            System.out.println("错误: " + e.getMessage());
            return new InsertFeedbackResult(false, null, "错误: " + e.getMessage());
        } catch (InvalidFeedbackException e) {
            System.out.println("数据验证失败: " + e.getMessage());
            return new InsertFeedbackResult(false, null, "数据验证失败: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("系统错误: " + e.getMessage());
            return new InsertFeedbackResult(false, null, "系统错误: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("未预期的错误: " + e.getMessage());
            return new InsertFeedbackResult(false, null, "未预期的错误: " + e.getMessage());
        }
    }
    
    /**
     * 检查游客是否可以为特定站点提供反馈
     * 这个方法单独公开，以便在其他地方进行预检查
     * @param touristId 游客ID
     * @param siteId 站点ID
     * @return 如果游客可以提供反馈返回true，否则返回false
     */
    public boolean canTouristProvideFeedback(String touristId, String siteId) {
        // 检查游客和站点是否存在
        if (!dataStorage.touristExists(touristId) || !dataStorage.siteExists(siteId)) {
            return false;
        }
        
        // 检查游客是否已经为该站点提供过反馈
        return !dataStorage.hasTouristGivenFeedback(touristId, siteId);
    }
    
    /**
     * 获取数据存储实例
     * @return 当前的数据存储实例
     */
    public DataStorage getDataStorage() {
        return dataStorage;
    }
    
    /**
     * 生成唯一的反馈ID
     * @return 唯一的反馈ID字符串
     */
    private String generateFeedbackId() {
        // 使用UUID生成唯一ID，在实际应用中可以根据需要调整生成策略
        return "FB_" + UUID.randomUUID().toString().substring(0, 8);
    }
    
    /**
     * InsertFeedbackResult类 - 内部结果类
     * 用于封装InsertFeedback操作的结果
     */
    public static class InsertFeedbackResult {
        private boolean success;
        private String feedbackId;
        private String message;
        
        /**
         * 构造函数
         * @param success 是否成功
         * @param feedbackId 反馈ID（成功时）
         * @param message 结果消息
         */
        public InsertFeedbackResult(boolean success, String feedbackId, String message) {
            this.success = success;
            this.feedbackId = feedbackId;
            this.message = message;
        }
        
        // Getter方法
        
        public boolean isSuccess() {
            return success;
        }
        
        public String getFeedbackId() {
            return feedbackId;
        }
        
        public String getMessage() {
            return message;
        }
        
        @Override
        public String toString() {
            return String.format("InsertFeedbackResult{success=%s, feedbackId='%s', message='%s'}", 
                               success, feedbackId, message);
        }
    }
    
    /**
     * 模拟测试方法：模拟反馈操作的不同场景
     * 用于内部测试和演示
     */
    public void runTestScenarios() {
        System.out.println("\n=== 运行测试场景 ===");
        
        // 场景1：正常流程
        System.out.println("\n场景1：正常反馈流程");
        InsertFeedbackResult result1 = processFeedback("T001", "S001");
        System.out.println("结果: " + result1.getMessage());
        
        // 场景2：重复反馈（应该触发FeedbackAlreadyReleasedException）
        System.out.println("\n场景2：重复反馈（游客T001为站点S001再次反馈）");
        InsertFeedbackResult result2 = processFeedback("T001", "S001");
        System.out.println("结果: " + result2.getMessage());
        
        // 场景3：新游客对新站点
        System.out.println("\n场景3：新游客对新站点（游客T002为站点S002反馈）");
        InsertFeedbackResult result3 = processFeedback("T002", "S002");
        System.out.println("结果: " + result3.getMessage());
        
        // 场景4：不存在的游客或站点
        System.out.println("\n场景4：不存在的游客（游客T999为站点S001反馈）");
        InsertFeedbackResult result4 = processFeedback("T999", "S001");
        System.out.println("结果: " + result4.getMessage());
    }
}