import java.time.LocalDate;

/**
 * AbsenceRecord类 - 表示学生的缺勤记录
 * 包含缺勤的核心数据和编辑逻辑
 */
public class AbsenceRecord {
    private String recordId;
    private String studentId;
    private String studentName;
    private LocalDate date;
    private boolean isAbsent;
    private String reason;
    private LocalDate lastModified;
    
    /**
     * 构造函数 - 创建新的缺勤记录
     * @param recordId 记录ID
     * @param studentId 学生ID
     * @param studentName 学生姓名
     * @param date 缺勤日期
     * @param isAbsent 是否缺勤
     * @param reason 缺勤原因
     */
    public AbsenceRecord(String recordId, String studentId, String studentName, 
                        LocalDate date, boolean isAbsent, String reason) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.date = date;
        this.isAbsent = isAbsent;
        this.reason = reason;
        this.lastModified = LocalDate.now();
    }
    
    /**
     * 缺勤记录的编辑逻辑 - 添加或删除缺勤
     * 根据用例要求，更改缺勤记录（添加或删除）
     * @param newAbsenceStatus 新的缺勤状态（true=缺勤，false=出席）
     * @param reason 缺勤原因（如果状态为缺勤）
     * @return 编辑是否成功
     */
    public boolean editAbsence(boolean newAbsenceStatus, String reason) {
        // 检查状态是否确实需要更改
        if (this.isAbsent == newAbsenceStatus) {
            System.out.println("警告：缺勤状态未改变（已为" + (newAbsenceStatus ? "缺勤" : "出席") + "）。");
            return false;
        }
        
        // 更新缺勤状态
        boolean oldStatus = this.isAbsent;
        this.isAbsent = newAbsenceStatus;
        
        // 如果新状态为缺勤，更新原因；否则清空原因
        if (newAbsenceStatus) {
            this.reason = (reason != null && !reason.trim().isEmpty()) ? reason : "未指定原因";
        } else {
            this.reason = null;
        }
        
        // 更新最后修改时间
        this.lastModified = LocalDate.now();
        
        String action = newAbsenceStatus ? "添加" : "删除";
        System.out.println("缺勤记录已成功" + action + "：");
        System.out.println("  学生：" + studentName);
        System.out.println("  日期：" + date);
        System.out.println("  状态：" + (oldStatus ? "缺勤" : "出席") + " → " + (newAbsenceStatus ? "缺勤" : "出席"));
        
        return true;
    }
    
    /**
     * 检查记录是否为指定日期
     * @param targetDate 目标日期
     * @return 是否为该日期的记录
     */
    public boolean isForDate(LocalDate targetDate) {
        return this.date.equals(targetDate);
    }
    
    /**
     * 验证记录数据的有效性
     * @return 记录是否有效
     */
    public boolean validate() {
        if (recordId == null || recordId.trim().isEmpty()) {
            System.out.println("错误：记录ID不能为空。");
            return false;
        }
        
        if (studentId == null || studentId.trim().isEmpty()) {
            System.out.println("错误：学生ID不能为空。");
            return false;
        }
        
        if (date == null) {
            System.out.println("错误：缺勤日期不能为空。");
            return false;
        }
        
        if (date.isAfter(LocalDate.now())) {
            System.out.println("警告：缺勤日期不能是未来日期。");
            return false;
        }
        
        // 如果标记为缺勤，但原因为空，给出警告但不阻止操作
        if (isAbsent && (reason == null || reason.trim().isEmpty())) {
            System.out.println("警告：缺勤记录缺少原因。");
        }
        
        return true;
    }
    
    // Getter和Setter方法
    
    public String getRecordId() {
        return recordId;
    }
    
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public boolean isAbsent() {
        return isAbsent;
    }
    
    public void setAbsent(boolean absent) {
        this.isAbsent = absent;
        if (!absent) {
            this.reason = null; // 如果不是缺勤，清除原因
        }
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public LocalDate getLastModified() {
        return lastModified;
    }
    
    /**
     * 获取记录摘要信息
     * @return 格式化后的记录信息
     */
    public String getSummary() {
        return String.format("记录ID: %s | 学生: %s (%s) | 日期: %s | 状态: %s | 最后修改: %s",
                           recordId, studentName, studentId, date,
                           isAbsent ? "缺勤" : "出席", lastModified);
    }
    
    @Override
    public String toString() {
        return String.format("AbsenceRecord{recordId='%s', studentId='%s', studentName='%s', date=%s, isAbsent=%s, reason='%s', lastModified=%s}",
                           recordId, studentId, studentName, date, isAbsent, reason, lastModified);
    }
}