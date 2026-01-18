import java.util.List;
import java.util.Scanner;

/**
 * UserInterface类提供控制台用户界面，引导用户完成评论修改的完整流程。
 * 按照用例描述的事件流程，处理站点选择、反馈选择、评论编辑和确认等步骤。
 * 包含错误处理、输入验证和用户友好的交互提示。
 */
public class UserInterface {
    private Scanner scanner;
    private AgencyOperator currentOperator;
    private CommentModificationService commentService;
    private List<Site> availableSites;
    
    /**
     * 构造函数，初始化用户界面。
     * @param operator 当前操作员，必须已登录
     * @param sites 可用的站点列表
     */
    public UserInterface(AgencyOperator operator, List<Site> sites) {
        this.scanner = new Scanner(System.in);
        this.currentOperator = operator;
        this.availableSites = sites;
        
        // 验证操作员权限
        if (operator == null || !operator.isLoggedIn()) {
            throw new IllegalStateException("操作员必须已登录才能使用评论修改功能");
        }
        
        // 初始化评论修改服务
        this.commentService = new CommentModificationService(operator);
    }
    
    /**
     * 主菜单入口，启动评论修改流程。
     * 遵循用例描述的事件流程顺序。
     */
    public void startModificationProcess() {
        System.out.println("====== 评论修改系统 ======");
        System.out.println("欢迎，" + currentOperator.getOperatorName());
        System.out.println("当前操作员ID: " + currentOperator.getOperatorId());
        System.out.println();
        
        // 检查服务器连接
        if (!commentService.checkServerConnection()) {
            System.err.println("错误：无法连接到服务器，请检查网络连接后重试");
            return;
        }
        
        try {
            // 按照用例流程执行
            boolean continueProcess = true;
            
            // 步骤1：查看站点列表并选择站点
            if (continueProcess) {
                continueProcess = step1_ViewAndSelectSite();
            }
            
            // 步骤2和3：显示反馈并选择反馈
            if (continueProcess) {
                continueProcess = step2and3_DisplayAndSelectFeedback();
            }
            
            // 步骤4和5：编辑评论
            if (continueProcess) {
                continueProcess = step4and5_EditComment();
            }
            
            // 步骤6：验证数据并请求确认
            if (continueProcess) {
                continueProcess = step6_ValidateAndRequestConfirmation();
            }
            
            // 步骤7：确认操作
            if (continueProcess) {
                step7_ConfirmOperation();
            }
            
            // 步骤8：完成流程
            if (continueProcess) {
                step8_CompleteProcess();
            }
            
            // 检查是否取消操作
            if (commentService.isOperationCancelled()) {
                System.out.println("操作已被用户取消");
            }
            
        } catch (Exception e) {
            System.err.println("发生意外错误: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("====== 评论修改流程结束 ======");
        }
    }
    
    /**
     * 步骤1：查看站点列表并选择站点。
     * 模拟从SearchSite用例获取站点列表。
     * @return 如果成功选择站点则返回true，否则返回false
     */
    private boolean step1_ViewAndSelectSite() {
        System.out.println("=== 步骤1：选择站点 ===");
        
        // 显示可用站点列表
        displaySiteList();
        
        // 如果站点列表为空，无法继续
        if (availableSites.isEmpty()) {
            System.err.println("错误：没有可用的站点");
            return false;
        }
        
        // 获取用户输入的站点ID
        Integer siteId = null;
        int attempts = 0;
        final int maxAttempts = 3;
        
        while (siteId == null && attempts < maxAttempts) {
            try {
                System.out.print("请输入要选择的站点ID (输入'cancel'取消操作): ");
                String input = scanner.nextLine().trim();
                
                // 检查用户是否取消操作
                if (input.equalsIgnoreCase("cancel")) {
                    commentService.cancelOperation();
                    return false;
                }
                
                // 验证输入是否为数字
                siteId = Integer.parseInt(input);
                
                // 尝试选择站点
                boolean success = commentService.selectSite(availableSites, siteId);
                if (!success) {
                    System.err.println("站点选择失败，请重试");
                    siteId = null;
                    attempts++;
                }
            } catch (NumberFormatException e) {
                System.err.println("错误：请输入有效的数字ID");
                attempts++;
            }
        }
        
        if (siteId == null) {
            System.err.println("错误：超过最大尝试次数，操作终止");
            return false;
        }
        
        System.out.println("站点选择成功");
        return true;
    }
    
    /**
     * 显示可用站点列表。
     */
    private void displaySiteList() {
        System.out.println("可用站点列表:");
        System.out.println("ID\t名称\t\t反馈数");
        System.out.println("--------------------------------");
        
        for (Site site : availableSites) {
            System.out.printf("%-6d %-15s %-3d%n", 
                site.getSiteId(), 
                site.getSiteName(), 
                site.getFeedbacks().size());
        }
        System.out.println();
    }
    
    /**
     * 步骤2和3：显示反馈并选择反馈。
     * 上传并显示站点的反馈，然后选择其中一个反馈。
     * @return 如果成功选择反馈则返回true，否则返回false
     */
    private boolean step2and3_DisplayAndSelectFeedback() {
        System.out.println("=== 步骤2和3：选择反馈 ===");
        
        Site selectedSite = commentService.getSelectedSite();
        if (selectedSite == null) {
            System.err.println("错误：未选择站点");
            return false;
        }
        
        // 显示站点的反馈列表
        displayFeedbackList(selectedSite);
        
        List<Feedback> feedbacks = selectedSite.getFeedbacks();
        if (feedbacks.isEmpty()) {
            System.err.println("错误：该站点没有可用的反馈");
            return false;
        }
        
        // 获取用户输入的反馈ID
        Integer feedbackId = null;
        int attempts = 0;
        final int maxAttempts = 3;
        
        while (feedbackId == null && attempts < maxAttempts) {
            try {
                System.out.print("请输入要编辑的反馈ID (输入'cancel'取消操作): ");
                String input = scanner.nextLine().trim();
                
                // 检查用户是否取消操作
                if (input.equalsIgnoreCase("cancel")) {
                    commentService.cancelOperation();
                    return false;
                }
                
                // 验证输入是否为数字
                feedbackId = Integer.parseInt(input);
                
                // 尝试选择反馈
                boolean success = commentService.selectFeedback(feedbackId);
                if (!success) {
                    System.err.println("反馈选择失败，请重试");
                    feedbackId = null;
                    attempts++;
                }
            } catch (NumberFormatException e) {
                System.err.println("错误：请输入有效的数字ID");
                attempts++;
            }
        }
        
        if (feedbackId == null) {
            System.err.println("错误：超过最大尝试次数，操作终止");
            return false;
        }
        
        System.out.println("反馈选择成功");
        return true;
    }
    
    /**
     * 显示站点的反馈列表。
     * @param site 要显示反馈的站点
     */
    private void displayFeedbackList(Site site) {
        System.out.println("站点 \"" + site.getSiteName() + "\" 的反馈列表:");
        System.out.println("ID\t创建时间\t\t\t评论预览");
        System.out.println("------------------------------------------------------------------------");
        
        List<Feedback> feedbacks = site.getFeedbacks();
        for (Feedback feedback : feedbacks) {
            String commentPreview = feedback.getComment();
            if (commentPreview.length() > 30) {
                commentPreview = commentPreview.substring(0, 27) + "...";
            }
            
            System.out.printf("%-6d %-20s %-30s%n",
                feedback.getFeedbackId(),
                feedback.getCreatedAt().toString().substring(0, 16),
                commentPreview);
        }
        System.out.println();
    }
    
    /**
     * 步骤4和5：编辑评论。
     * 显示当前评论并允许用户编辑。
     * @return 如果编辑成功则返回true，否则返回false
     */
    private boolean step4and5_EditComment() {
        System.out.println("=== 步骤4和5：编辑评论 ===");
        
        Feedback selectedFeedback = commentService.getSelectedFeedback();
        if (selectedFeedback == null) {
            System.err.println("错误：未选择反馈");
            return false;
        }
        
        // 显示当前评论
        System.out.println("当前评论内容:");
        System.out.println("----------------------------------------");
        System.out.println(selectedFeedback.getComment());
        System.out.println("----------------------------------------");
        System.out.println("评论创建时间: " + selectedFeedback.getCreatedAt());
        System.out.println("最后修改时间: " + selectedFeedback.getLastModifiedAt());
        System.out.println();
        
        // 获取用户输入的新评论
        String newComment = null;
        int attempts = 0;
        final int maxAttempts = 3;
        
        while (newComment == null && attempts < maxAttempts) {
            System.out.println("请输入新的评论内容 (输入'cancel'取消操作):");
            System.out.println("注意：评论不能为空，长度不能超过1000字符");
            System.out.print("> ");
            
            String input = scanner.nextLine().trim();
            
            // 检查用户是否取消操作
            if (input.equalsIgnoreCase("cancel")) {
                commentService.cancelOperation();
                return false;
            }
            
            // 检查输入是否为空
            if (input.isEmpty()) {
                System.err.println("错误：评论内容不能为空");
                attempts++;
                continue;
            }
            
            // 检查输入长度
            if (input.length() > 1000) {
                System.err.println("错误：评论长度超过1000字符限制");
                attempts++;
                continue;
            }
            
            newComment = input;
        }
        
        if (newComment == null) {
            System.err.println("错误：超过最大尝试次数，操作终止");
            return false;
        }
        
        // 尝试编辑评论
        boolean success = commentService.editComment(newComment);
        if (!success) {
            System.err.println("评论编辑失败");
            return false;
        }
        
        System.out.println("评论编辑完成，等待验证...");
        return true;
    }
    
    /**
     * 步骤6：验证数据并请求确认。
     * 系统验证输入的数据并请求用户确认更改。
     * @return 如果验证通过且用户准备确认则返回true，否则返回false
     */
    private boolean step6_ValidateAndRequestConfirmation() {
        System.out.println("=== 步骤6：验证和确认请求 ===");
        
        // 自动验证已在editComment方法中完成
        // 这里显示确认请求
        
        Feedback selectedFeedback = commentService.getSelectedFeedback();
        if (selectedFeedback == null || !selectedFeedback.isModified()) {
            System.err.println("错误：没有待确认的评论修改");
            return false;
        }
        
        System.out.println("评论修改验证通过！");
        System.out.println("请确认是否保存以下更改:");
        System.out.println("评论ID: " + selectedFeedback.getFeedbackId());
        System.out.println("修改时间: " + selectedFeedback.getLastModifiedAt());
        System.out.println();
        
        // 请求用户确认
        String confirmation = null;
        int attempts = 0;
        final int maxAttempts = 3;
        
        while (confirmation == null && attempts < maxAttempts) {
            System.out.print("确认保存更改吗？(yes/no/cancel): ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("cancel")) {
                commentService.cancelOperation();
                return false;
            } else if (input.equals("no")) {
                System.out.println("用户选择不保存更改");
                commentService.cancelOperation();
                return false;
            } else if (input.equals("yes")) {
                confirmation = input;
            } else {
                System.err.println("错误：请输入 yes, no 或 cancel");
                attempts++;
            }
        }
        
        if (confirmation == null) {
            System.err.println("错误：超过最大尝试次数，操作终止");
            return false;
        }
        
        System.out.println("确认收到，准备保存更改...");
        return true;
    }
    
    /**
     * 步骤7：确认操作。
     * 系统确认操作并保存更改。
     */
    private void step7_ConfirmOperation() {
        System.out.println("=== 步骤7：确认操作 ===");
        
        boolean success = commentService.confirmOperation();
        if (!success) {
            System.err.println("操作确认失败");
        } else {
            System.out.println("操作确认成功");
        }
    }
    
    /**
     * 步骤8：完成流程。
     * 记住已更改的评论并通知系统。
     */
    private void step8_CompleteProcess() {
        System.out.println("=== 步骤8：完成流程 ===");
        
        // 检查操作是否已确认
        if (commentService.isOperationConfirmed()) {
            // 通知系统（在服务类中已处理）
            commentService.notifySystem();
            System.out.println("评论修改流程完成！");
        } else {
            System.out.println("评论修改未完成或未确认");
        }
    }
    
    /**
     * 关闭用户界面，释放资源。
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     * 获取当前的评论修改服务实例。
     * @return 评论修改服务实例
     */
    public CommentModificationService getCommentService() {
        return commentService;
    }
}