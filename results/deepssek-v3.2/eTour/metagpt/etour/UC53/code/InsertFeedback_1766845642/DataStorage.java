import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataStorage类 - 数据存储类
 * 模拟数据库操作，包括反馈、站点和游客的存储和管理
 * 使用内存存储来模拟数据库，在实际应用中会被真正的数据库替换
 */
public class DataStorage {
    // 使用Map来存储数据，键为ID，值为对象
    private Map<String, Feedback> feedbackStorage;
    private Map<String, Site> siteStorage;
    private Map<String, Tourist> touristStorage;
    
    // 用于跟踪反馈ID的计数字
    private int feedbackCounter;
    
    /**
     * 构造函数，初始化所有存储
     */
    public DataStorage() {
        feedbackStorage = new HashMap<>();
        siteStorage = new HashMap<>();
        touristStorage = new HashMap<>();
        feedbackCounter = 1; // 从1开始计数
        
        // 初始化一些示例数据
        initializeSampleData();
    }
    
    /**
     * 初始化示例数据，用于演示
     */
    private void initializeSampleData() {
        // 创建示例站点
        Site site1 = new Site("S001", "埃菲尔铁塔", "法国巴黎的著名地标");
        Site site2 = new Site("S002", "大本钟", "英国伦敦的标志性建筑");
        Site site3 = new Site("S003", "故宫", "中国北京的历史宫殿");
        
        siteStorage.put(site1.getSiteId(), site1);
        siteStorage.put(site2.getSiteId(), site2);
        siteStorage.put(site3.getSiteId(), site3);
        
        // 创建示例游客
        Tourist tourist1 = new Tourist("T001", "张三");
        Tourist tourist2 = new Tourist("T002", "李四");
        Tourist tourist3 = new Tourist("T003", "王五");
        
        touristStorage.put(tourist1.getTouristId(), tourist1);
        touristStorage.put(tourist2.getTouristId(), tourist2);
        touristStorage.put(tourist3.getTouristId(), tourist3);
    }
    
    /**
     * 检查游客是否已经为特定站点发布过反馈
     * @param touristId 游客ID
     * @param siteId 站点ID
     * @return 如果已发布过反馈，返回true；否则返回false
     */
    public boolean hasTouristGivenFeedback(String touristId, String siteId) {
        for (Feedback feedback : feedbackStorage.values()) {
            if (feedback.getTouristId().equals(touristId) && 
                feedback.getSiteId().equals(siteId)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 保存反馈到存储
     * @param feedback 要保存的反馈对象
     * @return 保存后的反馈ID
     */
    public String saveFeedback(Feedback feedback) {
        // 生成唯一的反馈ID
        String feedbackId = "F" + String.format("%03d", feedbackCounter++);
        feedback.setFeedbackId(feedbackId);
        
        // 保存到反馈存储
        feedbackStorage.put(feedbackId, feedback);
        
        // 更新游客的已访问站点列表
        Tourist tourist = touristStorage.get(feedback.getTouristId());
        if (tourist != null) {
            tourist.addVisitedSite(feedback.getSiteId());
        }
        
        // 更新站点的已访问游客列表
        Site site = siteStorage.get(feedback.getSiteId());
        if (site != null) {
            site.addVisitedTourist(feedback.getTouristId());
        }
        
        return feedbackId;
    }
    
    /**
     * 获取指定ID的站点
     * @param siteId 站点ID
     * @return 站点对象，如果不存在则返回null
     */
    public Site getSite(String siteId) {
        return siteStorage.get(siteId);
    }
    
    /**
     * 获取指定ID的游客
     * @param touristId 游客ID
     * @return 游客对象，如果不存在则返回null
     */
    public Tourist getTourist(String touristId) {
        return touristStorage.get(touristId);
    }
    
    /**
     * 获取指定ID的反馈
     * @param feedbackId 反馈ID
     * @return 反馈对象，如果不存在则返回null
     */
    public Feedback getFeedback(String feedbackId) {
        return feedbackStorage.get(feedbackId);
    }
    
    /**
     * 获取所有反馈
     * @return 反馈列表
     */
    public List<Feedback> getAllFeedback() {
        return new ArrayList<>(feedbackStorage.values());
    }
    
    /**
     * 获取指定游客的所有反馈
     * @param touristId 游客ID
     * @return 该游客的反馈列表
     */
    public List<Feedback> getFeedbackByTourist(String touristId) {
        List<Feedback> result = new ArrayList<>();
        for (Feedback feedback : feedbackStorage.values()) {
            if (feedback.getTouristId().equals(touristId)) {
                result.add(feedback);
            }
        }
        return result;
    }
    
    /**
     * 获取指定站点的所有反馈
     * @param siteId 站点ID
     * @return 该站点的反馈列表
     */
    public List<Feedback> getFeedbackBySite(String siteId) {
        List<Feedback> result = new ArrayList<>();
        for (Feedback feedback : feedbackStorage.values()) {
            if (feedback.getSiteId().equals(siteId)) {
                result.add(feedback);
            }
        }
        return result;
    }
    
    /**
     * 检查站点是否存在
     * @param siteId 站点ID
     * @return 如果站点存在返回true，否则返回false
     */
    public boolean siteExists(String siteId) {
        return siteStorage.containsKey(siteId);
    }
    
    /**
     * 检查游客是否存在
     * @param touristId 游客ID
     * @return 如果游客存在返回true，否则返回false
     */
    public boolean touristExists(String touristId) {
        return touristStorage.containsKey(touristId);
    }
    
    /**
     * 获取所有站点
     * @return 站点列表
     */
    public List<Site> getAllSites() {
        return new ArrayList<>(siteStorage.values());
    }
    
    /**
     * 获取所有游客
     * @return 游客列表
     */
    public List<Tourist> getAllTourists() {
        return new ArrayList<>(touristStorage.values());
    }
    
    /**
     * 清空所有存储（主要用于测试）
     */
    public void clearAllStorage() {
        feedbackStorage.clear();
        siteStorage.clear();
        touristStorage.clear();
        feedbackCounter = 1;
        initializeSampleData();
    }
    
    /**
     * 获取存储统计信息
     * @return 包含各种统计信息的字符串
     */
    public String getStorageStats() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== 数据存储统计 ===\n");
        stats.append("站点数量: ").append(siteStorage.size()).append("\n");
        stats.append("游客数量: ").append(touristStorage.size()).append("\n");
        stats.append("反馈数量: ").append(feedbackStorage.size()).append("\n");
        
        // 计算每个站点的反馈数量
        stats.append("\n各站点反馈统计:\n");
        for (Site site : siteStorage.values()) {
            List<Feedback> siteFeedback = getFeedbackBySite(site.getSiteId());
            stats.append(String.format("  %s (%s): %d 条反馈\n", 
                site.getName(), site.getSiteId(), siteFeedback.size()));
        }
        
        return stats.toString();
    }
}