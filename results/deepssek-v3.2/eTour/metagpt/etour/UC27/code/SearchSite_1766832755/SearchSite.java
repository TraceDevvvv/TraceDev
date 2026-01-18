import java.util.List;
import java.util.Scanner;

/**
 * SearchSite主类 - 模拟完整的站点搜索流程
 * 实现用例：SearchSite - 在计算机上搜索特定站点
 * 流程：
 * 1. 激活站点的搜索功能
 * 2. 显示研究表单
 * 3. 填写研究表单并提交
 * 4. 处理请求
 * 退出条件：系统返回找到的站点列表
 * 中断处理：与服务器ETOUR的连接中断
 * 质量要求：系统应在设定的时间限制内返回结果
 */
public class SearchSite {
    
    /**
     * 主方法 - 程序入口点
     * 模拟完整的搜索流程：
     * 1. 激活搜索功能
     * 2. 显示并填写搜索表单
     * 3. 提交表单并处理请求
     * 4. 显示搜索结果
     * 
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        System.out.println("=== 站点搜索系统 ===");
        System.out.println("系统启动中...\n");
        
        // 1. 激活搜索功能
        activateSearchFunction();
        
        // 2. 显示研究表单并获取用户输入
        SearchForm searchForm = displayAndFillSearchForm();
        
        // 如果表单未成功填写，退出程序
        if (searchForm == null || !searchForm.isFilled()) {
            System.out.println("表单填写失败，程序退出。");
            return;
        }
        
        // 3. 提交表单并处理请求
        List<Site> searchResults = processSearchRequest(searchForm);
        
        // 4. 显示搜索结果
        displaySearchResults(searchResults, searchForm);
        
        System.out.println("\n=== 搜索流程完成 ===");
    }
    
    /**
     * 步骤1：激活站点的搜索功能
     * 初始化搜索服务，准备搜索环境
     */
    private static void activateSearchFunction() {
        System.out.println("步骤1: 激活搜索功能...");
        System.out.println("正在初始化搜索服务...");
        
        // 在实际应用中，这里可能包含：
        // - 初始化网络连接
        // - 加载配置文件
        // - 检查系统资源
        // - 验证用户权限等
        
        System.out.println("搜索功能已激活，准备就绪！\n");
    }
    
