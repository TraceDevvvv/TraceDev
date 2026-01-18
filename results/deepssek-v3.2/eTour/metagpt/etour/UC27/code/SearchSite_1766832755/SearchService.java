import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * SearchService类实现搜索逻辑，包含：
 * 1. 搜索站点功能
 * 2. 超时处理（符合质量要求中"在设定的时间限制内返回结果"）
 * 3. 连接中断处理（对应需求中的"中断：与服务器ETOUR的连接中断"）
 * 4. 模拟服务器交互
 */
public class SearchService {
    // 模拟的站点数据（在实际应用中，这些数据可能来自数据库或API）
    private List<Site> siteDatabase;
    
    /**
     * 构造函数 - 初始化模拟数据
     */
    public SearchService() {
        initializeMockData();
    }
    
    /**
     * 初始化模拟站点数据
     */
    private void initializeMockData() {
        siteDatabase = new ArrayList<>();
        
        // 添加一些模拟站点数据
        siteDatabase.add(new Site(1, "Google", "https://www.google.com", 
                                 "全球最大的搜索引擎", "搜索引擎"));
        siteDatabase.add(new Site(2, "GitHub", "https://github.com", 
                                 "代码托管平台", "开发工具"));
        siteDatabase.add(new Site(3, "StackOverflow", "https://stackoverflow.com", 
                                 "开发者问答社区", "开发工具"));
        siteDatabase.add(new Site(4, "Wikipedia", "https://www.wikipedia.org", 
                                 "自由的百科全书", "知识百科"));
        siteDatabase.add(new Site(5, "YouTube", "https://www.youtube.com", 
                                 "视频分享平台", "社交媒体"));
        siteDatabase.add(new Site(6, "Google Drive", "https://drive.google.com", 
                                 "云存储服务", "云服务"));
        siteDatabase.add(new Site(7, "Google Maps", "https://maps.google.com", 
                                 "在线地图服务", "地图导航"));
        siteDatabase.add(new Site(8, "ETOUR Server", "https://etour.example.com", 
                                 "ETOUR旅游服务器", "旅游服务"));
        siteDatabase.add(new Site(9, "ETOUR API", "https://api.etour.com", 
                                 "ETOUR API接口", "旅游服务"));
        siteDatabase.add(new Site(10, "Java Documentation", "https://docs.oracle.com/javase", 
                                 "Java官方文档", "文档资源"));
    }
    
