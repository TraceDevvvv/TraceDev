import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 管理旅游站点书签的类
 * 提供添加、删除、查找书签站点等功能
 * 处理与ETOUR服务器的连接和异常情况
 */
public class BookmarkManager {
    private List<TouristSite> bookmarkedSites;
    private boolean isConnectedToETour;
    
    /**
     * 构造方法，初始化书签列表和服务器连接状态
     */
    public BookmarkManager() {
        this.bookmarkedSites = new ArrayList<>();
        this.isConnectedToETour = true; // 默认连接正常
    }
    
    /**
     * 添加站点到书签列表
     * @param site 要添加的旅游站点
     * @throws ETourConnectionException 如果与ETOUR服务器的连接中断
     */
    public void addSiteToBookmarks(TouristSite site) throws ETourConnectionException {
        // 检查服务器连接
        checkConnection();
        
        // 检查站点是否已存在
        if (!bookmarkedSites.contains(site)) {
            bookmarkedSites.add(site);
            System.out.println("站点 '" + site.getName() + "' 已添加到书签列表");
        } else {
            System.out.println("站点 '" + site.getName() + "' 已在书签列表中");
        }
    }
    
    /**
     * 从书签列表中删除指定站点
     * @param siteId 要删除的站点ID
     * @return 如果成功删除返回true，否则返回false
     * @throws ETourConnectionException 如果与ETOUR服务器的连接中断
     */
    public boolean deleteSiteFromBookmarks(String siteId) throws ETourConnectionException {
        // 检查服务器连接
        checkConnection();
        
        // 查找要删除的站点
        Optional<TouristSite> siteToRemove = bookmarkedSites.stream()
                .filter(site -> site.getSiteId().equals(siteId))
                .findFirst();
        
        if (siteToRemove.isPresent()) {
            TouristSite removedSite = siteToRemove.get();
            bookmarkedSites.remove(removedSite);
            System.out.println("站点 '" + removedSite.getName() + "' 已从书签列表中删除");
            return true;
        } else {
            System.out.println("未找到ID为 '" + siteId + "' 的站点");
            return false;
        }
    }
    
    /**
     * 获取所有书签站点
     * @return 书签站点列表的副本
     * @throws ETourConnectionException 如果与ETOUR服务器的连接中断
     */
    public List<TouristSite> getAllBookmarkedSites() throws ETourConnectionException {
        // 检查服务器连接
        checkConnection();
        
        // 返回列表的副本以防止外部修改
        return new ArrayList<>(bookmarkedSites);
    }
    
    /**
     * 根据站点ID查找书签站点
     * @param siteId 要查找的站点ID
     * @return 如果找到则返回站点，否则返回null
     * @throws ETourConnectionException 如果与ETOUR服务器的连接中断
     */
    public TouristSite findSiteById(String siteId) throws ETourConnectionException {
        // 检查服务器连接
        checkConnection();
        
        return bookmarkedSites.stream()
                .filter(site -> site.getSiteId().equals(siteId))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * 获取书签数量
     * @return 书签数量
     * @throws ETourConnectionException 如果与ETOUR服务器的连接中断
     */
    public int getBookmarkCount() throws ETourConnectionException {
        // 检查服务器连接
        checkConnection();
        
        return bookmarkedSites.size();
    }
    
    /**
     * 检查是否包含指定站点
     * @param siteId 要检查的站点ID
     * @return 如果包含则返回true
     * @throws ETourConnectionException 如果与ETOUR服务器的连接中断
     */
    public boolean containsSite(String siteId) throws ETourConnectionException {
        // 检查服务器连接
        checkConnection();
        
        return bookmarkedSites.stream()
                .anyMatch(site -> site.getSiteId().equals(siteId));
    }
    
    /**
     * 检查与ETOUR服务器的连接状态
     * @throws ETourConnectionException 如果连接中断
     */
    private void checkConnection() throws ETourConnectionException {
        if (!isConnectedToETour) {
            throw new ETourConnectionException("与ETOUR服务器的连接已中断");
        }
    }
    
    /**
     * 设置服务器连接状态
     * @param connected 连接状态，true表示连接正常，false表示连接中断
     */
    public void setConnectionStatus(boolean connected) {
        this.isConnectedToETour = connected;
        if (!connected) {
            System.out.println("警告：与ETOUR服务器的连接已中断");
        } else {
            System.out.println("信息：与ETOUR服务器的连接已恢复");
        }
    }
    
    /**
     * 获取服务器连接状态
     * @return 连接状态
     */
    public boolean isConnectedToETour() {
        return isConnectedToETour;
    }
    
    /**
     * 清空所有书签
     * @throws ETourConnectionException 如果与ETOUR服务器的连接中断
     */
    public void clearAllBookmarks() throws ETourConnectionException {
        // 检查服务器连接
        checkConnection();
        
        int count = bookmarkedSites.size();
        bookmarkedSites.clear();
        System.out.println("已清空所有书签，共删除 " + count + " 个站点");
    }
    
    /**
     * 显示所有书签站点
     * @throws ETourConnectionException 如果与ETOUR服务器的连接中断
     */
    public void displayAllBookmarks() throws ETourConnectionException {
        // 检查服务器连接
        checkConnection();
        
        if (bookmarkedSites.isEmpty()) {
            System.out.println("书签列表为空");
        } else {
            System.out.println("当前书签列表（共 " + bookmarkedSites.size() + " 个站点）：");
            for (int i = 0; i < bookmarkedSites.size(); i++) {
                TouristSite site = bookmarkedSites.get(i);
                System.out.println((i + 1) + ". ID: " + site.getSiteId() + 
                                 ", 名称: " + site.getName() + 
                                 ", 位置: " + site.getLocation());
            }
        }
    }
}