    /**
     * 步骤2：显示研究表单并填写
     * 创建搜索表单对象，显示表单界面，收集用户输入
     * 
     * @return 填写完成的SearchForm对象，如果失败返回null
     */
    private static SearchForm displayAndFillSearchForm() {
        System.out.println("步骤2: 显示搜索表单...");
        
        // 创建Scanner对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);
        
        // 创建搜索表单对象
        SearchForm searchForm = new SearchForm();
        
        try {
            // 显示表单并收集用户输入
            boolean formFilledSuccessfully = searchForm.displayAndFill(scanner);
            
            if (!formFilledSuccessfully) {
                System.out.println("表单填写失败，请重新启动程序。");
                return null;
            }
            
            // 显示表单确认信息
            searchForm.submitForm();
            System.out.println();
            
            return searchForm;
            
        } catch (Exception e) {
            System.err.println("表单处理过程中发生错误: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 步骤3：处理搜索请求
     * 使用SearchService执行搜索，处理可能的中断和超时
     * 
     * @param searchForm 包含搜索条件的表单
     * @return 搜索结果列表，如果搜索失败返回null
     */
    private static List<Site> processSearchRequest(SearchForm searchForm) {
        System.out.println("步骤3: 处理搜索请求...");
        System.out.println("正在搜索站点，请稍候...");
        
        // 创建搜索服务实例
        SearchService searchService = new SearchService();
        
        try {
            // 执行搜索（包含超时控制和异常处理）
            List<Site> results = searchService.searchSites(searchForm);
            
            System.out.println("搜索请求处理完成！");
            return results;
            
        } catch (SearchService.SearchException e) {
            // 处理搜索过程中可能发生的各种异常
            handleSearchException(e);
            return null;
        } catch (Exception e) {
            // 处理其他未预期的异常
            System.err.println("搜索过程中发生未预期的错误: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 处理搜索异常，提供用户友好的错误信息
     * 
     * @param e 搜索异常对象
     */
    private static void handleSearchException(SearchService.SearchException e) {
        System.err.println("\n=== 搜索错误 ===");
        System.err.println("错误类型: " + e.getErrorType());
        System.err.println("错误信息: " + e.getUserFriendlyMessage());
        
        // 根据错误类型提供不同的处理建议
        switch (e.getErrorType()) {
            case TIMEOUT:
                System.err.println("建议: 请检查网络连接，或增加超时时间后重试");
                break;
            case CONNECTION_ERROR:
                System.err.println("建议: 请检查与ETOUR服务器的连接，稍后重试");
                System.err.println("注意: 这是用例中提到的'与服务器ETOUR的连接中断'情况");
                break;
            case INTERRUPTED:
                System.err.println("建议: 搜索被意外中断，请重新启动搜索");
                break;
            case VALIDATION_ERROR:
                System.err.println("建议: 请检查搜索条件是否正确填写");
                break;
            default:
                System.err.println("建议: 请稍后重试，或联系系统管理员");
        }
        
        // 如果是连接错误，显示更多详细信息
        if (e.getErrorType() == SearchService.SearchException.ErrorType.CONNECTION_ERROR) {
            System.err.println("\n连接中断详细信息:");
            System.err.println("- 错误类型: ETOUR服务器连接中断");
            System.err.println("- 可能原因: 网络问题、服务器维护、防火墙限制");
            System.err.println("- 解决方案: 检查网络连接，等待服务器恢复，或联系技术支持");
        }
    }
    
    /**
     * 步骤4：显示搜索结果
     * 以用户友好的格式显示搜索到的站点列表
     * 
     * @param results 搜索结果列表
     * @param searchForm 使用的搜索表单
     */
    private static void displaySearchResults(List<Site> results, SearchForm searchForm) {
        System.out.println("\n步骤4: 显示搜索结果...");
        System.out.println("========================================");
        System.out.println("搜索条件: " + searchForm.getSearchDescription());
        System.out.println("========================================\n");
        
        if (results == null) {
            System.out.println("搜索失败，无法获取结果。");
            return;
        }
        
        if (results.isEmpty()) {
            System.out.println("未找到匹配的站点。");
            System.out.println("建议: 尝试使用不同的关键字或放宽搜索条件。");
        } else {
            System.out.println("找到 " + results.size() + " 个匹配的站点:");
            System.out.println();
            
            // 显示每个站点的详细信息
            for (int i = 0; i < results.size(); i++) {
                Site site = results.get(i);
                System.out.println((i + 1) + ". " + site.getName());
                System.out.println("   网址: " + site.getUrl());
                System.out.println("   描述: " + site.getDescription());
                System.out.println("   分类: " + site.getCategory());
                
                // 显示URL有效性状态
                if (site.isValidUrl()) {
                    System.out.println("   URL状态: 有效格式");
                } else {
                    System.out.println("   URL状态: 无效格式（缺少http://或https://）");
                }
                
                System.out.println();
            }
            
            // 显示统计信息
            System.out.println("========================================");
            System.out.println("搜索结果统计:");
            System.out.println("- 总匹配数: " + results.size());
            System.out.println("- 搜索模式: " + (searchForm.isRequireExactMatch() ? "精确匹配" : "模糊匹配"));
            System.out.println("- 超时设置: " + searchForm.getTimeoutSeconds() + "秒");
            
            // 检查是否有ETOUR相关结果
            int etourCount = 0;
            for (Site site : results) {
                if (site.getName().toLowerCase().contains("etour") || 
                    site.getUrl().toLowerCase().contains("etour")) {
                    etourCount++;
                }
            }
            
            if (etourCount > 0) {
                System.out.println("- ETOUR相关站点: " + etourCount + "个");
                System.out.println("  注意: ETOUR服务器连接可能不稳定，如遇中断请稍后重试");
            }
        }
        
        System.out.println("========================================");
    }
    
    /**
     * 辅助方法：演示搜索流程的示例
     * 可以用于测试或演示目的
     */
    public static void demonstrateSearchProcess() {
        System.out.println("\n=== 搜索流程演示 ===");
        
        // 创建示例搜索表单
        SearchForm demoForm = new SearchForm("Google", "搜索引擎", 5, false);
        
        System.out.println("演示搜索条件:");
        System.out.println("- 关键字: Google");
        System.out.println("- 分类: 搜索引擎");
        System.out.println("- 超时: 5秒");
        System.out.println("- 精确匹配: 否");
        
        // 创建搜索服务
        SearchService service = new SearchService();
        
        try {
            System.out.println("\n开始演示搜索...");
            List<Site> results = service.searchSites(demoForm);
            
            System.out.println("演示搜索结果:");
            if (results != null && !results.isEmpty()) {
                for (Site site : results) {
                    System.out.println("  - " + site.getName() + ": " + site.getUrl());
                }
            } else {
                System.out.println("  未找到结果");
            }
            
        } catch (SearchService.SearchException e) {
            System.err.println("演示搜索失败: " + e.getUserFriendlyMessage());
        }
        
        System.out.println("=== 演示结束 ===\n");
    }
    
    /**
     * 显示程序使用说明
     */
    public static void showUsage() {
        System.out.println("\n=== 站点搜索系统使用说明 ===");
        System.out.println("功能: 在计算机上搜索特定站点");
        System.out.println("流程:");
        System.out.println("  1. 系统激活搜索功能");
        System.out.println("  2. 显示搜索表单供用户填写");
        System.out.println("  3. 处理搜索请求（支持超时控制）");
        System.out.println("  4. 显示搜索结果列表");
        System.out.println("\n质量要求:");
        System.out.println("  - 系统在设定的时间限制内返回结果");
        System.out.println("  - 处理与ETOUR服务器的连接中断");
        System.out.println("  - 提供友好的错误处理和用户反馈");
        System.out.println("\n注意事项:");
        System.out.println("  - 搜索关键字不能为空");
        System.out.println("  - 超时时间必须大于0秒");
        System.out.println("  - 搜索ETOUR相关站点时可能遇到连接中断");
        System.out.println("========================================\n");
    }
}