    /**
     * 执行站点搜索（主搜索方法）
     * @param form 包含搜索条件的表单
     * @return 搜索到的站点列表
     * @throws SearchException 如果搜索过程中发生错误（超时、连接中断等）
     */
    public List<Site> searchSites(SearchForm form) throws SearchException {
        if (form == null || !form.validateForm()) {
            throw new SearchException("无效的搜索表单");
        }
        
        System.out.println("开始搜索: " + form.getSearchDescription());
        
        // 创建执行搜索的任务
        Callable<List<Site>> searchTask = () -> {
            return performSearch(form);
        };
        
        // 创建线程池执行搜索任务，支持超时控制
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Site>> future = executor.submit(searchTask);
        
        try {
            // 根据表单中的超时设置等待搜索结果
            return future.get(form.getTimeoutSeconds(), TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            // 超时处理 - 取消任务
            future.cancel(true);
            throw new SearchException("搜索超时（" + form.getTimeoutSeconds() + "秒内未完成）", 
                                     SearchException.ErrorType.TIMEOUT);
        } catch (InterruptedException e) {
            // 线程中断处理
            Thread.currentThread().interrupt(); // 恢复中断状态
            throw new SearchException("搜索被中断", SearchException.ErrorType.INTERRUPTED);
        } catch (ExecutionException e) {
            // 任务执行异常处理
            Throwable cause = e.getCause();
            if (cause instanceof ConnectionException) {
                throw new SearchException("连接服务器ETOUR时发生中断: " + cause.getMessage(), 
                                         SearchException.ErrorType.CONNECTION_ERROR);
            } else {
                throw new SearchException("搜索过程中发生错误: " + cause.getMessage(), 
                                         SearchException.ErrorType.GENERAL_ERROR);
            }
        } finally {
            // 关闭线程池
            executor.shutdownNow();
        }
    }
    
    /**
     * 执行实际的搜索逻辑（模拟网络延迟和可能的连接问题）
     * @param form 搜索表单
     * @return 搜索结果列表
     * @throws ConnectionException 模拟连接中断
     */
    private List<Site> performSearch(SearchForm form) throws ConnectionException {
        String keyword = form.getSearchKeyword().toLowerCase();
        String category = form.getSiteCategory();
        boolean exactMatch = form.isRequireExactMatch();
        
        List<Site> results = new ArrayList<>();
        
        // 模拟搜索延迟（随机0.5-2秒）
        simulateNetworkDelay();
        
        // 检查是否模拟连接中断（ETOUR相关搜索可能触发）
        if (shouldSimulateConnectionError(keyword)) {
            throw new ConnectionException("与ETOUR服务器的连接中断");
        }
        
        // 遍历站点数据库进行搜索
        for (Site site : siteDatabase) {
            boolean matches = false;
            
            if (exactMatch) {
                // 精确匹配：检查字段是否完全等于关键字（忽略大小写）
                matches = (site.getName().equalsIgnoreCase(keyword) ||
                          site.getUrl().equalsIgnoreCase(keyword) ||
                          (site.getDescription() != null && site.getDescription().equalsIgnoreCase(keyword)) ||
                          (site.getCategory() != null && site.getCategory().equalsIgnoreCase(keyword)));
            } else {
                // 模糊匹配：检查字段是否包含关键字
                matches = site.matchesKeyword(keyword);
            }
            
            // 如果提供了分类，还需要检查分类匹配
            if (matches && category != null && !category.trim().isEmpty()) {
                String siteCategory = site.getCategory();
                if (siteCategory == null || !siteCategory.toLowerCase().contains(category.toLowerCase())) {
                    matches = false;
                }
            }
            
            if (matches) {
                results.add(site);
            }
        }
        
        return results;
    }
    
    /**
     * 模拟网络延迟（用于更真实的搜索体验）
     */
    private void simulateNetworkDelay() {
        try {
            // 随机延迟500-2000毫秒
            long delay = 500 + (long)(Math.random() * 1500);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("网络延迟模拟被中断", e);
        }
    }
    
    /**
     * 决定是否模拟连接错误
     * @param keyword 搜索关键字
     * @return 如果搜索ETOUR相关站点，有20%概率模拟连接中断
     */
    private boolean shouldSimulateConnectionError(String keyword) {
        // 如果搜索关键字包含"etour"，有概率模拟连接中断
        if (keyword.contains("etour")) {
            return Math.random() < 0.2; // 20%概率
        }
        return false;
    }
    
    /**
     * 获取所有站点（用于调试或测试）
     * @return 所有可用站点的列表
     */
    public List<Site> getAllSites() {
        return new ArrayList<>(siteDatabase);
    }
    
    /**
     * 添加新站点到数据库（用于扩展）
     * @param site 要添加的站点
     */
    public void addSite(Site site) {
        if (site != null) {
            siteDatabase.add(site);
        }
    }
    
    /**
     * 根据ID查找站点
     * @param id 站点ID
     * @return 找到的站点，未找到返回null
     */
    public Site findSiteById(int id) {
        for (Site site : siteDatabase) {
            if (site.getId() == id) {
                return site;
            }
        }
        return null;
    }
    
    /**
     * 获取站点总数
     * @return 站点总数
     */
    public int getSiteCount() {
        return siteDatabase.size();
    }
    
    /**
     * 搜索异常类，封装搜索过程中的各种错误
     */
    public static class SearchException extends Exception {
        public enum ErrorType {
            TIMEOUT,            // 超时错误
            CONNECTION_ERROR,   // 连接错误（包括ETOUR连接中断）
            INTERRUPTED,        // 中断错误
            VALIDATION_ERROR,   // 验证错误
            GENERAL_ERROR       // 一般错误
        }
        
        private final ErrorType errorType;
        
        public SearchException(String message) {
            super(message);
            this.errorType = ErrorType.GENERAL_ERROR;
        }
        
        public SearchException(String message, ErrorType errorType) {
            super(message);
            this.errorType = errorType;
        }
        
        public SearchException(String message, ErrorType errorType, Throwable cause) {
            super(message, cause);
            this.errorType = errorType;
        }
        
        public ErrorType getErrorType() {
            return errorType;
        }
        
        /**
         * 检查是否为严重错误需要用户干预
         * @return 如果是连接错误或超时返回true
         */
        public boolean isCriticalError() {
            return errorType == ErrorType.CONNECTION_ERROR || 
                   errorType == ErrorType.TIMEOUT;
        }
        
        /**
         * 获取用户友好的错误消息
         * @return 友好的错误描述
         */
        public String getUserFriendlyMessage() {
            switch (errorType) {
                case TIMEOUT:
                    return "搜索超时，请检查网络连接或增加超时时间后重试";
                case CONNECTION_ERROR:
                    return "连接服务器时发生错误，请稍后重试";
                case INTERRUPTED:
                    return "搜索被意外中断，请重新搜索";
                case VALIDATION_ERROR:
                    return "搜索条件无效，请检查后重试";
                default:
                    return "搜索过程中发生错误: " + getMessage();
            }
        }
    }
    
    /**
     * 连接异常类，模拟与ETOUR服务器的连接中断
     */
    public static class ConnectionException extends Exception {
        public ConnectionException(String message) {
            super(message);
        }
        
        public ConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    /**
     * 测试搜索功能的辅助方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        try {
            SearchService service = new SearchService();
            
            // 创建一个测试搜索表单
            SearchForm form = new SearchForm("google", "搜索引擎", 5, false);
            
            // 执行搜索
            System.out.println("开始测试搜索...");
            List<Site> results = service.searchSites(form);
            
            // 显示结果
            System.out.println("找到 " + results.size() + " 个结果:");
            for (Site site : results) {
                System.out.println("  - " + site.getName() + " (" + site.getUrl() + ")");
            }
            
        } catch (SearchException e) {
            System.err.println("搜索失败: " + e.getUserFriendlyMessage());
            e.printStackTrace();
        }
    }
}