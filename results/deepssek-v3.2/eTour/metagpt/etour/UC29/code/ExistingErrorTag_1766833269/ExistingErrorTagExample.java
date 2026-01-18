import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * ExistingErrorTagExample - 模拟ExistingErrorTag用例的Java程序
 * 
 * 用例描述：用户被要求输入系统中已存在的搜索标签时，系统显示错误消息，
 * 用户确认阅读后，系统恢复之前的状态。
 */
public class ExistingErrorTagExample {
    
    // 系统状态类，用于保存和恢复系统状态
    private static class SystemState {
        private Set<String> existingTags;
        private int inputCount;
        private String lastValidInput;
        
        public SystemState(Set<String> existingTags, int inputCount, String lastValidInput) {
            this.existingTags = new HashSet<>(existingTags);
            this.inputCount = inputCount;
            this.lastValidInput = lastValidInput;
        }
        
        public Set<String> getExistingTags() {
            return new HashSet<>(existingTags);
        }
        
        public int getInputCount() {
            return inputCount;
        }
        
        public String getLastValidInput() {
            return lastValidInput;
        }
        
        @Override
        public String toString() {
            return String.format("SystemState{标签数=%d, 输入次数=%d, 最后有效输入='%s'}",
                    existingTags.size(), inputCount, lastValidInput);
        }
    }
    
    // 标签管理系统
    private static class TagManager {
        private Set<String> existingTags;
        private SystemState previousState;
        
        public TagManager() {
            this.existingTags = new HashSet<>();
            this.previousState = null;
            // 初始化一些示例标签
            initializeDefaultTags();
        }
        
        private void initializeDefaultTags() {
            existingTags.add("java");
            existingTags.add("programming");
            existingTags.add("design");
            existingTags.add("system");
        }
        
        /**
         * 尝试添加新标签
         * @param tag 要添加的标签
         * @return 如果标签已存在返回false，成功添加返回true
         */
        public boolean tryAddTag(String tag) {
            if (tag == null || tag.trim().isEmpty()) {
                return false; // 处理空标签的边缘情况
            }
            
            String normalizedTag = tag.trim().toLowerCase();
            
            // 保存当前状态以便恢复
            saveCurrentState();
            
            if (existingTags.contains(normalizedTag)) {
                return false; // 标签已存在
            }
            
            existingTags.add(normalizedTag);
            return true;
        }
        
        /**
         * 保存当前系统状态
         */
        private void saveCurrentState() {
            String lastValidInput = existingTags.isEmpty() ? null : 
                existingTags.stream().skip(existingTags.size() - 1).findFirst().orElse(null);
            previousState = new SystemState(existingTags, existingTags.size(), lastValidInput);
        }
        
        /**
         * 恢复到之前的状态
         */
        public void restorePreviousState() {
            if (previousState != null) {
                this.existingTags = previousState.getExistingTags();
            }
        }
        
        /**
         * 检查标签是否已存在
         */
        public boolean tagExists(String tag) {
            if (tag == null || tag.trim().isEmpty()) {
                return false;
            }
            return existingTags.contains(tag.trim().toLowerCase());
        }
        
        /**
         * 获取当前所有标签
         */
        public Set<String> getAllTags() {
            return new HashSet<>(existingTags);
        }
        
        /**
         * 获取系统状态信息
         */
        public String getStatus() {
            return String.format("当前系统状态：共有 %d 个标签，标签列表：%s",
                    existingTags.size(), String.join(", ", existingTags));
        }
    }
    
    /**
     * 用户交互处理器
     */
    private static class UserInteractionHandler {
        private Scanner scanner;
        private TagManager tagManager;
        
        public UserInteractionHandler(TagManager tagManager) {
            this.scanner = new Scanner(System.in);
            this.tagManager = tagManager;
        }
        
        /**
         * 显示错误消息并要求用户确认阅读
         * @param tag 已存在的标签
         * @return 用户是否确认阅读
         */
        public boolean showErrorMessageAndConfirm(String tag) {
            System.out.println("\n========== 错误消息 ==========");
            System.out.printf("错误：标签 '%s' 已经存在于系统中！\n", tag);
            System.out.println("==============================");
            System.out.print("请确认您已阅读此错误消息（输入 'y' 确认，其他输入取消）：");
            
            String confirmation = scanner.nextLine().trim().toLowerCase();
            return confirmation.equals("y") || confirmation.equals("yes");
        }
        
        /**
         * 获取用户输入的标签
         */
        public String getUserInput() {
            System.out.print("\n请输入一个搜索标签（输入 'exit' 退出程序）：");
            return scanner.nextLine().trim();
        }
        
        /**
         * 显示欢迎信息和当前状态
         */
        public void showWelcome() {
            System.out.println("=== 标签管理系统 ===");
            System.out.println("模拟 ExistingErrorTag 用例");
            System.out.println("系统已初始化，包含以下标签：");
            System.out.println(tagManager.getAllTags());
            System.out.println("=====================");
        }
        
        /**
         * 显示退出信息
         */
        public void showExitMessage() {
            System.out.println("\n感谢使用标签管理系统！");
            System.out.println("最终系统状态：");
            System.out.println(tagManager.getStatus());
        }
        
        /**
         * 关闭资源
         */
        public void close() {
            scanner.close();
        }
    }
    
    /**
     * 主程序逻辑控制器
     */
    private static class ApplicationController {
        private TagManager tagManager;
        private UserInteractionHandler uiHandler;
        
        public ApplicationController() {
            this.tagManager = new TagManager();
            this.uiHandler = new UserInteractionHandler(tagManager);
        }
        
        /**
         * 运行主程序
         */
        public void run() {
            uiHandler.showWelcome();
            
            boolean running = true;
            while (running) {
                // 步骤1：获取用户输入
                String userInput = uiHandler.getUserInput();
                
                // 检查退出条件
                if (userInput.equalsIgnoreCase("exit")) {
                    running = false;
                    continue;
                }
                
                // 检查输入是否为空
                if (userInput.isEmpty()) {
                    System.out.println("错误：输入不能为空！");
                    continue;
                }
                
                // 检查标签是否已存在
                if (tagManager.tagExists(userInput)) {
                    // 步骤1：显示错误消息并要求确认阅读
                    boolean confirmed = uiHandler.showErrorMessageAndConfirm(userInput);
                    
                    if (confirmed) {
                        // 步骤2：用户确认已阅读通知
                        System.out.println("已确认阅读错误消息。");
                        
                        // 步骤3：恢复之前的状态
                        tagManager.restorePreviousState();
                        System.out.println("系统状态已恢复。");
                        System.out.println(tagManager.getStatus());
                    } else {
                        System.out.println("确认失败，系统将保持当前状态。");
                    }
                } else {
                    // 标签不存在，尝试添加
                    boolean added = tagManager.tryAddTag(userInput);
                    if (added) {
                        System.out.printf("成功添加标签 '%s' 到系统。\n", userInput);
                        System.out.println(tagManager.getStatus());
                    } else {
                        System.out.println("添加标签失败（可能是无效输入）。");
                    }
                }
            }
            
            uiHandler.showExitMessage();
            uiHandler.close();
        }
    }
    
    /**
     * 主方法 - 程序入口点
     */
    public static void main(String[] args) {
        try {
            ApplicationController app = new ApplicationController();
            app.run();
        } catch (Exception e) {
            System.err.println("程序运行时发生错误：" + e.getMessage());
            e.printStackTrace();
        }
    }
}