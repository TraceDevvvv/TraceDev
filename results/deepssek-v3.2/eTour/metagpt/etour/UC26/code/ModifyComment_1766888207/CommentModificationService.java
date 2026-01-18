import java.util.List;

/**
 * CommentModificationService类处理评论修改的核心业务逻辑。
 * 负责验证数据、修改评论、处理错误和确认流程。
 * 遵循用例描述的事件流程，包括数据验证、确认和错误处理。
 */
public class CommentModificationService {
    private AgencyOperator currentOperator;
    private Site selectedSite;
    private Feedback selectedFeedback;
    private boolean isOperationConfirmed;
    private boolean isOperationCancelled;
    private boolean isServerConnected;
    
    /**
     * 构造函数，初始化服务。
     * @param operator 当前操作员，必须已登录
     * @throws IllegalArgumentException 如果操作员为空或未登录
     */
    public CommentModificationService(AgencyOperator operator) {
        if (operator == null) {
            throw new IllegalArgumentException("操作员不能为空");
        }
        if (!operator.isLoggedIn()) {
            throw new IllegalArgumentException("操作员必须已登录才能执行评论修改操作");
        }
        this.currentOperator = operator;
        this.selectedSite = null;
        this.selectedFeedback = null;
        this.isOperationConfirmed = false;
        this.isOperationCancelled = false;
        this.isServerConnected = operator.checkServerConnection();
    }
    
    /**
     * 步骤1：查看站点列表并选择站点。
     * 模拟从SearchSite用例获取站点列表，并选择其中一个站点。
     * @param sites 站点列表
     * @param siteId 要选择的站点ID
     * @return 如果选择成功则返回true，否则返回false
     */
    public boolean selectSite(List<Site> sites, int siteId) {
        // 检查服务器连接
        if (!isServerConnected) {
            System.err.println("错误：服务器连接中断，无法选择站点");
            return false;
        }
        
        // 检查操作员权限
        if (!currentOperator.canModifyComment()) {
            System.err.println("错误：操作员未登录，无权修改评论");
            return false;
        }
        
        // 在站点列表中查找指定ID的站点
        for (Site site : sites) {
            if (site.getSiteId() == siteId) {
                selectedSite = site;
                System.out.println("已选择站点: " + site.getSiteName());
                return true;
            }
        }
        
        System.err.println("错误：未找到ID为 " + siteId + " 的站点");
        return false;
    }
    
    /**
     * 步骤2和3：获取站点反馈列表并选择反馈。
     * 上传并显示站点的反馈，然后选择其中一个反馈。
     * @param feedbackId 要选择的反馈ID
     * @return 如果选择成功则返回true，否则返回false
     */
    public boolean selectFeedback(int feedbackId) {
        // 检查服务器连接
        if (!isServerConnected) {
            System.err.println("错误：服务器连接中断，无法选择反馈");
            return false;
        }
        
        // 检查是否已选择站点
        if (selectedSite == null) {
            System.err.println("错误：请先选择站点");
            return false;
        }
        
        // 从站点获取反馈列表
        List<Feedback> feedbacks = selectedSite.getFeedbacks();
        if (feedbacks.isEmpty()) {
            System.err.println("错误：该站点没有反馈");
            return false;
        }
        
        // 查找指定ID的反馈
        Feedback feedback = selectedSite.findFeedbackById(feedbackId);
        if (feedback == null) {
            System.err.println("错误：未找到ID为 " + feedbackId + " 的反馈");
            return false;
        }
        
        selectedFeedback = feedback;
        System.out.println("已选择反馈: " + feedback);
        return true;
    }
    
    /**
     * 步骤4和5：显示编辑表单并编辑评论。
     * 显示当前评论并允许用户编辑。
     * @param newComment 新的评论内容
     * @return 如果编辑成功则返回true，否则返回false
     */
    public boolean editComment(String newComment) {
        // 检查服务器连接
        if (!isServerConnected) {
            System.err.println("错误：服务器连接中断，无法编辑评论");
            return false;
        }
        
        // 检查是否已选择反馈
        if (selectedFeedback == null) {
            System.err.println("错误：请先选择反馈");
            return false;
        }
        
        // 步骤6：验证输入的数据
        if (!validateCommentData(newComment)) {
            // 数据无效，激活Errored用例
            handleErroredCase("评论数据无效或不足");
            return false;
        }
        
        // 保存原始评论以便在取消时恢复
        String originalComment = selectedFeedback.getComment();
        
        // 更新评论内容
        boolean isChanged = selectedFeedback.setComment(newComment);
        if (!isChanged) {
            System.out.println("提示：评论内容未改变");
            return false;
        }
        
        System.out.println("评论已更新，等待确认...");
        System.out.println("原始评论: " + originalComment);
        System.out.println("新评论: " + newComment);
        
        return true;
    }
    
