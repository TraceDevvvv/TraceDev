import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Justification - 请假理由数据模型类
 * 表示请假理由的实体类，包含请假理由的所有相关字段。
 * 这个类遵循JavaBean规范，提供getter和setter方法。
 */
public class Justification {
    
    // 日期时间格式化器，用于日期的格式化和解析
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    // 请假理由的属性
    private String justificationId;          // 请假理由ID（唯一标识符）
    private String absenceRecordId;          // 缺勤记录ID（关联红色标记的缺勤记录）
    private LocalDate justificationDate;     // 请假日期
    private String reason;                   // 请假理由描述
    private String adminId;                  // 创建请假理由的管理员ID
    private LocalDate createdAt;             // 创建时间
    private LocalDate updatedAt;             // 更新时间
    private String status;                   // 状态（如：已保存、待审批、已批准等）
    
    /**
     * 默认构造函数
     * 初始化默认值
     */
    public Justification() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.status = "已保存";
        // 生成一个简单的ID（在实际应用中会使用更复杂的ID生成机制）
        this.justificationId = "JUST_" + System.currentTimeMillis();
    }
    
    /**
     * 带参数的构造函数
     * @param absenceRecordId 缺勤记录ID
     * @param justificationDate 请假日期
     * @param reason 请假理由
     * @param adminId 管理员ID
     */
    public Justification(String absenceRecordId, LocalDate justificationDate, 
                         String reason, String adminId) {
        this();
        this.absenceRecordId = absenceRecordId;
        this.justificationDate = justificationDate;
        this.reason = reason;
        this.adminId = adminId;
    }
    
    /**
     * 带所有参数的构造函数
     * @param justificationId 请假理由ID
     * @param absenceRecordId 缺勤记录ID
     * @param justificationDate 请假日期
     * @param reason 请假理由
     * @param adminId 管理员ID
     * @param createdAt 创建时间
     * @param updatedAt 更新时间
     * @param status 状态
     */
    public Justification(String justificationId, String absenceRecordId, 
                         LocalDate justificationDate, String reason, 
                         String adminId, LocalDate createdAt, 
                         LocalDate updatedAt, String status) {
        this.justificationId = justificationId;
        this.absenceRecordId = absenceRecordId;
        this.justificationDate = justificationDate;
        this.reason = reason;
        this.adminId = adminId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
    
    // Getter 和 Setter 方法
    
    /**
     * 获取请假理由ID
     * @return 请假理由ID
     */
    public String getJustificationId() {
        return justificationId;
    }
    
    /**
     * 设置请假理由ID
     * @param justificationId 请假理由ID
     */
    public void setJustificationId(String justificationId) {
        this.justificationId = justificationId;
        this.updatedAt = LocalDate.now();
    }
    
    /**
     * 获取缺勤记录ID
     * @return 缺勤记录ID
     */
    public String getAbsenceRecordId() {
        return absenceRecordId;
    }
    
    /**
     * 设置缺勤记录ID
     * @param absenceRecordId 缺勤记录ID
     */
    public void setAbsenceRecordId(String absenceRecordId) {
        this.absenceRecordId = absenceRecordId;
        this.updatedAt = LocalDate.now();
    }
    
    /**
     * 获取请假日期
     * @return 请假日期
     */
    public LocalDate getJustificationDate() {
        return justificationDate;
    }
    
    /**
     * 设置请假日期
     * @param justificationDate 请假日期
     */
    public void setJustificationDate(LocalDate justificationDate) {
        this.justificationDate = justificationDate;
        this.updatedAt = LocalDate.now();
    }
    
    /**
     * 获取请假理由描述
     * @return 请假理由描述
     */
    public String getReason() {
        return reason;
    }
    
    /**
     * 设置请假理由描述
     * @param reason 请假理由描述
     */
    public void setReason(String reason) {
        if (reason != null && reason.length() > 500) {
            // 如果理由过长，截断到500字符
            this.reason = reason.substring(0, 500);
        } else {
            this.reason = reason;
        }
        this.updatedAt = LocalDate.now();
    }
    
    /**
     * 获取管理员ID
     * @return 管理员ID
     */
    public String getAdminId() {
        return adminId;
    }
    
    /**
     * 设置管理员ID
     * @param adminId 管理员ID
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
        this.updatedAt = LocalDate.now();
    }
    
    /**
     * 获取创建时间
     * @return 创建时间
     */
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    
    /**
     * 设置创建时间
     * @param createdAt 创建时间
     */
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        this.updatedAt = LocalDate.now();
    }
    
    /**
     * 获取更新时间
     * @return 更新时间
     */
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
    
    /**
     * 设置更新时间
     * @param updatedAt 更新时间
     */
    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    /**
     * 获取请假理由状态
     * @return 状态
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * 设置请假理由状态
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDate.now();
    }
    
    /**
     * 将字符串格式的日期转换为LocalDate对象
     * @param dateString 日期字符串（格式：yyyy-MM-dd）
     * @return 转换后的LocalDate对象
     * @throws IllegalArgumentException 如果日期格式无效
     */
    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            throw new IllegalArgumentException("日期字符串不能为空");
        }
        return LocalDate.parse(dateString.trim(), DATE_FORMATTER);
    }
    
    /**
     * 将LocalDate对象格式化为字符串
     * @param date 要格式化的日期
     * @return 格式化后的日期字符串（格式：yyyy-MM-dd）
     */
    public static String formatDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.format(DATE_FORMATTER);
    }
    
    /**
     * 检查请假理由对象是否有效
     * @return true如果对象有效
     */
    public boolean isValid() {
        return absenceRecordId != null && !absenceRecordId.trim().isEmpty() &&
               justificationDate != null &&
               reason != null && !reason.trim().isEmpty() &&
               adminId != null && !adminId.trim().isEmpty();
    }
    
    /**
     * 用字符串表示的请假理由信息
     * @return 格式化的字符串
     */
    @Override
    public String toString() {
        return "请假理由信息：" +
               "\n  ID: " + justificationId +
               "\n  缺勤记录ID: " + absenceRecordId +
               "\n  请假日期: " + formatDate(justificationDate) +
               "\n  请假理由: " + (reason != null && reason.length() > 50 ? 
                                  reason.substring(0, 50) + "..." : reason) +
               "\n  管理员ID: " + adminId +
               "\n  创建时间: " + formatDate(createdAt) +
               "\n  更新时间: " + formatDate(updatedAt) +
               "\n  状态: " + status;
    }
    
    /**
     * 获取详细的信息展示
     * @return 包含所有字段的详细字符串
     */
    public String toDetailedString() {
        return "请假理由详细信息：" +
               "\n  ID: " + justificationId +
               "\n  缺勤记录ID: " + absenceRecordId +
               "\n  请假日期: " + formatDate(justificationDate) +
               "\n  请假理由: " + reason +
               "\n  管理员ID: " + adminId +
               "\n  创建时间: " + formatDate(createdAt) +
               "\n  更新时间: " + formatDate(updatedAt) +
               "\n  状态: " + status;
    }
}