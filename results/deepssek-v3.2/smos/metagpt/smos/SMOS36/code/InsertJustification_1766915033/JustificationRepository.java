import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JustificationRepository - 数据存储类
 * 模拟数据库操作，提供请假理由的保存、检索、更新和删除功能。
 * 这个类使用内存存储来模拟数据库，在实际应用中会替换为真实的数据库连接。
 */
public class JustificationRepository {
    
    // 使用ConcurrentHashMap来模拟数据库表，支持并发访问
    private final Map<String, Justification> justificationStore;
    
    // 使用AtomicInteger生成唯一ID
    private final AtomicInteger idCounter;
    
    // 模拟数据库连接状态
    private boolean isConnected;
    
    /**
     * 默认构造函数
     * 初始化内存存储和ID计数器
     */
    public JustificationRepository() {
        this.justificationStore = new ConcurrentHashMap<>();
        this.idCounter = new AtomicInteger(1);
        this.isConnected = true; // 默认连接正常
        
        // 初始化一些示例数据用于测试
        initializeSampleData();
    }
    
    /**
     * 初始化示例数据
     * 在实际应用中，这里会从数据库加载现有数据
     */
    private void initializeSampleData() {
        try {
            // 添加一些示例请假理由
            LocalDate today = LocalDate.now();
            
            Justification sample1 = new Justification(
                "ABS_001",
                today.minusDays(3),
                "因病请假，需要休息一天",
                "admin001"
            );
            sample1.setJustificationId("JUST_001");
            justificationStore.put(sample1.getJustificationId(), sample1);
            
            Justification sample2 = new Justification(
                "ABS_002",
                today.minusDays(1),
                "家庭紧急情况，需要处理家事",
                "admin002"
            );
            sample2.setJustificationId("JUST_002");
            justificationStore.put(sample2.getJustificationId(), sample2);
            
            System.out.println("数据存储初始化完成，已加载 " + justificationStore.size() + " 条示例数据。");
            
        } catch (Exception e) {
            System.out.println("初始化示例数据时发生错误: " + e.getMessage());
        }
    }
    