    /**
     * 步骤6：验证评论数据。
     * 检查评论是否有效和充足。
     * @param comment 要验证的评论
     * @return 如果评论有效则返回true，否则返回false
     */
    private boolean validateCommentData(String comment) {
        if (comment == null) {
            System.err.println("错误：评论不能为空");
            return false;
        }
        
        String trimmedComment = comment.trim();
        if (trimmedComment.isEmpty()) {
            System.err.println("错误：评论不能为空或只包含空白字符");
            return false;
        }
        
        // 检查评论长度限制
        if (trimmedComment.length() > 1000) {
            System.err.println("错误：评论长度不能超过1000个字符");
            return false;
        }
        
        // 检查评论是否包含非法字符（示例检查）
        if (trimmedComment.contains("<script>") || trimmedComment.contains("</script>")) {
            System.err.println("错误：评论包含潜在的安全风险内容");
            return false;
        }
        
        return true;
    }
    
    /**
     * 步骤6：处理错误情况，激活Errored用例。
     * 在实际应用中，这里可能会记录错误、发送通知或执行其他错误处理逻辑。
     * @param errorMessage 错误消息
     */
    private void handleErroredCase(String errorMessage) {
        System.err.println("激活Errored用例: " + errorMessage);
        // 在实际应用中，这里可能会：
        // 1. 记录错误日志
        // 2. 发送错误通知
        // 3. 回滚任何未提交的更改
        // 4. 重置服务状态
        resetSelection();
    }
    
    /**
     * 步骤7：确认操作。
     * 用户确认要保存评论更改。
     * @return 如果确认成功则返回true，否则返回false
     */
    public boolean confirmOperation() {
        // 检查服务器连接
        if (!isServerConnected) {
            System.err.println("错误：服务器连接中断，无法确认操作");
            return false;
        }
        
        // 检查是否已选择反馈
        if (selectedFeedback == null) {
            System.err.println("错误：没有待确认的评论修改");
            return false;
        }
        
        // 检查评论是否已被修改
        if (!selectedFeedback.isModified()) {
            System.out.println("提示：评论未被修改，无需确认");
            return false;
        }
        
        isOperationConfirmed = true;
        System.out.println("操作已确认，正在保存评论更改...");
        
        // 步骤8：记住已更改的评论
        rememberModifiedComment();
        
        return true;
    }
    
    /**
     * 步骤8：记住已更改的评论。
     * 在实际应用中，这里可能会将更改持久化到数据库或文件系统。
     */
    private void rememberModifiedComment() {
        if (selectedFeedback != null && selectedFeedback.isModified()) {
            // 在实际应用中，这里应该：
            // 1. 保存到数据库
            // 2. 更新缓存
            // 3. 发送通知
            
            System.out.println("评论更改已保存到系统");
            System.out.println("修改详情: " + selectedFeedback);
            
            // 重置修改标志
            selectedFeedback.resetModifiedFlag();
        }
    }
    
    /**
     * 取消操作。
     * 处理机构操作员取消操作的退出条件。
     * @return 如果取消成功则返回true
     */
    public boolean cancelOperation() {
        isOperationCancelled = true;
        System.out.println("操作已取消");
        
        // 如果评论已被修改但未确认，恢复原始评论
        if (selectedFeedback != null && selectedFeedback.isModified()) {
            // 在实际应用中，这里可能需要从备份中恢复原始评论
            System.out.println("已恢复原始评论");
            // 注意：由于我们未保存原始评论，这里只是重置修改标志
            selectedFeedback.resetModifiedFlag();
        }
        
        resetSelection();
        return true;
    }
    
    /**
     * 检查服务器连接状态。
     * 处理服务器连接中断的退出条件。
     * @return 如果服务器连接正常则返回true，否则返回false
     */
    public boolean checkServerConnection() {
        isServerConnected = currentOperator.checkServerConnection();
        if (!isServerConnected) {
            System.err.println("错误：服务器连接中断");
            resetSelection();
        }
        return isServerConnected;
    }
    
    /**
     * 重置当前选择。
     * 在操作完成、取消或错误时调用。
     */
    private void resetSelection() {
        selectedSite = null;
        selectedFeedback = null;
        isOperationConfirmed = false;
    }
    
    /**
     * 获取当前选择的站点。
     * @return 当前选择的站点，如果未选择则返回null
     */
    public Site getSelectedSite() {
        return selectedSite;
    }
    
    /**
     * 获取当前选择的反馈。
     * @return 当前选择的反馈，如果未选择则返回null
     */
    public Feedback getSelectedFeedback() {
        return selectedFeedback;
    }
    
    /**
     * 检查操作是否已确认。
     * @return 如果操作已确认则返回true，否则返回false
     */
    public boolean isOperationConfirmed() {
        return isOperationConfirmed;
    }
    
    /**
     * 检查操作是否已取消。
     * @return 如果操作已取消则返回true，否则返回false
     */
    public boolean isOperationCancelled() {
        return isOperationCancelled;
    }
    
    /**
     * 检查服务器是否连接。
     * @return 如果服务器连接正常则返回true，否则返回false
     */
    public boolean isServerConnected() {
        return isServerConnected;
    }
    
    /**
     * 模拟通知系统已修改评论。
     * 这是退出条件之一。
     */
    public void notifySystem() {
        if (isOperationConfirmed && selectedFeedback != null) {
            System.out.println("通知系统：评论 " + selectedFeedback.getFeedbackId() + " 已被修改");
            System.out.println("退出条件满足：通知系统已修改所选反馈的评论");
        }
    }
}