import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * ValidationUtils - 验证工具类
 * 提供请假理由的各种验证功能，包括日期格式验证、输入内容验证、数据完整性检查等。
 * 这个类包含静态方法，用于验证各种输入和数据格式。
 */
public class ValidationUtils {
    
    // 日期格式化器，用于验证和格式化日期
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * 验证请假理由对象是否有效
     * 这是MockUI.java中引用的主要验证方法
     * @param justification 请假理由对象
     * @return true如果对象有效
     */
    public static boolean validateJustification(Justification justification) {
        if (justification == null) {
            System.out.println("错误：请假理由对象不能为空。");
            return false;
        }
        
        // 检查必填字段
        if (justification.getAbsenceRecordId() == null || 
            justification.getAbsenceRecordId().trim().isEmpty()) {
            System.out.println("错误：缺勤记录ID不能为空。");
            return false;
        }
        
        if (justification.getJustificationDate() == null) {
            System.out.println("错误：请假日期不能为空。");
            return false;
        }
        
        if (justification.getReason() == null || 
            justification.getReason().trim().isEmpty()) {
            System.out.println("错误：请假理由不能为空。");
            return false;
        }
        
        if (justification.getAdminId() == null || 
            justification.getAdminId().trim().isEmpty()) {
            System.out.println("错误：管理员ID不能为空。");
            return false;
        }
        
        // 验证日期合理性
        LocalDate today = LocalDate.now();
        LocalDate justificationDate = justification.getJustificationDate();
        
        if (justificationDate.isAfter(today)) {
            System.out.println("警告：请假日期不能是未来日期。");
            // 这里可以返回false表示严格验证，但根据业务需求，可能允许未来日期
        }
        
        // 验证请假理由长度
        String reason = justification.getReason();
        if (reason.trim().length() < 5) {
            System.out.println("错误：请假理由太短，至少需要5个字符。");
            return false;
        }
        
        if (reason.trim().length() > 500) {
            System.out.println("错误：请假理由太长，最多500个字符。");
            return false;
        }
        
        // 检查是否有明显无效内容
        if (containsInvalidContent(reason)) {
            System.out.println("警告：请假理由可能包含无效内容。");
        }
        
        return true;
    }
    