    /**
     * 保存请假理由
     * 将请假理由对象保存到数据存储中
     * @param justification 要保存的请假理由对象
     * @return true表示保存成功，false表示保存失败
     * @throws RepositoryException 如果保存过程中发生错误
     */
    public boolean save(Justification justification) throws RepositoryException {
        // 检查数据库连接状态
        checkConnection();
        
        if (justification == null) {
            throw new RepositoryException("请假理由对象不能为空");
        }
        
        // 验证请假理由对象是否有效
        if (!justification.isValid()) {
            throw new RepositoryException("请假理由对象无效，缺少必要字段");
        }
        
        try {
            // 如果ID为空，生成新的ID
            if (justification.getJustificationId() == null || 
                justification.getJustificationId().trim().isEmpty()) {
                String newId = "JUST_" + idCounter.getAndIncrement();
                justification.setJustificationId(newId);
            }
            
            // 设置更新时间
            justification.setUpdatedAt(LocalDate.now());
            
            // 保存到内存存储
            justificationStore.put(justification.getJustificationId(), justification);
            
            System.out.println("请假理由已保存: ID=" + justification.getJustificationId());
            
            // 模拟数据库操作延迟
            simulateDatabaseDelay();
            
            return true;
            
        } catch (Exception e) {
            throw new RepositoryException("保存请假理由失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 根据ID查找请假理由
     * @param justificationId 请假理由ID
     * @return 找到的请假理由对象，如果未找到则返回null
     * @throws RepositoryException 如果查找过程中发生错误
     */
    public Justification findById(String justificationId) throws RepositoryException {
        // 检查数据库连接状态
        checkConnection();
        
        if (justificationId == null || justificationId.trim().isEmpty()) {
            throw new RepositoryException("请假理由ID不能为空");
        }
        
        try {
            // 从内存存储中查找
            Justification justification = justificationStore.get(justificationId);
            
            // 模拟数据库操作延迟
            simulateDatabaseDelay();
            
            return justification;
            
        } catch (Exception e) {
            throw new RepositoryException("查找请假理由失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 根据缺勤记录ID查找请假理由
     * @param absenceRecordId 缺勤记录ID
     * @return 请假理由列表，如果没有找到则返回空列表
     * @throws RepositoryException 如果查找过程中发生错误
     */
    public List<Justification> findByAbsenceRecordId(String absenceRecordId) throws RepositoryException {
        // 检查数据库连接状态
        checkConnection();
        
        if (absenceRecordId == null || absenceRecordId.trim().isEmpty()) {
            throw new RepositoryException("缺勤记录ID不能为空");
        }
        
        try {
            List<Justification> results = new ArrayList<>();
            
            // 遍历所有请假理由，查找匹配的缺勤记录ID
            for (Justification justification : justificationStore.values()) {
                if (absenceRecordId.equals(justification.getAbsenceRecordId())) {
                    results.add(justification);
                }
            }
            
            // 模拟数据库操作延迟
            simulateDatabaseDelay();
            
            return results;
            
        } catch (Exception e) {
            throw new RepositoryException("根据缺勤记录ID查找请假理由失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 查找所有请假理由
     * @return 所有请假理由的列表
     * @throws RepositoryException 如果查找过程中发生错误
     */
    public List<Justification> findAll() throws RepositoryException {
        // 检查数据库连接状态
        checkConnection();
        
        try {
            List<Justification> results = new ArrayList<>(justificationStore.values());
            
            // 模拟数据库操作延迟
            simulateDatabaseDelay();
            
            return results;
            
        } catch (Exception e) {
            throw new RepositoryException("查找所有请假理由失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 更新请假理由
     * @param justification 要更新的请假理由对象
     * @return true表示更新成功，false表示更新失败
     * @throws RepositoryException 如果更新过程中发生错误
     */
    public boolean update(Justification justification) throws RepositoryException {
        // 检查数据库连接状态
        checkConnection();
        
        if (justification == null) {
            throw new RepositoryException("请假理由对象不能为空");
        }
        
        if (justification.getJustificationId() == null || 
            justification.getJustificationId().trim().isEmpty()) {
            throw new RepositoryException("请假理由ID不能为空");
        }
        
        // 验证请假理由对象是否有效
        if (!justification.isValid()) {
            throw new RepositoryException("请假理由对象无效，缺少必要字段");
        }
        
        try {
            // 检查请假理由是否存在
            if (!justificationStore.containsKey(justification.getJustificationId())) {
                throw new RepositoryException("请假理由不存在，ID: " + justification.getJustificationId());
            }
            
            // 设置更新时间
            justification.setUpdatedAt(LocalDate.now());
            
            // 更新内存存储
            justificationStore.put(justification.getJustificationId(), justification);
            
            System.out.println("请假理由已更新: ID=" + justification.getJustificationId());
            
            // 模拟数据库操作延迟
            simulateDatabaseDelay();
            
            return true;
            
        } catch (Exception e) {
            throw new RepositoryException("更新请假理由失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 删除请假理由
     * @param justificationId 请假理由ID
     * @return true表示删除成功，false表示删除失败
     * @throws RepositoryException 如果删除过程中发生错误
     */
    public boolean delete(String justificationId) throws RepositoryException {
        // 检查数据库连接状态
        checkConnection();
        
        if (justificationId == null || justificationId.trim().isEmpty()) {
            throw new RepositoryException("请假理由ID不能为空");
        }
        
        try {
            // 检查请假理由是否存在
            if (!justificationStore.containsKey(justificationId)) {
                throw new RepositoryException("请假理由不存在，ID: " + justificationId);
            }
            
            // 从内存存储中删除
            Justification removed = justificationStore.remove(justificationId);
            
            if (removed != null) {
                System.out.println("请假理由已删除: ID=" + justificationId);
                
                // 模拟数据库操作延迟
                simulateDatabaseDelay();
                
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e) {
            throw new RepositoryException("删除请假理由失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 统计请假理由数量
     * @return 请假理由总数
     * @throws RepositoryException 如果统计过程中发生错误
     */
    public int count() throws RepositoryException {
        // 检查数据库连接状态
        checkConnection();
        
        try {
            return justificationStore.size();
        } catch (Exception e) {
            throw new RepositoryException("统计请假理由数量失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 检查数据库连接状态
     * @throws RepositoryException 如果数据库连接中断
     */
    private void checkConnection() throws RepositoryException {
        if (!isConnected) {
            throw new RepositoryException("数据库连接中断，无法执行操作");
        }
        
        // 模拟偶尔的连接检查失败
        if (Math.random() < 0.05) { // 5%的概率模拟连接失败
            isConnected = false;
            throw new RepositoryException("SMOS服务器连接意外中断");
        }
    }
    
    /**
     * 模拟数据库操作延迟
     * 在实际应用中，数据库操作会有一定的延迟
     */
    private void simulateDatabaseDelay() {
        try {
            // 模拟50-200毫秒的数据库操作延迟
            Thread.sleep(50 + (long)(Math.random() * 150));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * 测试数据库连接
     * @return true表示连接正常，false表示连接失败
     */
    public boolean testConnection() {
        try {
            // 模拟连接测试
            Thread.sleep(100);
            
            // 随机模拟连接测试失败
            if (Math.random() < 0.1) { // 10%的概率模拟测试失败
                isConnected = false;
                return false;
            }
            
            isConnected = true;
            return true;
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            isConnected = false;
            return false;
        }
    }
    
    /**
     * 获取数据库连接状态
     * @return true表示连接正常，false表示连接中断
     */
    public boolean isConnected() {
        return isConnected;
    }
    
    /**
     * 设置数据库连接状态
     * @param connected 连接状态
     */
    public void setConnected(boolean connected) {
        this.isConnected = connected;
    }
    
    /**
     * 清除所有数据（仅用于测试）
     * 在实际应用中，这个方法不应该在生产环境中使用
     */
    public void clearAll() {
        justificationStore.clear();
        idCounter.set(1);
        System.out.println("所有数据已清除");
    }
    
    /**
     * 数据存储异常类
     * 用于包装数据存储操作中发生的异常
     */
    public static class RepositoryException extends Exception {
        public RepositoryException(String message) {
            super(message);
        }
        
        public RepositoryException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}