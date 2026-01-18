import java.util.Scanner;

/**
 * SearchForm类表示搜索表单，用于收集用户的搜索条件。
 * 该类处理表单的显示、输入验证和提交。
 */
public class SearchForm {
    private String searchKeyword;        // 搜索关键字
    private String siteCategory;         // 站点分类（可选）
    private int timeoutSeconds = 10;     // 超时时间（秒），默认10秒
    private boolean requireExactMatch;   // 是否需要精确匹配
    
    /**
     * 默认构造函数
     */
    public SearchForm() {
    }
    
    /**
     * 带参数的构造函数
     * @param searchKeyword 搜索关键字
     * @param siteCategory 站点分类
     * @param timeoutSeconds 超时时间（秒）
     * @param requireExactMatch 是否需要精确匹配
     */
    public SearchForm(String searchKeyword, String siteCategory, 
                     int timeoutSeconds, boolean requireExactMatch) {
        this.searchKeyword = searchKeyword;
        this.siteCategory = siteCategory;
        this.timeoutSeconds = timeoutSeconds;
        this.requireExactMatch = requireExactMatch;
    }
    
    // Getter和Setter方法
    
    public String getSearchKeyword() {
        return searchKeyword;
    }
    
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
    
    public String getSiteCategory() {
        return siteCategory;
    }
    
    public void setSiteCategory(String siteCategory) {
        this.siteCategory = siteCategory;
    }
    
    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }
    
    public void setTimeoutSeconds(int timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
    }
    
    public boolean isRequireExactMatch() {
        return requireExactMatch;
    }
    
    public void setRequireExactMatch(boolean requireExactMatch) {
        this.requireExactMatch = requireExactMatch;
    }
    
    /**
     * 显示搜索表单并收集用户输入
     * @param scanner 用于读取用户输入的Scanner对象
     * @return 如果表单提交成功返回true，否则返回false
     */
    public boolean displayAndFill(Scanner scanner) {
        System.out.println("=== 搜索站点表单 ===");
        System.out.println("请填写以下搜索条件：");
        
        // 获取搜索关键字
        System.out.print("1. 搜索关键字 (必填): ");
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("错误：搜索关键字不能为空！");
            return false;
        }
        this.searchKeyword = keyword;
        
        // 获取站点分类（可选）
        System.out.print("2. 站点分类 (可选，按Enter跳过): ");
        String category = scanner.nextLine().trim();
        if (!category.isEmpty()) {
            this.siteCategory = category;
        } else {
            this.siteCategory = null;
        }
        
        // 获取超时时间
        System.out.print("3. 超时时间(秒) (默认10秒，按Enter使用默认值): ");
        String timeoutInput = scanner.nextLine().trim();
        if (!timeoutInput.isEmpty()) {
            try {
                int timeout = Integer.parseInt(timeoutInput);
                if (timeout <= 0) {
                    System.out.println("警告：超时时间必须大于0，将使用默认值10秒");
                } else {
                    this.timeoutSeconds = timeout;
                }
            } catch (NumberFormatException e) {
                System.out.println("警告：输入的不是有效数字，将使用默认值10秒");
            }
        }
        
        // 获取匹配模式
        System.out.print("4. 是否需要精确匹配? (y/n, 默认n): ");
        String exactMatchInput = scanner.nextLine().trim().toLowerCase();
        this.requireExactMatch = "y".equals(exactMatchInput) || "yes".equals(exactMatchInput);
        
        System.out.println("表单填写完成！");
        return validateForm();
    }
    
    /**
     * 验证表单数据的有效性
     * @return 如果表单数据有效返回true，否则返回false
     */
    public boolean validateForm() {
        // 验证搜索关键字
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            System.out.println("验证失败：搜索关键字不能为空！");
            return false;
        }
        
        // 验证超时时间
        if (timeoutSeconds <= 0) {
            System.out.println("验证失败：超时时间必须大于0！");
            return false;
        }
        
        // 验证分类（如果提供了）
        if (siteCategory != null && siteCategory.length() > 100) {
            System.out.println("警告：分类名称过长（超过100字符），已截断");
            siteCategory = siteCategory.substring(0, 100);
        }
        
        // 验证搜索关键字长度
        if (searchKeyword.length() > 200) {
            System.out.println("警告：搜索关键字过长（超过200字符），已截断");
            searchKeyword = searchKeyword.substring(0, 200);
        }
        
        return true;
    }
    
    /**
     * 提交表单（在实际应用中，这里可能包含网络请求等操作）
     * 在此模拟中，只是打印表单内容和验证结果
     */
    public void submitForm() {
        System.out.println("\n=== 表单提交确认 ===");
        System.out.println("搜索关键字: " + searchKeyword);
        System.out.println("站点分类: " + (siteCategory != null ? siteCategory : "未指定"));
        System.out.println("超时时间: " + timeoutSeconds + "秒");
        System.out.println("精确匹配: " + (requireExactMatch ? "是" : "否"));
        System.out.println("表单验证: " + (validateForm() ? "通过" : "失败"));
        
        if (validateForm()) {
            System.out.println("表单已提交，等待处理...");
        } else {
            System.out.println("表单验证失败，请修正错误后重新提交！");
        }
    }
    
    /**
     * 将搜索条件格式化为字符串，用于日志记录或调试
     * @return 搜索条件的字符串表示
     */
    @Override
    public String toString() {
        return String.format(
            "SearchForm{keyword='%s', category='%s', timeout=%ds, exactMatch=%b}",
            searchKeyword, siteCategory, timeoutSeconds, requireExactMatch
        );
    }
    
    /**
     * 判断表单是否已填写
     * @return 如果搜索关键字不为空且有效则返回true
     */
    public boolean isFilled() {
        return searchKeyword != null && !searchKeyword.trim().isEmpty();
    }
    
    /**
     * 重置表单为初始状态
     */
    public void reset() {
        this.searchKeyword = null;
        this.siteCategory = null;
        this.timeoutSeconds = 10;
        this.requireExactMatch = false;
        System.out.println("表单已重置！");
    }
    
    /**
     * 生成搜索条件的简要描述
     * @return 搜索条件的描述
     */
    public String getSearchDescription() {
        StringBuilder description = new StringBuilder();
        description.append("搜索关键字: \"").append(searchKeyword).append("\"");
        
        if (siteCategory != null && !siteCategory.isEmpty()) {
            description.append(", 分类: ").append(siteCategory);
        }
        
        if (requireExactMatch) {
            description.append(", 精确匹配模式");
        }
        
        description.append(", 超时: ").append(timeoutSeconds).append("秒");
        return description.toString();
    }
}