    /**
     * 验证日期字符串格式是否正确
     * @param dateString 日期字符串
     * @return true如果格式正确
     */
    public static boolean isValidDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return false;
        }
        
        try {
            LocalDate.parse(dateString, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    /**
     * 验证日期是否在合理范围内
     * 允许从今天起前1年到后30天
     * @param date 要验证的日期
     * @return true如果日期合理
     */
    public static boolean isValidDateRange(LocalDate date) {
        if (date == null) {
            return false;
        }
        
        LocalDate today = LocalDate.now();
        LocalDate minValidDate = today.minusYears(1); // 允许最多一年前的日期
        LocalDate maxValidDate = today.plusDays(30); // 允许最多30天后的日期
        
        return !date.isBefore(minValidDate) && !date.isAfter(maxValidDate);
    }
    
    /**
     * 验证请假理由内容是否有效
     * @param reason 请假理由
     * @return true如果理由有效
     */
    public static boolean isValidReason(String reason) {
        if (reason == null || reason.trim().isEmpty()) {
            return false;
        }
        
        String trimmedReason = reason.trim();
        
        // 检查长度
        if (trimmedReason.length() < 5 || trimmedReason.length() > 500) {
            return false;
        }
        
        // 检查是否有实际内容，不只是占位符
        if (trimmedReason.matches("^[\\p{P}\\s]+$")) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 验证管理员ID是否有效
     * @param adminId 管理员ID
     * @return true如果ID有效
     */
    public static boolean isValidAdminId(String adminId) {
        if (adminId == null || adminId.trim().isEmpty()) {
            return false;
        }
        
        // 检查ID格式
        return adminId.matches("^[a-zA-Z0-9_-]+$");
    }
    
    /**
     * 验证缺勤记录ID是否有效
     * @param absenceRecordId 缺勤记录ID
     * @return true如果ID有效
     */
    public static boolean isValidAbsenceRecordId(String absenceRecordId) {
        if (absenceRecordId == null || absenceRecordId.trim().isEmpty()) {
            return false;
        }
        
        // 检查ID格式
        return absenceRecordId.matches("^[a-zA-Z0-9_-]+$");
    }
    
    /**
     * 验证请假理由长度是否在有效范围内
     * @param reason 请假理由
     * @return true如果长度有效
     */
    public static boolean isValidReasonLength(String reason) {
        if (reason == null) {
            return false;
        }
        
        int length = reason.trim().length();
        return length >= 5 && length <= 500;
    }
    
    /**
     * 检查请假理由是否包含明显无效内容
     * @param reason 请假理由
     * @return true如果包含无效内容
     */
    public static boolean containsInvalidContent(String reason) {
        if (reason == null || reason.trim().isEmpty()) {
            return false;
        }
        
        String lowerReason = reason.toLowerCase().trim();
        
        // 检查是否只包含标点符号或空格
        if (lowerReason.matches("^[\\p{P}\\s]+$")) {
            return true;
        }
        
        // 检查是否包含明显的占位符
        String[] placeholders = {
            "test", "测试", "例子", "example", 
            "placeholder", "xxx", "aaa", "asdf",
            "qwerty", "12345", "样本"
        };
        
        for (String placeholder : placeholders) {
            if (lowerReason.contains(placeholder)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 验证日期是否在未来
     * @param date 要验证的日期
     * @return true如果是未来日期
     */
    public static boolean isFutureDate(LocalDate date) {
        if (date == null) {
            return false;
        }
        
        return date.isAfter(LocalDate.now());
    }
    
    /**
     * 验证日期是否是过去
     * @param date 要验证的日期
     * @return true如果是过去日期
     */
    public static boolean isPastDate(LocalDate date) {
        if (date == null) {
            return false;
        }
        
        return date.isBefore(LocalDate.now());
    }
    
    /**
     * 验证日期是否为今天
     * @param date 要验证的日期
     * @return true如果是今天
     */
    public static boolean isToday(LocalDate date) {
        if (date == null) {
            return false;
        }
        
        return date.isEqual(LocalDate.now());
    }
    
    /**
     * 获取验证错误的详细信息
     * @param justification 请假理由对象
     * @return 验证结果消息
     */
    public static String getValidationDetails(Justification justification) {
        if (justification == null) {
            return "错误：请假理由对象不能为空。";
        }
        
        StringBuilder details = new StringBuilder();
        details.append("请假理由验证结果：\n");
        
        boolean isValid = true;
        
        // 验证缺勤记录ID
        if (!isValidAbsenceRecordId(justification.getAbsenceRecordId())) {
            details.append("❌ 缺勤记录ID无效或为空\n");
            isValid = false;
        } else {
            details.append("✓ 缺勤记录ID有效\n");
        }
        
        // 验证请假日期
        if (justification.getJustificationDate() == null) {
            details.append("❌ 请假日期为空\n");
            isValid = false;
        } else if (!isValidDateRange(justification.getJustificationDate())) {
            details.append("❌ 请假日期超出有效范围\n");
            isValid = false;
        } else {
            details.append("✓ 请假日期有效\n");
        }
        
        // 验证请假理由
        if (!isValidReason(justification.getReason())) {
            details.append("❌ 请假理由无效\n");
            isValid = false;
        } else {
            details.append("✓ 请假理由有效\n");
        }
        
        // 验证管理员ID
        if (!isValidAdminId(justification.getAdminId())) {
            details.append("❌ 管理员ID无效\n");
            isValid = false;
        } else {
            details.append("✓ 管理员ID有效\n");
        }
        
        if (isValid) {
            details.append("\n✅ 所有验证通过");
        } else {
            details.append("\n❌ 存在验证错误");
        }
        
        return details.toString();
    }
    
    /**
     * 验证结果类
     * 用于存储详细的验证结果信息
     */
    public static class ValidationResult {
        private boolean isValid;
        private StringBuilder errorMessages;
        
        /**
         * 构造函数
         */
        public ValidationResult() {
            this.isValid = true;
            this.errorMessages = new StringBuilder();
        }
        
        /**
         * 添加错误信息
         * @param error 错误信息
         */
        public void addError(String error) {
            if (this.isValid) {
                this.isValid = false;
            }
            this.errorMessages.append("• ").append(error).append("\n");
        }
        
        /**
         * 检查验证是否通过
         * @return true如果验证通过
         */
        public boolean isValid() {
            return this.isValid;
        }
        
        /**
         * 获取所有错误信息
         * @return 错误信息字符串
         */
        public String getErrorMessages() {
            return this.errorMessages.toString();
        }
        
        /**
         * 获取验证结果摘要
         * @return 验证结果摘要
         */
        public String getSummary() {
            if (this.isValid) {
                return "✅ 所有验证通过。";
            } else {
                return "❌ 验证失败：\n" + getErrorMessages();
            }
        }
    }
    
    /**
     * 综合验证请假理由的所有字段
     * @param justification 请假理由对象
     * @return 详细的验证结果对象
     */
    public static ValidationResult performCompleteValidation(Justification justification) {
        ValidationResult result = new ValidationResult();
        
        if (justification == null) {
            result.addError("请假理由对象不能为空");
            return result;
        }
        
        // 验证缺勤记录ID
        if (!isValidAbsenceRecordId(justification.getAbsenceRecordId())) {
            result.addError("缺勤记录ID无效或为空");
        }
        
        // 验证请假日期
        if (justification.getJustificationDate() == null) {
            result.addError("请假日期为空");
        } else if (!isValidDateRange(justification.getJustificationDate())) {
            result.addError("请假日期超出有效范围（允许从今天起前1年到后30天）");
        }
        
        // 验证请假理由
        if (!isValidReason(justification.getReason())) {
            result.addError("请假理由无效（长度需在5-500字符之间且包含有效内容）");
        }
        
        // 验证管理员ID
        if (!isValidAdminId(justification.getAdminId())) {
            result.addError("管理员ID无效或为空");
        }
        
        return result;
    }
}